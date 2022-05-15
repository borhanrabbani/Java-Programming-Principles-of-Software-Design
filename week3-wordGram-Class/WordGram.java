package com.week3.wordGram;

public class WordGram {
	private String[] myWords;
	private int myHash;

	public WordGram(String[] source, int start, int size) {
		myWords = new String[size];
		System.arraycopy(source, start, myWords, 0, size);
	}

	public String wordAt(int index) {
		if (index < 0 || index >= myWords.length) {
			throw new IndexOutOfBoundsException("bad index in wordAt " + index);
		}
		return myWords[index];
	}

	public int length() {
		// TODO: Complete this method

		return myWords.length;
	}

	public String toString() {
		String ret = "";
		// TODO: Complete this method
		for (int i = 0; i < myWords.length; i++) {
			ret += myWords[i] + " ";
		}

		return ret.trim();
	}

	public boolean equals(Object o) {
		WordGram other = (WordGram) o;
		// TODO: Complete this method
		if (this.length() != other.length()) {
			return false;
		}
		for (int i = 0; i < length(); i++) {
			if (!myWords[i].equals(other.wordAt(i))) {
				return false;
			}
		}
		return true;

	}

	public WordGram shiftAdd(String word) {
		String[] newWords = new String[this.length()];
		for(int i=0; i<newWords.length-1; i++) {
			newWords[i] = this.wordAt(i+1);
			
		}
		newWords[newWords.length-1] = word;
		WordGram out = new WordGram(newWords, 0, newWords.length);
		// shift all words one towards 0 and add word at the end.
		// you lose the first word
		// TODO: Complete this method
		return out;
	}
	
//	public static void main(String[] args) {
//		String[] str = new String[19];
//		str[0]="this is a test" ;
//		WordGram wg = new WordGram(str, 1, 10);
//		System.out.println(wg.shiftAdd("this is a test"));
//	}
	
	public int hashCode() {
		return toString().hashCode();
	}

}