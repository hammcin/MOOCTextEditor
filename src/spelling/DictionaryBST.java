package spelling;

import java.util.TreeSet;

/**
 * @author Hamadi McIntosh
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class DictionaryBST implements Dictionary 
{
   private TreeSet<String> dict;
	
   public DictionaryBST() {
	   this.dict = new TreeSet<String>();
   }
	
    
    /** Add this word to the dictionary.  Convert it to lowercase first.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	String wordLower = word.toLowerCase();
    	boolean hasWord = dict.add(wordLower);
    	return hasWord;
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
    	return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
    	String wordLower = s.toLowerCase();
        return dict.contains(wordLower);
    }

}
