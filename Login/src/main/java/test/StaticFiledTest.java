package test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class StaticFiledTest {
	private final String name;// final需通过构造器赋值
	private static int count = 1;

	public StaticFiledTest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public static void main(String[] args) throws ClassNotFoundException {
		char[][] nums1 = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
		Boolean result = isValidSudoku(nums1);
		System.out.println(result);
	}
	
	 public static boolean check(char[] temp){
	        Map<Character,Integer> map = new HashMap<Character,Integer>();
	        for(int i=0;i<9;i++){
	            if(temp[i]!='.'){
	                if(map.containsKey(Character.valueOf(temp[i]))){
	                    return false;
	                }else{
	                    map.put(Character.valueOf(temp[i]),i);
	                }
	            }
	        }
	        return true;
	    }
	    public static boolean isValidSudoku(char[][] board) {
	        //行检查
	        for(int i=0;i<9;i++){
	            if(!check(board[i])){
	                return false;
	            }
	        }
	        //列检查
	        char[] col = new char[9];
	        for(int j = 0;j<9;j++){
	            for(int k=0;k<9;k++){
	                col[k]=board[k][j];
	            }
	            if(!check(col)){
	                return false;
	            }
	        }
	        //小九宫格检查
	        char[] square = new char[9];
	        for(int m=1;m<10;m++){
	        	int n=0;
	            for(int j = 3*(m-1);j<3*(m-1)+3;j++){
	                for(int k=(m-1)%3;k<(m-1)%3+3;k++){
	                    square[n]=board[j][k];
	                    if(n!=8){
	                    	n++;
	                    }
	                }
	            }
	            if(!check(square)){
	            	return false;
	            }
	        }
	        return true;
	    }
}
