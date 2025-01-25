package org.gerenciador_hotel.ui.quartos;

import javax.swing.*;

public class AlertasQuartoViews {

    private AlertasQuartoViews() {
    }

    public static void exibirAlertaQuartoJaCadastrado() {
        JOptionPane.showMessageDialog(null, "Quarto ja cadastrado.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlertaQuartoCadastradoComSucesso(){
        JOptionPane.showMessageDialog(null, "Quarto cadastrado com sucesso.");
    }

    public static void exibirAlertaSemQuartoDesseTipoCadastrados(){
        JOptionPane.showMessageDialog(null, "Sem quartos desse tipo cadastrado.");
    }

    public static void exibirAlertaSemQuartoDesseTipoDisponiveisParaEssaData() {
        JOptionPane.showMessageDialog(null, "Nao ha quartos disponiveis desse tipo para a data selecionada.");

    }

    public static void capacidadeMaximaDeHospedeUltrapassada(){
        JOptionPane.showMessageDialog(null, "Capacidade maxima de hospedes ultrapassada.");
    }

    public static void exibirAlertaSemQuartosDisponiveisParaManutencao() {
        JOptionPane.showMessageDialog(null, "Sem quartos disponiveis no momento para manutencao.");

    }

    public static void exibirAlertaManutencaoCriadaComSucesso() {
        JOptionPane.showMessageDialog(null, "Manutencao criada com sucesso.");

    }

    public static void exibirAlertaSemQuartoEmManutencao() {
        JOptionPane.showMessageDialog(null, "Sem quarto em manutencao.");

    }

    public static void exibirAlertaQuartoRetiradoDeManutencaoComSucesso() {
        JOptionPane.showMessageDialog(null, "Quarto retirado da manutencao com sucesso.");


    }

    public static void exibirAlertaQuartoAtualizadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Quarto atualizado com sucesso.");

    }
}
