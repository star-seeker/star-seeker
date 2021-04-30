package com.imooc.architect.aditya.code;

/**
 * 二分查找
 * @author zhangyoubao
 * @version 2021/4/14
 */
public class BinarySearch {

    public static int binarySearch(int[] arr, int ele) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == ele) {
                return mid;
            }
            if (ele < arr[mid]) {
                high = mid - 1;
            }
            if (ele > arr[mid]) {
                low = mid + 1;
            }
        }
        return -1;  // -1表示不存在
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 5, 7, 9};
        System.out.println(binarySearch(array, 3));
        System.out.println(binarySearch(array, -1));
    }
}
