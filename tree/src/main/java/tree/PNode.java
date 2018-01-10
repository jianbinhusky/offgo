package tree;

public class PNode{
    private int value;
    private PNode left;
    private PNode right;
    private PNode parent;//指向其父亲节点

    public PNode() {
    }

    public PNode(int value) {
        this.value = value;
    }

    public PNode(int value,PNode left, PNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public PNode getLeft() {
        return left;
    }

    public void setLeft(PNode left) {
        this.left = left;
    }

    public PNode getRight() {
        return right;
    }

    public void setRight(PNode right) {
        this.right = right;
    }

    public PNode getParent() {
        return parent;
    }

    public void setParent(PNode parent) {
        this.parent = parent;
    }
}
