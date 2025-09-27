public class BinarySearchVariation {

    public static int binarySearch(int[] arr, int target, int start, int end) {    // Iterative Binary Search Algorithm
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(target == arr[mid]) return mid;
            else if(target > arr[mid]) start = mid + 1;
            else end = mid - 1;
        }
        return -1;
    }

    public static int binarySearchOrderAgnostic(int[] arr, int target, int start, int end) {    // Iterative Order Agnostic Binary Search Algorithm
        boolean isAsc = arr[start] < arr[end];
        while(start <= end) {
            int mid = start + (end - start) / 2;
            if(target == arr[mid]) return mid;
            if(isAsc) {                  // If Array is Sorted in Increasing-Order
                if(target > arr[mid]) start = mid + 1;
                else end = mid - 1;
            } else {                    // If Array is Sorted in Decreasing-Order
                if(target < arr[mid]) start = mid + 1;
                else end = mid - 1;
            }
        }
        return -1;
    }

    public static int ceiling(int[] arr, int target) {     //Returns the index: smallest number >= target
        int start = 0;
        int end = arr.length - 1;
        if(target > arr[end]) return -1;          // <--- This part is optional and can be changed according to question.

        while(start <= end){
            int mid = start + (end - start) / 2;

            if(arr[mid] == target) return arr[mid];
            else if(arr[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return arr[start];
    }

    public static int floor(int[] arr, int target) {   //Returns the index: greatest number <= target
        int start = 0;
        int end = arr.length - 1;
        if(target < arr[start]) return -1;              // <--- This part is optional and can be changed according to question.

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) return arr[mid];
            else if (arr[mid] > target) end = mid - 1;
            else start = mid + 1;
        }
        return arr[end];
    }

    public static int peakOfMountain(int[] arr) {      //Returns the index of the Peak Element of a Mountain Array
        int start = 0;
        int end = arr.length - 1;

        while(start < end) {
            int mid = start + (end - start) / 2;

            if(arr[mid] > arr[mid + 1]) end = mid;
            else start = mid + 1;
        }
        return start;
    }


    public static int findPivot(int[] arr){        // Returns the index from where the maximum element is present in a Rotated-Sorted Array.
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if(mid < end && arr[mid] > arr[mid + 1]) return mid;
            if(mid > start && arr[mid] < arr[mid - 1]) return mid - 1;
            if(arr[mid] > arr[start]) start = mid + 1;
            else end = mid - 1;
        }
        return -1; // It means Array is not Rotated, it is normally sorted.
    }

    public static int findPivotWithDuplicates(int[] arr){    // Returns the index from where the maximum element is present in a Rotated-Sorted Array.
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if(mid < end && arr[mid] > arr[mid + 1]) return mid;
            if(mid > start && arr[mid] < arr[mid - 1]) return mid - 1;
            if(arr[start] == arr[mid] && arr[mid] == arr[end]) {
                if(arr[start] > arr[start + 1]) return start; start++;
                if(arr[end] < arr[end - 1]) return end - 1; end--;
            }
            else if(arr[start] < arr[mid] || (arr[start] == arr[mid]) && arr[mid] > arr[end]) start = mid + 1;
            else end = mid - 1;
        }
        return -1; // It means Array is not Rotated, it is normally sorted.
    }

    public static double findMedianSortedArrays(int[] a, int[] b) {   // Hard Problem
        int n1 = a.length, n2 = b.length;
        if(n1 > n2) return findMedianSortedArrays(b, a);

        int l = 0, r = n1;
        int total = n1 + n2;
        int left = (n1 + n2 + 1) / 2;

        while(l <= r) {
            int mid1 = l + (r - l) / 2;
            int mid2 = left - mid1;

            int l1 = Integer.MIN_VALUE, l2 = Integer.MIN_VALUE;
            int r1 = Integer.MAX_VALUE, r2 = Integer.MAX_VALUE;

            if(mid1 < n1) r1 = a[mid1];
            if(mid2 < n2) r2 = b[mid2];
            if(mid1 > 0) l1 = a[mid1 - 1];
            if(mid2 > 0) l2 = b[mid2 - 1];
            if(l1 <= r2 && l2 <= r1){
                if(total % 2 == 1) return Math.max(l1, l2);
                return ((double)(Math.max(l1,l2) + Math.min(r1,r2))) / 2.0;
            }
            else if(l1 > r2) r = mid1 - 1;
            else l = mid1 + 1;
        }
        return 0; 
    }
    
    public static boolean searchMatrix(int[][] matrix, int target) {               // Search in 2D (Fully Sorted) Matrix
        int m = matrix.length, n = matrix[0].length, l = 0, r = (m*n) - 1;
        while(l <= r) {
            int mid = l + ((r - l) / 2);
            int row = mid / n;
            int col = mid % n;

            if(matrix[row][col] == target) return true;
            else if(matrix[row][col] < target) l = mid + 1;
            else r = mid - 1; 
        }
        return false;
    }

    public static boolean searchMatrixII(int[][] matrix, int target) {        // Search in 2D (Row-Wise) Sorted Matrix 
        int n = matrix.length, m = matrix[0].length;
        int row = 0, col = m - 1;
        while(row < n && col >= 0) {
            if(matrix[row][col] == target) return true;
            else if(matrix[row][col] < target) row++;
            else col--;
        }
        return false;
    }

    class Solution {
    public int[] findPeakGrid(int[][] mat) {                    //  Returns Index of Peak Element of a 2D Matrix
        int l = 0, r = mat[0].length - 1, mid = -1;
        while(l <= r) {
            mid = l + (r - l) / 2;
            int row = maxElement(mat, mid);
            int left = mid - 1 >= 0 ? mat[row][mid - 1] : -1;
            int right = mid + 1 < mat[0].length ? mat[row][mid + 1] : -1;
            if(mat[row][mid] > left && mat[row][mid] > right) return new int[] {row, mid};
            else if(mat[row][mid] < left) r = mid - 1;
            else l = mid + 1;
        }
        return new int[] {-1, -1};
    }

    public int maxElement(int[][] mat, int mid) {
        int max = Integer.MIN_VALUE, idx = -1;
        for(int i = 0; i < mat.length; i++) {
            if(mat[i][mid] > max) {
                max = mat[i][mid];
                idx = i;
            }
        }
        return idx;
    }
}
    
    public static void main(String[] args) {
        int[] numbers = {22,20,17,14,11,7,4,1};                          // Normal Sorted in Decreasing-Order
        System.out.println(binarySearchOrderAgnostic(numbers, 17,0,7));  // answer = 2

        int[] num = {2,4,6,9,12,15,16};                                   // Normal Sorted in Increasing-Order
        System.out.println(binarySearch(num, 9,0,6));                     // answer = 3
        System.out.println(floor(num, 13));                               // answer = 12
        System.out.println(ceiling(num, 13));                             // answer = 15

        int[] arr = {3,7,9,12,8,7,6};               // Mountain Array (first half is sorted in Increasing-order & other half is in Decreasing-order)
        System.out.println(peakOfMountain(arr));    // answer = 3

        int[] nums = {16,17,18,7,8,11,13,14};      // Rotated-Sorted Array
        System.out.println(findPivot(nums));       // answer = 2

        int[] n = {16,17,17,17,7,8,8,11,12,12,14};    // Rotated-Sorted Array with Duplicates
        System.out.println(findPivotWithDuplicates(n));   // answer = 3
    }
}

/* Note :-
Difference between (start < end) & (start <= end) :-
    Use while(start < end) when start = mid, end = mid - 1;
    Use while(start < end) when start = mid + 1, end = mid;
    
    Use while(start <= end) when start = mid + 1, end = mid - 1;

In the beginning of the loop,
    if 'end' pointer was pointing to the possible answer :--- 'start' pointer will point to the answer after the loop is over.
    if 'start' pointer was pointing to the possible answer :--- 'end' pointer will point to the answer after the loop is over. 

*/


