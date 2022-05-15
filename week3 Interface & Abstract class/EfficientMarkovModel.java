package com.week3.imarkovmodel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class EfficientMarkovModel extends AbstractMarkovModel{
	
	private HashMap<String, ArrayList<String>> myMap;
	
	
	public EfficientMarkovModel(int number) {
		super(number);
		myRandom = new Random();
		myMap = new HashMap<String, ArrayList<String>>();
	}

	@Override
	public void setRandom(int seed) {
		myRandom = new Random(seed);
	}
	
	public void setTraining(String s) {
		myText = s.trim();
		buildMap();
	}
	
	private void buildMap() {
		for(int i=0; i<myText.length() - (myOrder-1); i++) {
			String current = myText.substring(i, i+myOrder);
			String follow = "";
			if(i+myOrder<myText.length()) {
				follow = myText.substring(i+myOrder, i+myOrder+1);
			}
			if(myMap.containsKey(current)) {
				myMap.get(current).add(follow);
			}
			else {
				ArrayList<String> list = new ArrayList<String>();
				list.add(follow);
				myMap.put(current, list);
			}
		}
	}
	
	public ArrayList<String> getFollows(String key){
		return myMap.get(key);
	}

	@Override
	public String getRandomText(int numChars) {
		if(myText==null) {
			return "";
		}
		
		StringBuilder sb = new StringBuilder();
		int idx = myRandom.nextInt(myText.length()-myOrder);
		String current = myText.substring(idx, idx+myOrder);
		sb.append(current);
		for(int k=0; k<numChars-myOrder; k++) {
			ArrayList<String> follows = getFollows(current);
			if(follows == null) {
				break;
			}
			
			idx = myRandom.nextInt(follows.size());
			String next = follows.get(idx);
			sb.append(next);
			current = current.substring(1) + next;
		}
		return sb.toString();
	}
	
	public void printHashMapInfo() {
		System.out.println("It has " + myMap.size() + " keys in the hashMap");
		int maxSize = 0;
		for(String key : myMap.keySet()) {
			maxSize = Math.max(maxSize, myMap.get(key).size());
//			System.out.println("Key:\t[%s]\tvalues: " +key);
//			System.out.println(myMap.get(key));
			
		}
		System.out.println("The maximum number of keys following a key is: "+ maxSize);
		ArrayList<String> keys = new ArrayList<String>();
		for(String key : myMap.keySet()) {
			if(myMap.get(key).size() == maxSize) {
				keys.add(key);
			}
		}
		
		System.out.println("Keys that have the largest ArrayList are: " + keys);
	}
	
	public String toString() {
		return "Efficient Markov model of order: " + myOrder;
	}

}
