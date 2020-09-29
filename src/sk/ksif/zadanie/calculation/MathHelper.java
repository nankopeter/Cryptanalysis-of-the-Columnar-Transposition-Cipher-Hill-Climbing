/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sk.ksif.zadanie.calculation;

/**
 * @author xnankop
 */
public class MathHelper {

    public static boolean isPrime(int input) {
        if (input <= 1) { return false; }
        if (input < 4 ) { return true; }
        if (input % 2 == 0 ) { return false; }
        if (input < 9 ) { return true ; }
        if (input % 3 == 0) { return false; }
        for (int i = 5; i <= (int)(Math.sqrt((double)input)); i += 6) {
            if (input % i == 0 ) { return false; }
            if (input % (i + 2) == 0) { return false; }
        }
        return true;
    }

    public static int factorial(int n) {
        if (n == 0) { return 1; }
        return n * factorial(n - 1);
    }

    public static int determinant(int [] [] matrix)
    {
        int x = matrix[0][0] * matrix[1][1] * matrix[2][2];
        int y = matrix[1][0] * matrix[2][1] * matrix[0][2];
        int z = matrix[2][0] * matrix[0][1] * matrix[1][2];

        int a = matrix[0][2] * matrix[1][1] * matrix[2][0];
        int b = matrix[1][2] * matrix[2][1] * matrix[0][0];
        int c = matrix[2][2] * matrix[0][1] * matrix[1][0];

        int result = (x+y+z) - (a+b+c);

        return result;
    }

    public static int[][] multiply(int [][] matrix1, int[][] matrix2)
    {
        int [][]result = new int[matrix1.length][matrix2[0].length];
        for(int i = 0;i < matrix1.length;i++)
        {
            for(int j = 0;j < matrix2[0].length;j++)
            {
                for(int k = 0;k < matrix1[i].length;k++)
                {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return result;
    }
    public static int inverseValue(int value)
    {
        //int result = 0;
        for(int i = 1;i < 27;i++)
        {
            int help = (value * i);
            if(help % 26 == 1)
                return i;
        }
        return 0;
    }
    public static boolean isGcd1(int det)
    {
        if(det%26 == 0 || det%2 == 0 || det%13 == 0)
            return false;
        return true;
    }
    public static int [][] inverse(int [][] matrix)
    {
        int [][] result = new int[3][3];
        int det = determinant(matrix);

        if (det != 0 && isGcd1(det)){
            int a = (matrix[1][1]*matrix[2][2] - matrix[1][2]*matrix[2][1]) ;///det;
            int b = (-(matrix[0][1] * matrix[2][2] - matrix[0][2]*matrix[2][1])) ;///det;
            int c = (matrix[0][1] * matrix[1][2] - matrix[0][2] * matrix[1][1]);///det;
            int d = (-(matrix[1][0]*matrix[2][2] - matrix[1][2]*matrix[2][0]));///det;
            int e = (matrix[0][0] * matrix[2][2] - matrix[0][2]*matrix[2][0]);///det;
            int f= (-(matrix[0][0]*matrix[1][2] - matrix[0][2]*matrix[1][0]));///det;
            int g = (matrix[1][0]*matrix[2][1] - matrix[1][1]*matrix[2][0]);///det;
            int h = (-(matrix[0][0]*matrix[2][1] - matrix[0][1]*matrix[2][0]));///det;
            int i =(matrix[0][0]*matrix[1][1] - matrix[0][1]*matrix[1][0]);///det;
            int l = 0;
            det = det%26;
            if(det<0)
                det+=26;
            int inverseVal = inverseValue(det);
            //System.out.println(inverseVal);
            int []help = new int[]{a,b,c,d,e,f,g,h,i};
            for(int j = 0;j < result.length;j++)
            {
                for(int k = 0;k < result[0].length;k++)
                {
                    help[l] = (help[l] % 26);
                    help[l] = (help[l] * inverseVal) % 26;
                    if(help[l] < 0)
                        help[l]+=26;
                    result[j][k] = help[l];
                    l++;
                }
            }
        }
        return result;
    }
    public static void proove(int [][] inverse, int [][]ZT)
    {
        int result = 0;
        //int j = 0;
        String column = "";
        int [] a = new int[3];
        int [] b = new int[3];
        for(int i = 0;i < inverse[0].length;i++)
        {
            a[i] = inverse[0][i];
        }
//        for(int i = 0;i < ZT[0].length;i++)
//        {
//            b[i] = ZT[0][i];
//        }
        for(int j = 0;j < ZT[0].length;j++)   //ZT
        {
            for(int i = 0;i < 3;i++)
            {
                //System.out.println(a[i]+" "+ZT[i][j]);  // j i
                result+=a[i]*ZT[i][j];
            }
            //System.out.println(result);
            result%=26;
            //System.out.println(result);
            column += (char)(result+'a');
            result = 0;
        }

        System.out.println(column);
    }

    public static double average(Double []p)
    {
        double result = 0;
        for(int i = 0;i < p.length;i++)
        {
            result+=p[i];
        }
        return (result/(p.length));
    }
}
