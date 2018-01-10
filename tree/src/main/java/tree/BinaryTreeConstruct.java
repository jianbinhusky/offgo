package tree;

public class BinaryTreeConstruct {

    public static Node constructBinaryTreeFromPreMid(int[] pre, int[] mid) {
        if (pre == null || mid == null) {
            throw new NullPointerException();
        }
        return constructFromPreMid(pre,0,pre.length-1,mid,0,mid.length-1);
    }

    private static Node constructFromPreMid(int[] pre, int preStart, int preEnd, int[] mid, int midStart, int midEnd) {
        if (preStart > preEnd || midStart > midEnd) {
            return null;
        }
        Node node = new Node(pre[preStart]);
        for (int i = midStart; i <= midEnd; i++) {
            if (mid[i] == pre[preStart]) {
                node.setLeft(constructFromPreMid(pre, preStart + 1, i - midStart + preStart, mid, midStart, i - 1));
                node.setRight(constructFromPreMid(pre, preStart + i - midStart + 1, preEnd, mid, i + 1, midEnd));
            }
        }

        return node;
    }

    public static Node constructBinaryTreeFromMidPos(int[] mid, int[] pos) {
        if (mid == null || pos == null) {
            throw new NullPointerException();
        }

        return constructFromMidPos(mid, 0, mid.length - 1, pos, 0, pos.length - 1);
    }

    private static Node constructFromMidPos(int[] mid, int midStart, int midEnd, int[] pos, int posStart, int posEnd) {
        if (midStart > midEnd || posStart > posEnd) {
            return null;
        }

        Node node = new Node(pos[posEnd]);
        for (int i = midStart; i <= midEnd; i++) {
            if (mid[i] == pos[posEnd]) {
                node.setLeft(constructFromMidPos(mid, midStart, i - 1, pos, posStart, posStart + i - midStart-1));
                node.setRight(constructFromMidPos(mid, i + 1, midEnd, pos, posStart + i - midStart, posEnd - 1));
            }
        }

        return node;
    }
}
