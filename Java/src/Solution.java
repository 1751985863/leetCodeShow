import java.util.HashMap;
import java.util.PriorityQueue;

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

    public static void main(String[] args) {
        int abba = lengthOfLongestSubstring("abba");
    }
}