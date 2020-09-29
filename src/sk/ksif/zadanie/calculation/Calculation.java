package sk.ksif.zadanie.calculation;

import sk.ksif.zadanie.text.ReaderFromFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Calculation {
    public static Long totalCountQuadgrams = 4224127912L;
    public static Long totalCountTrigrams = 4274127909L;
    public static Long totalCountBigrams = 4324127906L;

    public static Map<String,Double> logProbabilityOfQuadGrams(){
        Map<String,Integer> quadgrams = ReaderFromFile.getQuadgramsMap();
        Map<String,Double> logProbability = new HashMap<>();
        for (String quadgram : quadgrams.keySet()){
            logProbability.put(quadgram,Math.log((double) quadgrams.get(quadgram)/totalCountQuadgrams));
        }
        return logProbability;
    }

    public static Map<String,Double> logProbabilityOfTriGrams(){
        Map<String,Integer> trigrams = ReaderFromFile.getTrigramsMap();
        Map<String,Double> logProbability = new HashMap<>();
        for (String quadgram : trigrams.keySet()){
            logProbability.put(quadgram,Math.log((double) trigrams.get(quadgram)/totalCountTrigrams));
        }
        return logProbability;
    }

    public static Map<String,Double> logProbabilityOfBiGrams(){
        Map<String,Integer> quadgrams = ReaderFromFile.getBigramsMap();
        Map<String,Double> logProbability = new HashMap<>();
        for (String bigram : quadgrams.keySet()){
            logProbability.put(bigram,Math.log((double) quadgrams.get(bigram)/totalCountBigrams));
        }
        return logProbability;
    }

    public static Double fitnessOfQuadgrams(Map<String,Double> logProbability,String input){
        ArrayList<String> quadgramsInput = new ArrayList<>();
        for(int i = 0;i+4<=input.length();i++){
            String quadgram = input.substring(i,i+4);
            quadgramsInput.add(quadgram);
        }
        Double toReturn = new Double(0);
        for (String str:quadgramsInput){
            if(logProbability.containsKey(str)){
                toReturn += logProbability.get(str);
            }else{
                totalCountQuadgrams += 1;
                toReturn += Math.log((double) 1/totalCountQuadgrams);
            }
        }
        return toReturn;
    }

    public static Double fitnessOfTrigrams(Map<String,Double> logProbability,String input){
        ArrayList<String> trigramsInput = new ArrayList<>();
        for(int i = 0;i+3<=input.length();i++){
            String quadgram = input.substring(i,i+3);
            trigramsInput.add(quadgram);
        }
        Double toReturn = new Double(0);
        for (String str:trigramsInput){
            if(logProbability.containsKey(str)){
                toReturn += logProbability.get(str);
            }else{
                totalCountTrigrams += 1;
                toReturn += Math.log((double) 1/totalCountTrigrams);
            }
        }
        return toReturn;
    }

    public static Double fitnessOfBigrams(Map<String,Double> logProbability,String input){
        ArrayList<String> BigramInput = new ArrayList<>();
        for(int i = 0;i+3<=input.length();i++){
            String quadgram = input.substring(i,i+3);
            BigramInput.add(quadgram);
        }
        Double toReturn = new Double(0);
        for (String str:BigramInput){
            if(logProbability.containsKey(str)){
                toReturn += logProbability.get(str);
            }else{
                totalCountBigrams += 1;
                toReturn += Math.log((double) 1/totalCountBigrams);
            }
        }
        return toReturn;
    }
}
