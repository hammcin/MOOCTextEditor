package document;

import java.util.List;

/** 
 * A class that represents a text document
 * It does one pass through the document to count the number of syllables, words, 
 * and sentences and then stores those values.
 * 
 * @author Hamadi McIntosh
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class EfficientDocument extends Document {

	private int numWords;  // The number of words in the document
	private int numSentences;  // The number of sentences in the document
	private int numSyllables;  // The number of syllables in the document
	
	public EfficientDocument(String text)
	{
		super(text);
		processText();
	}
	
	
	/** 
	 * Take a string that either contains only alphabetic characters,
	 * or only sentence-ending punctuation.  Return true if the string
	 * contains only alphabetic characters, and false if it contains
	 * end of sentence punctuation.  
	 * 
	 * @param tok The string to check
	 * @return true if tok is a word, false if it is punctuation. 
	 */
	private boolean isWord(String tok)
	{
		return !(tok.indexOf("!") >=0 || tok.indexOf(".") >=0 || tok.indexOf("?")>=0);
	}
	
	
    /** Passes through the text one time to count the number of words, syllables 
     * and sentences, and set the member variables appropriately.
     * Words, sentences and syllables are defined as described below. 
     */
	private void processText()
	{
		// Call getTokens on the text to preserve separate strings that are 
		// either words or sentence-ending punctuation.  Ignore everything
		// that is not a word or a sentence-ending punctuation.
		List<String> tokens = getTokens("[!?.]+|[a-zA-Z]+");
		numWords = 0;
		numSentences = 0;
		numSyllables = 0;
		for (String tok : tokens) {
			if (isWord(tok)) {
				numWords++;
				numSyllables += countSyllables(tok);
			}
			else {
				numSentences++;
			}
		}
		if (tokens.size()>0) {
			if (isWord(tokens.get(tokens.size()-1))) {
				numSentences++;
			}
		}
	}

	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * Check the examples in the main method below for more information. 
	 *  
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the EfficientDocument object.
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences() {
		return numSentences;
	}

	
	/**
	 * Get the number of words in the document.
	 * A "word" is defined as a contiguous string of alphabetic characters
	 * i.e. any upper or lower case characters a-z or A-Z.  This method completely 
	 * ignores numbers when you count words, and assumes that the document does not have 
	 * any strings that combine numbers and letters. 
	 * 
	 * Check the examples in the main method below for more information.
	 * 
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the EfficientDocument object.
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords() {
		return numWords;
	}


	/**
	 * Get the total number of syllables in the document (the stored text). 
	 * To calculate the the number of syllables in a word, it uses the following rules:
	 *       Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       You should consider y a vowel.
	 *       
	 * Check the examples in the main method below for more information.
	 * 
	 * This method does NOT process the whole text each time it is called.  
	 * It returns information already stored in the EfficientDocument object.
	 * 
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables() {
		return numSyllables;
	}
	
	// Can be used for testing
	public static void main(String[] args)
	{
		int numSyllables = 16;
		int numWords = 13;
		int numSentences = 5;
		double fleschScore = 100.072;
		testCase(new BasicDocument("This is a test.  How many???  "
		        + "Senteeeeeeeeeences are here... there should be 5!  Right?"),
				numSyllables, numWords, numSentences, fleschScore);
		
		numSyllables = 0;
		numWords = 0;
		numSentences = 0;
		fleschScore = 100.0;
		testCase(new BasicDocument(""), numSyllables, numWords, numSentences, fleschScore);
		
		numSyllables = 15;
		numWords = 11;
		numSentences = 4;
		fleschScore = 88.680;
		testCase(new BasicDocument("sentence, with, lots, of, commas.!  "
		        + "(And some poaren)).  The output is: 7.5."), numSyllables, numWords, numSentences, fleschScore);
		
		numSyllables = 6;
		numWords = 3;
		numSentences = 2;
		fleschScore = 36.112;
		testCase(new BasicDocument("many???  Senteeeeeeeeeences are"),
				numSyllables, numWords, numSentences, fleschScore);
		
		numSyllables = 49;
		numWords = 33;
		numSentences = 3;
		fleschScore = 70.051;
		testCase(new BasicDocument("Here is a series of test sentences. Your program should "
				+ "find 3 sentences, 33 words, and 49 syllables. Not every word will have "
				+ "the correct amount of syllables (example, for example), "
				+ "but most of them will."), numSyllables, numWords, numSentences, fleschScore);
		
		numSyllables = 2;
		numWords = 1;
		numSentences = 1;
		fleschScore = 36.620;
		testCase(new BasicDocument("Segue"), numSyllables, numWords, numSentences, fleschScore);
		
		numSyllables = 2;
		numWords = 1;
		numSentences = 1;
		fleschScore = 36.620;
		testCase(new BasicDocument("Sentence"), numSyllables, numWords, numSentences, fleschScore);
		
		numSyllables = 3;
		numWords = 1;
		numSentences = 1;
		fleschScore = -47.979;
		testCase(new BasicDocument("Sentences?!"), numSyllables, numWords, numSentences, fleschScore);
		
		numSyllables = 32;
		numWords = 15;
		numSentences = 1;
		fleschScore = 11.130;
		testCase(new BasicDocument("Lorem ipsum dolor sit amet, qui ex choro quodsi moderatius, nam dolores explicari forensibus ad."),
				numSyllables, numWords, numSentences, fleschScore);
		
	}
	

}
