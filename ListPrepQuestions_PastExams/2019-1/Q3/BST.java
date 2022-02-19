/**
 * IMPORTANT: This class is incomplete. Please look for "TODO" comments.
 *
 * @author huy.pham
 */
public class BST {
    Node root;

    /**
     * Node class
     */
    public class Node {
        String value;
        Node parent;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
            this.parent = null;
            this.left = null;
            this.right = null;
        }

        /**
         * @param s
         * @return the node that has the given value.
         */
        public Node find(String s) {
            // TODO: Add your implementation here.
            //return this;
            if (this.value == null) return null;
            if (s == null || s.length() == 0) return null;
            if (s.compareTo(this.value) < 0) {
                if (this.left != null) {
                    return this.left.find(s);
                } else {
                    return null;
                }

            } else if ((s.compareTo(this.value) > 0)) {
                if (this.right != null) {
                    return this.right.find(s);
                } else {
                    return null;
                }

            } else if ((s.compareTo(this.value) == 0)) {
                return this;
            } else {
                return null;
            }
        }

        /**
         * Insert a new node into the tree
         *
         * @param s
         * @return {@link Node}
         */
        public Node insert(String s) {
            // TODO: Add your implementation here.
            //return this;
            if (s == null || s.length() == 0) return null;

            if (this.value == null) {
                this.value = s;
                return this;
            }

            if (s.compareTo(this.value) < 0) {
                if (this.left == null) {
                    this.left = new Node(s);
                    this.left.parent = this;
                    return this.left;
                } else {
                    return this.left.insert(s);
                }

            } else if ((s.compareTo(this.value) > 0)) {
                if (this.right == null) {
                    this.right = new Node(s);
                    this.right.parent = this;
                    return this.right;
                } else {
                    return this.right.insert(s);
                }
            } else {
                return this;
            }


        }

        /**
         * @return pre-order traversal of the nodes that have odd number of children.
         */
        public String printOddNodes() {
            // TODO: Add your implementation here.
            //return "";
            String s = "";
            if (this != null && this.value != null) {
                if ((this.left == null && this.right != null) || (this.left != null && this.right == null)) {
                    s = s + " " + this.value;
                }
                if (this.left != null) {
                    s = s + this.left.printOddNodes();
                }
                if (this.right != null) {
                    s = s + this.right.printOddNodes();
                }

            }
            return s;

        }
    }

    public String printOddNodes() {
        return root.printOddNodes();
    }

    public BST() {
        root = null;
    }

    public Node insert(String value) {
        if (root == null) {
            root = new Node(value);
            return root;
        }

        return root.insert(value);
    }

    public Node find(String s) {
        if (root == null) {
            return null;
        }

        return root.find(s);
    }
