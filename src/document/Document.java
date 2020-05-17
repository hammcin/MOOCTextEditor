package document;

/** 
 * A class that represents a text document
 * @author Hamadi McIntosh
 * @author UC San Diego Intermediate Programming MOOC team
 */
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Document {

	private String text;
	
	/** Create a new document from the given text.
	 * Because this class is abstract, this is used only from subclasses.
	 * @param text The text of the document.
	 */
	protected Document(String text)
	{
		this.text = text;
	}
	
	/** Returns the tokens that match the regex pattern from the document 
	 * text string.
	 * @param pattern A regular expression string specifying the 
	 *   token pattern desired
	 * @return A List of tokens from the document text that match the regex 
	 *   pattern
	 */
	protected List<String> getTokens(String pattern)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(text);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		
		return tokens;
	}
	
	private boolean isVowel(char letter)
	{
		boolean isCharVowel = (letter == 'a')
				|| (letter == 'e')
				|| (letter == 'i')
				|| (letter == 'o')
				|| (letter == 'u')
				|| (letter == 'y');
		return isCharVowel;
	}
	
	/** This is a helper function that returns the number of syllables
	 * in a word.
	 * 
	 * @param word  The word to count the syllables in
	 * @return The number of syllables in the given word, according to 
	 * this rule: Each contiguous sequence of one or more vowels is a syllable, 
	 *       with the following exception: a lone "e" at the end of a word 
	 *       is not considered a syllable unless the word has no other syllables. 
	 *       Y is considered to be a vowel.
	 */
	protected int countSyllables(String word)
	{
		String wordLower = word.toLowerCase();
		int numSyllables = 0;
		for (int i=0;i<wordLower.length();i++) {
			char currLetter = wordLower.charAt(i);
			boolean isVowelCurr = isVowel(currLetter);
			if (i==0) {
				if (isVowelCurr) {
					numSyllables += 1;
				}
			}
			else {
				char prevLetter = wordLower.charAt(i-1);
				boolean isVowelPrev = isVowel(prevLetter);
				if (i<(wordLower.length()-1)) {
					if (isVowelCurr && !isVowelPrev) {
						numSyllables += 1;
						}
					}
				else {
					if ((currLetter == 'e') && !isVowelPrev) {
						if (numSyllables == 0) {
							numSyllables += 1;
						}
					}
					else {
						if (isVowelCurr && !isVowelPrev) {
							numSyllables += 1;
						}
					}
				}
			}
		}
	    return numSyllables;
	}
	
	/** A method for testing
	 * 
	 * @param doc The Document object to test
	 * @param syllables The expected number of syllables
	 * @param words The expected number of words
	 * @param sentences The expected number of sentences
	 * @return true if the test case passed.  False otherwise.
	 */
	public static boolean testCase(Document doc, int syllables, int words, int sentences, double fleschScore)
	{
		System.out.println("Testing text: ");
		System.out.print(doc.getText() + "\n....");
		boolean passed = true;
		int syllFound = doc.getNumSyllables();
		int wordsFound = doc.getNumWords();
		int sentFound = doc.getNumSentences();
		double fleschScoreFound = doc.getFleschScore();
		if (syllFound != syllables) {
			System.out.println("\nIncorrect number of syllables.  Found " + syllFound 
					+ ", expected " + syllables);
			passed = false;
		}
		if (wordsFound != words) {
			System.out.println("\nIncorrect number of words.  Found " + wordsFound 
					+ ", expected " + words);
			passed = false;
		}
		if (sentFound != sentences) {
			System.out.println("\nIncorrect number of sentences.  Found " + sentFound 
					+ ", expected " + sentences);
			passed = false;
		}
		if ((fleschScoreFound < (fleschScore - 0.0001)) && ((fleschScoreFound > (fleschScore + 0.0001)))) {
			System.out.println("\nIncorrect Flesch Score.  Found " + fleschScoreFound
					+ ", expected " + fleschScore);
			passed = false;
		}
		
		if (passed) {
			System.out.println("passed.\n");
		}
		else {
			System.out.println("FAILED.\n");
		}
		return passed;
	}
	
	
	/** Return the number of words in this document */
	public abstract int getNumWords();
	
	/** Return the number of sentences in this document */
	public abstract int getNumSentences();
	
	/** Return the number of syllables in this document */
	public abstract int getNumSyllables();
	
	/** Return the entire text of this document */
	public String getText()
	{
		return this.text;
	}
	
	/** return the Flesch readability score of this document */
	public double getFleschScore()
	{
		double fleschScore = 100.0;
		int numSentences = getNumSentences();
		int numWords = getNumWords();
		if (numSentences>0 || numWords>0) {
			fleschScore = 206.835
					- 1.015*(((double) numWords)/((double) numSentences))
					- 84.6*(((double) getNumSyllables())/((double) numWords));
			if (fleschScore > 100.0) {
				fleschScore = 100.0;
			}
			if (fleschScore < 0.0) {
				fleschScore = 0.0;
			}
		}
	    return fleschScore;
	}
	
	
	
}
