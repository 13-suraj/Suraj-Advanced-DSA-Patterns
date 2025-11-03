package RecusriveBacktracking;

import java.util.*;

public class SubsetsAndSubsequence {
    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));  // [[1, 2, 3], [1, 2], [1, 3], [1], [2, 3], [2], [3], []]
        System.out.println();
        System.out.println(subsets2(new int[]{1, 2, 2}));  // [[], [1], [1, 2], [1, 2, 2], [1], [1, 2]]
        System.out.println();
        System.out.println(subsequence("abc"));  // [abc, ab, ac, a, bc, b, ,c]
        System.out.println();
        System.out.println(subsequence2("aab"));  // [aa, , ab, a, b, aab]
    }


    // Subsets Function if Array doesn't contain Duplicates
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        backtrack(nums, 0, subset, res);
        return res;
    }
    private static void backtrack(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
        if (i >= nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }
        subset.add(nums[i]);
        backtrack(nums, i + 1, subset, res);
        subset.removeLast();
        backtrack(nums, i + 1, subset, res);
    }


    // Subsets Function if Array contains Duplicates
    public static List<List<Integer>> subsets2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        backtrack2(nums, 0, subset, res);
        return res;
    }
    private static void backtrack2(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
        res.add(new ArrayList<>(subset));
        for(int j = i; j < nums.length; j++) {
            if (j > i && nums[j] == nums[j - 1]) continue;
            subset.add(nums[i]);
            backtrack2(nums, j + 1, subset, res);
            subset.removeLast();
        }
    }


    // Subsequence Function if String doesn't contain Duplicates
    public static List<String> subsequence(String str) {
        List<String> res = new ArrayList<>();
        backtrack(0, "", str, res);
        return res;
    }
    private static void backtrack(int i, String p, String up, List<String> res) {
        if (i >= up.length()) {
            res.add(p);
            return;
        }
        backtrack(i + 1, p + up.charAt(i), up, res);
        backtrack(i + 1, p, up, res);
    }


    // Subsequence Function if String Contains Duplicates
    public static List<String> subsequence2(String str) {
        Set<String> res = new HashSet<>();
        backtrack2(0, "", str, res);
        return new ArrayList<>(res);
    }
    private static void backtrack2(int i, String p, String up, Set<String> res) {
        if (i >= up.length()) {
            res.add(p);
            return;
        }
        backtrack2(i + 1, p + up.charAt(i), up, res);
        backtrack2(i + 1, p, up, res);
    }
}
