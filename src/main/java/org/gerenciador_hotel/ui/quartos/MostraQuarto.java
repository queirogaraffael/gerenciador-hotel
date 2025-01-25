package org.gerenciador_hotel.ui.quartos;

import org.gerenciador_hotel.model.domain.entities.Quarto;

import javax.swing.*;

public class MostraQuarto {

    private MostraQuarto() {
    }

    public static void printaQuarto(Quarto quarto){
        JOptionPane.showMessageDialog(null, quarto);
    }
}
