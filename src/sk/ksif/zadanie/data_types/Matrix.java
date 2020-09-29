package sk.ksif.zadanie.data_types;

public class Matrix {

    private int rows;// count of blocks
    private int cols;// block size
    private char[][] matrix;

    public Matrix(String input , int blockSize){
        //inicializacia
        this.cols = blockSize;
        if((input.length() % blockSize) == 0){//
            this.rows = (input.length() / blockSize);
        }else{
            this.rows = (input.length() / blockSize) + 1;
        }
        this.matrix = new char[rows][cols];
        //vyplnenie
        char[] inputArray = input.toCharArray();
        int counter = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                this.matrix[i][j] = inputArray[counter];
                counter++;
                if(input.length() == counter){
                    break;
                }
            }
        }
    }

    @Deprecated //zastarale nepouzivaj
    public Matrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        matrix = new char[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                matrix[i][j] = 0;
            }
        }
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public char[][] getMatrix() {
        return matrix;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                builder.append(matrix[i][j]);
            }
        }
        return builder.toString();
    }

    public char getMatrixChar(int row, int col){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(i == row && j == col) {
                    return matrix[i][j];
                }
            }
        }
        return ' ';
    }
}
