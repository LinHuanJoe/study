package com.lq.algorithm;

import java.util.List;

/**
 * @program:
 * @Date: 2019/8/5 0005 10:44
 * @Author: lin huan
 * @Description: LeetCode双指针
 */
public class DoublePointer {

    /**
     * 在有序数组中找出两个数，使它们的和为 target。
     *
     * @param area
     * @param target
     * @return 结果数字的位置
     */
    public static int[] twoSumIndex(int[] area, int target) {
        int i = 0, j = area.length - 1;
        int[] result = new int[2];
        while (i < j) {
            int sum = area[i] + area[j];
            if (sum == target) {
                result[0] = i;
                result[1] = j;
                return result;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return null;
    }

    /**
     * 判断一个数是否为两个数的平方和。
     *
     * @param in
     * @return
     */
    public static boolean judgeSquareSum(int in) {
        int j = (int) Math.sqrt(in);
        int i = 0;
        while (i <= j) {
            int sum = i * i + j * j;
            if (sum == in) {
                return true;
            } else if (sum < in) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    /**
     * 可以删除一个字符，判断是否能构成回文字符串。
     *
     * @param in
     * @return
     */
    public static boolean validPalindrome(String in) {
        for (int i = 0, j = in.length() - 1; i < j; i++, j--) {
            if (in.charAt(i) != in.charAt(j)) {
                return isPalindrome(in, ++i, j) || isPalindrome(in, i, --j);
            }
        }
        return true;
    }

    private static boolean isPalindrome(String s, int i, int j) {
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }

    /**
     * 归并两个有序数列
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     * @return
     */
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = new int[m + n];
        int index1 = m - 1;
        int index2 = n - 1;
        int mergeIndex = m + n - 1;
        while (index1 >= 0 || index2 >= 0) {
            if (nums1[index1] > nums2[index2]) {
                result[mergeIndex--] = nums1[index1--];
            } else if (nums1[index1] < nums2[index2]) {
                result[mergeIndex--] = nums2[index2--];
            } else if (index1 < 0) {
                result[mergeIndex--] = nums2[index2--];
            } else {
                result[mergeIndex--] = nums1[index1--];
            }
        }
        return result;
    }

    private class ListNode {
        public ListNode next;
        private String val;

        ListNode(ListNode next, String val) {
            this.next = next;
            this.val = val;
        }
    }

    /**
     * 判断链表是否存在环
     *
     * @param head
     * @return
     */
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode step1 = head;
        ListNode step2 = head.next;
        while (step1 != null && step2 != null && step2.next != null) {
            if (step1 == step2) {
                return true;
            }
            step1 = step1.next;
            step2 = step2.next.next;
        }
        return false;
    }

    /**
     * 删除 s 中的一些字符，使得它构成字符串列表 d 中的一个字符串，找出能构成的最长字符串。
     * 如果有多个相同长度的结果，返回字典序的最小字符串。
     *
     * @param s
     * @param words
     * @return
     */
    public static String findLongestWord(String s, List<String> words) {
        String longestWord = "";
        for (String word : words) {
            if (longestWord.length() > word.length() || (longestWord.length() == word.length() && longestWord.compareTo(word) < 0)) {
                continue;
            }
            if (isSubstr(s, word)) {
                longestWord = word;
            }
        }
        return longestWord;
    }

    private static boolean isSubstr(String s, String word) {
        int i = 0, j = 0;
        while (i < s.length() && j < word.length()) {
            if (s.charAt(i) == word.charAt(j)) {
                j++;
            }
            i++;
        }
        return j == word.length();
    }
}
