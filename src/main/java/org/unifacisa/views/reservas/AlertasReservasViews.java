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

    public static void exibirAlertaSemReservasCadastradasOuEmUso() {
        JOptionPane.showMessageDialog(null, "No momento, o hospede nao possui nenhum agendamento ou reserva em uso.");

    }

    public static void exibirAlertaSemReservasCanceladasOuFinalizadas() {
        JOptionPane.showMessageDialog(null, "No momento o hospede nao tem dados no sistemas.");

    }

    public static void exibirAlertaReservaCheckOutRealizadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Check-Out realizado com sucesso.");

    }

    public static void exibirAlertaReservaCheckInRealizadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Check-In realizado com sucesso.");

    }
}
