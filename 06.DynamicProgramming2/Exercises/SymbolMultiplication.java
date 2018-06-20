package DynamicProgramming2.Exercises;

import java.util.*;

public class SymbolMultiplication {
    public static void main(String[] args) {
        ExpressionTree tree = new ExpressionTree("bacacbcabbbcacab");
        Set<String> results = tree.getAllValidResults();
        for (String result : results) {
            System.out.println(result);
        }
        System.out.println("Matches found: " + results.size());
    }
}

class ExpressionTree {
    private Node root;
    private List<Node> multipliers;
    private Set<String> results;

    public ExpressionTree(String text) {
        this.multipliers = new ArrayList<>();
        this.initializeTree(text);
        this.results = new HashSet<>();
    }

    public Set<String> getAllValidResults() {
        if (calculateResult(this.root) == 'a') {
            this.results.add(getExpression());
        }
        for (int i = 1; i < this.multipliers.size(); i++) {
            this.generateExpressions(this.multipliers.get(i), "right");
        }
        return Collections.unmodifiableSet(this.results);
    }

    private void generateExpressions(Node node, String direction) {
        if (node.value != '*') {
            return;
        }
        if (direction.equals("right")) {
            rotateRight(node);
            if (calculateResult(this.root) == 'a') {
                this.results.add(getExpression());
            }
            Node current = node;
            while (current.right.value == '*') {
                generateExpressions(current, "left");
                current = current.right;
            }
            if (node.parent.left.value == '*') {
                generateExpressions(node.parent, "right");
                current = node.parent.left;
                while (current.left.value == '*') {
                    generateExpressions(current, "right");
                    current = current.left;
                }
            }
            rotateLeft(node.parent);
        } else if (direction.equals("left")) {
            rotateLeft(node);
            if (calculateResult(this.root) == 'a') {
                this.results.add(getExpression());
            }
            Node current = node;
            while (current.left.value == '*') {
                generateExpressions(current, "right");
                current = current.left;
            }
            if (node.parent.right.value == '*') {
                generateExpressions(node.parent, "left");
                current = node.parent.right;
                while (current.right.value == '*') {
                    generateExpressions(current, "left");
                    current = current.right;
                }
            }
            rotateRight(node.parent);
        }
    }

    private char calculateResult(Node node) {
        if (node.value == '*') {
            char left = calculateResult(node.left);
            char right = calculateResult(node.right);
            if (left == 'a' && right == 'a' || left == 'a' && right == 'b'
                    || left == 'b' && right == 'b') {
                return 'b';
            } else if (left == 'a' && right == 'c' || left == 'b' && right == 'c'
                    || left == 'c' && right == 'a') {
                return 'a';
            } else {
                return 'c';
            }
        }
        return node.value;
    }

    private void initializeTree(String text) {
        Node previous = null;
        for (int i = 0; i < text.length() - 1; i++) {
            Node node = new Node('*');
            node.left = previous;
            if (i == 0) {
                Node leftChild = new Node(text.charAt(0));
                leftChild.parent = node;
                node.left = leftChild;
                leftChild.parent = node;
            } else {
                previous.parent = node;
            }
            Node rightChild = new Node(text.charAt(i + 1));
            rightChild.parent = node;
            node.right = rightChild;
            multipliers.add(node);
            previous = node;
        }
        this.root = this.multipliers.get(this.multipliers.size() - 1);
    }

    private String getExpression() {
        StringBuilder sb = new StringBuilder();
        sb.append("(");
        if (this.root.left != null) {
            sb.append(this.generateStringDFS(sb, this.root.left));
        }
        sb.append("*");
        if (this.root.right != null) {
            sb.append(this.generateStringDFS(sb, this.root.right));
        }
        sb.append(")");
        return sb.toString();
    }

    private String generateStringDFS(StringBuilder sb, Node node) {
        if (node.value == '*') {
            sb.append("(");
            if (node.left != null) {
                sb.append(this.generateStringDFS(sb, node.left));
            }
            sb.append("*");
            if (node.right != null) {
                sb.append(this.generateStringDFS(sb, node.right));
            }
            sb.append(")");
            return "";
        }
        return Character.toString(node.value);
    }

    private void rotateRight(Node node) {
        Node parent = node.parent;
        Node leftNode = node.left;
        node.left = leftNode.right;
        leftNode.right.parent = node;
        leftNode.right = node;
        node.parent = leftNode;
        leftNode.parent = parent;
        if (this.root == node) {
            this.root = leftNode;
        } else {
            if (parent.right == node) {
                parent.right = leftNode;
            } else if (parent.left == node) {
                parent.left = leftNode;
            }
        }
    }

    private void rotateLeft(Node node) {
        Node parent = node.parent;
        Node rightNode = node.right;
        node.right = rightNode.left;
        rightNode.left.parent = node;
        rightNode.left = node;
        node.parent = rightNode;
        rightNode.parent = parent;
        if (this.root == node) {
            this.root = rightNode;
        } else {
            if (parent.right == node) {
                parent.right = rightNode;
            } else if (parent.left == node) {
                parent.left = rightNode;
            }
        }
    }

    private class Node {
        private char value;
        private Node left;
        private Node right;
        private Node parent;

        private Node(char value) {
            this.value = value;
        }
    }
}
