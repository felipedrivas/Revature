package com.revature;

public class BinarySearch {

    public static void main(String[] args) {

        //Array of ints to search through
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        int target = 9;
        int result = binarySearch(arr, target);
        if (result !=-1){
            System.out.println("Target "+target+" found at index: "+ result);
        }

    }


    /*Binary Search

    * A more efficient but more complex algorithm (compared to linear search)
        -BINARY SEARCH ONLY WORKS ON SORTED DATA! So we would have sort unsorted data (see sorting algos)
    * It works by comparing the target to the middle element in the data
    * If the target is less than the middle element, we only search the left half of the data
    * If the target is greater than the middle element, we only search the right half of the data
    * We repeat this process on the focused half of the data until we find the target */
    public static int binarySearch(int[] arr, int target){

        //start with a counter to determine the first and last index
        int left = 0;
        int right = arr.length - 1;

        //while the left pointer <= the right pointer...
        while(left <= right){

            //find the middle index
            int mid = left + (right - left) / 2; //ex: 0 + (9-0)/2 = 4.5, but int rounds down to 4

            System.out.println("Middle index is: " + mid);

            //if the middle index is the target, return it
            if(arr[mid] == target){
                return mid;
            }

            System.out.println("Data splits here");

            if(arr[mid]<target){
                left = mid +1;
            }else{
                right = mid -1;
            }

        } //end of while loop

        //if the while loop ends before returning, the element wasn't found
        return -1;
    }

}
