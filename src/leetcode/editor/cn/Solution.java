package leetcode.editor.cn;

import java.util.*;

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

    public ListNode reverseList(ListNode head) {
        ListNode empty = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = empty;
            empty = head;
            head = next;
        }
        return empty;
    }

    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(nums[i]);
        }
        for (int i = k; i < nums.length; i++) {
            if (priorityQueue.peek() <= nums[i]) {
                priorityQueue.poll();
                priorityQueue.offer(nums[i]);
            }
        }
        return priorityQueue.peek();
    }

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i+1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                    while (left < nums.length - 1 && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > i && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }
        return result;
    }

    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int f = 0;
        for (int x : nums) {
            f = Math.max(f,0) + x;
            ans = Math.max(ans,f);
        }
        return ans;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{0, 0, 0});
    }
}