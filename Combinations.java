package RecusriveBacktracking;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combinations{
    public static void main(String[] args) {
        int[] num = {1, 3, 5};
        System.out.println(combinations(3, 2));                     // [[1, 1], [1, 2], [1, 3], [2, 2], [2, 3], [3, 3]]
        System.out.println(combinationsWithUnique(3, 2));   // [[1, 2], [1, 3], [2, 3]]
        System.out.println(combinationSum(num, 9));                 // [[1, 1, 1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 3], [1, 1, 1, 1, 5], [1, 1, 1, 3, 3], [1, 3, 5], [3, 3, 3]]
        System.out.println(combinationSumWithUnique(num, 9));       // [[1, 3, 5]]

        int[] num2 = {1, 1, 2, 3, 3, 4};
        System.out.println(combinationSumWithDuplicates(num2, 7));  // [[1, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 2], [1, 1, 1, 1, 3], [1, 1, 1, 2, 2], [1, 1, 1, 4], [1, 1, 2, 3], [1, 2, 2, 2], [1, 2, 4], [1, 3, 3], [2, 2, 3], [3, 4]]
        System.out.println(combinationSumWithDuplicatesAndUnique(num2, 7));  // [[1, 1, 2, 3], [1, 2, 4], [1, 3, 3], [3, 4]]
    }



    public static List<List<Integer>> combinations(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(1, n, k, new ArrayList<>(), res);
        return res;
    }
    public static void backtrack(int start, int n, int k, List<Integer> comb, List<List<Integer>> res) {
        if (comb.size() == k) {
            res.add(new ArrayList<>(comb));
            return;
        }

        for (int i = start; i <= n; i++) {
            comb.add(i);
            backtrack(i, n, k, comb, res);
            comb.removeLast();
        }
    }



    public static List<List<Integer>> combinationsWithUnique(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack2(1, n, k, new ArrayList<>(), res);
        return res;
    }
    public static void backtrack2(int start, int n, int k, List<Integer> comb, List<List<Integer>> res) {
        if (comb.size() == k) {
            res.add(new ArrayList<>(comb));
            return;
        }

        for (int i = start; i <= n; i++) {
            comb.add(i);
            backtrack2(i + 1, n, k, comb, res);
            comb.removeLast();
        }
    }



    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(0,candidates, target, new ArrayList<>(), res);
        return res;
    }
    public static void backtrack(int idx, int[] candidates, int target, List<Integer> ds, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(ds));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (target - candidates[i] < 0) return;
            ds.add(candidates[i]);
            backtrack(i, candidates, target - candidates[i], ds, res);
            ds.removeLast();
        }
    }



    public static List<List<Integer>> combinationSumWithUnique(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack2(0,candidates, target, new ArrayList<>(), res);
        return res;
    }
    public static void backtrack2(int idx, int[] candidates, int target, List<Integer> ds, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(ds));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (target - candidates[i] < 0) return;
            ds.add(candidates[i]);
            backtrack2(i + 1, candidates, target - candidates[i], ds, res);
            ds.removeLast();
        }
    }



    public static List<List<Integer>> combinationSumWithDuplicates(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack3(0, candidates, target, new ArrayList<>(), res);
        return res;
    }
    public static void backtrack3(int idx, int[] candidates, int target, List<Integer> ds, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(ds));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1]) continue;
            if (target - candidates[i] < 0) break;

            ds.add(candidates[i]);
            backtrack3(i, candidates, target - candidates[i], ds, res);
            ds.removeLast();
        }
    }



    public static List<List<Integer>> combinationSumWithDuplicatesAndUnique(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack4(0, candidates, target, new ArrayList<>(), res);
        return res;
    }
    public static void backtrack4(int idx, int[] candidates, int target, List<Integer> ds, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(ds));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1]) continue;
            if (target - candidates[i] < 0) break;

            ds.add(candidates[i]);
            backtrack4(i + 1, candidates, target - candidates[i], ds, res);
            ds.removeLast();
        }
    }
}
