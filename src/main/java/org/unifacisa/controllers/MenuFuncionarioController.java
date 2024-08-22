package org.unifacisa.controllers;

import org.unifacisa.commons.utils.ManipulaData;
import org.unifacisa.commons.utils.SelecionaExtratoFuncionarioDTO;
import org.unifacisa.commons.utils.SelecionaFuncionarioDTO;
import org.unifacisa.commons.utils.VerificaCPF;
import org.unifacisa.constantes.ConstantesMenuFuncionarioController;
import org.unifacisa.constantes.ConstantesMenuModificacaoDadosFuncionario;
import org.unifacisa.dtos.ExtratoFuncionarioDTO;
import org.unifacisa.dtos.FuncionarioDTO;
import org.unifacisa.enums.Turno;
import org.unifacisa.model.domain.entities.Endereco;
import org.unifacisa.model.domain.entities.ExtratoFuncionario;
import org.unifacisa.model.domain.entities.Funcionario;
import org.unifacisa.services.FuncionarioService;
import org.unifacisa.views.EnderecoView;
import org.unifacisa.views.MenuFuncionarioControllerView;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.time.YearMonth;
import java.util.List;

public class MenuFuncionarioController {

    private final MenuFuncionarioControllerView menuFuncionarioControllerView;
    private final FuncionarioService funcionarioService;

    public MenuFuncionarioController(EntityManagerFactory entityManagerFactory) {
        this.menuFuncionarioControllerView = new MenuFuncionarioControllerView();
        this.funcionarioService = new FuncionarioService(entityManagerFactory);
    }


    public void menuGerenciadorFuncionario() {
        String opcaoMenuGerenciadoFuncionario;

        do {
            opcaoMenuGerenciadoFuncionario = menuFuncionarioControllerView.exibirMenuTarefasView();

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


        } while (!opcaoMenuGerenciadoFuncionario.equals(ConstantesMenuFuncionarioController.VOLTAR));
    }


