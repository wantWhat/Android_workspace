package com.example.pc.kotlindemoclass.binarytreedemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pc.kotlindemoclass.Book;
import com.example.pc.kotlindemoclass.R;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BinraryTreeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Intent intent = getIntent();
        Book book = intent.getParcelableExtra("data");
        Log.i("demo",book.toString());
        setTreeData();
    }

    private void setTreeData() {
        Tree root = new Tree('a');
        Tree left = new Tree('b');
        Tree right = new Tree('c');
        root.setLeftTree(left);
        root.setRightTree(right);
        Tree subLeft = new Tree('e');
        Tree subRight = new Tree('f');
        left.setRightTree(subRight);
        left.setLeftTree(subLeft);
        Tree subLeft1 = new Tree('g');
        Tree subRight1 = new Tree('h');
        right.setRightTree(subRight1);
        right.setLeftTree(subLeft1);
        subRight1.setLeftTree(new Tree('i'));
        subRight1.setRightTree(new Tree('j'));
        //fun(root);
        fun1(root);
        //fun2(root);
    }
    //二叉树递归遍历（前序后序中序差别只是入栈的顺序不同）
    private void fun(Tree tree) {
        if (tree == null) {
            return;
        }
        tree.printData();
        fun(tree.getLeftTree());
        fun(tree.getRightTree());

    }

    //二叉树非递归遍历（前序后序中序差别只是入栈的顺序不同）
    private void fun1(Tree tree) {
        Stack<Tree> stack = new Stack<>();
        if (tree != null) {
            stack.push(tree);
            while (!stack.empty()) {
                tree = stack.pop();
                tree.printData();
                if (tree.getLeftTree() != null) {
                    stack.push(tree.getLeftTree());
                }
                if (tree.getRightTree()!=null) {
                    stack.push(tree.getRightTree());
                }
            }
        }
    }
    //二叉树层级遍历
    private void fun2(Tree tree) {
        if (tree ==null) {
            return;
        }
        Queue<Tree> queue = new ArrayDeque<>();
        queue.offer(tree);
        while (!queue.isEmpty()) {
            tree = queue.poll();
            tree.printData();
            if (tree.getLeftTree() != null) {
                queue.offer(tree.getLeftTree());
            }
            if (tree.getRightTree() != null) {
                queue.offer(tree.getRightTree());
            }
        }
    }
}
