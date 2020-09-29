package sk.ksif.zadanie;

import sk.ksif.zadanie.calculation.Calculation;
import sk.ksif.zadanie.calculation.Permutations;
import sk.ksif.zadanie.data_types.Matrix;
import sk.ksif.zadanie.data_types.StringBlocks;

import java.text.Normalizer;
import java.util.*;

//main for all(bigrams,tri and quad grams)
public class Main {
    //public static Map<Double,List<Object>> mapPermToFitness = new HashMap<>();//ok
    public static int guessedKeyLength = 10;
    public static String encrypted = "SELHARTEIEEOOFMSTRFSNEIRWRCTTILERRUIGQEDTTOTEMILREGNTARIHWINSPEMEMILNTPNOIEMSCSTDPOOECELULRTDOEANRMOOIALERETCYOIALERRTTEEOTPENPNSRUIAUEDQRCTRCTLYPAROGHPAY";
    public static List<Object> permList = fillListForPerm(guessedKeyLength);
    public static StringBlocks stringBlocks = new StringBlocks(encrypted,guessedKeyLength);
    public static int counter = 0; //for make swapping keys easier
    public static int tooLong = 0;
    //for quadgrams
    public static Map<String,Double> logProbabilityOfQuadGrams = Calculation.logProbabilityOfQuadGrams();
    public static Double lastQuadgramFitness = Calculation.fitnessOfQuadgrams(logProbabilityOfQuadGrams,encrypted);
    //for trigrams
    public static Map<String,Double> logProbabilityOfTriGrams = Calculation.logProbabilityOfTriGrams();
    public static Double lastTrigramFitness = Calculation.fitnessOfTrigrams(logProbabilityOfTriGrams,encrypted);
    //for bigrams
    public static Map<String,Double> logProbabilityOfBiGrams = Calculation.logProbabilityOfBiGrams();
    public static Double lastBigramFitness = Calculation.fitnessOfBigrams(logProbabilityOfBiGrams,encrypted);

    public static void main(String[] args) {
        System.out.println(stringBlocks.getBlocks());//ok

        Object[] tmp = Permutations.rndPerm(permList.toArray());//random
        permList = Arrays.asList(tmp);

        List<Object> lastBestPerm = new ArrayList<>();
        for(Object o : permList){
            lastBestPerm.add(o);
        }

        Double potentialBestFitnessQuad = Calculation.fitnessOfQuadgrams(logProbabilityOfQuadGrams,stringBlocks.toString());
        Double potentialBestFitnessTri = Calculation.fitnessOfQuadgrams(logProbabilityOfTriGrams,stringBlocks.toString());;
        Double potentialBestFitnessBi = Calculation.fitnessOfQuadgrams(logProbabilityOfBiGrams,stringBlocks.toString());;

        for(;;){

            List<Object> potentialBestPerm = new ArrayList<>();
            for(Object o : permList){
                potentialBestPerm.add(o);
            }
            swapTwoObjectsInKey(potentialBestPerm);

            StringBlocks potentialBestBlock = decrypt(stringBlocks,potentialBestPerm.toArray());

            potentialBestFitnessQuad = Calculation.fitnessOfQuadgrams(logProbabilityOfQuadGrams,potentialBestBlock.toString());

            potentialBestFitnessTri = Calculation.fitnessOfTrigrams(logProbabilityOfTriGrams,potentialBestBlock.toString());

            potentialBestFitnessBi = Calculation.fitnessOfBigrams(logProbabilityOfBiGrams,potentialBestBlock.toString());

            if(lastQuadgramFitness <=  potentialBestFitnessQuad){//think
                lastQuadgramFitness = potentialBestFitnessQuad.doubleValue();
                stringBlocks = new StringBlocks(potentialBestBlock.toString(),guessedKeyLength);
                permList = potentialBestPerm;
                lastBestPerm.clear();
                for(Object o : permList){
                    lastBestPerm.add(o);
                }
                System.out.println("____________________________________________________________________________________");
                System.out.println("quadgram improvement");
                System.out.println("quad fitness " + lastQuadgramFitness);
                System.out.println(lastBestPerm);
                System.out.println(stringBlocks.toString());

                if(tooLong > 500){// go back to best perm
                    tooLong = 0;
                    permList.clear();
                    for(Object o : lastBestPerm){
                        permList.add(o);
                    }
                }
            }

            if(lastTrigramFitness <=  potentialBestFitnessTri) {//think
                lastTrigramFitness = potentialBestFitnessTri.doubleValue();
                stringBlocks = new StringBlocks(potentialBestBlock.toString(), guessedKeyLength);
                permList = potentialBestPerm;
                lastBestPerm.clear();
                for (Object o : permList) {
                    lastBestPerm.add(o);
                }
                System.out.println("____________________________________________________________________________________");
                System.out.println("trigram improvement");
                System.out.println("tri fitness " + lastTrigramFitness);
                System.out.println(lastBestPerm);
                System.out.println(stringBlocks.toString());

                if (tooLong > 500) {// go back to best perm
                    tooLong = 0;
                    permList.clear();
                    for (Object o : lastBestPerm) {
                        permList.add(o);
                    }
                }
            }

            if (lastBigramFitness <= potentialBestFitnessBi) {//think
                lastBigramFitness = potentialBestFitnessBi.doubleValue();
                stringBlocks = new StringBlocks(potentialBestBlock.toString(), guessedKeyLength);
                permList = potentialBestPerm;
                lastBestPerm.clear();
                for (Object o : permList) {
                    lastBestPerm.add(o);
                }
                System.out.println("____________________________________________________________________________________");
                System.out.println("bigram improvement");
                System.out.println("bi fitness " + lastBigramFitness);
                System.out.println(lastBestPerm);
                System.out.println(stringBlocks.toString());

                if (tooLong > 500) {// go back to best perm
                    tooLong = 0;
                    permList.clear();
                    for (Object o : lastBestPerm) {
                        permList.add(o);
                    }
                }
            }
        }
    }

