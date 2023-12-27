package aoc.wasteland;

import java.util.Objects;

public class Node {
    private final String key;
    private final Node[] neighbours = new Node[2];
    private final String left;
    private final String right;

    public Node(String key, String left, String right ){
        this.key = key;
        this.left = left;
        this.right = right;
    }

    public void setNeighbours(Node left, Node right) {
        this.neighbours[0] = left;
        this.neighbours[1] = right;
    }

    public Node goTo(Direction direction) {
        return neighbours[direction.value()];
    }

    public String getKey() { return this.key; }
    public String getLeft() { return left; }
    public String getRight() { return right; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
       return Objects.equals(key, node.getKey());
    }

    @Override
    public int hashCode() { return Objects.hash(key); }
}
