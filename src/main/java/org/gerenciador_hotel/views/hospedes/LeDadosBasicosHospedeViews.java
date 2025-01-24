package org.gerenciador_hotel.views.hospedes;

import org.gerenciador_hotel.utils.ManipulaData;
import org.gerenciador_hotel.utils.VerificaCPF;

import javax.swing.*;

public class LeDadosBasicosHospedeViews {

    private LeDadosBasicosHospedeViews() {
    }

    public static String leNomeHospede() {
        String nome;
        while (true) {
            nome = JOptionPane.showInputDialog("Nome do hospede (obrigatorio):");
            if (nome != null && !nome.trim().isEmpty()) {
                return nome;
            } else {
                JOptionPane.showMessageDialog(null, "Nome do hospede e obrigatorio. Por favor, insira um nome valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leCPFHospede() {
        String cpf;
        while (true) {
            cpf = JOptionPane.showInputDialog("Digite o CPF no formato: " + VerificaCPF.PADRAO_CPF);
            if (cpf != null && !cpf.trim().isEmpty()) {
                return cpf;
            } else {
                JOptionPane.showMessageDialog(null, "CPF e obrigatorio. Por favor, insira um CPF valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leDataNascimentoHospede() {
        String data;
        while (true) {
            data = JOptionPane.showInputDialog("Digite a data de nascimento do hospede no formato: " + ManipulaData.FORMATO_DATA);
            if (data != null && !data.trim().isEmpty()) {
                return data;
            } else {
                JOptionPane.showMessageDialog(null, "Data de nascimento do hospede e obrigatoria. Por favor, insira uma data valida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leNumeroTelefoneHospede() {
        String telefone;
        while (true) {
            telefone = JOptionPane.showInputDialog("Telefone (obrigatorio):");
            if (telefone != null && !telefone.trim().isEmpty()) {
                return telefone;
            } else {
                JOptionPane.showMessageDialog(null, "Telefone e obrigatorio. Por favor, insira um telefone valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static int leNumeroHospedesQuarto(int capacidadeMaximaHospedePorQuarto) {
        String entrada;
        while (true) {
            entrada = JOptionPane.showInputDialog("Numero de hospedes (capacidade maxima: " + capacidadeMaximaHospedePorQuarto + "):");
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, insira um numero inteiro valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
