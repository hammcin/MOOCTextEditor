package document;

import java.util.List;

/** 
 * A naive implementation of the Document abstract class.
 * @author Hamadi McIntosh 
 * @author UC San Diego Intermediate Programming MOOC team
 */
public class BasicDocument extends Document 
{
	/** Create a new BasicDocument object
	 * 
	 * @param text The full text of the Document.
	 */
	public BasicDocument(String text)
	{
		super(text);
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
	 * This method processes the entire text string each time it is called.  
	 * 
	 * @return The number of words in the document.
	 */
	@Override
	public int getNumWords()
	{
		List<String> words = getTokens("[a-zA-Z]+");
		int numWords = words.size();
	    return numWords;
	}
	
	/**
	 * Get the number of sentences in the document.
	 * Sentences are defined as contiguous strings of characters ending in an 
	 * end of sentence punctuation (. ! or ?) or the last contiguous set of 
	 * characters in the document, even if they don't end with a punctuation mark.
	 * 
	 * Check the examples in the main method below for more information.  
	 * 
	 * This method processes the entire text string each time it is called.  
	 * 
	 * @return The number of sentences in the document.
	 */
	@Override
	public int getNumSentences()
	{
		List<String> sentences = getTokens("[^.!?]+");
		int numSentences = sentences.size();
        return numSentences;
	}
	
	/**
	 * Get the total number of syllables in the document (the stored text). 
	 * To count the number of syllables in a word, it uses the following rules:
	 *       Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       Y is considered to be a vowel.
	 *       
	 * Check the examples in the main method below for more information.  
	 * 
	 * This method processes the entire text string each time it is called.  
	 * 
	 * @return The number of syllables in the document.
	 */
	@Override
	public int getNumSyllables()
	{
		int numSyllables = 0;
		List<String> words = getTokens("[a-zA-Z]+");
		for (String word : words) {
			numSyllables += countSyllables(word);
		}
        return numSyllables;
	}
	
	
	/* The main method for testing this class.  */
	public static void main(String[] args)
	{
		/* Each of the test cases below uses the method testCase.  The first 
		 * argument to testCase is a Document object, created with the string shown.
		 * The next three arguments are the number of syllables, words and sentences
		 * in the string, respectively.  The final argument is the Flesch score for
		 * the string.
		 */
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
