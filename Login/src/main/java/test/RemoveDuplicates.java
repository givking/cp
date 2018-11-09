package test;

import java.util.HashMap;
import java.util.Map;

public class RemoveDuplicates {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		removeDuplicates();
		
	}
	public static int removeDuplicates() {
		int[] nums={1,1,1,2,2,2,3,4,4,5};
		 int i =0;
	        int j =1;
	        int size = 1;
	        int temp = 0;
	        while(i<nums.length){
	            temp=i;
	            while(j<nums.length){
	                if(nums[temp]==nums[j]){
	                    i=j+1;
	                }else{
	                    nums[size]=nums[j];
	                    size++;
	                }
	                j++;
	            }
	        }
	        return size;
	    }
    }
