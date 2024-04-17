import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Stack;

/**
 * Simple binary trees.
 */
public class BinaryTree<T> implements Iterable<T> {

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The root of the tree
   */
  BinaryTreeNode<T> root;

  /**
   * The number of values in the tree.
   */
  int size;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty, tree.
   */
  public BinaryTree() {
    this.size = 0;
    this.root = null;
  } // BinaryTree

  /**
   * Create a new, somewhat balanced, tree.
   */
  public BinaryTree(T[] values) {
    this.size = values.length;
    this.root = makeTree(values, 0, values.length);
  } // BinaryTree(T[])

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

/**
 * Print all of the elements in some order or other.
 * 
 * Note: We are trying to avoid recursion.
 */
public void print(PrintWriter pen) {
  // A collection of the remaining things to print
  Stack<Object> remaining = new Stack<Object>();
  remaining.push(this.root);
  // Invariants: 
  //   remaining only contains Strings or Nodes
  //   All values in the tree are either
  //     (a) already printed,
  //     (b) in remaining, or
  //     (c) in or below a node in remaining
  while (!remaining.isEmpty()) {
    Object next = remaining.pop();
    if (next instanceof BinaryTreeNode<?>) {
      @SuppressWarnings("unchecked")
      BinaryTreeNode<T> node = (BinaryTreeNode<T>) next;
      if (node.right != null) {
        remaining.push(node.right);
      } // if (node.right != null)
      if (node.left != null) {
        remaining.push(node.left);
      } // if (node.left != null)
      remaining.push(node.value);
    } else {
      pen.print(next);
      pen.print(" ");
    } // if/else
  } // while
  pen.println();
} // print(PrintWriter)

  /**
   * Dump the tree to some output location.
   */
  public void dump(PrintWriter pen) {
    dump(pen, root, "");
  } // dump(PrintWriter)

  public void elements01(PrintWriter pen){
    String str = new String();
    str = elements01(pen, root, str);
    pen.println(str);
  }

  public void elements02(PrintWriter pen){
    String str = new String();
    str = elements02(pen, root, str);
    pen.println(str);
  }

  /**
   * Get an iterator for the tree.
   */
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      public boolean hasNext() {
        // STUB
        return false;
      } // hasNext()

      public T next() {
        // STUB
        return null;
      } // next()
    }; // new Iterator()
  } // iterator()

  // +---------+-----------------------------------------------------
  // | Helpers |
  // +---------+

  /**
   * Dump a portion of the tree to some output location.
   */
  void dump(PrintWriter pen, BinaryTreeNode<T> node, String indent) {
    if (node == null) {
      pen.println(indent + "<>");
    } else {
      pen.println(indent + node.value);
      if ((node.left != null) || (node.right != null)) {
        dump(pen, node.left, indent + "  ");
        dump(pen, node.right, indent + "  ");
      } // if has children
    } // else
  } // dump

  String elements01(PrintWriter pen, BinaryTreeNode<T> node, String str){
    if(node == null){
      return str;
    } else {
      str = str + node.value + " ";
      if ((node.left != null) || (node.right != null)){
        str = elements01(pen, node.left, str);
        str = elements01(pen, node.right, str);
      }
      return str;
    }
  }

  String elements02(PrintWriter pen, BinaryTreeNode<T> node, String str){
    if (node == null){
      return str;
    } else {
      if ((node.left != null) || (node.right != null)){
        str = elements02(pen, node.left, str);
        str = str + node.value + " ";
        str = elements02(pen, node.right, str);
      } else {
        str = str + node.value + " ";
      }
      return str;
    }
  }

  /**
   * Build a tree from a subarray from lb (inclusive) to ub (exclusive).
   */
  BinaryTreeNode<T> makeTree(T[] values, int lb, int ub) {
    if (ub <= lb) {
      return null;
    } else if (ub - lb == 1) {
      return new BinaryTreeNode<T>(values[lb]);
    } else {
      int mid = lb + (ub - lb) / 2;
      return new BinaryTreeNode<T>(values[mid], makeTree(values, lb, mid),
          makeTree(values, mid + 1, ub));
    } // if/else
  } // makeTree(T[], int, int)

} // class BinaryTree
