import java.util.*;

public class BinarySearchTreeWithDups<T extends Comparable<? super T>> extends BinarySearchTree<T>
    implements SearchTreeInterface<T>, java.io.Serializable {

  public BinarySearchTreeWithDups() {
    super();
  }

  public BinarySearchTreeWithDups(T rootEntry) {
    super(rootEntry);
    setRootNode(new BinaryNode<T>(rootEntry));
  }

  @Override
  public T add(T newEntry) {
    if (newEntry != null) {
      if (this.isEmpty()) setRootNode(new BinaryNode<T>(newEntry));
      else addEntryHelperNonRecursive(newEntry);
      return newEntry;
    }
    return null;
  }

  // YOUR CODE HERE! THIS METHOD CANNOT BE RECURSIVE.
  private void addEntryHelperNonRecursive(T newEntry) {
    BinaryNodeInterface<T> currentNode = getRootNode();
    assert currentNode != null;
    boolean found = false;

    while (!found) {
      T currentEntry = currentNode.getData();
      int comparison = newEntry.compareTo(currentEntry);

      if (comparison == 0 || comparison < 0) { // newEntry matches currentEntry;
        if (currentNode.hasLeftChild())
          currentNode = currentNode.getLeftChild();
        else {
          found = true;
          currentNode.setLeftChild(new BinaryNode<T>(newEntry));
        } // end if
      } else {
        if (currentNode.hasRightChild()) {
          currentNode = currentNode.getRightChild();
        } else {
          found = true;
          currentNode.setRightChild(new BinaryNode<T>(newEntry));
        }
      }
    }
  }

  // YOUR CODE HERE! THIS METHOD CANNOT BE RECURSIVE.
  public int countEntriesNonRecursive(T target) {
    // this initial code is meant as a suggestion to get your started- use it or delete it!
    int count = 0;
    BinaryNodeInterface<T> currentNode = getRootNode();

    // consider a loop!

    return count;
  }

  // YOUR CODE HERE! MUST BE RECURSIVE!! YOU CAN ALSO CREATE A PRIVATE HELPER.
  public int countGreaterRecursive(T target) {
    // this initial code is meant as a suggestion to get your started- use it or delete it!
    int count = 0;
    BinaryNodeInterface<T> rootNode = getRootNode();

    // consider a helper method!

    return count;
  }

  // YOUR CODE HERE! MUST BE USE A STACK!!
  public int countGreaterWithStack(T target) {
    // this initial code is meant as a suggestion to get your started- use it or delete it!
    int count = 0;
    BinaryNodeInterface<T> rootNode = getRootNode();
    Stack<BinaryNodeInterface<T>> nodeStack = new Stack<BinaryNodeInterface<T>>();
    nodeStack.push(rootNode);

    // consider a loop based on the stack!
    return count;
  }

  // YOUR EXTRA CREDIT CODE HERE! THIS METHOD MUST BE O(n).
  public int countUniqueValues() {
    return 0;
  }

  public int getLeftHeight() {
    BinaryNodeInterface<T> rootNode = getRootNode();
    if (rootNode == null) {
      return 0;
    } else if (!rootNode.hasLeftChild()) {
      return 0;
    } else {
      return rootNode.getLeftChild().getHeight();
    }
  }

  public int getRightHeight() {
    BinaryNodeInterface<T> rootNode = getRootNode();
    if (rootNode == null) {
      return 0;
    } else if (!rootNode.hasRightChild()) {
      return 0;
    } else {
      return rootNode.getRightChild().getHeight();
    }
  }


}