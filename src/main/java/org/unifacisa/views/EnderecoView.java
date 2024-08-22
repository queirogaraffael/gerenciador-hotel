package org.unifacisa.views;

import javax.swing.*;

public class EnderecoView {

    public static String leNumeroCasa() {
        return JOptionPane.showInputDialog("Digite o numero da casa: ");
    }

    public static String leCidade() {
        return JOptionPane.showInputDialog("Digite o nome da cidade: ");
    }

    public static String leRua() {
        return JOptionPane.showInputDialog("Digite o nome da rua: ");
    }

    public static String leBairro() {
        return JOptionPane.showInputDialog("Digite o nome do bairro: ");
    }

    public static String leEstado() {
        return JOptionPane.showInputDialog("Digite o nome do estado: ");
    }


}
