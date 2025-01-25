package org.gerenciador_hotel.ui.quartos;

import javax.swing.*;

public class LeDadosBasicosQuartoView {

    private LeDadosBasicosQuartoView() {
    }


    public static int leNumeroQuarto() {
        int numero;
        while (true) {
            try {
                numero = Integer.parseInt(JOptionPane.showInputDialog("Numero do quarto (obrigatorio):"));
                return numero;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, insira um numero inteiro valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static int leCapacidadeQuarto() {
        String entrada;
        while (true) {
            entrada = JOptionPane.showInputDialog("Capacidade do quarto (obrigatoria):");
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, insira um numero inteiro valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static double lePrecoQuarto() {
        String entrada;
        while (true) {
            entrada = JOptionPane.showInputDialog("Preco da diaria do quarto (obrigatorio):");
            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, insira um numero valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


}
