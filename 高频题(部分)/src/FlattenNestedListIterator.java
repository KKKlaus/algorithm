public class FlattenNestedListIterator {

//    Stack<NestedInteger> stack;
//    public NestedIterator(List<NestedInteger> nestedList) {
//        stack = new Stack<>();
//        iterateList(nestedList);
//    }
//
//    @Override
//    public Integer next() {
//        if (hasNext()) {
//            return stack.pop().getInteger();
//        }
//        return null;
//    }
//
//    @Override
//    public boolean hasNext() {
//        while (!stack.isEmpty() && !stack.peek().isInteger()) {
//            List<NestedInteger> top = stack.pop().getList();
//            iterateList(top);
//        }
//        return !stack.isEmpty();
//    }
//
//    private void iterateList(List<NestedInteger> nestedList) {
//        for (int i = nestedList.size() - 1; i >= 0; i--) {
//            stack.push(nestedList.get(i));
//        }
//    }
}
