import java.io.*;
import java.util.*;

/**
 * Created by Marc on 09.03.14.
 */
public class Classifier {

    public HashMap<String, HashMap<String, Double>> categories = new HashMap<String, HashMap<String, Double>>();
    private ArrayList<MessageNode> trainMessages = new ArrayList<MessageNode>();

    class MessageNode {
        public String cat;
        public ArrayList<String> text;
        MessageNode(String cat, ArrayList<String> text){
            this.cat = cat;
            this.text = text;
        }
    }

    public ArrayList<MessageNode> getTrainMessages(){
        return this.trainMessages;
    }

    /**
     * Get probability for a message and category
     *
     * @param message
     * @param category
     * @return
     */
    public double calculateProbability(ArrayList<String> message, String category){
        double probMessageIsCat = getOptimizedProbabilityOfCategory(category);

        for(String word : message){
            Double occuranceInCategory = categories.get(category).get(word);

            if (occuranceInCategory != null) {
                probMessageIsCat += Math.log10(occuranceInCategory/wordsPerCategory(category));
            } else {
                probMessageIsCat += Math.log10(0.000001);
            }
        }
        return probMessageIsCat;
    }


    /**
     * Load given test messages
     * (Also remove stop words and unneccessary characters)
     * @param input
     * @return
     * @throws IOException
     */
    public ArrayList<ArrayList<String>> loadTestMessages(BufferedReader input) throws IOException {
        String line;
        Tokenizer t = new Tokenizer();
        ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
        while((line = input.readLine()) != null){
            if(line.length() > 0){
                String textLine = t.clearUglyChars(line);
                String[] text = textLine.split(" ");

                messages.add(t.clear(text));
            }
        }
        return messages;
    }


    /**
     * Load given training messages
     * (Also remove stop words and unnecessary characters)
     * @param input
     * @throws IOException
     */
    public void loadTrainMessages(BufferedReader input) throws IOException {
        String line;
        Tokenizer t = new Tokenizer();
        while((line = input.readLine()) != null){
            if(line.length() > 0){

                String[] words = line.split("\t");
                String category = words[0];


                String textLine = t.clearUglyChars(words[1]);
                String[] text = textLine.split(" ");

                MessageNode message = new MessageNode(category, t.clear(text));
                getTrainMessages().add(message);
            }
        }
    }

    /**
     * Attach each word from training messages to a category and count the words occurrence
     */
    public void train(){
        for(MessageNode n : getTrainMessages()){
            for(String word : n.text){

                HashMap<String, Double> tuple = categories.get(n.cat);
                if(tuple == null){
                    tuple = new HashMap<String, Double>();
                }

                Double count = tuple.get(word);
                if(count != null){
                    tuple.put(word, count+1.0);
                } else {
                    tuple.put(word, 1.0);
                }

                categories.put(n.cat, tuple);

            }
        }
    }

    public int numberOfMessages(){
        return getTrainMessages().size();
    }

    public int numberOfMessagesPerCategory(String cat){
        int i = 0;
        for(MessageNode m : getTrainMessages()){
            if(m.cat.equals(cat)){
                i++;
            }
        }
        return i;
    }

    public int wordsPerCategory(String cat){
        return categories.get(cat).size();
    }

    public double getProbabilityOfCategory(String cat){
        return (double) numberOfMessagesPerCategory(cat)/(double) numberOfMessages();
    }

    public double getOptimizedProbabilityOfCategory(String cat){
        return Math.log(getProbabilityOfCategory(cat));
    }

}
