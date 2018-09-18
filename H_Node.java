import java.util.HashMap;

public class H_Node {

    private char character;
    private int frequency;
    private H_Node[] children;

    public H_Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        children = new H_Node[] {null, null};
    }

    public H_Node(H_Node h) {
        character = h.getCharacter();
        frequency = h.getFrequency();
        children = new H_Node[] {h.getLeftChild(), h.getRightChild()};
    }

    public boolean isLeaf() {
        if (children[0] == null && children[1] == null) return true;
        return false;
    }

    public char getCharacter() {

        return character;
    }

    public int getFrequency() {

        return frequency;
    }

    public H_Node getLeftChild() {

        return children[0];
    }

    public H_Node getRightChild() {

        return children[1];
    }

    public void setCharacter(char character) {

        this.character = character;
    }

    public void setFrequency(int frequency) {

        this.frequency = frequency;
    }

    public void setLeft(H_Node left) {

        children[0] = left;
    }

    public void setRight(H_Node right) {

        children[1] = right;
    }

    public static H_Node mergeNodes(H_Node h1, H_Node h2) {
        H_Node h3 = new H_Node('$',
                h1.getFrequency()+h2.getFrequency());
        h3.children[0] = h1;
        h3.children[1] = h2;
        return h3;
    }

    public static void preOrderPrint(H_Node root) {
        if (root != null) {
            preOrderPrint(root.children[0]);
            preOrderPrint(root.children[1]);
            System.out.printf("char: %c :: freq: %d\n",
                    root.character, root.frequency);
        }
    }

    public static void getPrefixes(H_Node node, String code, HashMap h) {
        if (!node.isLeaf()) {
            getPrefixes(node.getLeftChild(), code + '0', h);
            getPrefixes(node.getRightChild(), code + '1', h);
        }
        else {
            h.put(node.getCharacter(), code);
        }
    }
}

