package Google.线段树绳索树脐带树;

// Google interview: https://leetcode.com/discuss/interview-question/1593355/google-phone-interview-rejected/1162221
public class CordTree {

    CordNode root = null;
    CordTree() {
        // init root
    }

    public char findCharacter(CordNode root, int index) {
        if (index >= root.length || index < 0) throw new NullPointerException();
        if (root instanceof LeafNode) {
            return ((LeafNode) root).value.charAt(index);
        } else {
            int leftLen = ((InternalNode) root).left.length;
            if (index <= leftLen) {
                return findCharacter(((InternalNode) root).left, index);
            } else {
                return findCharacter(((InternalNode) root).right, index - leftLen);
            }
        }
    }

    public String substring(CordNode root, int index, int length) {
        if (index < 0 || index + length >= root.length) throw  new NullPointerException();
        if (root instanceof LeafNode) {
            return ((LeafNode) root).value.substring(index, index + length);
        }
        int leftLen = ((InternalNode) root).left.length;
        int start = index, end = index + length;
        String res = "";
        if (end <= leftLen) {
            res += substring(((InternalNode) root).left, start, end);
        } else if (start > leftLen) {
            res += substring(((InternalNode) root).right, start, end);
        } else {
            res += substring(((InternalNode) root).left, start, leftLen);
            res += substring(((InternalNode) root).right, leftLen + 1, end);
        }
        return res;
    }

    public void remove(CordNode root, int start, int end) {
        if (start < 0 || end >= root.length) throw  new NullPointerException();
        if (root instanceof LeafNode) {
            String s = ((LeafNode) root).value.substring(0 , start) + ((LeafNode) root).value.substring(end, root.length);
            ((LeafNode) root).value = s;
            root.length = s.length();
            return;
        }

        int leftLen = ((InternalNode) root).left.length;
        if (end <= leftLen) {
            remove(((InternalNode) root).left, start, end);
        } else if (start > leftLen) {
            remove(((InternalNode) root).right, start, end);
        } else {
            remove(((InternalNode) root).left, start, leftLen);
            remove(((InternalNode) root).right, leftLen + 1, end);
        }
        root.length = ((InternalNode) root).left.length + ((InternalNode) root).right.length; // 这一行重要，看线段树的query就懂了
    }

}

abstract class CordNode {
    protected int length;

    CordNode(int length) {
        this.length = length;
    }
}

class InternalNode extends CordNode {
    CordNode left, right;

    InternalNode(int length) {
        super(length);
        left = null;
        right = null;
    }
}


class LeafNode extends CordNode {
    String value;

    LeafNode(int length, String value) {
        super(length);
        this.value = value;
    }
}

