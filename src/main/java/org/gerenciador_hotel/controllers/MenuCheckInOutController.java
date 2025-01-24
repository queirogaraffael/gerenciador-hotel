package org.gerenciador_hotel.controllers;

import org.gerenciador_hotel.constantes.controllers.ConstantesMenuCheckInOutController;
import org.gerenciador_hotel.dtos.ReservaDTO;
import org.gerenciador_hotel.dtos.utils.SelecionaReservaDTO;
import org.gerenciador_hotel.enums.StatusQuarto;
import org.gerenciador_hotel.enums.StatusReserva;
import org.gerenciador_hotel.model.domain.entities.Hospede;
import org.gerenciador_hotel.model.domain.entities.Quarto;
import org.gerenciador_hotel.model.domain.entities.Reserva;
import org.gerenciador_hotel.services.HospedeService;
import org.gerenciador_hotel.services.QuartoService;
import org.gerenciador_hotel.services.ReservaService;
import org.gerenciador_hotel.utils.ManipulaData;
import org.gerenciador_hotel.utils.VerificaCPF;
import org.gerenciador_hotel.views.CheckInOut.MenuCheckInOutControllerView;
import org.gerenciador_hotel.views.hospedes.AlertasHospedesViews;
import org.gerenciador_hotel.views.hospedes.LeDadosBasicosHospedeViews;
import org.gerenciador_hotel.views.reservas.AlertasReservasViews;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MenuCheckInOutController {

    private final ReservaService reservaService;
    private final HospedeService hospedeService;
    private final QuartoService quartoService;

    public MenuCheckInOutController(EntityManagerFactory entityManagerFactory) {
        this.reservaService = new ReservaService(entityManagerFactory);
        this.hospedeService = new HospedeService(entityManagerFactory);
        this.quartoService = new QuartoService(entityManagerFactory);
    }


    public void validaCpfParaEntrarNoMenuCheckInOut() {

        Hospede hospede = validaCPFDoHospedeERetornaHospede();

        if (hospede == null) {
            return;
        }

        exibirMenuCheckInOut(hospede);

    }


    public void exibirMenuCheckInOut(Hospede hospede) {

        int opcaoMenu;

        do {
            opcaoMenu = MenuCheckInOutControllerView.exibeViewMenuCheckInOut();

            switch (opcaoMenu) {

                case ConstantesMenuCheckInOutController
                             .CHECK_IN:
                    checkIn(hospede);
                    break;

                case ConstantesMenuCheckInOutController.CHECK_OUT:
                    checkOut(hospede);
                    break;

                default:
                    break;

            }
        } while (opcaoMenu != ConstantesMenuCheckInOutController.VOLTAR);


    }

    private void checkIn(Hospede hospede) {

        List<ReservaDTO> reservasAgendadas = reservaService.getReservasDTOByStatusReservaEByCPFHospede(StatusReserva.AGENDADO, hospede.getCpf());

        if (reservasAgendadas == null || reservasAgendadas.isEmpty()) {
            AlertasReservasViews.exibirAlertaSemReservasCadastradasOuEmUso();
            return;
        }


        Long idReserva = SelecionaReservaDTO.selecionaReserva(reservasAgendadas);

        Reserva reserva = reservaService.getReservaById(idReserva);
        reserva.setStatusReserva(StatusReserva.EM_USO);

        Quarto quarto = reserva.getQuarto();
        quarto.setStatusQuarto(StatusQuarto.OCUPADO);

        quartoService.atualizaDadosQuarto(quarto);

        reservaService.atualizaReserva(reserva);

        AlertasReservasViews.exibirAlertaReservaCheckInRealizadoComSucesso();

    }

    private void checkOut(Hospede hospede) {

        List<ReservaDTO> reservasAgendadas = reservaService.getReservasDTOByStatusReservaEByCPFHospede(StatusReserva.EM_USO, hospede.getCpf());

        if (reservasAgendadas == null || reservasAgendadas.isEmpty()) {
            AlertasReservasViews.exibirAlertaSemReservasCadastradasOuEmUso();
            return;
        }

        Long idReserva = SelecionaReservaDTO.selecionaReserva(reservasAgendadas);

        Reserva reserva = reservaService.getReservaById(idReserva);

        double valorTotal = ManipulaData.calculaDuracao(reserva.getDataEntrada(), reserva.getDataSaida()) * reserva.getQuarto().getPrecoDiaria();

        reserva.setValorTotal(valorTotal);
        reserva.setStatusReserva(StatusReserva.FINALIZADO);

        Quarto quarto = reserva.getQuarto();
        quarto.setStatusQuarto(StatusQuarto.DISPONIVEL);

        quartoService.atualizaDadosQuarto(quarto);

        reservaService.atualizaReserva(reserva);

        AlertasReservasViews.exibirAlertaReservaCheckOutRealizadoComSucesso();

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
