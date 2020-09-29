package sk.ksif.zadanie.data_types.converter;

import sk.ksif.zadanie.data_types.*;

public class Converter {

    public static StringBlocks convertMatrixToStringBlock(Matrix matrix){
        return new StringBlocks(matrix.toString(),matrix.getCols());
    }

    public static Matrix convertStringBlockToMatrix(StringBlocks stringBlocks){
        return new Matrix(stringBlocks.toString(), stringBlocks.getBlockSize());
    }
}
