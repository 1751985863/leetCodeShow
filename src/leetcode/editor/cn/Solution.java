package leetcode.editor.cn;

import java.util.HashMap;

class Solution {
    public static int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        HashMap<Character,Integer>  map = new HashMap<>();
        int left = 0, right = 0;
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (map.containsKey(rightChar) && map.get(rightChar) >= left) { // map.get(rightChar) >= left 是避免 abba left指针向回走的问题
                left = map.get(rightChar) + 1;
            }
            map.put(rightChar,right);
            maxLen = Math.max(maxLen,right - left + 1);
            right++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int abba = lengthOfLongestSubstring("abba");
    }
}