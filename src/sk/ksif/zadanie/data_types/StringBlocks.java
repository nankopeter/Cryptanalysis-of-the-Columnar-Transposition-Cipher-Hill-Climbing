package sk.ksif.zadanie.data_types;

import sk.ksif.zadanie.Main;

import java.util.ArrayList;
import java.util.List;

public class StringBlocks {
    private int blockSize;
    private List<String> blocks;

    public StringBlocks(String input, int blockSize){
        this.blockSize = blockSize;
        if((input.length() % blockSize) != 0){
            this.blocks = new ArrayList<>();
            //parse input to strings of same length
            int lastIndex = (input.length() % blockSize);//needed for indexOutOfBound
            int beginIndex = 0;
            int endIndex = blockSize;
            while(endIndex <= input.length()){
                this.blocks.add(input.substring(beginIndex, endIndex));
                beginIndex += blockSize;
                endIndex += blockSize;
                if(endIndex > input.length()){
                    StringBuilder builder = new StringBuilder();
                    builder.append(input.substring(beginIndex, beginIndex + lastIndex));
                    for(int i = beginIndex + lastIndex ; i < endIndex;i++){
                        builder.append(" ");
                    }
                    this.blocks.add(builder.toString());
                    break;
                }
            }
        }else{
            this.blocks = new ArrayList<>();
            //parse input to strings of same length
            int beginIndex = 0;
            int endIndex = blockSize;
            while(endIndex <= input.length()){
                this.blocks.add(input.substring(beginIndex, endIndex));
                beginIndex += blockSize;
                endIndex += blockSize;
            }
        }
    }

    public int getBlockSize() {
        return blockSize;
    }

    public List<String> getBlocks() {
        return blocks;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for(int i = 0 ;i < blocks.size();i++){
            if(i == blocks.size()-1){
                builder.append(Main.normalizeString(blocks.get(i)));
            }else
            {
                builder.append(blocks.get(i));
            }
        }
        return builder.toString();
    }
}
