package com.zto.zhangna;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangna on 2020/9/10 9:25 下午
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
