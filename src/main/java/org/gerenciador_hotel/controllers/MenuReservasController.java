package org.gerenciador_hotel.controllers;

import org.gerenciador_hotel.constantes.controllers.ConstantesMenuReservasController;
import org.gerenciador_hotel.dtos.QuartoDTO;
import org.gerenciador_hotel.dtos.ReservaDTO;
import org.gerenciador_hotel.utils.dtos.SelecionaQuartoDTO;
import org.gerenciador_hotel.utils.dtos.SelecionaReservaDTO;
import org.gerenciador_hotel.enums.StatusReserva;
import org.gerenciador_hotel.enums.TipoQuarto;
import org.gerenciador_hotel.model.domain.entities.Hospede;
import org.gerenciador_hotel.model.domain.entities.Quarto;
import org.gerenciador_hotel.model.domain.entities.Reserva;
import org.gerenciador_hotel.services.HospedeService;
import org.gerenciador_hotel.services.QuartoService;
import org.gerenciador_hotel.services.ReservaService;
import org.gerenciador_hotel.utils.VerificaCPF;
import org.gerenciador_hotel.views.commons.DataViews;
import org.gerenciador_hotel.views.hospedes.AlertasHospedesViews;
import org.gerenciador_hotel.views.hospedes.LeDadosBasicosHospedeViews;
import org.gerenciador_hotel.views.quartos.AlertasQuartoViews;
import org.gerenciador_hotel.views.quartos.EscolheTipoQuartoView;
import org.gerenciador_hotel.views.reservas.AlertasReservasViews;
import org.gerenciador_hotel.views.reservas.MenuReservasControllerView;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

import static org.gerenciador_hotel.utils.ManipulaData.dataInicialEPosteriorDataFinal;

public class MenuReservasController {

    private final ReservaService reservaService;
    private final QuartoService quartoService;
    private final HospedeService hospedeService;

    public MenuReservasController(EntityManagerFactory entityManagerFactory) {
        this.reservaService = new ReservaService(entityManagerFactory);
        this.quartoService = new QuartoService(entityManagerFactory);
        this.hospedeService = new HospedeService(entityManagerFactory);
    }


    public void menuGerenciadorReservas() {
        int opcaoMenuGerenciadoReservas;

        do {
            opcaoMenuGerenciadoReservas = MenuReservasControllerView.exibirMenuGerenciadorReservasView();

            switch (opcaoMenuGerenciadoReservas) {

                case (ConstantesMenuReservasController.CRIAR_RESERVA):
                    criaReserva();
                    break;

                case (ConstantesMenuReservasController.CANCELAR_RESERVA):
                    cancelaReserva();
                    break;

                default:
                    break;

            }


        } while (opcaoMenuGerenciadoReservas != ConstantesMenuReservasController.VOLTAR);
    }

    private void criaReserva() {

        Hospede hospede = validaCPFDoHospedeERetornaHospede();

        if (hospede == null) {
            return;
        }

        TipoQuarto tipoQuarto = EscolheTipoQuartoView.exibeEEscolheTipoQuartoView();


        LocalDate dataEntrada = DataViews.leDataEntrada();


        if (dataEntrada == null) {
            DataViews.exibirAlertaDataFormatoErrado();
            return;
        }


        if (dataInicialEPosteriorDataFinal(LocalDate.now(), dataEntrada)) {
            DataViews.exibirAlertaNaoPodeDataAnteriorAAtual();

            return;
        }


        LocalDate dataSaida = DataViews.leDataSaida();


        if (dataSaida == null) {
            DataViews.exibirAlertaDataFormatoErrado();
            return;
        }


        if (dataInicialEPosteriorDataFinal(dataEntrada, dataSaida)) {
            DataViews.exibirAlertaDataEntradaEPosteriorDataSaida();
            return;
        }


        List<QuartoDTO> quartos = quartoService.getQuartosDisponiveisPorTipo(tipoQuarto, dataEntrada, dataSaida);

        if (quartos == null || quartos.isEmpty()) {
            AlertasQuartoViews.exibirAlertaSemQuartoDesseTipoDisponiveisParaEssaData();

            return;
        }


        int numeroQuarto = SelecionaQuartoDTO.selecionaNumeroQuarto(quartos);
        Quarto quarto = quartoService.getQuartoByNumero(numeroQuarto);

        int capacidadeMaximaHospedePorQuarto = quarto.getCapacidade();

        int numeroHospedes = LeDadosBasicosHospedeViews.leNumeroHospedesQuarto(capacidadeMaximaHospedePorQuarto);


        if(numeroHospedes > capacidadeMaximaHospedePorQuarto){
            AlertasQuartoViews.capacidadeMaximaDeHospedeUltrapassada();
            return;
        }


        Reserva reserva = new Reserva();

        reserva.setDataEntrada(dataEntrada);
        reserva.setDataSaida(dataEntrada);
        reserva.setNumeroHospedes(numeroHospedes);
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setStatusReserva(StatusReserva.AGENDADO);

        reservaService.criaReserva(reserva);

        AlertasReservasViews.exibirAlertaReservaCriadaComSucesso();

    }


    private void cancelaReserva() {

        Hospede hospede = validaCPFDoHospedeERetornaHospede();

        if (hospede == null) {
            return;
        }


        List<ReservaDTO> reservasDoHospede = reservaService.getReservasDTOByStatusReservaEByCPFHospede(StatusReserva.AGENDADO, hospede.getCpf());

        if (reservasDoHospede == null || reservasDoHospede.isEmpty()) {
            AlertasReservasViews.exibirAlertaSemReservaAssociadaAHospede();
            return;
        }

        Long idReserva = SelecionaReservaDTO.selecionaReserva(reservasDoHospede);

        reservaService.mudaStatusReservaById(StatusReserva.CANCELADO, idReserva);

        AlertasReservasViews.exibirAlertaReservaCanceladaComSucesso();


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
}
