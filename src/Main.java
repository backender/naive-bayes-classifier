import java.io.*;
import java.util.ArrayList;

/**
 * Created by Marc on 10.03.14.
 */
public class Main {

    private static String TrainFile = "smsSpamCollection-train.txt";
    private static String TestFile = "smsSpamCollection-test-sample.txt";
    private static String predictionFile = "prediction.txt";

    public static void main(String[] args) {
        try {
            BufferedReader trainInput = new BufferedReader(new FileReader(args[0]));
            BufferedReader testInput = new BufferedReader(new FileReader(args[1]));

            Classifier c = new Classifier();
            try {
                c.loadTrainMessages(trainInput);
                c.train();

                /* Uncomment for more info
                System.out.println("Number of Messages: " + c.numberOfMessages());
                System.out.println("Number of Messages in ham: " + c.numberOfMessagesPerCategory("ham"));
                System.out.println("Number of Words in ham: " + c.wordsPerCategory("ham"));
                System.out.println("Probability of ham: " + c.getProbabilityOfCategory("ham"));
                System.out.println("Optmized (log) Probability of ham: " + c.getOptimizedProbabilityOfCategory("ham"));
                System.out.println("Number of Messages in spam: " + c.numberOfMessagesPerCategory("spam"));
                System.out.println("Number of Words in spam: " + c.wordsPerCategory("spam"));
                System.out.println("Probability of spam: " + c.getProbabilityOfCategory("spam"));
                System.out.println("Optmized (log) Probability of spam: " + c.getOptimizedProbabilityOfCategory("spam"));
                */

                //Test
                FileWriter write = new FileWriter(predictionFile, false);
                PrintWriter predictionPrinter = new PrintWriter(write);

                ArrayList<ArrayList<String>> messages = c.loadTestMessages(testInput);
                for(ArrayList<String> message : messages){
                    double probMessageIsHam = c.calculateProbability(message, "ham");
                    double probMessageIsSpam = c.calculateProbability(message, "spam");

                    //then compare probs -> the one with the highest value
                    if (probMessageIsHam < probMessageIsSpam) {
                        System.out.println("spam");
                        predictionPrinter.printf("%s" + "%n", "spam");
                    } else {
                        System.out.println("hame");
                        predictionPrinter.printf("%s" + "%n", "ham");
                    }
                }

                predictionPrinter.close();



            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
