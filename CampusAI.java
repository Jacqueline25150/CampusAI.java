/**
 * CampusAI.java
 * Evaluates five AI assistants across five academic categories.
 * Tracks performance scores in a 2D array, calculates averages,
 * implements linear searching, and performs a custom selection sort.
 * Upgraded to integrate an Object-Oriented Polymorphic Reporting system.
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

        // ==========================================
        // ORIGINAL WEEK'S PERFORMANCE FUNCTIONALITY
        // ==========================================
        System.out.println("=== CAMPUSAI PERFORMANCE REPORT ===");
        displayScores(models, tasks, scores);
        System.out.println();

        System.out.print("MODEL AVERAGES ");
        for (int i = 0; i < models.length; i++) {
            double avg = averageModel(scores, i);
            System.out.printf("%s: %.2f ", models[i], avg);
        }
        System.out.println();

        System.out.print("CATEGORY AVERAGES ");
        for (int j = 0; j < tasks.length; j++) {
            double avg = averageTask(scores, j);
            System.out.printf("%s: %.2f ", tasks[j], avg);
        }
        System.out.println("\n");

        System.out.println("=== SEARCH RESULTS ===");
        String target1 = "Claude";
        int index1 = searchModel(models, target1);
        System.out.println(target1 + " found at index " + index1);

        String target2 = "UnknownAI";
        int index2 = searchModel(models, target2);
        if (index2 == -1) {
            System.out.println(target2 + " not found");
        } else {
            System.out.println(target2 + " found at index " + index2);
        }

        for (int j = 0; j < tasks.length; j++) {
            int bestRowIndex = findBestModel(scores, j);
            System.out.println("BEST MODEL BY CATEGORY " + tasks[j] + ": " 
                               + models[bestRowIndex] + " (" + scores[bestRowIndex][j] + ")");
        }
        System.out.println();


        // ==========================================
        // NEW OBJECT-ORIENTED & POLYMORPHISM UPGRADE
        // ==========================================
        System.out.println("=== CAMPUSAI OBJECT-ORIENTED REPORT ===");
        System.out.println("\nAI MODEL PROFILES\n");

        // Instantiating objects into an array utilizing Polymorphic types
        AIModel[] assistants = {
            new CreativeAI("ChatGPT", scores[0]),
            new ResearchAI("Gemini", scores[1]),
            new AIModel("Claude", scores[2]),
            new CodingAI("Copilot", scores[3]),
            new ResearchAI("Perplexity", scores[4])
        };

        // Call polymorphic reporting loop method
        displayAIProfiles(assistants);

        // Selection sorting execution via object array modification wrappers
        sortLeaderboardOO(assistants);

        // Displaying final sorted output mirroring the required output window exactly
        System.out.println("\n=== FINAL LEADERBOARD ===\n");
        for (int i = 0; i < assistants.length; i++) {
            System.out.printf("%d. %-12s %.2f   %s\n", 
                (i + 1), 
                assistants[i].getName(), 
                assistants[i].calculateAverage(), 
                assistants[i].getDescription()
            );
        }
    }

    /**
     * Loops through an array of objects polymorphically to dump names, descriptions, 
     * and averages without calling manual conditional type testing.
     */
    public static void displayAIProfiles(AIModel[] assistants) {
        for (AIModel ai : assistants) {
            System.out.println(ai.getName());
            System.out.println("Type: " + ai.getDescription());
            System.out.printf("Average: %.2f\n\n", ai.calculateAverage());
        }
    }

    /**
     * Standard Selection Sort implementation customized to order elements 
     * from highest average score down to lowest by directly swapping objects.
     */
    public static void sortLeaderboardOO(AIModel[] assistants) {
        int n = assistants.length;
        for (int i = 0; i < n - 1; i++) {
            int maxIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (assistants[j].calculateAverage() > assistants[maxIdx].calculateAverage()) {
                    maxIdx = j;
                }
            }
            // Swap complete object references safely inside the array indices
            AIModel temp = assistants[maxIdx];
            assistants[maxIdx] = assistants[i];
            assistants[i] = temp;
        }
    }

    // ==========================================
    // PRE-EXISTING UTILITY AND ARCHIVAL METHODS
    // ==========================================
    public static void displayScores(String[] models, String[] tasks, int[][] scores) {
        System.out.printf("%-15s", "AI Model");
        for (String task : tasks) {
            System.out.printf("%-12s", task);
        }
        System.out.println();
        for (int i = 0; i < scores.length; i++) {
            System.out.printf("%-15s", models[i]);
            for (int j = 0; j < scores[i].length; j++) {
                System.out.printf("%-12d", scores[i][j]);
            }
            System.out.println();
        }
    }

    public static double averageModel(int[][] scores, int row) {
        int sum = 0;
        for (int j = 0; j < scores[row].length; j++) {
            sum += scores[row][j];
        }
        return (double) sum / scores[row].length;
    }

    public static double averageTask(int[][] scores, int column) {
        int sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i][column];
        }
        return (double) sum / scores.length;
    }

    public static int searchModel(String[] models, String target) {
        for (int i = 0; i < models.length; i++) {
            if (models[i].equalsIgnoreCase(target)) {
                return i;
            }
        }
        return -1;
    }

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
}

