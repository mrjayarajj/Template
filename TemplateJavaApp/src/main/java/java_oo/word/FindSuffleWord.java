package java_oo.word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FindSuffleWord {

	public static void main(String[] args) {

		WordList wordlist = new WordList();
		wordlist.add("dog");
		wordlist.add("god");
		wordlist.add("odg");
		wordlist.add("gdo");
		wordlist.add("dgo");
		
		wordlist.add("jay");
		wordlist.add("raj");
		
		
		System.out.println(wordlist.getAllShufledWords("jay"));

	}

}

class WordList {
	
	List<String> words = new ArrayList<String>();

	public void add(String word) {
		words.add(word);
	}
	
	public List<Word> getAllShufledWords(String inputWord){
		
		if(words.contains(inputWord)==false){
			return null;
		}
		
		Iterator<String> wordsItr = words.iterator();

		Map<Word, List<Word>> wordsMap = new HashMap<Word, List<Word>>();

		while (wordsItr.hasNext()) {

			String wordStr = wordsItr.next();
			Word word = new Word(wordStr);

			List<Word> valList = wordsMap.get(word);

			if (valList == null) {
				List<Word> newList = new ArrayList<Word>();
				newList.add(word);
				wordsMap.put(word, newList);
			} else {
				valList.add(word);
				wordsMap.put(word, valList);
			}
		}
		
		return wordsMap.get(new Word(inputWord));
	}
}

class Word {

	private String word = null;

	public Word(String word) {
		this.word = word;
	}

	public String getWord() {
		return word;
	}

	public String getCharCount() {

		Map<Character, Integer> charInt = new HashMap<Character, Integer>();

		char chars[] = word.toCharArray();

		for (int i = 0; i < chars.length; i++) {

			Integer val = charInt.get(new Character(chars[i]));

			if (val == null) {
				charInt.put(new Character(chars[i]), new Integer(1));
			} else {
				charInt.put(new Character(chars[i]), new Integer(val.intValue() + 1));
			}
		}

		return charInt.toString();
	}

	public boolean equals(Object obj) {

		if (obj == null) {
			return false;
		}

		if (obj instanceof Word == false) {
			return false;
		}

		return getCharCount().equals(((Word) obj).getCharCount());
	}

	public int hashCode() {
		return getCharCount().hashCode();
	}

	public String toString() {
		return this.word;
	}

}
