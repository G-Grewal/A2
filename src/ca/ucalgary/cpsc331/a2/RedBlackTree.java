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
        if(member(k)){
            return false;
        }

        // create new node
        Node newNode = new Node(k);
        newNode.left = NIL;
        newNode.right = NIL;
        newNode.red = true; // new nodes are always red

        Node current = root;  // node being compared with k
        Node parent = NIL;    // will be the parent of k

        while(current != NIL){
            current = parent;
            if(k < current.key){


            }
        }

        return false;
    }

    public boolean FixInsert(){
        return false;
    }

    // delete(k): deletes k and returns true if k is
    // a member of the dictionary; otherwise returns
    // false without deleting k.
    @Override
    public boolean delete(int k) {
        return false;
    }

    // member(k): returns true iff k is a member.
    @Override
    public boolean member(int k) {
        return false;
    }
}
