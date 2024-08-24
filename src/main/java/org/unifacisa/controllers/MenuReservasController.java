package org.unifacisa.controllers;

import org.unifacisa.constantes.ConstantesMenuReservasController;
import org.unifacisa.services.ReservaService;
import org.unifacisa.views.reservas.MenuReservasControllerView;

import javax.persistence.EntityManagerFactory;

public class MenuReservasController {


private final ReservaService reservaService;

    public MenuReservasController(EntityManagerFactory entityManagerFactory) {
        this.reservaService = new ReservaService(entityManagerFactory);
    }


    public void menuGerenciadorReservas() {
        String opcaoMenuGerenciadoReservas;

        do {
            opcaoMenuGerenciadoReservas = MenuReservasControllerView.exibirMenuGerenciadorReservasView();

            switch (opcaoMenuGerenciadoReservas) {

                case (ConstantesMenuReservasController.CRIAR_RESERVA):

                    break;

                case (ConstantesMenuReservasController.CANCELAR_RESERVA):

                    break;

                default:
                    break;

            }


        } while (!opcaoMenuGerenciadoReservas.equals(ConstantesMenuReservasController.VOLTAR));
    }
}
