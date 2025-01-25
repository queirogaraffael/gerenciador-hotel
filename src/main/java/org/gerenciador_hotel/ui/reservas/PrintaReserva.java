package org.gerenciador_hotel.ui.reservas;

import org.gerenciador_hotel.model.domain.entities.Reserva;

import javax.swing.*;

public class PrintaReserva {

    public static void exibeReserva(Reserva reserva) {
        JOptionPane.showMessageDialog(null, reserva);
    }
}
