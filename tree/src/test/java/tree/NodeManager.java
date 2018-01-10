package tree;

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

    public static NodeManager getInstance(){
        return NodeManagerHolder.instance;
    }

    private static class NodeManagerHolder{
        private static NodeManager instance = new NodeManager();
    }
}
