package com.week3.wordGram;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovWord implements IMarkovModel {

	private String[] myText;
	private Random myRandom;
	private int myOrder;
	private HashMap<WordGram, ArrayList<String>> myMap;

	public EfficientMarkovWord(int n) {
		myMap = new HashMap<WordGram, ArrayList<String>>();
		myOrder = n;
	}

	@Override
	public void setTraining(String text) {
		myText = text.split("\\s+");
		buildMap();
		printHashMapInfo();

	}

	public void buildMap() {
		for (int i = 0; i < myText.length - (myOrder - 1); i++) {
			WordGram wg = new WordGram(myText, i, myOrder);
			String next = "";
			if (i + myOrder < myText.length) {
				next = myText[i + myOrder];
			}
			if (myMap.containsKey(wg)) {
				myMap.get(wg).add(next);
			} else {
				ArrayList<String> list = new ArrayList<String>();
				list.add(next);
				myMap.put(wg, list);
			}
		}
	}

	@Override
	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}

	@Override
	public String getRandomText(int numWords) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length - myOrder); // random word to start with
		WordGram key = new WordGram(myText, index, myOrder);
		sb.append(key.toString());
		sb.append(" ");
		for (int k = 0; k < numWords - myOrder; k++) {
//			System.out.print("Key \"" + key + "\" ");
			ArrayList<String> follows = getFollows(key);
//		    System.out.println("is followed by: " + follows);
			if (follows == null) {
				break;
			}
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = key.shiftAdd(next);
		}

		return sb.toString().trim();

	}

	public ArrayList<String> getFollows(WordGram kGram) {
		return myMap.get(kGram);
	}
	
	public void printHashMapInfo(){
		System.out.println("it has " + myMap.size() + " keys in the hashMap");
		int maxSize = 0;
		for(WordGram wg: myMap.keySet()) {
			maxSize = Math.max(maxSize, myMap.get(wg).size());
		}
		
		System.out.println("The mazimum number of elements following a key is " + maxSize);
		
		System.out.println("Keys with the maximum size value is: ");
		for(WordGram wg: myMap.keySet()) {
			if(myMap.get(wg).size()==maxSize) {
				System.out.println(wg);
				System.out.println("(The follow words: " + myMap.get(wg) +")");
			}
		}
	}

}
