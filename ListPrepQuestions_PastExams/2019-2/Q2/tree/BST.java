/**
 * This class is a part of {@code package tree}. Do not change the package structure.
 * Make sure that your IDE do not change it to for example {@code package src.tree}.
 * If it happens, please revert it once you finish the implementation.
 */
package tree;

/**
 * Binary search tree with integer keys (values). {@code insert} method is
 * provided.
 *
 * @author dongwoo
 */
public class BST {
    Node root;

    /**
     * Q2 - Task1 TODO: Implement "find" method. The method should return "true" if
     * a tree contains the node with value, otherwise return "false". You can define
     * additional functions in class BST or Node if you need.
     *
     * @param value
     * @return return true if the tree contains the node with value otherwise false
     */
    public Boolean find(int value) {

        // start your code
        Node n = root.find(value);
        return (n != null);
        // end your code
    }



    /**
     * Q2 - Task2 TODO: Implement "delete" method. Find the node with {@code value}
     * and remove it from the tree. If the target node has two children, use
     * successor to replace the target node. You can define additional functions in
     * class BST or Node if you need.
     * <p>
     * Do not care about the case where the target node does not exist.
     *
     * @param value value of the target node
     */
    public void delete(int value) {

        // start your code
        Node n = root.find(value);
        Node p = n.parent;

        //case 1: no child.
        if (n.left == null && n.right == null) {
            if (n.value < p.value) {// n是左孩子
                p.left = null;
                n = null;
            } else {
                p.right = null;
                n = null;
            }
        } else if (n.left == null && n.right != null) { // case 2：n only has right child
            if (n.value < p.value) {// n是左孩子
                n.right.parent=p;
                p.left = n.right;
            } else {
                n.right.parent=p;
                p.right = n.right;
            }
        } else if (n.left != null && n.right == null) {//// case 2：n only has left child
            if (n.value < p.value) {// n是左孩子
                n.left.parent=p;
                p.left = n.left;
            } else {
                n.left.parent=p;
                p.right = n.left;
            }
        } else { // case 3: n has 2 nodes
            Node successor = n.right.getSuccessorInDeletion();
            Node psuc = successor.parent;
            int sucValue = successor.value;
            n.value=sucValue;

            //case 3.1: no child.
            if (successor.left == null && successor.right == null) {
                if (successor.value < psuc.value) {// n是左孩子
                    psuc.left = null;
                    successor = null;
                } else {
                    psuc.right = null;
                    successor = null;
                }
            } else if (successor.left == null && successor.right != null) { // case 2：n only has right child
                if (successor.value < psuc.value) {// n是左孩子
                    successor.right.parent=psuc;
                    psuc.left = successor.right;
                } else {
                    successor.right.parent=psuc;
                    psuc.right = successor.right;
                }
            } else if (successor.left != null && successor.right == null) {//// case 2：n only has left child
                if (successor.value < psuc.value) {// n是左孩子
                    successor.left.parent=psuc;
                    psuc.left = successor.left;
                } else {
                    successor.left.parent=psuc;
                    psuc.right = successor.left;
                }
            }

        }

        //


        // end your code
    }

    /**
     * Q2 - Task3 TODO: Implement "sumEvenNodes" function. The method should return
     * print the sum of the nodes that have an even number of direct children (zero
     * is an even number). You can define additional functions in class BST or Node
     * if you need.
     *
     * @return Sum of the nodes that have an even number of direct children
     */
    public int sumEvenNodes() {
        //start your code
        return 0;


        //end your code
    }

    public class Node {
        public Integer value;
        public Node parent;
        public Node left;
        public Node right;

        public Node(Integer value) {
            this.value = value;
            this.parent = null;
            this.left = null;
            this.right = null;
        }


        public Node getSuccessorInDeletion() {
            if (this.left == null && this.value != null) return this;
            return this.left.getSuccessorInDeletion();
        }

        public Node find(int input) {
            // TODO: Add your implementation here.
            //return this;
            if (this.value == null) return null;
            if (input < this.value) {
                if (this.left != null) {
                    return this.left.find(input);
                } else {
                    return null;
                }

            } else if (input > this.value) {
                if (this.right != null) {
                    return this.right.find(input);
                } else {
                    return null;
                }

            } else if ((input - this.value) == 0) {
                return this;
            } else {
                return null;
            }
        }
    }

    public BST() {
        root = null;
    }


    /**
     * Insert a new node based on an input value. Do not modify the method.
     * <p>
     * Do not consider the case where a tree already has the node with the same
     * value.
     *
     * @param value value of inserted node
     * @return inserted node (not the entire tree)
     */
    public Node insert(int value) {
        Node parent = null;
        Node current = root;
        while (current != null) {
            if (current.value < value) {
                parent = current;
                current = current.right;
            } else if (current.value > value) {
                parent = current;
                current = current.left;
            }
        }

        if (parent == null && current == null) {
            root = new Node(value); // no parent = root is empty
            return root;
        } else {
            Node newNode = new Node(value);

            if (parent.value < value) {
                parent.right = newNode;
                newNode.parent = parent;
            } else if (parent.value > value) {
                parent.left = newNode;
                newNode.parent = parent;
            }
            return newNode;
        }
    }

}