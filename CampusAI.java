/**
 * CampusAI.java
 * Evaluates five AI assistants across five academic categories.
 * Tracks performance scores in a 2D array, calculates averages,
 * implements linear searching, and performs a custom selection sort.
 */
public class CampusAI {

    public static void main(String[] args) {

        String[] models = {
            "ChatGPT", "Gemini", "Claude",
            "Copilot", "Perplexity"
        };

        String[] tasks = {
            "Writing", "Research", "Coding",
            "Math", "Creativity"
        };

        int[][] scores = {
            {92, 88, 95, 90, 86},
            {89, 94, 87, 85, 91},
            {95, 90, 89, 92, 84},
            {85, 82, 96, 88, 90},
            {91, 97, 80, 86, 93}
        };

        // PART 1: Display the performance matrix
        System.out.println("=== CAMPUSAI PERFORMANCE REPORT ===");
        displayScores(models, tasks, scores);
        System.out.println();

        // PART 2: Display model averages
        System.out.print("MODEL AVERAGES ");
        for (int i = 0; i < models.length; i++) {
            double avg = averageModel(scores, i);
            System.out.printf("%s: %.2f ", models[i], avg);
        }
        System.out.println();

        // Display category averages
        System.out.print("CATEGORY AVERAGES ");
        for (int j = 0; j < tasks.length; j++) {
            double avg = averageTask(scores, j);
            System.out.printf("%s: %.2f ", tasks[j], avg);
        }
        System.out.println("\n");

        // PART 3: Searching
        System.out.println("=== SEARCH RESULTS ===");
        
        // Demonstrate successful name search
        String target1 = "Claude";
        int index1 = searchModel(models, target1);
        System.out.println(target1 + " found at index " + index1);

        // Demonstrate unsuccessful name search
        String target2 = "UnknownAI";
        int index2 = searchModel(models, target2);
        if (index2 == -1) {
            System.out.println(target2 + " not found");
        } else {
            System.out.println(target2 + " found at index " + index2);
        }

        // Identify the best-performing AI model in each category
        for (int j = 0; j < tasks.length; j++) {
            int bestRowIndex = findBestModel(scores, j);
            System.out.println("BEST MODEL BY CATEGORY " + tasks[j] + ": " 
                               + models[bestRowIndex] + " (" + scores[bestRowIndex][j] + ")");
        }
        System.out.println();

        // PART 4: Sorting
        // Create separate arrays for the leaderboard to keep original arrays unchanged
        String[] leaderboardModels = new String[models.length];
        double[] leaderboardAverages = new double[models.length];

        for (int i = 0; i < models.length; i++) {
            leaderboardModels[i] = models[i];
            leaderboardAverages[i] = averageModel(scores, i);
        }

        // Call our selection sort routine
        sortLeaderboard(leaderboardModels, leaderboardAverages);

        // Display completed leaderboard from highest to lowest
        System.out.println("=== FINAL LEADERBOARD ===");
        for (int i = 0; i < leaderboardModels.length; i++) {
            System.out.printf("%d. %s %.2f\n", (i + 1), leaderboardModels[i], leaderboardAverages[i]);
        }
    }

    /**
     * Prints the performance table formatted neatly with headings.
     */
    public static void displayScores(String[] models, String[] tasks, int[][] scores) {
        // Print category headings with a structured width
        System.out.printf("%-15s", "AI Model");
        for (String task : tasks) {
            System.out.printf("%-12s", task);
        }
        System.out.println();

        // Outer loop traverses models (rows)
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("%-15s", models[i]);
            // Inner loop traverses scores inside the current row (columns)
            for (int j = 0; j < scores[i].length; j++) {
                System.out.printf("%-12d", scores[i][j]);
            }
            System.out.println();
        }
    }

    /**
     * Calculates the average score for a single row (AI Model).
     */
    public static double averageModel(int[][] scores, int row) {
        int sum = 0;
        for (int j = 0; j < scores[row].length; j++) {
            sum += scores[row][j];
        }
        // Use floating-point division by casting to double
        return (double) sum / scores[row].length;
    }

    /**
     * Calculates the average score for a single column (Academic Category).
     */
    public static double averageTask(int[][] scores, int column) {
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i][column];
        }
        // Use floating-point division by casting to double
        return (double) sum / scores.length;
    }

    /**
     * Case-insensitive linear search looking for a target model name.
     */
    public static int searchModel(String[] models, String target) {
        for (int i = 0; i < models.length; i++) {
            if (models[i].equalsIgnoreCase(target)) {
                return i; // Return matched index
            }
        }
        return -1; // Target model not found
    }

    /**
     * Searches a column for the maximum value and returns its row index.
     */
    public static int findBestModel(int[][] scores, int taskIndex) {
        int maxIndex = 0;
        int maxScore = scores[0][taskIndex];

        for (int i = 1; i < scores.length; i++) {
            if (scores[i][taskIndex] > maxScore) {
                maxScore = scores[i][taskIndex];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    /**
     * Standard Selection Sort implementation sorting down from highest to lowest.
     * Keeps corresponding parallel arrays tightly coupled during data swaps.
     */
    public static void sortLeaderboard(String[] models, double[] averages) {
        int n = averages.length;

        // Outer loop moves boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            
            // Inner loop scans remaining array segment to find largest remaining value
            for (int j = i + 1; j < n; j++) {
                if (averages[j] > averages[maxIdx]) {
                    maxIdx = j;
                }
            }

            // Swap maximum average score into correct sorted position
            double tempAvg = averages[maxIdx];
            averages[maxIdx] = averages[i];
            averages[i] = tempAvg;

            // Critically swap parallel model names matching indices to preserve ownership
            String tempModel = models[maxIdx];
            models[maxIdx] = models[i];
            models[i] = tempModel;
        }
    }
}
