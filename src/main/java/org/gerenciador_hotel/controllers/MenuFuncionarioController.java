package org.gerenciador_hotel.controllers;

import org.gerenciador_hotel.utils.ManipulaData;
import org.gerenciador_hotel.utils.dtos.SelecionaExtratoFuncionarioDTO;
import org.gerenciador_hotel.utils.dtos.SelecionaFuncionarioDTO;
import org.gerenciador_hotel.utils.VerificaCPF;
import org.gerenciador_hotel.constantes.controllers.ConstantesMenuFuncionarioController;
import org.gerenciador_hotel.constantes.modificacoes.ConstantesMenuModificacaoDadosFuncionario;
import org.gerenciador_hotel.dtos.ExtratoFuncionarioDTO;
import org.gerenciador_hotel.dtos.FuncionarioDTO;
import org.gerenciador_hotel.enums.Turno;
import org.gerenciador_hotel.model.domain.entities.Endereco;
import org.gerenciador_hotel.model.domain.entities.ExtratoFuncionario;
import org.gerenciador_hotel.model.domain.entities.Funcionario;
import org.gerenciador_hotel.services.FuncionarioService;
import org.gerenciador_hotel.views.commons.DataViews;
import org.gerenciador_hotel.views.commons.EnderecoViews;
import org.gerenciador_hotel.views.funcionarios.*;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.time.YearMonth;
import java.util.List;

public class MenuFuncionarioController {

    private final FuncionarioService funcionarioService;

    public MenuFuncionarioController(EntityManagerFactory entityManagerFactory) {
        this.funcionarioService = new FuncionarioService(entityManagerFactory);
    }


    public void menuGerenciadorFuncionario() {
        int opcaoMenuGerenciadoFuncionario;

        do {
            opcaoMenuGerenciadoFuncionario = MenuFuncionarioControllerView.exibirMenuTarefasView();

            switch (opcaoMenuGerenciadoFuncionario) {

                case (ConstantesMenuFuncionarioController.CADASTRAR_FUNCIONARIO):
                    cadastraFuncionario();
                    break;

                case (ConstantesMenuFuncionarioController.EDITAR_DADOS_FUNCIONARIO):
                    editarDadosFuncionario();
                    break;

                case (ConstantesMenuFuncionarioController.VISUALIZAR_FUNCIONARIO_CPF):
                    visualizaFuncionarioByCPF();
                    break;

                case (ConstantesMenuFuncionarioController.BUSCA_VISUALIZA_FUNCIONARIO_POR_NOME):
                    buscaFuncionarioPorNome();
                    break;

                case (ConstantesMenuFuncionarioController.EXTRATOS):
                    extratosFuncionario();
                    break;

                default:
                    break;

            }


        } while ( opcaoMenuGerenciadoFuncionario != ConstantesMenuFuncionarioController.VOLTAR);
    }


