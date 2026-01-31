package DP;

import java.util.Arrays;

public class LCS {
    // Memoization (Top-Down)
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) Arrays.fill(dp[i], -1);
        return lcs(text1, text2, m, n, dp);
    }
    private int lcs(String t1, String t2, int i, int j, int[][] dp) {
        // Base Case (index out of bound)
        if(i == 0 || j == 0) return 0;

        if(dp[i][j] != -1) return dp[i][j];

        // Matched
        if(t1.charAt(i - 1) == t2.charAt(j - 1)) return dp[i][j] = 1 + lcs(t1, t2, i - 1, j - 1, dp);

        // Not - Matched
        return dp[i][j] = Math.max(lcs(t1, t2, i - 1, j, dp), lcs(t1, t2, i, j - 1, dp));
    }


    // Tabulation (Bottom-Up)
    public int longestCommonSubsequence2(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) dp[i][0] = 0;
        for(int j = 0; j <= n; j++) dp[0][j] = 0;

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                // Matched
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];

                // Not - Matched
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }



    // Tabulation (Bottom-Up && Space-Optimized)
    public int longestCommonSubsequence3(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[] prev = new int[n + 1];

        for(int j = 0; j <= n; j++) prev[j] = 0;

        for(int i = 1; i <= m; i++) {
            int[] curr = new int[n + 1];
            for(int j = 1; j <= n; j++) {
                // Matched
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) curr[j] = 1 + prev[j - 1];

                // Not - Matched
                else curr[j] = Math.max(prev[j], curr[j - 1]);
            }
            prev = curr;
        }
        return prev[n];
    }


    // Returns the LCS String
    public String longestCommonSubsequence4(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) dp[i][0] = 0;
        for(int j = 0; j <= n; j++) dp[0][j] = 0;

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                // Matched
                if(text1.charAt(i - 1) == text2.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];

                    // Not - Matched
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        int len = dp[m][n];
        char[] ans = new char[len];
        int index = len - 1;
        int i = m, j = n;
        while(i > 0 && j > 0) {
            if(text1.charAt(i - 1) == text2.charAt(j - 1)) {
                ans[index] = text1.charAt(i - 1);
                index--; i--; j--;
            }
            else if(dp[i - 1][j] > dp[i][j - 1]) i--;
            else j--;
        }
        String s = new String(ans);
        return s;
    }
}



