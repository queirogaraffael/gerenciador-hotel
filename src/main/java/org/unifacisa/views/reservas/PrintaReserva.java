package org.unifacisa.views.reservas;

import org.unifacisa.model.domain.entities.Reserva;

import javax.swing.*;

public class PrintaReserva {

    public static void exibeReserva(Reserva reserva) {
        JOptionPane.showMessageDialog(null, reserva);
    }
}
