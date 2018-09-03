package com.example.pc.kotlindemoclass.binarytreedemo;

import android.util.Log;

public class Tree {
    char data;
    private Tree LeftTree;
    private Tree rightTree;

    public char getData() {
        return data;
    }

    public Tree(char data) {
        this.data = data;
    }
    public void setData(char data) {
        this.data = data;
    }

    public void printData() {
        Log.i("demo","data==" + data);
    }
    public Tree getLeftTree() {
        return LeftTree;
    }

    public void setLeftTree(Tree leftTree) {
        LeftTree = leftTree;
    }

    public Tree getRightTree() {
        return rightTree;
    }

    public void setRightTree(Tree rightTree) {
        this.rightTree = rightTree;
    }
}
