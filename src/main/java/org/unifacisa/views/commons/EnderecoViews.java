package org.unifacisa.views.commons;

import javax.swing.*;

public class EnderecoViews {

    private EnderecoViews() {
    }

    public static String leNumeroCasa() {
        return JOptionPane.showInputDialog("Numero: ");
    }

    public static String leCidade() {
        return JOptionPane.showInputDialog("Cidade: ");
    }

    public static String leRua() {
        return JOptionPane.showInputDialog("Rua: ");
    }

    public static String leBairro() {
        return JOptionPane.showInputDialog("Bairro: ");
    }

    public static String leEstado() {
        return JOptionPane.showInputDialog("Estado: ");
    }

    public static int desejaAdicionarEndereco() {
        return JOptionPane.showConfirmDialog(null, "Deseja adicionar endereco ?", "Adicionar Endereco", JOptionPane.YES_NO_OPTION);

    }
}
