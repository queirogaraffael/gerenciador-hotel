package org.unifacisa.views.quartos;

import javax.swing.*;

public class LeDadosBasicosQuartoView {

    private LeDadosBasicosQuartoView() {
    }

    public static String leNumeroQuarto() {
        return JOptionPane.showInputDialog("Digite o numero do quarto: ");
    }

    public static int leCapacidadeQuarto() {
        return Integer.parseInt(JOptionPane.showInputDialog("Digite a capacidade do quarto: "));
    }


    public static double lePrecoQuarto() {
        return Double.parseDouble(JOptionPane.showInputDialog("Digite o preco do quarto: "));
    }


}
