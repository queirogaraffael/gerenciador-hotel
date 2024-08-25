package org.unifacisa.views.funcionarios;

import org.unifacisa.utils.VerificaCPF;

import javax.swing.*;

public class AlertasFuncionarioViews {

    private static final String ALERTA = "Alerta";

    private AlertasFuncionarioViews() {
    }

    public static void exibirAlertaNomeNaoPodeSerVazio() {
        JOptionPane.showMessageDialog(null, "Nome nao pode ser vazio.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }


    public static void exibirAlertaCPFNaoPodeSerVazio() {
        JOptionPane.showMessageDialog(null, "CPF nao pode ser vazio.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlertaCPFNaoSeguePadrao() {
        JOptionPane.showMessageDialog(null, "CPF nao segue padrao: " + VerificaCPF.PADRAO_CPF, ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlertaCPFJaExiste() {
        JOptionPane.showMessageDialog(null, "CPF ja cadastrado.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }


    public static void exibirAlertaCargoVazio() {

        JOptionPane.showMessageDialog(null, "Cargo nao pode ser vazio.", ALERTA
                , JOptionPane.ERROR_MESSAGE);

    }

    public static void exibirAlertaNumeroTelefoneVazio() {
        JOptionPane.showMessageDialog(null, "Numero de telefone nao pode ser vazio.", ALERTA
                , JOptionPane.ERROR_MESSAGE);

    }

    public static void exibirAlertaSemFuncionariosCorrespondentes() {
        JOptionPane.showMessageDialog(null, "Sem funcionario(s) correspondente(s) para esse nome.", ALERTA
                , JOptionPane.ERROR_MESSAGE);

    }


    public static void exibirAlertaCPFNaoExiste() {
        JOptionPane.showMessageDialog(null, "Nenhum funcionario correspondente.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }


    public static void exibirAlertaSemExtratoParaFuncionario() {
        JOptionPane.showMessageDialog(null, "Sem extrato no sistema para este funcionario.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }


    public static void exibirAlertaJaExisteExtratoParaMesReferente() {
        JOptionPane.showMessageDialog(null, "Extrato do mes ja cadastrado.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }


    public static void exibirAlertaDdosFuncionarioModificadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Dados do funcionario modificado com sucesso.");
    }

    public static void exibirAlertaExtratoCriadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Extrato criado com sucesso.");
    }

    public static void exibirAlertaFuncionarioAdicionadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Funcionario adicionado com sucesso.");
    }

    public static void exibirAlertaNaoPodeMenorDeIdade() {


        JOptionPane.showMessageDialog(null, "Nao pode cadastrar funcionario menor de idade.", ALERTA
                , JOptionPane.ERROR_MESSAGE);
    }
}
