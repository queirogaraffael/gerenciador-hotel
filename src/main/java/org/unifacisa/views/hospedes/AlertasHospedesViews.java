package org.unifacisa.views.hospedes;

import org.unifacisa.utils.VerificaCPF;

import javax.swing.*;

public class AlertasHospedesViews {

    private static final String ALERTA = "Alerta";

    public static void exibirAlertaCPFNaoSeguePadrao() {
        JOptionPane.showMessageDialog(null, "CPF nao segue padrao: " + VerificaCPF.PADRAO_CPF, ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlertaCPFJaExiste() {
        JOptionPane.showMessageDialog(null, "Hospede ja cadastrado.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }


    public static void exibirAlertaCPFNaoExiste() {
        JOptionPane.showMessageDialog(null, "Nenhum hospede correspondente.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }


    public static void exibirAlertaDadosHospedeModificadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Dados do hospede modificado com sucesso.");
    }


    public static void exibirAlertaHospedeAdicionadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Hospede adicionado com sucesso.");
    }

    public static void exibirAlertaNaoPodeMenorDeIdade() {
            JOptionPane.showMessageDialog(null, "Não é permitido cadastrar um hospede responsavel menor de idade.", ALERTA
                    , JOptionPane.ERROR_MESSAGE);
    }
}
