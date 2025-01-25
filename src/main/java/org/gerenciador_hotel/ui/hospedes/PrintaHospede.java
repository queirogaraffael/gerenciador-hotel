package org.gerenciador_hotel.ui.hospedes;

import org.gerenciador_hotel.model.domain.entities.Hospede;

import javax.swing.*;

public class PrintaHospede {

    private PrintaHospede() {
    }

    public static void exibeHospede(Hospede hospede) {
        JOptionPane.showMessageDialog(null, hospede);
    }
}
