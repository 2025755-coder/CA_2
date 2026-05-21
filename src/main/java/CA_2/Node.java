/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CA_2;

/**
 *
 * @author tina
 */

final class Node {
    final Employee employee;
    Node left;
    Node right;

    Node(Employee employee) {
        this.employee = employee;
    }
}