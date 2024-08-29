package org.unifacisa.views.commons;

import javax.swing.*;

public class EnderecoViews {

    private EnderecoViews() {
    }

    public static String leNumeroCasa() {
        String numero;
        while (true) {
            numero = JOptionPane.showInputDialog("Numero (obrigatorio):");
            if (numero != null && !numero.trim().isEmpty()) {
                return numero;
            } else {
                JOptionPane.showMessageDialog(null, "Numero e obrigatorio. Por favor, insira um numero valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leCidade() {
        String cidade;
        while (true) {
            cidade = JOptionPane.showInputDialog("Cidade (obrigatorio):");
            if (cidade != null && !cidade.trim().isEmpty()) {
                return cidade;
            } else {
                JOptionPane.showMessageDialog(null, "Cidade e obrigatoria. Por favor, insira uma cidade valida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leRua() {
        String rua;
        while (true) {
            rua = JOptionPane.showInputDialog("Rua (obrigatoria):");
            if (rua != null && !rua.trim().isEmpty()) {
                return rua;
            } else {
                JOptionPane.showMessageDialog(null, "Rua e obrigatoria. Por favor, insira uma rua valida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leBairro() {
        String bairro;
        while (true) {
            bairro = JOptionPane.showInputDialog("Bairro (obrigatorio):");
            if (bairro != null && !bairro.trim().isEmpty()) {
                return bairro;
            } else {
                JOptionPane.showMessageDialog(null, "Bairro e obrigatorio. Por favor, insira um bairro valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leEstado() {
        String estado;
        while (true) {
            estado = JOptionPane.showInputDialog("Estado (obrigatorio):");
            if (estado != null && !estado.trim().isEmpty()) {
                return estado;
            } else {
                JOptionPane.showMessageDialog(null, "Estado e obrigatorio. Por favor, insira um estado valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static int desejaAdicionarEndereco() {
        return JOptionPane.showConfirmDialog(null, "Deseja adicionar endereco?", "Adicionar Endereco", JOptionPane.YES_NO_OPTION);
    }
}
