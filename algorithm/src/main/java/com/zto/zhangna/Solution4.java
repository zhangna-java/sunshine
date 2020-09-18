package com.zto.zhangna;

import java.util.Stack;

/**
 * Created by zhangna on 2020/9/11 11:50 上午
 *  输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 */
public class Solution4 {



    public int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode tmp = head;

        while (tmp != null){
            stack.push(tmp);
            tmp = tmp.next;
        }

        int size = stack.size();
        
        int[] print = new int[size];
        for (int i = 0; i < size; i++) {
            print[i] = stack.pop().val;
        }

        return print;


    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
        }
    }

}
