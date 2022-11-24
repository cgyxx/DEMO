package algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 经典回溯
 */
public class A_78子集 {
    public static void main(String[] args) {
        int[] nums = {0};
        List<List<Integer>> r = subsets(nums);
        System.out.println(r);
    }

    static List<List<Integer>> res = new ArrayList<>();
    public static List<List<Integer>> subsets(int[] nums) {
        //回溯算法
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            List<Integer> attr = new ArrayList<>();
            backTrace(nums,attr,i);
        }

        return res;
    }

    public static void backTrace(int[] nums, List<Integer> attr,int loc){
        if (loc==nums.length){
            List<Integer> at = new ArrayList<>();
            at.addAll(attr);
            res.add(at);
            return;
        }
        for (int i = loc; i < nums.length; i++) {
            attr.add(nums[loc]);
            backTrace(nums,attr,i+1);
            attr.remove(attr.size()-1);
        }
    }


}