    private void cadastraFuncionario() {

        Funcionario funcionario = new Funcionario();

        String cpf = menuFuncionarioControllerView.leCPFFuncionario();

        if (cpf == null || cpf.trim().isEmpty()) {
            menuFuncionarioControllerView.exibirAlertaCPFNaoPodeSerVazio();
            return;
        }


        if (!VerificaCPF.isCpfValido(cpf)) {
            menuFuncionarioControllerView.exibirAlertaCPFNaoSeguePadrao();
            return;
        }


        if (funcionarioService.verificaSeHaFuncionarioComMesmoCPF(cpf)) {
            menuFuncionarioControllerView.exibirAlertaCPFJaExiste();
            return;
        }

        funcionario.setCpf(cpf);

        String nome = menuFuncionarioControllerView.leNomeFuncionario();

        if (nome == null || nome.trim().isEmpty()) {
            menuFuncionarioControllerView.exibirAlertaNomeNaoPodeSerVazio();
            return;
        }

        funcionario.setNome(nome);

        String dataNascimento = menuFuncionarioControllerView.leDataNascimentoFuncionario();

        if (!ManipulaData.verificaFormatoDataEstaCorreto(dataNascimento)) {
            menuFuncionarioControllerView.exibirAlertaDataFormatoErrado();
            return;
        }

        funcionario.setDataNascimento(ManipulaData.retornaLocalDate(dataNascimento));

        String numeroTelefone = menuFuncionarioControllerView.leNumeroTelefoneFuncionario();

        if (numeroTelefone == null || numeroTelefone.trim().isEmpty()) {
            menuFuncionarioControllerView.alertaNumeroTelefoneVazio();
            return;
        }

        funcionario.setNumeroTelefone(numeroTelefone);


        String cargo = menuFuncionarioControllerView.leCargoFuncionario();

        if (cargo == null || cargo.trim().isEmpty()) {
            menuFuncionarioControllerView.alertaCargoVazio();
            return;
        }

        funcionario.setCargo(cargo);

        Turno turno = menuFuncionarioControllerView.exibeEEscolheTurnoView();
        funcionario.setTurno(turno);


        int desejaAdicionarEndereco = menuFuncionarioControllerView.desejaAdicionarEndereco();

        Endereco endereco = new Endereco();

        if (desejaAdicionarEndereco == JOptionPane.YES_OPTION) {
            endereco = adicionarEndereco(endereco);
        } else if (desejaAdicionarEndereco == JOptionPane.CLOSED_OPTION) {
            return;
        }


        funcionario.setEndereco(endereco);

        funcionarioService.adicionarFuncionario(funcionario);

        menuFuncionarioControllerView.alertaFuncionarioAdicionadoComSucesso();


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


            menuFuncionarioControllerView.exibeFuncionario(funcionario);
        }

    }

    private void buscaFuncionarioPorNome() {
        String nome = menuFuncionarioControllerView.leNomeFuncionario();

        if (nome == null || nome.trim().isEmpty()) {
            menuFuncionarioControllerView.exibirAlertaNomeNaoPodeSerVazio();
            return;
        }

        List<FuncionarioDTO> funcionarioDTOList = funcionarioService.getFuncionariosDTOByNome(nome);


        if (funcionarioDTOList.isEmpty()) {
            menuFuncionarioControllerView.exibirAlertaSemFuncionariosCorrespondentes();
            return;
        }

        selecionaEExibeFuncionario(funcionarioDTOList);

    }


    private void extratosFuncionario() {

        Funcionario funcionario = validaCPFDoFuncionarioERetornaFuncionario();


        if (funcionario == null) {
            return;
        }


        int opcao = menuFuncionarioControllerView.exibeEEscolheOpcaoExtratoView();


        if (opcao == 0) {
            visualizaExtrato(funcionario.getCpf());
        } else if (opcao == 1) {
            adicionarExtrato(funcionario);
        }
    }


    public void selecionaEExibeFuncionario(List<FuncionarioDTO> funcionarios) {
        String cpf = SelecionaFuncionarioDTO.selecionaCPFFuncionario(funcionarios);

        Funcionario funcionario = funcionarioService.getFuncionarioByCPF(cpf);
        menuFuncionarioControllerView.exibeFuncionario(funcionario);

    }


    public Endereco adicionarEndereco(Endereco endereco) {

        String rua = EnderecoView.leRua();
        String numeroCasa = EnderecoView.leNumeroCasa();
        String cidade = EnderecoView.leCidade();
        String bairro = EnderecoView.leBairro();
        String estado = EnderecoView.leEstado();

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
            opcao = menuFuncionarioControllerView.exibeOpcoesModificarDadosView();

            switch (opcao) {
                case (ConstantesMenuModificacaoDadosFuncionario.MODIFICA_NOME):
                    String nome = menuFuncionarioControllerView.leNomeFuncionario();
                    funcionario.setNome(nome);
                    break;

                case (ConstantesMenuModificacaoDadosFuncionario.MODIFICA_NUMERO_TELEFONE):
                    String numeroTelefone = menuFuncionarioControllerView.leNumeroTelefoneFuncionario();
                    funcionario.setNumeroTelefone(numeroTelefone);
                    break;
                case (ConstantesMenuModificacaoDadosFuncionario.MODIFICA_TURNO):
                    Turno turno = menuFuncionarioControllerView.exibeEEscolheTurnoView();
                    funcionario.setTurno(turno);
                    break;
                case (ConstantesMenuModificacaoDadosFuncionario.MODIFICA_CARGO):
                    String cargo = menuFuncionarioControllerView.leCargoFuncionario();
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
                menuFuncionarioControllerView.exibirAlertaDdosFuncionarioModificadoComSucesso();
            }


        } while (opcao != ConstantesMenuModificacaoDadosFuncionario.VOLTAR);
    }


    private void adicionarExtrato(Funcionario funcionario) {

        YearMonth data = menuFuncionarioControllerView.leData();

        double horasTrabalhadas = menuFuncionarioControllerView.leHorasTrabalhadas();

        double valorHora = menuFuncionarioControllerView.leValorHora();

        double salario = horasTrabalhadas * valorHora;

        ExtratoFuncionario extratoFuncionario = new ExtratoFuncionario();
        extratoFuncionario.setMesReferente(data);
        extratoFuncionario.setValorHora(valorHora);
        extratoFuncionario.setHorasTrabalhadas(horasTrabalhadas);
        extratoFuncionario.setSalario(salario);

        funcionarioService.criaExtratoFuncionario(funcionario, extratoFuncionario);

        menuFuncionarioControllerView.exibirAlertaExtratoCriadoComSucesso();


    }

    private void visualizaExtrato(String cpf) {

        List<ExtratoFuncionarioDTO> extratoFuncionarioDTOS = funcionarioService.getExtratosFuncionarioDTOByCPF(cpf);


        if (extratoFuncionarioDTOS == null || extratoFuncionarioDTOS.isEmpty()) {
            menuFuncionarioControllerView.exibirAlertaSemExtratoParaFuncionario();
            return;
        }

        Long idExtrato = SelecionaExtratoFuncionarioDTO.selecionaExtratoFuncionario(extratoFuncionarioDTOS);

        ExtratoFuncionario extrato = funcionarioService.getExtratoFuncionarioById(idExtrato);

        menuFuncionarioControllerView.exibeExtratoFuncionario(extrato);


    }

    public Funcionario validaCPFDoFuncionarioERetornaFuncionario() {
        String cpf = menuFuncionarioControllerView.leCPFFuncionario();

        if (cpf == null || cpf.trim().isEmpty()) {
            menuFuncionarioControllerView.exibirAlertaCPFNaoPodeSerVazio();
            return null;
        }

        if (!VerificaCPF.isCpfValido(cpf)) {
            menuFuncionarioControllerView.exibirAlertaCPFNaoSeguePadrao();
            return null;
        }

        Funcionario funcionario = funcionarioService.getFuncionarioByCPF(cpf);

        if (funcionario == null) {
            menuFuncionarioControllerView.exibirAlertaCPFNaoExiste();
            return null;
        }

        return funcionario;
    }


}
