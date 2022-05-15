package com.week3.imarkovmodel;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Duke Software
 * @version 1.0
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
        markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 200;
		
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

    }

	private void printOut(String s){
		String[] words = s.split("\\s+");
		int psize = 0;
		System.out.println("----------------------------------");
		for(int k=0; k < words.length; k++){
			System.out.print(words[k]+ " ");
			psize += words[k].length() + 1;
			if (psize > 60) {
				System.out.println();
				psize = 0;
			}
		}
		System.out.println("\n----------------------------------");
	}
	
	private void testHashMap() {
//		String str = "yes-this-is-a-thin-pretty-pink-thistle";
		
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		
		int seed = 531;
		int size = 50;
		EfficientMarkovModel em = new EfficientMarkovModel(5);
		runModel(em, st, size, seed);
		em.printHashMapInfo();
	}
	
	public void compareMethods() {
		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int seed = 42;
		int size = 1000;
		
		double startTime  = System.nanoTime();
		MarkovModel mm = new MarkovModel(2);
		runModel(mm, st, size, seed);
		double endTime = System.nanoTime();
		
		System.out.println("Running time of Markov Model is: " + (endTime-startTime)/1000000000.0 + "Seconds");
		
		startTime  = System.nanoTime();
		EfficientMarkovModel em = new EfficientMarkovModel(2);
		runModel(em, st, size, seed);
		endTime = System.nanoTime();
		
		System.out.println("Running time of Efficient Markov Model is: " + (endTime-startTime)/1000000000.0 + "Seconds");
		
	}
	
	public static void main(String[] args) {
		MarkovRunnerWithInterface mr = new MarkovRunnerWithInterface();
		mr.testHashMap();
	}
	
}
