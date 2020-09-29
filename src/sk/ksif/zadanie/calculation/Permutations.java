/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.ksif.zadanie.calculation;

import java.util.*;

public class Permutations {

    public static Object[] rndPerm(Object input[]) {
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < (input.length - 1); i++) {
            int j = rnd.nextInt(input.length - i) + i;
            // swap
            Object tmp = input[i];
            input[i] = input[j];
            input[j] = tmp;
        }
        return input;
    }

    public static void randomPerm(Object input[]) {
        Random rnd = new Random(System.currentTimeMillis());
        for (int i = 0; i < (input.length - 1); i++) {
            int j = rnd.nextInt(input.length - i) + i;
            // swap
            Object tmp = input[i];
            input[i] = input[j];
            input[j] = tmp;
        }
    }

    public static Integer[] inverse(Object[] input) {
        List list = Arrays.asList(input.clone());
        Collections.sort(list);

        Integer[] tmp = new Integer[input.length];
        for (int i = 0; i < input.length; i++) {
            tmp[i] = list.indexOf(input[i]) + 1;
            // poradie ako pri vstupe
        }
        return inverse(tmp);
    }

    public static Integer[] inverse(Integer[] input) {
        Integer[] inv = new Integer[input.length];
        for (int i = 0; i < input.length; i++) {
            inv[input[i] - 1] = i + 1;
        }
        return inv;
    }

    public static Character[] inverse(Character[] input) {
        Character[] inv = new Character[input.length];
        for (int i = 0; i < input.length; i++) {
            inv[input[i] - 'a'] = (char) (i + 'a');
        }
        return inv;
    }

    public static List<?> allPerm(Object[] input){//reference
        List<Object[]> retVal = new ArrayList(MathHelper.factorial(input.length));
        allPerm(0, input, retVal);

        return retVal;
    }

    private static void allPerm(int fixed, Object input[], List output) {
        Object in[] = input.clone();
        if (fixed == input.length) {
            output.add(input);
        } else {
            for (int i = fixed; i < input.length; i++) {
                // swap
                Object tmp = in[i];
                in[i] = in[fixed];
                in[fixed] = tmp;
                // recursion
                allPerm(fixed + 1, in, output);
            }
        }
    }

    /*
     * vstup je perm objektov, ktore je mozne zoradit a neopakuju sa
     */
    public static int lexicographicalOrder(Object mc[]) {
        List tmp = new ArrayList<>(Arrays.asList(mc));
        Collections.sort(tmp);// zoradit

        int ti[] = new int[mc.length]; // poradie prvku mc[i] v tmp
        for (int j = 0; j < mc.length; j++) {
            int idx = tmp.indexOf(mc[j]);
            ti[j] = idx;
            tmp.remove(idx); // poradie ďalšieho - budeme uvažovať už len zvyšné prvky
        }

        int retVal = 0;
        for (int j = 0, k = ti.length - 1; j < ti.length - 1; j++, k--) {
            retVal += ti[j] * MathHelper.factorial(k);
        }
        return retVal + 1;
    }

    /*
     * vstup je poradove cislo
     * a mnozina objektov, nemusi byt usporiadana
     */
    public static Object[] nthPerm(int n, Object []m) {

        List<Integer> koeficienty = new ArrayList<>();
        int i=1;
        koeficienty.add(n%i);
        n = n/i;
        // postupne delim a ukladam si zvysok ako koeficient
        while(n > 0){
            i++;
            koeficienty.add(n%i);
            n = n/i;
        }
        Collections.reverse(koeficienty); // otocim aby to islo od posledneho koeficientu
        System.out.println("koeficienty: "+koeficienty.toString());
        // teraz tu permutaciu z fakt. rozkladu a mnoziny m
        List prvky = new ArrayList<>(Arrays.asList(m.clone()));
        Collections.sort(prvky); // zoradime
        Object retVal[]= new Object[m.length];
        for(int c=0; c < koeficienty.size(); c++){
            // postupne
            int ai = koeficienty.get(c); // koeficient
            Object el = prvky.get(ai); // zadava index pri usp. m
            prvky.remove(el); // odstranim z nepouzitych
            retVal[c] = el;
        }
        return retVal;
    }

    /*
     * vstup je perm z M={1,..,n}
     */
    public static List<String> cycles(Integer[] perm) {

        List<String> retVal = new ArrayList<>();
        TreeSet<Integer> unused = new TreeSet<>(Arrays.asList(perm)); // zoradime

        while (unused.isEmpty() == false) { // postupne po jednom
            StringBuilder c = new StringBuilder();

            int startPoint = unused.first(); // zoberiem prvy nepouzity
            c.append(startPoint).append(","); // ulozim
            unused.remove(startPoint); // prec zo zoznamu
            int next = perm[startPoint - 1]; // na co sa mapuje je next
            while (next != startPoint) { // opakujem kym nenatrafim na povodny
                c.append(next).append(","); // ulozim dalsi
                unused.remove(next); // prec zo zoznamu
                next = perm[next - 1]; // na co sa mapuje je next

            };
            retVal.add(c.substring(0, c.length() - 1)); // -1 kvoli ciarke na konci
        }

        return retVal;
    }

    /*
     * vstup je perm z M={1,..,n}
     */
    public static int orderPermutation(Integer perm[]) {
        // rozlozim na cykly
        List<String> cycles = Permutations.cycles(perm);
        List<Integer> ls = new ArrayList<>();
        int max = 0;
        int mul = 1;
        // ulozim si dlzky jednotlivych cyklov
        for (String l : cycles) {
            int len = l.split(",").length; // kvoli ,
            ls.add(len);
            mul *= len;
            if (len > max) {
                max = len;
            }
        }

        // lcm
        int lcm = 0;
        for (int i = max; i <= mul; i++) {
            boolean flag = true;
            for (Integer l : ls) {
                if (i % l != 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                lcm = i;
                break;
            }
        }
        return lcm;
    }
}