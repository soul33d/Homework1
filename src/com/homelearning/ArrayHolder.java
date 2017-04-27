package com.homelearning;

import java.util.Arrays;

public class ArrayHolder {
    private int [] numbers;

    public ArrayHolder(int length) {
        numbers = new int[length];
    }

    public void sort(){
        numbers = mergeSort(numbers);
    }

    private int[] mergeSort(int[] intArray) {
        if (intArray.length < 2) return intArray;
        int middle = intArray.length / 2;
        int[] left = Arrays.copyOfRange(intArray, 0, middle);
        int[] right = Arrays.copyOfRange(intArray, middle, intArray.length);
        return merge(mergeSort(left), mergeSort(right));
    }

    private int[] merge(int[] left, int[] right){
        int leftIndex = 0, rightIndex = 0;
        int[] result = new int[left.length + right.length];
        for (int i = 0; i < result.length; i++) {
            if (leftIndex < left.length && rightIndex < right.length){
                if (left[leftIndex] <= right[rightIndex]) result[i] = left[leftIndex++];
                else if (rightIndex < right.length) result[i] = right[rightIndex++];
            } else if (leftIndex < left.length) result[i] = left[leftIndex++];
            else result[i] = right[rightIndex++];
        }
        return result;
    }

    public int findNumberIndex(int value){
        int result = -1;
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i] == value) result = i;
        }
        return result;
    }

    public int getSize(){
        return numbers.length;
    }

    public void setElement(int index, int value){
        numbers[index] = value;
    }

    public int[] getNumbers() {
        return numbers;
    }

    @Override
    public String toString() {
        return Arrays.toString(numbers);
    }
}