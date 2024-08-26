package org.unifacisa.views.quartos;

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
}
