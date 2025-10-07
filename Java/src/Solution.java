import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    List<List<Integer>> resultList;

    public List<List<Integer>> levelOrder(TreeNode root) {
        resultList = new ArrayList<>();
        levelRun(root,1);
        return resultList;
    }

    private void levelRun(TreeNode root, int level) {
        if (root == null) return;
        if (resultList.size() < level) {
            resultList.add(new ArrayList<>());
        }
        resultList.get(level - 1).add(root.val);
        levelRun(root.left,level+1);
        levelRun(root.right,level+1);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode t = head;
        while (list1 != null && list2 != null) {
            if (list1.val > list2.val) {
                t.next = list2;
                t = list2;
                list2 = list2.next;
            } else {
                t.next = list1;
                t = list1;
                list1 = list1.next;
            }
        }
        t.next = list1== null?list2:list1;
        return head.next;
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() <= 1) return s;
        String result = "";
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i; j < s.length(); j++) {
                String str = s.substring(i, j+1);
                StringBuilder builder = new StringBuilder(str);
                StringBuilder reverse = builder.reverse();
                if (str.equals(reverse.toString()) && str.length() > result.length()) {
                    result = str;
                }
            }
        }
        return result;
    }
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
        String babad = longestPalindrome("bb");
        System.out.println(babad);

    }
}