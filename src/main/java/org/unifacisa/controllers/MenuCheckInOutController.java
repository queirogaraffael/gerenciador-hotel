package org.unifacisa.controllers;

import org.unifacisa.constantes.controllers.ConstantesMenuCheckInOutController;
import org.unifacisa.services.ReservaService;
import org.unifacisa.views.CheckInOut.MenuCheckInOutControllerView;

import javax.persistence.EntityManagerFactory;

public class MenuCheckInOutController {

    private final ReservaService reservaService;

    public MenuCheckInOutController(EntityManagerFactory entityManagerFactory) {
        this.reservaService = new ReservaService(entityManagerFactory);
    }

    public void exibirMenuCheckInOut() {

        int opcaoMenu;

        do {
            opcaoMenu = MenuCheckInOutControllerView.exibeViewMenuCheckInOut();

            switch (opcaoMenu) {

                case ConstantesMenuCheckInOutController
                             .CHECK_IN:
                    break;

                case ConstantesMenuCheckInOutController.CHECK_OUT:
                    break;

                default:
                    break;

            }
        } while (opcaoMenu != ConstantesMenuCheckInOutController.VOLTAR);


    }
}
