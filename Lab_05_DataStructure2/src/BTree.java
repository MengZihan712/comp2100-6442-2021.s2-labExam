import java.util.Collection;
import java.util.Collections;
import java.util.Objects;

/**
 * Welcome!
 * You have two tasks:
 * Task 1: complete the split and insert methods of this Java class.
 * Task 2: complete the max() method of BTreeNode.
 * You are free to add helper methods to help you accomplish these tasks.
 * We are here to help so ask questions if you are lost or unsure :)
 * <p>
 * DO NOT edit any other classes.
 * <p>
 * Lastly, please read the readme.md markdown file for a rundown of the specifics/description of this
 * implementation (as well BTree basics to refresh your mind).
 *
 * @param <T> the generic type this BTree uses. It extends comparable
 *            which allows us to order two of the same type.
 */
public class BTree<T extends Comparable<T>> {
    /**
     * Fields of a BTree.
     * <p>
     * Notice that we keep track of the root of the BTree. This is because
     * of the nature of the BTree. It grows upwards! Unless you can return the
     * root each time (which is a possible implementation approach) you will need
     * to keep track of the root.
     */
    private int order;          // Order of the BTree.
    private BTreeNode root;     // Root node of the BTree.

    /**
     * Constructor which sets the field 'order'
     *
     * @param order of the BTree.
     */
    public BTree(int order) {
        this.order = order;
        root = new BTreeNode();
    }

    /**
     * Adds key to the BTree.
     *
     * @param key to be inserted.
     */
    public void insert(T key) {
        /*
             Task 1.
             TODO: complete this insert method.
             Hint: grow upwards from the root if necessary.
         */

        // Ensure input is not null.
        if (key == null)
            throw new IllegalArgumentException("Input cannot be null");

        // Purely insert first (insert to the leaf) and get the inserted node.
        BTreeNode inserted = root.insert(key);   // Feel free to replace this.

        // Check if it is necessary to split.(recursively checking)
        //When splitting, assume the current node is the parent of the childToSplit.
        //If the childToSplit does not have a parent ( parent is null here), then make a new node as parent.


        // Recursively check and split.
        BTreeNode recurser = inserted;





            while (recurser.keys.size() == order) {
                //Need to split

                //If the childToSplit does not have a parent (parent is null here, which means the whole tree is a small leaf here and root=leaf)
                // , then make a new node as parent.(Change the root also!)

                if (recurser.parent == null) {

                BTreeNode newParent = new BTreeNode();
                recurser.parent = newParent;
                newParent.children.add(recurser);
                split(recurser.parent, recurser);
                root = recurser.parent;
                recurser = root;

                } else {
                    //childToSplit has a parent
                    split(recurser.parent, recurser);
                    recurser=recurser.parent;
                }

            }





    }

    /**
     * Recursively check whether this need to be split and split if necessary.
     *
     * @param nodeToSplit The inserted node (going to be checked) after  [BTreeNode.insert()]
     */

    private void checkAndSplit(BTreeNode nodeToSplit) {
        // Check if it is necessary to split.(recursively checking)
        //When splitting, assume the current node is the parent of the childToSplit.
        //If the childToSplit does not have a parent ( parent is null here), then make a new node as parent.


    }


