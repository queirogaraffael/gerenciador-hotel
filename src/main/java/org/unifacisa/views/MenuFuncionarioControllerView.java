package org.unifacisa.views;

import org.unifacisa.commons.utils.ManipulaData;
import org.unifacisa.commons.utils.VerificaCPF;
import org.unifacisa.enums.Turno;
import org.unifacisa.model.domain.entities.ExtratoFuncionario;
import org.unifacisa.model.domain.entities.Funcionario;

import javax.swing.*;
import java.time.YearMonth;

public class MenuFuncionarioControllerView {

    private static final String MENU_TITLE = "Gerenciador de Funcionarios";
    private static final String MENU_PROMPT = "Escolha uma opcao: ";

    public MenuFuncionarioControllerView() {
    }


    public final Object[] menuOptions = {

            "Cadastrar Funcionario", //
            "Editar Dados do Funcionario", // quais campos ?
            "Visualizar Funcionario pelo CPF",
            "Buscar e Visualizar Funcionario por Nome",
            "Extrato(s)", // criar e visualizar
            "Voltar"
    };


    public String exibirMenuTarefasView() {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                MENU_PROMPT,
                MENU_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuOptions,
                menuOptions[0]
        );

        return opcaoSelecionada.toString();
    }


    private static final Object[] opcoes = {"Diurno", "Noturno"};

    public Turno exibeEEscolheTurnoView() {
        int escolha = JOptionPane.showOptionDialog(
                null,
                "Escolha o turno:",
                "Menu escolha",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );

        if (escolha == 0) {
            return Turno.DIURNO;
        } else {
            return Turno.NOTURNO;
        }


    }


    public int exibeEEscolheOpcaoExtratoView() {
        Object[] opcoes = {"Visualizar", "Adicionar", "Voltar"};

        return JOptionPane.showOptionDialog(
                null,
                "Escolha uma opcao:",
                "Menu escolha",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoes,
                opcoes[0]
        );


    }


    public String leNomeFuncionario() {
        return JOptionPane.showInputDialog("Digite o nome do funcionario: ");
    }

    public void exibirAlertaNomeNaoPodeSerVazio() {
        JOptionPane.showMessageDialog(null, "Nome nao pode ser vazio.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }

    public String leCPFFuncionario() {
        return JOptionPane.showInputDialog("Digite o CPF do funcionario no formato:" + VerificaCPF.padraoCPF);
    }

    public void exibirAlertaCPFNaoPodeSerVazio() {
        JOptionPane.showMessageDialog(null, "CPF nao pode ser vazio.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }

    public void exibirAlertaCPFNaoSeguePadrao() {
        JOptionPane.showMessageDialog(null, "CPF nao segue padrao: " + VerificaCPF.padraoCPF, "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }

    public void exibirAlertaCPFJaExiste() {
        JOptionPane.showMessageDialog(null, "CPF ja cadastrado.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }


    public String leDataNascimentoFuncionario() {
        return JOptionPane.showInputDialog("Digite a data de nascimento do funcionario no formato: " + ManipulaData.FORMATO_DATA);
    }


    public void exibirAlertaDataFormatoErrado() {
        JOptionPane.showMessageDialog(null, "Data no formato errado.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }

    public String leNumeroTelefoneFuncionario() {
        return JOptionPane.showInputDialog("Digite o numero de telefone do funcionario: ");
    }

    public String leCargoFuncionario() {
        return JOptionPane.showInputDialog("Escreva o cargo do funcionario: ");
    }

    public void alertaCargoVazio() {

        JOptionPane.showMessageDialog(null, "Cargo nao pode ser vazio.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);

    }

    public void alertaNumeroTelefoneVazio() {
        JOptionPane.showMessageDialog(null, "Numero de telefone nao pode ser vazio. ", "Alerta"
                , JOptionPane.ERROR_MESSAGE);

    }

    public void alertaFuncionarioAdicionadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Funcionario adicionado com sucesso.");
    }

    public void exibirAlertaSemFuncionariosCorrespondentes() {
        JOptionPane.showMessageDialog(null, "Sem funcionarios correspondentes para esse nome.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);

    }

    public void exibeFuncionario(Funcionario funcionario) {
        JOptionPane.showMessageDialog(null, funcionario);
    }

    public void exibirAlertaCPFNaoExiste() {
        JOptionPane.showMessageDialog(null, "Nenhum funcionario correspondente.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }


    public int desejaAdicionarEndereco() {
        return JOptionPane.showConfirmDialog(null, "Deseja adicionar endereco:", "Escolha um", JOptionPane.YES_NO_OPTION);

    }

    public void exibirAlertaDdosFuncionarioModificadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Dados do funcionario modificado com sucesso.");
    }


    private final Object[] opcoesModificar = {"Nome", "Numero telefone", "Turno", "Cargo", "Endereco", "Voltar"};

    public int exibeOpcoesModificarDadosView() {
        return JOptionPane.showOptionDialog(
                null,
                "Escolha uma opcao: ",
                "Modificar dados do funcionario",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesModificar,
                opcoesModificar[0]
        );

    }

    public void exibirAlertaSemExtratoParaFuncionario() {
        JOptionPane.showMessageDialog(null, "Sem extrato no sistema para este funcionario.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }

    public void exibeExtratoFuncionario(ExtratoFuncionario extratoFuncionario) {
        JOptionPane.showMessageDialog(null, extratoFuncionario);
    }

    public void exibirAlertaNaoHaFuncionarioComEsseCPF() {
        JOptionPane.showMessageDialog(null, "Funcionario invalido.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);


    }


    public YearMonth leData() {

        String data = JOptionPane.showInputDialog("Digite a data no formato: " + ExtratoFuncionario.FORMATO_DATA);
        return YearMonth.parse(data, ExtratoFuncionario.formato);
    }

    public void exibirAlertaJaExisteExtratoParaMesReferente() {
        JOptionPane.showMessageDialog(null, "Ja existe extrato para esse mes.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }


    public double leHorasTrabalhadas() {
        return Double.parseDouble(JOptionPane.showInputDialog("Digite o numero de horas trabalhadas: "));
    }

    public double leValorHora() {
        return Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da hora: "));
    }


    public void exibirAlertaExtratoCriadoComSucesso() {
        JOptionPane.showMessageDialog(null, "Extrato criado com sucesso.");
    }


}
