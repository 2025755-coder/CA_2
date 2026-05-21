/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author tina
 */

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    private Node root;

    public void insertLevelOrder(Employee employee) {
        Node newNode = new Node(employee);
        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.peek();

            if (current.left == null) {
                current.left = newNode;
                return;
            }

            if (current.right == null) {
                current.right = newNode;
                return;
            }

            queue.poll();
            queue.add(current.left);
            queue.add(current.right);
        }
    }

    public void printLevelOrder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        int level = 1;

        while (!queue.isEmpty()) {
            int nodesAtLevel = queue.size();
            System.out.println("Level " + level + ":");

            for (int i = 0; i < nodesAtLevel; i++) {
                Node current = queue.poll();
                System.out.println("- " + current.employee.getFullName() + " | " + current.employee.getRole()
                        + " | " + current.employee.getDepartment());

                if (current.left != null) {
                    queue.add(current.left);
                }
                if (current.right != null) {
                    queue.add(current.right);
                }
            }

            level++;
        }
    }

    public int height() {
        return height(root);
    }

    public int countNodes() {
        return countNodes(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(height(node.left), height(node.right));
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
}