    private void cadastraFuncionario() {

        Funcionario funcionario = new Funcionario();

        String cpf = LeDadosBasicosFuncionarioViews.leCPFFuncionario();

        if (!VerificaCPF.isCpfValido(cpf)) {
            AlertasFuncionarioViews.exibirAlertaCPFNaoSeguePadrao();
            return;
        }


        if (funcionarioService.haFuncionarioComMesmoCPF(cpf)) {
            AlertasFuncionarioViews.exibirAlertaCPFJaExiste();
            return;
        }

        funcionario.setCpf(cpf);

        String nome = LeDadosBasicosFuncionarioViews.leNomeFuncionario();

        funcionario.setNome(nome);

        String dataNascimento = LeDadosBasicosFuncionarioViews.leDataNascimentoFuncionario();

        if (!ManipulaData.verificaFormatoDataEstaCorreto(dataNascimento)) {
            DataViews.exibirAlertaDataFormatoErrado();
            return;
        }


        if(!ManipulaData.eMaiorDeIdade(ManipulaData.retornaLocalDate(dataNascimento))){
            AlertasFuncionarioViews.exibirAlertaNaoPodeMenorDeIdade();
            return;
        }


        funcionario.setDataNascimento(ManipulaData.retornaLocalDate(dataNascimento));

        String numeroTelefone = LeDadosBasicosFuncionarioViews.leNumeroTelefoneFuncionario();

        funcionario.setNumeroTelefone(numeroTelefone);

        String cargo = LeDadosBasicosFuncionarioViews.leCargoFuncionario();

        funcionario.setCargo(cargo);

        Turno turno = EscolheTurnoTrabalhoView.exibeEEscolheTurnoView();
        funcionario.setTurno(turno);


        int desejaAdicionarEndereco = EnderecoViews.desejaAdicionarEndereco();

        Endereco endereco = new Endereco();

        if (desejaAdicionarEndereco == JOptionPane.YES_OPTION) {
            endereco = adicionarEndereco(endereco);
        } else if (desejaAdicionarEndereco == JOptionPane.CLOSED_OPTION) {
            return;
        }


        funcionario.setEndereco(endereco);

        funcionarioService.adicionarFuncionario(funcionario);

        AlertasFuncionarioViews.exibirAlertaFuncionarioAdicionadoComSucesso();


    }


    private void editarDadosFuncionario() {

        Funcionario funcionario = validaCPFDoFuncionarioERetornaFuncionario();

        if (funcionario != null) {

            exibiOpcoesDeModificacaoDoFuncionarioEModifica(funcionario);
        }


    }


    private void visualizaFuncionarioByCPF() {

        Funcionario funcionario = validaCPFDoFuncionarioERetornaFuncionario();

        if (funcionario != null) {


            PrintaFuncionarioView.exibeFuncionario(funcionario);
        }

    }

    private void buscaFuncionarioPorNome() {
        String nome = LeDadosBasicosFuncionarioViews.leNomeFuncionario();

        List<FuncionarioDTO> funcionarioDTOList = funcionarioService.getFuncionariosDTOByNome(nome);


        if (funcionarioDTOList.isEmpty()) {
            AlertasFuncionarioViews.exibirAlertaSemFuncionariosCorrespondentes();
            return;
        }

        selecionaEExibeFuncionario(funcionarioDTOList);

    }


    private void extratosFuncionario() {

        Funcionario funcionario = validaCPFDoFuncionarioERetornaFuncionario();


        if (funcionario == null) {
            return;
        }


        int opcao = ExtratoViews.exibeEEscolheOpcaoExtratoView();


        if (opcao == 0) {
            visualizaExtrato(funcionario.getCpf());
        } else if (opcao == 1) {
            adicionarExtrato(funcionario);
        }
    }


    public void selecionaEExibeFuncionario(List<FuncionarioDTO> funcionarios) {
        String cpf = SelecionaFuncionarioDTO.selecionaCPFFuncionario(funcionarios);

        Funcionario funcionario = funcionarioService.getFuncionarioByCPF(cpf);
        PrintaFuncionarioView.exibeFuncionario(funcionario);

    }


    public Endereco adicionarEndereco(Endereco endereco) {

        String rua = EnderecoViews.leRua();
        String numeroCasa = EnderecoViews.leNumeroCasa();
        String cidade = EnderecoViews.leCidade();
        String bairro = EnderecoViews.leBairro();
        String estado = EnderecoViews.leEstado();

        endereco.setRua(rua);
        endereco.setNumero(numeroCasa);
        endereco.setCidade(cidade);
        endereco.setBairro(bairro);
        endereco.setEstado(estado);

        return endereco;

    }


