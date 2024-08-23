package org.unifacisa.views.common;

import javax.swing.*;

public class EnderecoViews {

    private EnderecoViews() {
    }

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

    public static int desejaAdicionarEndereco() {
        return JOptionPane.showConfirmDialog(null, "Deseja adicionar endereco:", "Escolha um", JOptionPane.YES_NO_OPTION);

    }
}
