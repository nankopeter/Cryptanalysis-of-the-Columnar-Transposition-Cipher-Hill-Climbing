package sk.ksif.zadanie.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
//remeber that directory with spaces throw exception
public class ReaderFromFile {

    public static Map<String,Integer> getQuadgramsMap(){
        URL f = ReaderFromFile.class.getClassLoader().getResource("sk/ksif/zadanie/dictionary/english/ngrams/english_quadgrams.txt");
        Map<String,Integer> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f.getFile()));
            String st;
            while ((st = br.readLine()) != null){
                String quadgram = st.substring(0,4);
                Integer count = Integer.valueOf(st.substring(5,st.length()));
                map.put(quadgram,count);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String,Integer> getTrigramsMap(){
        URL f = ReaderFromFile.class.getClassLoader().getResource("sk/ksif/zadanie/dictionary/english/ngrams/trigram.txt");
        Map<String,Integer> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f.getFile()));
            String st;
            while ((st = br.readLine()) != null){
                String trigram = st.substring(0,3);
                Integer count = Integer.valueOf(st.substring(4,st.length()));
                map.put(trigram,count);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        Long count = 0L;
        for(String s : map.keySet()){
            count += map.get(s);
        }
        return map;
    }

    public static Map<String,Integer> getBigramsMap(){
        URL f = ReaderFromFile.class.getClassLoader().getResource("sk/ksif/zadanie/dictionary/english/ngrams/bigrams.txt");
        Map<String,Integer> map = new HashMap<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f.getFile()));
            String st;
            while ((st = br.readLine()) != null){
                String trigram = st.substring(0,2);
                Integer count = Integer.valueOf(st.substring(3,st.length()));
                map.put(trigram,count);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        Long count = 0L;
        for(String s : map.keySet()){
            count += map.get(s);
        }
        return map;
    }
}
