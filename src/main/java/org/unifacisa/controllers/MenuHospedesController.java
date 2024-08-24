package org.unifacisa.controllers;

import org.unifacisa.constantes.ConstantesMenuHospedesController;
import org.unifacisa.constantes.ConstantesMenuModificacaoHospede;
import org.unifacisa.model.domain.entities.Endereco;
import org.unifacisa.model.domain.entities.Hospede;
import org.unifacisa.services.HospedeService;
import org.unifacisa.services.ReservaService;
import org.unifacisa.utils.ManipulaData;
import org.unifacisa.utils.VerificaCPF;
import org.unifacisa.views.commons.DataViews;
import org.unifacisa.views.commons.EnderecoViews;
import org.unifacisa.views.hospedes.*;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;

public class MenuHospedesController {

    private final HospedeService hospedeService;
    private final ReservaService reservaService;

    public MenuHospedesController(EntityManagerFactory entityManagerFactory) {
        this.hospedeService = new HospedeService(entityManagerFactory);
        this.reservaService = new ReservaService(entityManagerFactory);
    }


    public void menuGerenciadorHospedes() {
        String opcaoMenuGerenciadoHospedes;

        do {
            opcaoMenuGerenciadoHospedes = MenuHospedesControllerView.exibirMenuTarefasView();

            switch (opcaoMenuGerenciadoHospedes) {

                case (ConstantesMenuHospedesController.CADASTRAR):
                    cadastrarHospede();
                    break;

                case (ConstantesMenuHospedesController.VISUALIZAR_HOSPEDE_PELO_CPF):
                    visualizarHospede();
                    break;

                case (ConstantesMenuHospedesController.ATUALIZAR):
                    atualizarHospede();
                    break;

                case (ConstantesMenuHospedesController.BUSCA_VISUALIZA_RESERVAS_HOSPEDE):
                    break;

                case (ConstantesMenuHospedesController.BUSCA_VISUALIZA_HISTORICO_HOSPEDE):
                    break;

                case (ConstantesMenuHospedesController.CHECK_IN_OUT):
                    break;

                default:
                    break;

            }


        } while (!opcaoMenuGerenciadoHospedes.equals(ConstantesMenuHospedesController.VOLTAR));
    }


    private void cadastrarHospede() {

        Hospede hospede = new Hospede();

        String cpf = LeDadosBasicosHospedeViews.leCPFHospede();


        if (cpf == null || cpf.trim().isEmpty()) {
            AlertasHospedesViews.exibirAlertaCPFNaoPodeSerVazio();
            return;
        }


        if (!VerificaCPF.isCpfValido(cpf)) {
            AlertasHospedesViews.exibirAlertaCPFNaoSeguePadrao();
            return;
        }


        if (hospedeService.verificaSeHaHospedeComMesmoCPF(cpf)) {
            AlertasHospedesViews.exibirAlertaCPFJaExiste();
            return;
        }


        hospede.setCpf(cpf);


        String nome = LeDadosBasicosHospedeViews.leNomeHospede();


        if (nome == null || nome.trim().isEmpty()) {
            AlertasHospedesViews.exibirAlertaNomeNaoPodeSerVazio();
            return;
        }

        hospede.setNome(nome);

        String dataNascimento = LeDadosBasicosHospedeViews.leDataNascimentoHospede();


        if (!ManipulaData.verificaFormatoDataEstaCorreto(dataNascimento)) {


            DataViews.exibirAlertaDataFormatoErrado();


            return;
        }

        hospede.setDataNascimento(ManipulaData.retornaLocalDate(dataNascimento));

        String numeroTelefone = LeDadosBasicosHospedeViews.leNumeroTelefoneHospede();

        if (numeroTelefone == null || numeroTelefone.trim().isEmpty()) {


            AlertasHospedesViews.exibirAlertaNumeroTelefoneVazio();


            return;
        }

        hospede.setNumeroTelefone(numeroTelefone);


        int desejaAdicionarEndereco = EnderecoViews.desejaAdicionarEndereco();

        Endereco endereco = new Endereco();

        if (desejaAdicionarEndereco == JOptionPane.YES_OPTION) {

            endereco = adicionarEndereco(endereco);

        } else if (desejaAdicionarEndereco == JOptionPane.CLOSED_OPTION) {
            return;
        }

        hospede.setEndereco(endereco);

        hospedeService.cadastraHospede(hospede);

        AlertasHospedesViews.exibirAlertaHospedeAdicionadoComSucesso();

    }

    private void visualizarHospede() {

        Hospede hospede = validaCPFDoHospedeERetornaHospede();

        if (hospede != null) {
            PrintaHospede.exibeHospede(hospede);
        }


    }

    private void atualizarHospede() {
        Hospede hospede = validaCPFDoHospedeERetornaHospede();

        if (hospede != null) {

            exibiOpcoesDeModificacaoDoHospedeEModifica(hospede);
        }

    }



    public Hospede validaCPFDoHospedeERetornaHospede() {

        String cpf = LeDadosBasicosHospedeViews.leCPFHospede();

        if (cpf == null || cpf.trim().isEmpty()) {
            AlertasHospedesViews.exibirAlertaCPFNaoPodeSerVazio();
            return null;
        }


        if (!VerificaCPF.isCpfValido(cpf)) {
            AlertasHospedesViews.exibirAlertaCPFNaoSeguePadrao();
            return null;
        }


        Hospede hospede = hospedeService.getHospedeByCPF(cpf);

        if (hospede == null) {
            AlertasHospedesViews.exibirAlertaCPFNaoExiste();
            return null;
        }

        return hospede;
    }


    public void exibiOpcoesDeModificacaoDoHospedeEModifica(Hospede hospede) {
        int opcao;


        do {
            opcao = MenuModificacaoDadosHospedeView.exibeOpcoesModificarDadosView();


            switch (opcao) {
                case (ConstantesMenuModificacaoHospede.MODIFICA_NOME):
                    String nome = LeDadosBasicosHospedeViews.leNomeHospede();
                    hospede.setNome(nome);
                    break;

                case (ConstantesMenuModificacaoHospede.MODIFICA_NUMERO_TELEFONE):
                    String numeroTelefone = LeDadosBasicosHospedeViews.leNumeroTelefoneHospede();
                    hospede.setNumeroTelefone(numeroTelefone);
                    break;

                case (ConstantesMenuModificacaoHospede.MODIFICA_ENDERECO):
                    Endereco endereco = new Endereco();
                    adicionarEndereco(endereco);
                    hospede.setEndereco(endereco);
                    break;

                default:
                    break;
            }

            if (opcao != ConstantesMenuModificacaoHospede.VOLTAR) {
                hospedeService.atualizaHospede(hospede);
                AlertasHospedesViews.exibirAlertaDadosHospedeModificadoComSucesso();
            }


        } while (opcao != ConstantesMenuModificacaoHospede.VOLTAR);


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


}
