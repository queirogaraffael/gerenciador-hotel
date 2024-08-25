package org.unifacisa.controllers;

import org.unifacisa.constantes.controllers.ConstantesMenuCheckInOutController;
import org.unifacisa.dtos.ReservaDTO;
import org.unifacisa.dtos.utils.SelecionaReservaDTO;
import org.unifacisa.enums.StatusReserva;
import org.unifacisa.model.domain.entities.Hospede;
import org.unifacisa.services.HospedeService;
import org.unifacisa.services.ReservaService;
import org.unifacisa.utils.VerificaCPF;
import org.unifacisa.views.CheckInOut.MenuCheckInOutControllerView;
import org.unifacisa.views.hospedes.AlertasHospedesViews;
import org.unifacisa.views.hospedes.LeDadosBasicosHospedeViews;
import org.unifacisa.views.reservas.AlertasReservasViews;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MenuCheckInOutController {

    private final ReservaService reservaService;
    private final HospedeService hospedeService;

    public MenuCheckInOutController(EntityManagerFactory entityManagerFactory) {
        this.reservaService = new ReservaService(entityManagerFactory);
        this.hospedeService = new HospedeService(entityManagerFactory);
    }


    public void validaCpfParaEntrarNoMenuCheckInOut(){

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


        reservaService.mudaStatusReservaById(StatusReserva.EM_USO, idReserva);

        AlertasReservasViews.exibirAlertaReservaCheckInRealizadoComSucesso();

    }

    private void checkOut(Hospede hospede) {

        List<ReservaDTO> reservasAgendadas = reservaService.getReservasDTOByStatusReservaEByCPFHospede(StatusReserva.EM_USO, hospede.getCpf());

        if (reservasAgendadas == null || reservasAgendadas.isEmpty()) {
            AlertasReservasViews.exibirAlertaSemReservasCadastradasOuEmUso();
            return;
        }


        Long idReserva = SelecionaReservaDTO.selecionaReserva(reservasAgendadas);


        reservaService.mudaStatusReservaById(StatusReserva.FINALIZADO, idReserva);

        AlertasReservasViews.exibirAlertaReservaCheckOutRealizadoComSucesso();

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
