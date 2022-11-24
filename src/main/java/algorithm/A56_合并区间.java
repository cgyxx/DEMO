package algorithm;

import com.sun.scenario.effect.Merge;
import jdk.internal.org.objectweb.asm.commons.StaticInitMerger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class A56_合并区间 {

    //以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
    // 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
    //
    //来源：力扣（LeetCode）
    //链接：https://leetcode.cn/problems/merge-intervals


    public static void main(String[] args) {
        int[][] intervals =  {{1,3},{2,6},{8,10},{15,18}};
        int[][] res = merge(intervals);
        System.out.println(res);
    }

    public static int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        int[][] res = new int[intervals.length][intervals[0].length];
        int pos = 0;
        //核心逻辑是要两两比较，合并区间，合并完的区间顶替前两个区间，
        for (int i = 0; i < intervals.length; i++) {
            int[] fst = intervals[i];
            for (int j = i + 1; j < intervals.length; j++) {
                int[] sec = intervals[j];
                //比较逻辑：先判断出左边界那个大，然后判断中间是否交接
                if (fst[0] <= sec[0] && fst[1] >= sec[0]) {
                    //有交集，看下谁的有区间最大然后封底
                    if (fst[1] <= sec[1]) {
                        res[pos] = new int[]{fst[0], sec[1]};
                        fst[1]=sec[1];
                    } else {
                        res[pos] = fst;
                    }
                    pos++;i++;
                } else if (fst[0] >= sec[0] && fst[0] <= sec[1]) {
                    //有交集，看下谁的有区间最大然后封底
                    if (fst[1] >= sec[1]) {
                        res[pos] = new int[]{sec[0], fst[1]};
                        sec[1]=fst[1];
                    } else {
                        res[pos] = sec;
                    }
                    pos++;i++;
                }else {
                    i++;
                    continue;
                }
            }

        }
        return res;
    }


    public int[][] merge2(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] interval1, int[] interval2) {
                return interval1[0] - interval2[0];
            }
        });
        List<int[]> merged = new ArrayList<int[]>();
        for (int i = 0; i < intervals.length; ++i) {
            int L = intervals[i][0], R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size() - 1)[1] < L) {
                merged.add(new int[]{L, R});
            } else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], R);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }


}
