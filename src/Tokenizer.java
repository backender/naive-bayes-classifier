import java.util.ArrayList;

/**
 * Created by Marc on 09.03.14.
 */
public class Tokenizer {

    private static Character[] uglyChars = new Character[] {
            '.', ',', '"', '!', '?', '{', '}', '(', ')', '[', ']', ':'
    };

    private static String[] tokenSet = new String[] {
            "a", "about", "above", "across", "after", "afterwards",
            "again", "against", "all", "almost", "alone", "along",
            "already", "also", "although", "always", "am", "among",
            "amongst", "amoungst", "amount", "an", "and", "another",
            "any", "anyhow", "anyone", "anything", "anyway", "anywhere",
            "are", "around", "as", "at", "back", "be",
            "became", "because", "become", "becomes", "becoming", "been",
            "before", "beforehand", "behind", "being", "below", "beside",
            "besides", "between", "beyond", "bill", "both", "bottom",
            "but", "by", "call", "can", "cannot", "cant", "dont",
            "co", "computer", "con", "could", "couldnt", "cry",
            "de", "describe", "detail", "do", "done", "down",
            "due", "during", "each", "eg", "eight", "either",
            "eleven", "else", "elsewhere", "empty", "enough", "etc", "even", "ever", "every",
            "everyone", "everything", "everywhere", "except", "few", "fifteen",
            "fify", "fill", "find", "fire", "first", "five",
            "for", "former", "formerly", "forty", "found", "four",
            "from", "front", "full", "further", "get", "give",
            "go", "had", "has", "hasnt", "have", "he",
            "hence", "her", "here", "hereafter", "hereby", "herein",
            "hereupon", "hers", "herself", "him", "himself", "his",
            "how", "however", "hundred", "i", "ie", "if",
            "in", "inc", "indeed", "interest", "into", "is",
            "it", "its", "itself", "keep", "last", "latter",
            "latterly", "least", "less", "ltd", "made", "many",
            "may", "me", "meanwhile", "might", "mill", "mine",
            "more", "moreover", "most", "mostly", "move", "much",
            "must", "my", "myself", "name", "namely", "neither",
            "never", "nevertheless", "next", "nine", "no", "nobody",
            "none", "noone", "nor", "not", "nothing", "now",
            "nowhere", "of", "off", "often", "on", "once",
            "one", "only", "onto", "or", "other", "others",
            "otherwise", "our", "ours", "ourselves", "out", "over",
            "own", "part", "per", "perhaps", "please", "put",
            "rather", "re", "same", "see", "seem", "seemed",
            "seeming", "seems", "serious", "several", "she", "should",
            "show", "side", "since", "sincere", "six", "sixty",
            "so", "some", "somehow", "someone", "something", "sometime",
            "sometimes", "somewhere", "still", "such", "system", "take",
            "ten", "than", "that", "the", "their", "them",
            "themselves", "then", "thence", "there", "thereafter", "thereby",
            "therefore", "therein", "thereupon", "these", "they", "thick",
            "thin", "third", "this", "those", "though", "three",
            "through", "throughout", "thru", "thus", "to", "together",
            "too", "top", "toward", "towards", "twelve", "twenty",
            "two", "un", "under", "until", "up", "upon",
            "us", "very", "via", "was", "we", "well",
            "were", "what", "whatever", "when", "whence", "whenever",
            "where", "whereafter", "whereas", "whereby", "wherein", "whereupon",
            "wherever", "whether", "which", "while", "whither", "who",
            "whoever", "whole", "whom", "whose", "why", "will",
            "with", "within", "without", "would", "yet", "you", "your", "yours",
            "yourself", "yourselves"
    };

    /**
     * Remove stop words from a given String array
     * @param input
     * @return ArrayList
     */
    public ArrayList<String> clear(String[] input){
        ArrayList<String> output = new ArrayList<String>();
        for(String word : input){
            boolean t = false;
            for(String token : tokenSet){
                if (token.equals(word)){
                    t = true;
                    break;
                }
            }
            if (!t){
                output.add(word);
            }
        }
        return output;
    }

    /**
     * Remove unnecessary characters from a given string
     * @param input
     * @return
     */
    public String clearUglyChars(String input){
        String output = input;
        for(Character c : uglyChars){
            output = output.replace(c.toString(), "");
        }
        return output;
    }
}
