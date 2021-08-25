# Specifics/Description of Implementations
The aim of this document is to provide you with a quick overview of the of this implementation of trees, BSTs and AVL trees. Certain attributes are mentioned to prevent any confusion, such as whether the left child or right child are greater than the current node's value.

Anything not mentioned here can be assumed to be the same as a typical tree structure definition. E.g. each tree has a left and right child, for the AVL tree: rotation is necessary to maintain balance, etc.

## Tree Implementation
<b>Immutable</b><br>
The tree is an immutable implementation. That is, the state of the object cannot be modified after it is created. What this means in the context of your 'insert' code is that you must change the reference to the object, not the object itself!

<b>Empty Trees</b><br>
Leaf nodes are an instance of 'EmptyTree' as defined by the class extending the Tree interface.

## Binary Search Tree Implementation
Inherits all implementation requirements of Tree.

<b>Sorted</b><br>
The values of the all left children of the current node are less than the current node's value.<br>
The values of the all right children of the current node are greater than the current node's value.

<b>Left Insertion Preference</b><br>
***WARNGING: only for BST, AVL-Tree handles duplicates differently.*** <br>
If the element being inserted is equal to the current node value: insert to the left of the node.

## AVL Tree Implementation
Inherits all implementation requirements of Binary Search Tree with the following exceptions:
- Left Insertion Preference.

<b>Balance Factor</b><br>
The balance factor is the height difference between the left and right subtrees of the current node. It will be defined as:<br>
Balance factor = height(left subtree) - height(right subtree)<br>
The balance factor for any node must be between -1 and 1.

<b>No Duplicates</b><br>
The AVL-Tree implementation does not add duplicate values. This is the same approach to duplicates as sets. <br>
For example:
<br>
If we insert the values: 1, 2, 3 and 1. <br>
The tree should only contain: 1, 2 and 3. The last insertion is a duplicate (1) and is not added again.
