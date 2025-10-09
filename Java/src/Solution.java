import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;

class Solution {

    static void main() {
        char[][] grid = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '1'},
        };
        int nummed = numIslands(grid);
        System.out.println(nummed);
    }
    public static int numIslands(char[][] grid) {
        int sum = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') sum++;
                cleanIslands(grid,i,j);
            }
        }
        return sum;
    }

    private static void cleanIslands(char[][] grid, int i, int j) {
        int r = grid.length;
        int c = grid[0].length;
        if (i >= r  || j >= c || i < 0 || j < 0) return;
        if (grid[i][j] == '0') return;
        // 右边走
        grid[i][j] = '0';
        cleanIslands(grid,i,j+1);
        cleanIslands(grid,i+1,j);
        cleanIslands(grid,i,j - 1);
        cleanIslands(grid,i - 1,j);
    }

    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                List<Integer> list = new ArrayList<>();
                map.put(nums[i],list);
            }
            map.get(nums[i]).add(i);
        }
        for (int i = 0; i < nums.length; i++) {
            int result = target - nums[i];
            if (map.containsKey(result)) {
                if (nums[i] != result) {
                    return new int[]{i,map.get(result).get(0)};
                } else {
                    if (map.get(result).size() >= 2) {
                        return new int[]{map.get(result).get(0),map.get(result).get(1)};
                    }
                }
            }
        }
        return new int[]{-1,-1};
    }

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

}