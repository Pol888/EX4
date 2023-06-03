package ru.geekbrains.lesson4;

public class Tree{
    private Node root;


    public boolean addVal(int value){
        if (root != null){
            boolean res = add(root, value);
            root = rebalanse(root);
            root.color = Color.black;
            return res;
        } else {
            root = new Node();
            root.color = Color.black;
            root.value = value;
            return true;
        }
    }



    private boolean add(Node node, int value){
        if (node.value == value){
            return false;
        } else {
            if (node.value > value){
                if (node.left != null){
                    boolean res = add(node.left, value);
                    node.left = rebalanse(node.left);
                    return res;
                } else {
                    node.left = new Node();
                    node.left.color = Color.red;
                    node.left.value = value;
                    return true;
                }
            } else {
                if (node.right != null) {
                    boolean res = add(node.right, value);
                    node.right = rebalanse(node.right);
                    return res;
                } else {
                    node.right = new Node();
                    node.right.color = Color.red;
                    node.right.value = value;
                    return true;
                }
            }
        }
    }
    private void colorСhange(Node node){
        node.right.color = Color.black;
        node.left.color = Color.black;
        node.color = Color.red;
    }
    private Node leftTurn(Node node){
        Node left = node.left;
        Node betweenCh = left.right;
        left.right = node;
        node.left = betweenCh;
        left.color = node.color;
        node.color = Color.red;
        return left;
    }
    private Node rightTurn(Node node){
        Node right = node.right;
        Node betweenCh = right.left;
        right.left = node;
        node.right = betweenCh;
        right.color = node.color;
        node.color = Color.red;
        return right;
    }
    private Node rebalanse(Node node){
        Node res = node;
        boolean flag;
        do {
            flag = false;
            if (res.right != null && res.right.color == Color.red && (res.left == null || res.left.color == Color.black)){
               flag = true;
               res = rightTurn(res);
            }
            if (res.left != null && res.left.color == Color.red && res.left.left != null && res.left.left.color == Color.red){
                flag = true;
                res = leftTurn(res);
            }
            if (res.left != null && res.left.color == Color.red &&  res.right != null && res.right.color == Color.red){
                flag = true;
                colorСhange(res);
            }
        }
        while (flag);
        return res;
    }





    private class Node {
        private Integer value;
        private Color color;
        private Node left;
        private Node right;



    }





    private enum Color
    {
        black, red
    }


}
