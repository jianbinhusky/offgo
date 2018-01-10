package tree;

public class BinaryTreeUtils extends BinaryTreeTraverse {

    private static int[] convert2Array() {
        Object[] array = list.toArray();
        int[] target = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i] instanceof Integer) {
                target[i] = (Integer) array[i];
            }
        }
        list.clear();
        return target;
    }

    public static int[] binaryTree2PreOrderArray(Node node) {
        preOrderNonRecursiveTraverse(node);

        return convert2Array();
    }

    public static int[] binaryTree2MidOrderArray(Node node) {
        midOrderNonRecursiveTraverse(node);

        return convert2Array();
    }

    public static int[] binaryTree2PosOrderArray(Node node) {
        posOrderNonRecursiveTraverse(node);

        return convert2Array();
    }

}
