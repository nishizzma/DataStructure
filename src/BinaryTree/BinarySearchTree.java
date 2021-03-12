package BinaryTree;

/**
 * 构建完整的二叉查找🌲
 * @param <AnyType>
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>{

    private static class BinaryNode<AnyType>
    {

        BinaryNode(AnyType theElement)
        {this(theElement,null,null);}

        BinaryNode(AnyType theElement,BinaryNode<AnyType> lt,BinaryNode<AnyType> rt)
        {element = theElement; left = lt; right = rt ;}

        AnyType element;
        BinaryNode<AnyType> left;
        BinaryNode<AnyType> right;
    }

    public BinaryNode<AnyType> root;

    public void
}
