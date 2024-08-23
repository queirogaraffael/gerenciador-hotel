package org.unifacisa.views.hospedes;

import org.unifacisa.model.domain.entities.Hospede;

import javax.swing.*;

public class PrintaHospede {

    private PrintaHospede() {
    }

    public static void exibeHospede(Hospede hospede) {
        JOptionPane.showMessageDialog(null, hospede);
    }
}
