package com.revature;

public class LinearSearch {
    public static void main(String[] args) {


        //Array of ints to search for


        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        if((linearSearch(arr, 5)) !=-1){
            System.out.println("Number found Array index is: "+linearSearch(arr,5));;
    }
        if((linearSearch(arr, 50)) !=-1){
            System.out.println("Number found Array index is: "+linearSearch(arr,50));;
        }else {
            System.out.println("Number not found!");
        }


    //Linear Search Algorithm
    }
    public static int linearSearch(int[] arr,int target) {

        for (int i = 0; i < arr.length; i++) {
        System.out.println("We are on index: " + i);

        if (arr[i] == target) {
            return i;
        }
    }

return -1;
    }
}

