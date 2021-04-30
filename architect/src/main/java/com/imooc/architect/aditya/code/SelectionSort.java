package com.imooc.architect.aditya.code;

import java.util.Arrays;

/**
 * 选择排序-遍历列表，逐个筛选元素
 * @author zhangyoubao
 * @version 2021/4/14
 */
public class SelectionSort {

    public static int[] sort(int[] array, boolean desc) {

        for (int i = 0; i < array.length - 1; i++) {
            int k = i;
            for (int j = i + 1; j < array.length; j++) {
                if (desc) {
                    // 降序，选最大值
                    if (array[j] > array[k]) {
                        k = j;
                    }
                } else {
                    // 升序，选最小值
                    if (array[j] < array[k]) {
                        k = j;
                    }
                }
            }
            if (k != i) {
                int tmp = array[i];
                array[i] = array[k];
                array[k] = tmp;
            }
        }

        return array;
    }

    public static void main(String[] args) {
        int[] array = {5, 3, 6, 2, 10};
        System.out.println(Arrays.toString(sort(array, true)));
    }
}