    //main for bigrams
//    public static void main(String[] args) {
//        System.out.println(stringBlocks.getBlocks());//ok
//
//        Object[] tmp = Permutations.rndPerm(permList.toArray());//random
//        permList = Arrays.asList(tmp);
//
//        List<Object> lastBestPerm = new ArrayList<>();
//        for(Object o : permList){
//            lastBestPerm.add(o);
//        }
//
//        for(;;){
//            List<Object> potentialBestPerm = new ArrayList<>();
//            for(Object o : permList){
//                potentialBestPerm.add(o);
//            }
//            swapTwoObjectsInKey(potentialBestPerm);
//
//            StringBlocks potentialBestBlock = decrypt(stringBlocks,potentialBestPerm.toArray());
//
//            Double potentialBestFitness = Calculation.fitnessOfBigrams(logProbabilityOfBiGrams,potentialBestBlock.toString());
//
//            if(lastBigramFitness <=  potentialBestFitness){//think
//                lastBigramFitness = potentialBestFitness.doubleValue();
//                stringBlocks = new StringBlocks(potentialBestBlock.toString(),guessedKeyLength);
//                permList = potentialBestPerm;
//                lastBestPerm.clear();
//                for(Object o : permList){
//                    lastBestPerm.add(o);
//                }
//                System.out.println("bigram improvement");
//                System.out.println(lastBigramFitness);
//                System.out.println(lastBestPerm);
//                System.out.println(stringBlocks.toString());
//            }
//            if(tooLong > 100){// go back to best perm
//                tooLong = 0;
//                permList.clear();
//                for(Object o : lastBestPerm){
//                    permList.add(o);
//                }
//            }
//        }
//    }

    //main for trigrams
//    public static void main(String[] args) {
//        System.out.println(stringBlocks.getBlocks());//ok
//
//        Object[] tmp = Permutations.rndPerm(permList.toArray());//random
//        permList = Arrays.asList(tmp);
//
//        List<Object> lastBestPerm = new ArrayList<>();
//        for(Object o : permList){
//            lastBestPerm.add(o);
//        }
//
//        for(;;){
//            List<Object> potentialBestPerm = new ArrayList<>();
//            for(Object o : permList){
//                potentialBestPerm.add(o);
//            }
//            swapTwoObjectsInKey(potentialBestPerm);
//
//            StringBlocks potentialBestBlock = decrypt(stringBlocks,potentialBestPerm.toArray());
//
//            Double potentialBestFitness = Calculation.fitnessOfTrigrams(logProbabilityOfTriGrams,potentialBestBlock.toString());
//
//            if(lastTrigramFitness <=  potentialBestFitness){//think
//                lastTrigramFitness = potentialBestFitness.doubleValue();
//                stringBlocks = new StringBlocks(potentialBestBlock.toString(),guessedKeyLength);
//                permList = potentialBestPerm;
//                lastBestPerm.clear();
//                for(Object o : permList){
//                    lastBestPerm.add(o);
//                }
//                System.out.println("trigram improvement");
//                System.out.println(lastTrigramFitness);
//                System.out.println(lastBestPerm);
//                System.out.println(stringBlocks.toString());
//            }
//            if(tooLong > 100){// go back to best perm
//                tooLong = 0;
//                permList.clear();
//                for(Object o : lastBestPerm){
//                    permList.add(o);
//                }
//            }
//        }
//    }


