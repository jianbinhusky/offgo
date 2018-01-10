package tree;

import java.util.Random;

public class NodeManager {


    private NodeManager(){}

    public Node getRoot() {
        Node node1 = new Node(2);
        Node node2 = new Node(8);
        Node node3 = new Node(3, null, node1);
        Node node4 = new Node(4, node3, null);
        Node node5 = new Node(5);
        Node node6 = new Node(9, node2, null);
        Node node7 = new Node(6, node5, node6);
        Node root = new Node(10,node4,node7);

        return root;
    }

    public PNode getRandomPNode(int num) {
        PNode node1 = new PNode(2);
        PNode node2 = new PNode(8);
        PNode node3 = new PNode(3, null, node1);
        PNode node4 = new PNode(4, node3, null);
        PNode node5 = new PNode(5);
        PNode node6 = new PNode(9, node2, null);
        PNode node7 = new PNode(6, node5, node6);
        PNode root = new PNode(10,node4,node7);

        node1.setParent(node3);
        node2.setParent(node6);
        node3.setParent(node4);
        node4.setParent(root);
        node5.setParent(node7);
        node6.setParent(node7);
        node7.setParent(root);
        root.setParent(null);

        PNode[] nodes = {node1, node2, node3, node4, node5, node6, node7, root};
        Random random = new Random();
        int index = random.nextInt(7);

        return nodes[num];
    }

    public static NodeManager getInstance(){
        return NodeManagerHolder.instance;
    }

    private static class NodeManagerHolder{
        private static NodeManager instance = new NodeManager();
    }
}
