package RecusriveBacktracking;

import java.util.*;

public class Permutations {
    public static void main(String[] args) {
        System.out.println(permutations("","abc"));
        System.out.println(permutations("", "abbc"));
        System.out.println(permute(new int[] {1,2,3}));     // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]]
        System.out.println(permute2(new int[] {1,2,2,3}));  // [[1, 2, 2, 3], [1, 2, 3, 2], [1, 3, 2, 2], [2, 1, 2, 3], [2, 1, 3, 2], [2, 2, 1, 3], [2, 2, 3, 1], [2, 3, 1, 2], [2, 3, 2, 1], [3, 1, 2, 2], [3, 2, 1, 2], [3, 2, 2, 1]]
    }

    public static List<String> permutations(String p, String up) {
        if(up.isEmpty()) {
            List<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        char ch = up.charAt(0);
        Set<String> ans = new HashSet<>();

        for(int i = 0; i <= p.length(); i++) {
            String prefix = p.substring(0, i);
            String suffix = p.substring(i);
            ans.addAll(permutations(prefix + ch + suffix, up.substring(1)));
        }
        return new ArrayList<>(ans);
    }




    public static List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }
    private static void backtrack(List<List<Integer>> res, List<Integer> perm, int[] nums, boolean[] pick) {
        if (perm.size() == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!pick[i]) {
                perm.add(nums[i]);
                pick[i] = true;
                backtrack(res, perm, nums, pick);
                perm.removeLast();
                pick[i] = false;
            }
        }
    }



    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack2(res, new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }
    private static void backtrack2(List<List<Integer>> res, List<Integer> perm, int[] nums, boolean[] pick) {
        if (perm.size() == nums.length) {
            res.add(new ArrayList<>(perm));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (pick[i] || (i > 0 && nums[i] == nums[i - 1] && !pick[i - 1]))
                continue;
            perm.add(nums[i]);
            pick[i] = true;
            backtrack2(res, perm, nums, pick);
            perm.removeLast();
            pick[i] = false;
        }
    }
}