    //main for quadgrams
//    public static void main(String[] args) {
//        System.out.println(stringBlocks.getBlocks());//ok
//
//        Object[] tmp = Permutations.rndPerm(permList.toArray());//random
//        permList = Arrays.asList(tmp);
//
//        List<Object> lastBestPerm = new ArrayList<>();
//        for(Object o : permList){
//            lastBestPerm.add(o);
//        }
//
//        for(;;){
//            List<Object> potentialBestPerm = new ArrayList<>();
//            for(Object o : permList){
//                potentialBestPerm.add(o);
//            }
//            swapTwoObjectsInKey(potentialBestPerm);
//
//            StringBlocks potentialBestBlock = decrypt(stringBlocks,potentialBestPerm.toArray());
//
//            Double potentialBestFitness = Calculation.fitnessOfQuadgrams(logProbabilityOfQuadGrams,potentialBestBlock.toString());
//
//            if(lastQuadgramFitness <=  potentialBestFitness){//think
//                lastQuadgramFitness = potentialBestFitness.doubleValue();
//                stringBlocks = new StringBlocks(potentialBestBlock.toString(),guessedKeyLength);
//                permList = potentialBestPerm;
//                lastBestPerm.clear();
//                for(Object o : permList){
//                    lastBestPerm.add(o);
//                }
//                System.out.println("quadgram improvement");
//                System.out.println(lastQuadgramFitness);
//                System.out.println(lastBestPerm);
//                System.out.println(stringBlocks.toString());
//
//                if(tooLong > 400){// go back to best perm
//                    tooLong = 0;
//                    permList.clear();
//                    for(Object o : lastBestPerm){
//                        permList.add(o);
//                    }
//                }
//            }
//        }
//    }

    public static void swapTwoObjectsInKey(List<Object> objectList){
        Random rnd = new Random(System.currentTimeMillis());
        for(;;){
            int j = rnd.nextInt(objectList.size());
            int k = rnd.nextInt(objectList.size());
            if(j == k){
                continue;
            }else{
                tooLong++;
                counter++;
                if(counter == 50){
                    for(int i = 0 ;i<objectList.size()-1;i++ ){
                        Object tmp = objectList.get(i);
                        objectList.set(i,objectList.get(i+1));
                        objectList.set(i+1,tmp);
                    }
                    counter = 0;
                    permList.clear();
                    for(Object o : objectList){
                        permList.add(o);
                    }
                    break;
                }else{
                    Object tmp = objectList.get(j);
                    objectList.set(j,objectList.get(k));
                    objectList.set(k,tmp);
                    break;
                }
            }
        }
    }

    public static StringBlocks encrypt(StringBlocks stringBlocks,Object[] rndPerm){
        StringBuilder builder = new StringBuilder();
        for (String string : stringBlocks.getBlocks()){
            StringBuilder appendix = new StringBuilder();
            for(int i = 0 ; i < guessedKeyLength ; i++){
                appendix.append(string.charAt(((Integer) rndPerm[i]) - 1));
            }
            String str = normalizeString(appendix.toString());
            builder.append(str);
        }
        return new StringBlocks(builder.toString(),guessedKeyLength);
    }

    public static StringBlocks decrypt(StringBlocks stringBlocks,Object[] rndPerm){
        StringBuilder builder = new StringBuilder();
        for (String string : stringBlocks.getBlocks()){
            StringBuilder appendix = new StringBuilder();
            for(int i = 0 ; i < guessedKeyLength ; i++){
                appendix.append(string.charAt(((Integer) rndPerm[i]) - 1));
            }
            String str = appendix.toString();
            builder.append(str);
        }
        return new StringBlocks(builder.toString(),guessedKeyLength);
    }

    public static List<Object> fillListForPerm(Integer lengthOfAnagram){
        List<Object> objectList = new ArrayList<>();//numbers for permutations
        for (int i = 1; i <= lengthOfAnagram; i++) {
            objectList.add(Integer.valueOf(i));
        }
        return objectList;
    }

    public static String normalizeString(String input){
        String normalizedString = input.toUpperCase();
        normalizedString = Normalizer.normalize(normalizedString, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
                .replaceAll("[^A-Z]", "");
        return normalizedString;
    }
}
