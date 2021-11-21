public class NestedListWeightSum {

    // LC339. Nested List Weight Sum

//    public int depthSum(List<NestedInteger> nestedList) {
//        Queue<NestedInteger> q = new LinkedList<>();
//        // Queue<NestedInteger> queue = new LinkedList<NestedInteger>(nestedList);
//        for (NestedInteger nest : nestedList) {
//            q.offer(nest);
//        }
//
//        int level = 1, sum = 0;
//        while (!q.isEmpty()) {
//            int size = q.size();
//            for (int i = 0; i < size; i++) {
//                NestedInteger cur = q.poll();
//                if (cur.isInteger()) {
//                    sum += level * cur.getInteger();
//                } else {
//                    for (NestedInteger nest : cur.getList()) {
//                        q.offer(nest);
//                    }
//                }
//            }
//            level++;
//        }
//        return sum;
//    }


    // 364. Nested List Weight Sum II
    //方法1： dfs求深度，bfs求sum
    // 方法2： 用prevSum记录之前所有的integer的和，其实每次到新的一层，sum都需要再加一次prevSum
    // Each integer get added one extra time for the mere existence of each one level under it.
}
