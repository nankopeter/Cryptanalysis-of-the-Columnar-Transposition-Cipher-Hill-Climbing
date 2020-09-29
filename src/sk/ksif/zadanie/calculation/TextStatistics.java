package sk.ksif.zadanie.calculation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TextStatistics {

    public Map<String, Double> absolute;
    public Map<String, Double> relative;

    public static double indexOFCoincidence(String s){

        int i;
        int N = 0;
        double sum = 0.0;
        double indexOfCoincidence = 0.0;
        s = s.toUpperCase();
        int[] values = new int[26];
        for(i=0; i<26; i++){
            values[i] = 0;
        }
        int ch;
        for(i=0; i<s.length(); i++){
            ch = s.charAt(i)-65;
            if(ch>=0 && ch<26){
                values[ch]++;
                N++;
            }
        }
        for(i=0; i<26; i++){
            ch = values[i];
            sum = sum + (ch * (ch-1));
        }
        indexOfCoincidence = sum/(N*(N-1));
        return indexOfCoincidence;
    }

    public static double indexOfCoincidence(Double[] p, int len) {
        double IC = 0;

        double cons1 = (len * (len - 1));
        double cons = 1 / cons1;
        for (int i = 0; i < p.length; i++) {
            IC += p[i] * (p[i] - 1);
        }
        return IC * cons;
    }

    public static Map<String, Double> readNgram(String txt, int n, boolean relativeFr) {
        Map<String, Double> map = new HashMap<String, Double>();
        int i = txt.length() % n;
        if (i > 0) {
            for (int j = 0; j < (n - i); j++) {
                txt = txt.concat("x");
            }
        }
        ArrayList<String> ngrams = new ArrayList<>();
        for (int j = 0; j < txt.length(); j++) {
            StringBuilder builder = new StringBuilder();
            for (int k = 0; k < n; k++) {
                if(j+k < txt.length()){
                    builder.append(txt.charAt((j + k)));
                }
            }
            if(builder.toString().length() != 0){
                ngrams.add(builder.toString());
            }
        }
        int lengthNgrams = ngrams.size();
        for (String str : ngrams) {
            Double count = new Double(0);
            for (int j = ngrams.indexOf(str); j < lengthNgrams; j++) {
                if (str.equals(ngrams.get(j))) {
                    count++;
                }
            }
            if (relativeFr) {
                if (map.get(str) == null) {
                    map.put(str, count / lengthNgrams);
                }
            } else {
                if (map.get(str) == null) {
                    map.put(str, count);
                }
            }
        }
        return map;
    }

    public static void printStaticticsOfText(String input){
        System.out.println("Absolute frequency of characters:");
        Map<String, Double> absolute= TextStatistics.readNgram(input, 1, false);
        absolute.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
        mapValues(absolute);
        System.out.println("Relative frequency of characters:");
        Map<String, Double> relative = TextStatistics.readNgram(input, 1, true);
        relative.entrySet().forEach(entry->{
            System.out.println(entry.getKey() + " " + entry.getValue());
        });
    }

    public static void printNgram(String txt, int n, boolean relativeFr) {
        Map<String, Double> map = new HashMap<String, Double>();
        int i = txt.length() % n;
        if (i > 0) {
            for (int j = 0; j < (n - i); j++) {
                txt = txt.concat("x");
            }
        }
        ArrayList<String> ngrams = new ArrayList<>();
        for (int j = 0; j < txt.length(); j = j + n) {
            StringBuilder builder = new StringBuilder();
            for (int k = 0; k < n; k++) {
                builder.append(txt.charAt((j + k)));
            }
            ngrams.add(builder.toString());
        }
        int lengthNgrams = ngrams.size();
        for (String str : ngrams) {
            Double count = new Double(0);
            for (int j = ngrams.indexOf(str); j < lengthNgrams; j++) {
                if (str.equals(ngrams.get(j))) {
                    count++;
                }
            }
            if (relativeFr) {
                if (map.get(str) == null) {
                    map.put(str, count / lengthNgrams);
                }
            } else {
                if (map.get(str) == null) {
                    map.put(str, count);
                }
            }
        }
        Double finalValue = new Double(0);
        for (String name : map.keySet()) {
            String key = name.toString();
            Double value = map.get(name);
            finalValue += Double.valueOf(value);
            if(relativeFr){
                System.out.println(key + " " + value*100);
            }else{
                System.out.println(key + " " + value);
            }
        }
        System.out.println("SUM: " + finalValue);
    }

    public static Double[] mapValues(Map<String,Double> absoluteOrRelativeFr)
    {
        Double []result = new Double[absoluteOrRelativeFr.keySet().size()];
        int j = 0;
        for(String i : absoluteOrRelativeFr.keySet())
        {
            result[j] = absoluteOrRelativeFr.get(i);
            j++;
        }
        return result;
    }
}
