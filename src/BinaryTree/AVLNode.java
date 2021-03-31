package BinaryTree;

import java.nio.BufferUnderflowException;

/**
 * 关于AVL树来说，旋转存在4种情况(AVL树同样遵循左小右大)
 * 1.左左插入   2.左右插入  3.右左插入  4.右右插入
 * 其中1，4镜像对称(single roatation单旋转)，2，3镜像对称(double rotation双旋转)
 *
 */
public class AVLNode<AnyType extends Comparable<? super AnyType>> {

    AVLNode(AnyType theElement){
        this(theElement,null,null);
    }

    public AVLNode(AnyType element, AVLNode<AnyType> left, AVLNode<AnyType> right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    private int height(AVLNode<AnyType> t){
        return t == null ? -1:t.height;
    }

    AnyType             element;
    AVLNode<AnyType>    left;
    AVLNode<AnyType>    right;
    int                 height;

    private AVLNode<AnyType> findMin(AVLNode<AnyType> t)
    {
        if (t == null)
            return null;
        else if(t.left == null)
            return t;
        return findMin(t.left);
    }

    private AVLNode<AnyType> findMax(AVLNode<AnyType> t)
    {
        if(t != null)
            while (t.right != null)
                t = t.right;

        return t;
    }

    private AVLNode<AnyType> insert(AnyType x,AVLNode<AnyType> t){
        if (t == null)
            return new AVLNode<>(x,null,null);

        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
            t.left = insert(x,t.left);
        else if(compareResult > 0)
            t.right = insert(x,t.right);
        else
            ;

        return balance(t);
    }

    private static final int ALLOW_IMBALANCE = 1;



    private AVLNode<AnyType> balance(AVLNode<AnyType> t){
        if(t == null)
            return t;

        if(height(t.left) - height(t.right) > ALLOW_IMBALANCE)
            if (height(t.right.right) >= height(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);

        t.height = Math.max(height(t.left),height(t.right)+1);
        return t;
    }

    private AVLNode<AnyType> rotateWithLeftChild(AVLNode<AnyType> k2){
        AVLNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left),height(k2.right))+1;
        k1.height = Math.max(height(k1.left),k2.height)+1;
        return k1;
    }

    private AVLNode<AnyType> rotateWithRightChild(AVLNode<AnyType> k2){
        AVLNode<AnyType> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left),height(k2.right))+1;
        k1.height = Math.max(height(k1.right),k2.height)+1;
        return k1;
    }

    private AVLNode<AnyType> doubleWithRightChild(AVLNode<AnyType> k3){
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private AVLNode<AnyType> remove(AnyType x,AVLNode<AnyType> t){
        if(t == null)
            return t;
        int compareResult = x.compareTo(t.element);

        if(compareResult < 0)
            t.left = remove(x,t.left);
        else if(compareResult > 0)
            t.right = remove(x,t.right);
        else if(t.left != null && t.right != null){
            t.element = findMin(t.right).element;
            t.right = remove(t.element,t.right);
        }else
            t = (t.left != null) ? t.left : t.right;

        return balance(t);
    }

}
