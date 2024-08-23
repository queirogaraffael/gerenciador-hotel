package org.unifacisa.views.quartos;

import org.unifacisa.model.domain.entities.Quarto;

import javax.swing.*;

public class MostraQuarto {

    private MostraQuarto() {
    }

    public static void printaQuarto(Quarto quarto){
        JOptionPane.showMessageDialog(null, quarto);
    }
}
