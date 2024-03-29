package com.DSA.Sort;

class MissingNumber {
    public static void main(String[] args) {
        int[] num = {0,2,1,5,4};
        int mn=missingNumber(num);
        System.out.println(mn);
    }
    public static int missingNumber(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correct = nums[i];
            if (nums[i]<nums.length && nums[i] != nums[correct])
                swap(nums, i, correct);
            else
                i++;
        }
        for(int index=0; index< nums.length; index++){
            if(index != nums[index])
                return index;
        }
        return nums.length;
    }
    static void swap(int[] arr, int first, int second){
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}