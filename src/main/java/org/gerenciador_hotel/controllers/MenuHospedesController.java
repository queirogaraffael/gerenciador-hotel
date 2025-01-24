package org.gerenciador_hotel.controllers;

import org.gerenciador_hotel.constantes.controllers.ConstantesMenuHospedesController;
import org.gerenciador_hotel.constantes.modificacoes.ConstantesMenuModificacaoHospede;
import org.gerenciador_hotel.dtos.ReservaDTO;
import org.gerenciador_hotel.utils.dtos.SelecionaReservaDTO;
import org.gerenciador_hotel.enums.StatusReserva;
import org.gerenciador_hotel.model.domain.entities.Endereco;
import org.gerenciador_hotel.model.domain.entities.Hospede;
import org.gerenciador_hotel.model.domain.entities.Reserva;
import org.gerenciador_hotel.services.HospedeService;
import org.gerenciador_hotel.services.ReservaService;
import org.gerenciador_hotel.utils.ManipulaData;
import org.gerenciador_hotel.utils.VerificaCPF;
import org.gerenciador_hotel.views.commons.DataViews;
import org.gerenciador_hotel.views.commons.EnderecoViews;
import org.gerenciador_hotel.views.hospedes.*;
import org.gerenciador_hotel.views.reservas.AlertasReservasViews;
import org.gerenciador_hotel.views.reservas.PrintaReserva;

import javax.persistence.EntityManagerFactory;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MenuHospedesController {

    private final HospedeService hospedeService;
    private final ReservaService reservaService;

    public MenuHospedesController(HospedeService hospedeService, ReservaService reservaService) {
        this.hospedeService = hospedeService;
        this.reservaService = reservaService;
    }


    public void menuGerenciadorHospedes() {
        int opcaoMenuGerenciadoHospedes;

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

                case (ConstantesMenuHospedesController.BUSCA_VISUALIZA_RESERVAS_ATIVAS_E_EM_USO_HOSPEDE):
                    visualizaReservasHospede();
                    break;

                case (ConstantesMenuHospedesController.BUSCA_VISUALIZA_RESERVAS_FINALIZADAS_CANCELADAS_HOSPEDE):
                    visualizaReservasFinalizadasHospede();
                    break;

                default:
                    break;

            }


        } while (opcaoMenuGerenciadoHospedes != ConstantesMenuHospedesController.VOLTAR);
    }


    private void cadastrarHospede() {

        Hospede hospede = new Hospede();

        String cpf = LeDadosBasicosHospedeViews.leCPFHospede();


        if (!VerificaCPF.isCpfValido(cpf)) {
            AlertasHospedesViews.exibirAlertaCPFNaoSeguePadrao();
            return;
        }


        if (hospedeService.haHospedeComMesmoCPF(cpf)) {
            AlertasHospedesViews.exibirAlertaCPFJaExiste();
            return;
        }


        hospede.setCpf(cpf);


        String nome = LeDadosBasicosHospedeViews.leNomeHospede();

        hospede.setNome(nome);

        String dataNascimento = LeDadosBasicosHospedeViews.leDataNascimentoHospede();


        if (!ManipulaData.verificaFormatoDataEstaCorreto(dataNascimento)) {
            DataViews.exibirAlertaDataFormatoErrado();
            return;
        }

        if (!ManipulaData.eMaiorDeIdade(ManipulaData.retornaLocalDate(dataNascimento))) {
            AlertasHospedesViews.exibirAlertaNaoPodeMenorDeIdade();
            return;
        }

        hospede.setDataNascimento(ManipulaData.retornaLocalDate(dataNascimento));

        String numeroTelefone = LeDadosBasicosHospedeViews.leNumeroTelefoneHospede();

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

    private void visualizaReservasHospede() {
        Hospede hospede = validaCPFDoHospedeERetornaHospede();

        if (hospede == null) {
            return;
        }

        List<ReservaDTO> reservasMarcadas = reservaService.getReservasDTOByStatusReservaEByCPFHospede(StatusReserva.AGENDADO, hospede.getCpf());

        List<ReservaDTO> reservasEmUso = reservaService.getReservasDTOByStatusReservaEByCPFHospede(StatusReserva.EM_USO, hospede.getCpf());

        List<ReservaDTO> listasUnida = new ArrayList<>();

        listasUnida.addAll(reservasMarcadas);
        listasUnida.addAll(reservasEmUso);

        Collections.sort(listasUnida);

        if (listasUnida.isEmpty()) {
            AlertasReservasViews.exibirAlertaSemReservasCadastradasOuEmUso();
            return;
        }

        Long idReserva = SelecionaReservaDTO.selecionaReserva(listasUnida);

        Reserva reserva = reservaService.getReservaById(idReserva);

        PrintaReserva.exibeReserva(reserva);

    }

    private void visualizaReservasFinalizadasHospede() {
        Hospede hospede = validaCPFDoHospedeERetornaHospede();

        if (hospede == null) {
            return;
        }

        List<ReservaDTO> reservasCanceladas = reservaService.getReservasDTOByStatusReservaEByCPFHospede(StatusReserva.CANCELADO, hospede.getCpf());

        List<ReservaDTO> reservasFinalizadas = reservaService.getReservasDTOByStatusReservaEByCPFHospede(StatusReserva.FINALIZADO, hospede.getCpf());

        List<ReservaDTO> listasUnida = new ArrayList<>();

        listasUnida.addAll(reservasCanceladas);
        listasUnida.addAll(reservasFinalizadas);

        Collections.sort(listasUnida);

        if (listasUnida.isEmpty()) {
            AlertasReservasViews.exibirAlertaSemReservasCanceladasOuFinalizadas();
            return;
        }

        Long idReserva = SelecionaReservaDTO.selecionaReserva(listasUnida);

        Reserva reserva = reservaService.getReservaById(idReserva);

        PrintaReserva.exibeReserva(reserva);

    }


    public Hospede validaCPFDoHospedeERetornaHospede() {

        String cpf = LeDadosBasicosHospedeViews.leCPFHospede();


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