    /**
     * Will conduct a split on the provided indexed child of the provided node.
     * <p>
     * Here's a question: why could we not run '.split()' without inputs on the node we want to split?
     * Answer: We actually can! This is just one of many design decisions you may have to consider when working
     * on a project. In this case, if this split method was in BTreeNode and used '.split()' without inputs
     * on the node you want to split, you would need to have a reference to the parent of the current BTreeNode.
     * We chose not to pursue this. Please do not move this method into BTreeNode to prevent complications.
     *
     * @param node         The current node whose provided (child to split) will be split.// current node, parent of the 2nd parameter
     * @param childToSplit The child to split within the provided node.// the node to split, 1st parameter's child.
     */
    private void split(BTreeNode node, BTreeNode childToSplit) {//pure split, assume having the parent.
        /*
             Task 1.
             TODO: complete this split method.
             Feel free to use helper methods!
             Hint: need to get all LimitedArrayList elements from index i to j?
             Use 'limitedArrayList.get(i, j)' from LimitedArrayList.java. See
             the file for description on the method.
         */

        // Ensure neither input is null.
        if (node == null || childToSplit == null)
            throw new IllegalArgumentException("Input cannot be null");

        // Get median key
        int med = childToSplit.keys.size() / 2;
        T medValue = childToSplit.keys.get(med);

        // Add the median key to the parent node in the correct position.
        this.addInOrder(node.keys, medValue);

        // Two new children as the split result.
        BTreeNode left = new BTreeNode();
        left.parent = node;
        BTreeNode right = new BTreeNode();
        right.parent = node;

        int pointerIndexBeforeInsertParent=node.children.indexOf(childToSplit);
        node.children.set(pointerIndexBeforeInsertParent,left);
        node.children.add(pointerIndexBeforeInsertParent+1,right);


        if (childToSplit.children.size() != 0) {// childToSplit is an inner node
            // TODO: get an array of everything left of the median.
            for (int i = 0; i < med; i++) {
                left.insert(childToSplit.keys.get(i));

                childToSplit.children.get(i).parent = left;
                left.children.add(childToSplit.children.get(i));
            }
            childToSplit.children.get(med).parent = left;
            left.children.add(childToSplit.children.get(med));


            // TODO: get an array of everything right of the median.
            for (int i = med + 1; i < childToSplit.keys.size(); i++) {
                right.insert(childToSplit.keys.get(i));
            }

            for (int i = med+1; i < childToSplit.children.size(); i++) {
                childToSplit.children.get(i).parent = right;
                right.children.add(childToSplit.children.get(i));

            }

            // TODO: think of and write the rest of the split method. You may also choose to re-write the above.

        } else {// childToSplit  is a leaf

            // TODO: get an array of everything left of the median.
            for (int i = 0; i < med; i++) {
                left.insert(childToSplit.keys.get(i));
            }

            // TODO: get an array of everything right of the median.
            for (int i = med + 1; i < childToSplit.keys.size(); i++) {
                right.insert(childToSplit.keys.get(i));
            }

            // TODO: think of and write the rest of the split method. You may also choose to re-write the above.
        }
    }

    /**
     * Adds element in ascending order.
     * Helper function for insert.
     * Warning: may result in IndexOutOfBoundsException if you add too many.
     *
     * @param list    to add element into.
     * @param element to add into list.
     */
    private void addInOrder(LimitedArrayList<T> list, T element) {
        // Ensure inputs are not null.
        if (list == null || element == null)
            throw new IllegalArgumentException("Input cannot be null");

        // Go through each index and check if the element being inserted is less than the current element being looked at.
        for (int i = 0; i < list.size() && i < list.getCapacity(); i++) {
            // If less, insert it at that index (pushing all other elements forward by 1).
            if (element.compareTo(list.get(i)) < 0) {
                list.add(i, element);
                return;
            }
        }
        // If nothing is greater than the element being inserted, than it must be the greatest element. Insert at end.
        list.add(element);
    }

    /**
     * @return maximum key of the BTree
     */
    public T max() {
        return root.max();
    }

    /**
     * @return minimum key of the BTree
     */
    public T min() {
        return root.min();
    }

    /**
     * Translates the class into something human readable.
     * DO NOT EDIT THIS. Your tests rely on the toString() method.
     */
    @Override
    public String toString() {
        return "{" +
                "order=" + order +
                ", root=" + root +
                '}';
    }

    /**
     * @return Graphical representation of the tree.
     */
    public String display() {
        return this.root.display(0);
    }

    /**
     * Defines the nodes that make up the BTree.
     * You are free to add helper methods to this class to help you accomplish the tasks.
     */
    class BTreeNode {
        /**
         * Fields of the node class.
         */
        private BTreeNode parent;

        private LimitedArrayList<T> keys;               // Keys held by the node.
        private LimitedArrayList<BTreeNode> children;   // Children of the node.

