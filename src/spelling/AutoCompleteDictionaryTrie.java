package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Hamadi McIntosh
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * Convert the string to all lower case before it is inserted. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie. It appropriately uses existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		String wordLower = word.toLowerCase();
		TrieNode currNode = root;
		TrieNode nextNode;
		Character currChar;
		for (int i=0; i<wordLower.length();i++) {
			currChar = wordLower.charAt(i);
			nextNode = currNode.getChild(currChar);
			if (nextNode==null) {
				nextNode = currNode.insert(currChar);
			}
			currNode = nextNode;
		}
		if (currNode.endsWord()) {
			return false;
		}
		else {
			currNode.setEndsWord(true);
			this.size++;
		}
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
		return this.size;
	}
	
	
	/** Returns whether the string is a word in the trie. */
	@Override
	public boolean isWord(String s) 
	{
		String sLower = s.toLowerCase();
		TrieNode currNode = root;
		TrieNode nextNode;
		Character currChar;
		for (int i=0;i<sLower.length();i++) {
			currChar = sLower.charAt(i);
			nextNode = currNode.getChild(currChar);
			if (nextNode==null) {
				return false;
			}
			currNode = nextNode;
		}
		if (currNode.endsWord()) {
			return true;
		}
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 LinkedList<String> wordComplete = new LinkedList<String>();
    	 String prefixLower = prefix.toLowerCase();
    	 TrieNode stemNode = root;
    	 TrieNode nextNode;
    	 Character currChar;
    	 for (int i=0;i<prefixLower.length();i++) {
    		 currChar = prefixLower.charAt(i);
    		 nextNode = stemNode.getChild(currChar);
    		 if (nextNode==null) {
    			 return wordComplete;
    		 }
    		 stemNode = nextNode;
    	 }
    	 LinkedList<TrieNode> q = new LinkedList<TrieNode>();
    	 q.addLast(stemNode);
    	 TrieNode currNode;
    	 while ((q.size()>0) && (wordComplete.size()<numCompletions)) {
    		 currNode = q.removeFirst();
    		 if (currNode.endsWord()) {
    			 wordComplete.add(currNode.getText());
    			 }
    		 for (Character c : currNode.getValidNextCharacters()) {
				 q.addLast(currNode.getChild(c));
    		 }
    	 }
         return wordComplete;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}