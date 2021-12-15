package Google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

// https://leetcode.com/discuss/interview-question/1457059/Google-Virtual-Onsite-or-India-or-July-2021/1081432
public class ArrayTree {

    public static void main(String[] args) {
        ArrayTree t = new ArrayTree();
        t.test();
    }

    private void test() {
        init();
//        remove(1);
        removeSubtree(1);
    }

    TreeNode[] arr;

    public void init() {
        TreeNode A = new TreeNode('A', -1);
        TreeNode B = new TreeNode('B', 0);
        TreeNode C = new TreeNode('C', 1);
        TreeNode D = new TreeNode('D', 0);
        TreeNode E = new TreeNode('E', 1);
        TreeNode F = new TreeNode('F', 3);
        TreeNode G = new TreeNode('G', 3);
        TreeNode H = new TreeNode('H', 0);
        arr = new TreeNode[]{A,B,C,D,E,F,G, H};
    }

    public TreeNode[] remove(int index) {
        TreeNode[] res = new TreeNode[arr.length - 1];
        int p = 0, parentIndex = arr[index].parent;
        for (int i = 0; i < arr.length; i++) {
            if (i < index) res[p++] = arr[i];
            else if (i == index) continue;
            else {
                if (arr[i].parent == index) {
                    arr[i].parent = parentIndex;
                } else if (arr[i].parent > index) {
                    arr[i].parent--;
                }
                res[p++] = arr[i];
            }
        }
        return res;
    }

    // 自己的方法，比较麻烦
    public TreeNode[] removeSubtree(int index) {
        List<TreeNode> res = new ArrayList<>();
        HashMap<Integer, Integer> indexToCountMap = new HashMap<>();
        HashSet<Integer> removeIndex = new HashSet<>();
        for (int i = 0; i < arr.length; i++) {
            if (i == index) {
                removeIndex.add(index);
                indexToCountMap.put(i, removeIndex.size());
                continue;
            }
            if (removeIndex.contains(arr[i].parent)) {
                removeIndex.add(i);
            } else {
                int parentIndex = arr[i].parent;
                if (indexToCountMap.containsKey(parentIndex)) {
                    arr[i].parent -= indexToCountMap.get(parentIndex);
                }
                res.add(arr[i]);
            }
            indexToCountMap.put(i, removeIndex.size());
        }
        return res.toArray(new TreeNode[0]);
    }

    class TreeNode {
        char c;
        int parent; // index of the parent TreeNode in the array

        TreeNode(char c, int parent) {
            this.c = c;
            this.parent = parent;
        }
    }
}
