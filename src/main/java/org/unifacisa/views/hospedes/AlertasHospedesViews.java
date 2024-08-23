package org.unifacisa.views.hospedes;

import org.unifacisa.utils.VerificaCPF;

import javax.swing.*;

public class AlertasHospedesViews {

    private static final String ALERTA = "Alerta";

    public static void exibirAlertaNomeNaoPodeSerVazio() {
        JOptionPane.showMessageDialog(null, "O nome do hospede nao pode ser vazio.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }


    public static void exibirAlertaCPFNaoPodeSerVazio() {
        JOptionPane.showMessageDialog(null, "CPF do hospede nao pode ser vazio.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlertaCPFNaoSeguePadrao() {
        JOptionPane.showMessageDialog(null, "CPF do hospede nao segue padrao: " + VerificaCPF.padraoCPF, ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlertaCPFJaExiste() {
        JOptionPane.showMessageDialog(null, "CPF de hospede ja cadastrado.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }


    public static void exibirAlertaNumeroTelefoneVazio() {
        JOptionPane.showMessageDialog(null, "Numero de telefone do hospede nao pode ser vazio.", ALERTA
                , JOptionPane.ERROR_MESSAGE);

    }

    public static void exibirAlertaSemHospedesCorrespondentes() {
        JOptionPane.showMessageDialog(null, "Sem hospedes correspondentes para esse nome.", ALERTA
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
}