        /**
         * Constructor which initialises the fields.
         * <p>
         * (Readability tip: you can minimise methods using the minimise icon on the left of the method. This will help
         * clear the class for better readability).
         */
        public BTreeNode() {
            /*
                Why do we use LimitedArray (an extension of ArrayList) here instead of
                just initiating an array? For example:
                    this.keys = T[order - 1];
                Well... because Java does not allow this!
                Java arrays at runtime contain information about their type. However,
                with the generic type, 'T', Java does not KNOW information about the type
                it is allocating memory for (when creating the array). Therefore it
                cannot be instantiated.

                You can work around this limitation but your approach will most likely
                be unsafe. Hence, we will use an extension of ArrayLists. As for why
                they don't suffer from this problem: we invite you to do research and see if
                you can find out yourself ;)
            */

            // The limit of keys is actually 'order - 1' but at times it will go over by 1 in which we will need to split.
            this.keys = new LimitedArrayList<>(order);

            // The limit on children is actually 'order' but at times we will go over by 1 in which we will need to split.
            this.children = new LimitedArrayList<>(order + 1);

            this.parent = null;
        }

        /**
         * Adds a key to the node. Splitting if necessary.
         *
         * @param key to be inserted.
         */
        private BTreeNode insert(T key) {// purely insert without splitting, return the inserted BTreeNode.
            /*
                Task 1.
                TODO: complete this method.
                Hint1: check if the current node is a leaf and handle it appropriately.
                Hint2: remember to check if you need to split.
             */


            // Ensure input is not null.
            if (key == null)
                throw new IllegalArgumentException("Input cannot be null");


            // Is a leaf
            if (this.children.size() == 0) {

                addInOrder(this.keys, key);
                return this;

            } else {//Not a leaf, recursion

                // look for the correct child
                if (key.compareTo(this.keys.get(0)) < 0) {//最左
                    return this.children.get(0).insert(key);

                } else if (key.compareTo(this.keys.get(keys.size() - 1)) > 0) {//最右
                    return this.children.get(children.size() - 1).insert(key);

                } else {//在中间
                    for (int i = 0; i + 1 < this.keys.size(); i++) {
                        if (key.compareTo(this.keys.get(i)) > 0 && key.compareTo(this.keys.get(i + 1)) < 0) {
                            int childIndex = i + 1;
                            return this.children.get(childIndex).insert(key);
                        }
                    }
                }
            }


            return null;
        }

        /**
         * @return maximum key of the BTree
         */
        public T max() {
            /*
                Task 2.
                TODO: complete this method.
             */

            if (this.keys.size() == 0) {
                return null;
            }

            // Check if leaf
            if (this.children.size() == 0) {
                // Return minimum value (should be left most).
                return this.keys.get(keys.size()-1);
            } else {
                // Recurse through the leftmost node.
                return this.children.get(children.size()-1).max();
            }



        }

        /**
         * @return minimum key of the BTree
         */
        public T min() {
            // Ensure that the BTreeNode is not empty (note that if there are no keys, then there can't be children).
            if (this.keys.size() == 0) {
                return null;
            }

            // Check if leaf
            if (this.children.size() == 0) {
                // Return minimum value (should be left most).
                return this.keys.get(0);
            } else {
                // Recurse through the leftmost node.
                return this.children.get(0).min();
            }
        }

        /**
         * Translates the class into something human readable.
         * <p>
         * DO NOT EDIT THIS. Your tests rely on the toString() method.
         */
        @Override
        public String toString() {
            return "{" +
                    "keys=" + keys +
                    ", children=" + children +
                    '}';
        }

        /**
         * Graphically visualises the tree for human readability.
         *
         * @param tabs from the left side of the screen.
         * @return graph of the tree.
         */
        public String display(int tabs) {
            // StringBuilder is faster than using string concatenation (which in java makes a new object per concatenation).
            StringBuilder sb = new StringBuilder(keys.toString());
            for (BTreeNode node : children) {
                sb.append("\n").append("\t".repeat(tabs)).append("├─").append(node.display(tabs + 1));
            }
            return sb.toString();
        }
    }


}
