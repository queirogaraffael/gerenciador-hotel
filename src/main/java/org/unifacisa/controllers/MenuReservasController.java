package org.unifacisa.controllers;

import org.unifacisa.constantes.controllers.ConstantesMenuReservasController;
import org.unifacisa.dtos.QuartoDTO;
import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.enums.StatusReserva;
import org.unifacisa.enums.TipoQuarto;
import org.unifacisa.model.domain.entities.Hospede;
import org.unifacisa.model.domain.entities.Quarto;
import org.unifacisa.model.domain.entities.Reserva;
import org.unifacisa.services.HospedeService;
import org.unifacisa.services.QuartoService;
import org.unifacisa.services.ReservaService;
import org.unifacisa.dtos.utils.SelecionaQuartoDTO;
import org.unifacisa.dtos.utils.SelecionaReservaDTO;
import org.unifacisa.utils.VerificaCPF;
import org.unifacisa.views.commons.DataViews;
import org.unifacisa.views.hospedes.AlertasHospedesViews;
import org.unifacisa.views.hospedes.LeDadosBasicosHospedeViews;
import org.unifacisa.views.quartos.AlertasQuartoViews;
import org.unifacisa.views.quartos.EscolheTipoQuartoView;
import org.unifacisa.views.reservas.AlertasReservasViews;
import org.unifacisa.views.reservas.MenuReservasControllerView;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

import static org.unifacisa.utils.ManipulaData.dataInicialEPosteriorDataFinal;

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
        String opcaoMenuGerenciadoReservas;

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


        } while (!opcaoMenuGerenciadoReservas.equals(ConstantesMenuReservasController.VOLTAR));
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


        String numeroQuarto = SelecionaQuartoDTO.selecionaNumeroQuarto(quartos);

        int numeroHospedes = LeDadosBasicosHospedeViews.leNumeroHospedesQuarto();

        Quarto quarto = quartoService.getQuartoByNumero(numeroQuarto);
        Reserva reserva = new Reserva();

        reserva.setDataEntrada(dataEntrada);
        reserva.setDataSaida(dataEntrada);
        reserva.setNumeroHospedes(numeroHospedes);
        reserva.setHospede(hospede);
        reserva.setQuarto(quarto);
        reserva.setStatusReserva(StatusReserva.ATIVO);

        reservaService.criaReserva(reserva);

        AlertasReservasViews.exibirAlertaReservaCriadaComSucesso();

    }


    private void cancelaReserva() {

        Hospede hospede = validaCPFDoHospedeERetornaHospede();

        if (hospede == null) {
            return;
        }


        List<ReservaDTO> reservasDoHospede = reservaService.getReservasDTOByStatusReservaEByCPFHospede(StatusReserva.ATIVO, hospede.getCpf());

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
}