    public void exibiOpcoesDeModificacaoDoFuncionarioEModifica(Funcionario funcionario) {
        int opcao;

        do {
            opcao = MenuModificacaoDadosFuncionarioView.exibeOpcoesModificarDadosView();

            switch (opcao) {
                case (ConstantesMenuModificacaoDadosFuncionario.MODIFICA_NOME):
                    String nome = LeDadosBasicosFuncionarioViews.leNomeFuncionario();
                    funcionario.setNome(nome);
                    break;

                case (ConstantesMenuModificacaoDadosFuncionario.MODIFICA_NUMERO_TELEFONE):
                    String numeroTelefone = LeDadosBasicosFuncionarioViews.leNumeroTelefoneFuncionario();
                    funcionario.setNumeroTelefone(numeroTelefone);
                    break;
                case (ConstantesMenuModificacaoDadosFuncionario.MODIFICA_TURNO):
                    Turno turno = EscolheTurnoTrabalhoView.exibeEEscolheTurnoView();
                    funcionario.setTurno(turno);
                    break;
                case (ConstantesMenuModificacaoDadosFuncionario.MODIFICA_CARGO):
                    String cargo = LeDadosBasicosFuncionarioViews.leCargoFuncionario();
                    funcionario.setCargo(cargo);
                    break;
                case (ConstantesMenuModificacaoDadosFuncionario.MODIFICA_ENDERECO):
                    Endereco endereco = new Endereco();
                    adicionarEndereco(endereco);
                    funcionario.setEndereco(endereco);
                    break;

                default:
                    break;
            }

            if (opcao != ConstantesMenuModificacaoDadosFuncionario.VOLTAR) {
                funcionarioService.atualizaFuncionario(funcionario);
                AlertasFuncionarioViews.exibirAlertaDadosFuncionarioModificadoComSucesso();
            }


        } while (opcao != ConstantesMenuModificacaoDadosFuncionario.VOLTAR);
    }


    private void adicionarExtrato(Funcionario funcionario) {

        YearMonth data = LeDadosBasicosFuncionarioViews.leData();


        if (funcionarioService.existeExtratoFuncionarioPorMesAno(funcionario.getCpf(), data)) {
            AlertasFuncionarioViews.exibirAlertaJaExisteExtratoParaMesReferente();
            return;
        }

        double horasTrabalhadas = LeDadosBasicosFuncionarioViews.leHorasTrabalhadas();

        double valorHora = LeDadosBasicosFuncionarioViews.leValorHora();

        double salario = horasTrabalhadas * valorHora;

        ExtratoFuncionario extratoFuncionario = new ExtratoFuncionario();
        extratoFuncionario.setMesReferente(data);
        extratoFuncionario.setValorHora(valorHora);
        extratoFuncionario.setHorasTrabalhadas(horasTrabalhadas);
        extratoFuncionario.setSalario(salario);

        funcionarioService.criaExtratoFuncionario(funcionario, extratoFuncionario);

        AlertasFuncionarioViews.exibirAlertaExtratoCriadoComSucesso();


    }

    private void visualizaExtrato(String cpf) {

        List<ExtratoFuncionarioDTO> extratoFuncionarioDTOS = funcionarioService.getExtratosFuncionarioDTOByCPF(cpf);


        if (extratoFuncionarioDTOS == null || extratoFuncionarioDTOS.isEmpty()) {
            AlertasFuncionarioViews.exibirAlertaSemExtratoParaFuncionario();
            return;
        }

        Long idExtrato = SelecionaExtratoFuncionarioDTO.selecionaExtratoFuncionario(extratoFuncionarioDTOS);

        ExtratoFuncionario extrato = funcionarioService.getExtratoFuncionarioById(idExtrato);

        ExtratoViews.exibeExtratoFuncionario(extrato);


    }

    public Funcionario validaCPFDoFuncionarioERetornaFuncionario() {
        String cpf = LeDadosBasicosFuncionarioViews.leCPFFuncionario();

        if (!VerificaCPF.isCpfValido(cpf)) {
            AlertasFuncionarioViews.exibirAlertaCPFNaoSeguePadrao();
            return null;
        }

        Funcionario funcionario = funcionarioService.getFuncionarioByCPF(cpf);

        if (funcionario == null) {
            AlertasFuncionarioViews.exibirAlertaCPFNaoExiste();
            return null;
        }

        return funcionario;
    }


}
