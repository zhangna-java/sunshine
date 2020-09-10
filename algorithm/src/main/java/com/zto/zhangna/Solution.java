package com.zto.zhangna;


/**
 * Created by zhangna on 2020/9/10 8:20 下午
 * 二维数组中的查找
 *
 * 示例:
 *
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 *
 * 给定 target = 20，返回 false。
 *
 */
public class Solution {


    public static void main(String[] args){

        int[][] matrix = {{2,3,5},{34,5,6},{44,66,77}} ;
        int target =2;
        boolean numberIn2DArray = findNumberIn2DArray(matrix, target);
        System.out.println(numberIn2DArray);
    }

    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        boolean rs =false;

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j] == target){
                    rs = true;
                }
            }
        }
        return rs;
    }

}
