package com.week3.wordGram;
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(5); 
        runModel(markovWord, st, 200, 844); 
    } 
    
    public void testHashMap() {
//    	String str = "this is a test yes this is really a test yes a test this is wow";
    	 FileResource fr = new FileResource(); 
         String st = fr.asString(); 
         st = st.replace('\n', ' '); 
    	EfficientMarkovWord ew = new EfficientMarkovWord(2);
    	runModel(ew, st, 200, 65);
    }
    
    public void compareMethods() {
    	 FileResource fr = new FileResource(); 
         String st = fr.asString(); 
         st = st.replace('\n', ' '); 
         int size = 100;
         int seed = 42;
         
         double startTime = System.nanoTime();
         MarkovWord mw = new MarkovWord(2);
         runModel(mw, st, size, seed);
         double endTime = System.nanoTime();
         System.out.println("The running time of MarkovWord is: " + (endTime-startTime)/1000000000.0 +" seconds");
         
         startTime = System.nanoTime();
         EfficientMarkovWord em = new EfficientMarkovWord(2);
         runModel(em, st, size, seed);
         endTime = System.nanoTime();
         System.out.println("The running time of MarkovWord is: " + (endTime-startTime)/1000000000.0 +" seconds");
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
    
    public static void main(String[] args) {
    	MarkovRunner mr = new MarkovRunner();
    	mr.testHashMap();
    }

}
