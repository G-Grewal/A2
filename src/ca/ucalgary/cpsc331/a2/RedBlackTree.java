package ca.ucalgary.cpsc331.a2;

public class RedBlackTree implements Dictionary {

    // Root of the red-black tree
    private Node root;

    // Sentinel NIL node (all leaves point to this)
    private Node NIL;

    // initializes an empty red-black tree
    public RedBlackTree(){
        NIL = new Node(0); // sentinel NIL node
        NIL.red = false;
        NIL.left = null;
        NIL.right = null;
        NIL.parent = null;

        root = NIL; // initialize empty tree
    }


    // empty(): returns true iff the dictionary is empty.
    @Override
    public boolean empty() {
        return root == NIL;
    }

    // insert(k): inserts k and returns true if k is
    // not already in the dictionary; otherwise
    // returns false without inserting k.
    @Override
    public boolean insert(int k) {

        // check if key already exists
        if (member(k)) {
            return false;
        }

        // create new node
        Node newNode = new Node(k);
        newNode.left = NIL;
        newNode.right = NIL;
        newNode.red = true; // new nodes are always red

        Node current = root;  // node being compared with k
        Node parent = NIL;    // will be the parent of k

        while (current != NIL) {   // descend until reaching sentinel ( current points to sentinel at end but parent is on above sentinal )
            current = parent;
            if (k < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        newNode.parent = parent;  // found the location - insert k with parent

        if (parent == NIL) {
            root = newNode; // tree was empty
        } else if (k < parent.key) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
        insertFixup(newNode);  // fix property
        return true;
    }

    // Fixes red-black property after insertion
    public void insertFixup(Node z){
        while(z.parent.red){    // new node is always red
            if(z.parent == z.parent.parent.left){  // is z's parent a left child?
                Node uncle = z.parent.parent.right; // set uncle node

                if(uncle.red){ // are z's parent and uncle both red?
                    // case 1 (uncle is red)
                    z.parent.red = false;
                    uncle.red = false;
                    z.parent.parent.red = true;
                    z = z.parent.parent; // after re-coloring z becomes grandparent

                }else{ // uncle is black
                    // case 2 (uncle is black and z is right child)
                    if(z == z.parent.right){  // check if z is right child
                        z = z.parent; // move up z pointer
                        leftRotate(z);
                    }
                    // case 3 (uncle is black and z is left child)
                    z.parent.red = false;  // Z's parent recolor to black
                    z.parent.parent.red = true; // Z's grandparent recolor to red
                    rightRotate(z.parent.parent);  // rotate right on grandparent of z
                }
            } else{ // same as before but right and left exchanged (Z's parent is right child)
                Node uncle = z.parent.parent.left;
                    if(uncle.red){ // case 1
                        z.parent.red = false;
                        uncle.red = false;
                        z.parent.parent.red = true;
                        z = z.parent.parent; // after re-coloring z becomes grandparent
                    } else{ // case 2 (uncle is black)
                        if(z == z.parent.left){ // check if z is a left child
                            z = z.parent;
                            rightRotate(z);
                        }
                        // case 3 (uncle is black)
                        z.parent.red = false;
                        z.parent.parent.red = true;
                        leftRotate(z.parent.parent);
                    }

            }
        }
        root.red = false;   // root is alaways black
    }


    // left rotation
    private void leftRotate(Node x) {
        Node y = x.right; // x is parent, y is right child
        x.right = y.left; // turns y's left subtree into x's right subtree
        if(y.left != NIL) { // if y's left subtree is not empty
            y.left.parent = x; // then x becomes the parent of the subtree's root
        }
        y.parent = x.parent; // x's parent becomes y's parent
        if(x.parent == NIL){ // if x was the root
            root = y; // make y the root
        } else if(x == x.parent.left){  // otherwise, if x was a left child
            x.parent.left = y; // then y becomes a left child
        } else{
            x.parent.right = y; // otherwise, x was a right child now y is
        }
        y.left = x; // make x become y's left child
        x.parent = y; // make y become x's parent
    }

    private void rightRotate(Node y){
        Node x = y.left; // y is the parent, x is a left child
        y.left = x.right; // make x's right subtree into y's left subtree
        if(x.right != NIL){ // if x's right subtree is not empty
            x.right.parent = y;  // then y becomes the parent of the subtrees root
        }
        x.parent = y.parent; // make y's parent x's parent
        if(y.parent == NIL){ // if y is the root
            root = x; // then x becomes the root
        } else if(y == y.parent.right){ // otherwise, if y was a right child
            y.parent.right = x; // then x becomes a right child
        } else{ // if y is a left child
            y.parent.left = x; // then x becomes a left child
        }
        x.right = y; // make y become x's right child
        y.parent = x; // make y's parent become x
    }

    // delete(k): deletes k and returns true if k is
    // a member of the dictionary; otherwise returns
    // false without deleting k.
    @Override
    public boolean delete(int k) {
        return false;
    }

    // Replaces subtree rooted u with subtree rooted at v
    private void trasnplant(Node u, Node v){
        if(u.parent == NIL) {  // u is the root
            root = v;
        } else if(u == u.parent.left){  // u is the parents left child
            u.parent.left = v;
        } else{
            u.parent.right = v;  // u is the parents right child
        }
        v.parent = u.parent;
    }

    // member(k): returns true iff k is a member.
    @Override
    public boolean member(int k) {
        return false;
    }
}
