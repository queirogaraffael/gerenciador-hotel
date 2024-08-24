package org.unifacisa.views.reservas;

import javax.swing.*;

public class AlertasReservasViews {

    public static void exibirAlertaReservaCriadaComSucesso() {
        JOptionPane.showMessageDialog(null, "Reserva criada com sucesso.");
    }

    public static void exibirAlertaSemReservaAssociadaAHospede() {
        JOptionPane.showMessageDialog(null, "Sem reservas associadas a hospede.");
    }

    public static void exibirAlertaReservaCanceladaComSucesso() {
        JOptionPane.showMessageDialog(null, "Reserva cancelada com sucesso.");
    }

}
