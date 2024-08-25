package org.unifacisa.views.quartos;

import javax.swing.*;

public class LeDadosBasicosQuartoView {

    private LeDadosBasicosQuartoView() {
    }

    public static String leNumeroQuarto() {
        return JOptionPane.showInputDialog("Numero do quarto: ");
    }

    public static int leCapacidadeQuarto() {
        return Integer.parseInt(JOptionPane.showInputDialog("Capacidade do quarto: "));
    }


    public static double lePrecoQuarto() {
        return Double.parseDouble(JOptionPane.showInputDialog("Preco da diaria do quarto: "));
    }


}
