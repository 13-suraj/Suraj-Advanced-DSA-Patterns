public class PrefixSum {
    // Prefix Sum Algorithm is used for solving range sum query (immutable) problems.

    // If the given array or matrix is immutable means it will never change then we use this algorithm
    // for finding sum between given ranges in constant time;

    // For achieving this we pre-compute the results in other array or matrix.

    // Prefix Sum for 1D Array
    private int[] prefixSum;
    public void numArray(int[] nums) {
        for(int i = 1; i < nums.length; i++){
            nums[i] += nums[i-1];
        }
        this.prefixSum = nums;
    }
    public int sumRange(int left, int right) {
        if(left == 0){
            return prefixSum[right];
        }
        return prefixSum[right] - prefixSum[left-1];
    }


    // Prefix Sum for 2D Matrix
    private int[][] prefix;
    public void numMatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        prefix = new int[rows + 1][cols + 1];
        for(int r = 1; r <= rows; r++) {
            for(int c = 1; c <= cols; c++) {
                prefix[r][c] = prefix[r - 1][c] + prefix[r][c - 1] - prefix[r - 1][c - 1] + matrix[r - 1][c - 1];
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return prefix[row2 + 1][col2 + 1] - prefix[row2 + 1][col1] - prefix[row1][col2 + 1] + prefix[row1][col1];
    }
}
