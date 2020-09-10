package com.zto.zhangna;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangna on 2020/9/10 9:25 下午
 *
 * 找出数组中重复的数字。
 *
 *
 * 在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。
 *
 * 示例 1：
 *
 * 输入：
 * [2, 3, 1, 0, 2, 5, 3]
 * 输出：2 或 3
 */
public class Solution2 {
    public static int findRepeatNumber(int[] nums) {
//        Arrays.sort(nums);
        Map<Integer,Integer> map = new HashMap();
        int rs= -100;
        for(int i=0;i<=nums.length;i++){
            if(null == map.get(nums[i])){
                map.put(nums[i],i);
            }else{
                rs= nums[i];
                break;
            }
        }
        return rs;
    }

    public static void main(String[] args){
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int rs= findRepeatNumber(nums);
        System.out.println(rs);

    }
}
