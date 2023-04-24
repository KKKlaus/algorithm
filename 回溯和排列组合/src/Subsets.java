import java.util.ArrayList;
import java.util.List;


// Input: nums = [1,2,3]
// Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
public class Subsets {
    // 复杂度搞清楚下
    //Time complexity: {O}(N * 2^N)
    //
    // to generate all subsets and then copy them into output list.
    //
    //Space complexity: {O}(N). We are using O(N) space to maintain curr,
    // and are modifying curr in-place with backtracking. Note that for space complexity analysis, we do not count space that is only used for the purpose of returning output, so the output array is ignored.


    //The recursive function is called 2^n times.
    //
    // Because we have 2 choices at each iteration in nums array.
    // Either we include nums[i] in the current set, or we exclude nums[i]. This array nums is of size n = number of elements in nums.
    //We need to create a copy of the current set because we reuse the original one to build all the valid subsets.
    // This copy costs O(n) and it is performed at each call of the recursive function, which is called 2^n times as mentioned in above. So total time complexity is O(n x 2^n).

    public static void main(String[] args) {
        subsets(new int[]{1, 2, 3});
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, res, new ArrayList<>());
        return res;
    }

    public static void dfs(int[] nums, int start, List<List<Integer>> res, List<Integer> temp) {
        res.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            dfs(nums, i + 1, res, temp);
            temp.remove(temp.size() - 1);
        }
    }

    // bit mask
    public List<List<Integer>> subsets_2(int[] nums) {
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            List<Integer> temp = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    temp.add(nums[j]);
                }
            }
            res.add(new ArrayList<>(temp));
        }
        return res;
    }

    // This is an amazing solution.Learnt a lot.Let me try to explain this to those who didn't get the logic.
    //
    // Number of subsets for {1 , 2 , 3 } = 2^3 .
    // why ?
    //case    possible outcomes for the set of subsets
    //  1   ->          Take or dont take = 2
    //  2   ->          Take or dont take = 2
    //  3   ->          Take or dont take = 2
    //
    //therefore , total = 2*2*2 = 2^3 = { { } , {1} , {2} , {3} , {1,2} , {1,3} , {2,3} , {1,2,3} }
    //
    //Lets assign bits to each outcome  -> First bit to 1 , Second bit to 2 and third bit to 3
    //Take = 1
    //Dont take = 0
    //
    //0) 0 0 0  -> Dont take 3 , Dont take 2 , Dont take 1 = { }
    //1) 0 0 1  -> Dont take 3 , Dont take 2 ,   take 1       =  {1 }
    //2) 0 1 0  -> Dont take 3 ,    take 2       , Dont take 1 = { 2 }
    //3) 0 1 1  -> Dont take 3 ,    take 2       ,      take 1    = { 1 , 2 }
    //4) 1 0 0  ->    take 3      , Dont take 2  , Dont take 1 = { 3 }
    //5) 1 0 1  ->    take 3      , Dont take 2  ,     take 1     = { 1 , 3 }
    //6) 1 1 0  ->    take 3      ,    take 2       , Dont take 1 = { 2 , 3 }
    //7) 1 1 1  ->    take 3     ,      take 2     ,      take 1     = { 1 , 2 , 3 }
    //
    //In the above logic ,Insert S[i] only if (j>>i)&1 ==true   { j E { 0,1,2,3,4,5,6,7 }   i = ith element in the input array }
    //
    //element 1 is inserted only into those places where 1st bit of j is 1
    //   if( j >> 0 &1 )  ==> for above above eg. this is true for sl.no.( j )= 1 , 3 , 5 , 7
    //
    //element 2 is inserted only into those places where 2nd bit of j is 1
    //   if( j >> 1 &1 )  == for above above eg. this is true for sl.no.( j ) = 2 , 3 , 6 , 7
    //
    //element 3 is inserted only into those places where 3rd bit of j is 1
    //   if( j >> 2 & 1 )  == for above above eg. this is true for sl.no.( j ) = 4 , 5 , 6 , 7
    //
    //Time complexity : O(n*2^n) , for every input element loop traverses the whole solution set length i.e. 2^n
}
