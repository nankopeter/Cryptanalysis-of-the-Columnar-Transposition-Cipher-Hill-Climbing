package sk.ksif.zadanie.calculation;

import sk.ksif.zadanie.data_types.StringBlocks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Decryption {

    public static ArrayList<String> Decrypt(StringBlocks stringBlocks, List<Object[]> permutations) {
        ArrayList<String> allPermutedOutput = new ArrayList<String>();
        for (Object[] permutation : permutations) {
            StringBuilder builder = new StringBuilder();
            for (String block : stringBlocks.getBlocks()) {
                if(block.length() == stringBlocks.getBlockSize()){
                    HashMap<Integer, Character> map = getMap(block);
                    builder.append(getDecryptedString(map, permutation));
                }else{
                    builder.append(block);
                }
            }
            allPermutedOutput.add(builder.toString());
        }
        return allPermutedOutput;
    }

    public static String getDecryptedString(HashMap<Integer, Character> map, Object[] permutation) {
        StringBuilder builder = new StringBuilder();
        for(int j = 0; j < permutation.length; j++) {
                builder.append(map.get(permutation[j]));
        }
        return builder.toString();
    }

    public static HashMap<Integer, Character> getMap(String str){
        HashMap<Integer, Character> map = new HashMap<>();
        char tmpTextArray[] = str.toCharArray();
        for(int i = 0; i < str.length(); i++){
            map.put(i+1, tmpTextArray[i]);
        }
        return map;
    }
}
