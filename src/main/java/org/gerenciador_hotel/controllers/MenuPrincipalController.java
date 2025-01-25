package org.gerenciador_hotel.controllers;

import org.gerenciador_hotel.constantes.controllers.ConstantesMenuPrincipalController;
import org.gerenciador_hotel.hibernate_connection.EntityManagerFactoryService;
import org.gerenciador_hotel.ui.menuPrincipal.MenuPrincipalControllerView;

public class MenuPrincipalController {

    private final EntityManagerFactoryService entityManagerFactoryService;

    private MenuQuartosController menuQuartosController;
    private MenuHospedesController menuHospedesController;
    private MenuReservasController menuReservasController;
    private MenuFuncionarioController menuFuncionarioController;
    private MenuCheckInOutController menuCheckInOutController;

    public MenuPrincipalController(EntityManagerFactoryService entityManagerFactoryService, MenuCheckInOutController menuCheckInOutController,
                                   MenuFuncionarioController menuFuncionarioController, MenuHospedesController menuHospedesController,
                                   MenuQuartosController menuQuartosController,
                                   MenuReservasController menuReservasController
    ) {

        this.entityManagerFactoryService = entityManagerFactoryService;
        this.menuQuartosController = menuQuartosController;
        this.menuHospedesController = menuHospedesController;
        this.menuReservasController = menuReservasController;
        this.menuFuncionarioController = menuFuncionarioController;
        this.menuCheckInOutController = menuCheckInOutController;
    }


    public void exibirMenuPrincipal() {

        int opcaoMenuPrincipal;

        try {
            do {
                opcaoMenuPrincipal = MenuPrincipalControllerView.exibeViewMenuPrincipal();

                switch (opcaoMenuPrincipal) {

                    case ConstantesMenuPrincipalController.GERENCIADOR_QUARTOS:
                        menuQuartosController.menuGerenciadorQuartos();
                        break;

                    case ConstantesMenuPrincipalController.GERENCIADOR_HOSPEDES:
                        menuHospedesController.menuGerenciadorHospedes();
                        break;

                    case ConstantesMenuPrincipalController.GERENCIADOR_RESERVAS:
                        menuReservasController.menuGerenciadorReservas();
                        break;

                    case ConstantesMenuPrincipalController.CHECK_IN_OUT:
                        menuCheckInOutController.validaCpfParaEntrarNoMenuCheckInOut();
                        break;

                    case ConstantesMenuPrincipalController.GERENCIADOR_FUNCIONARIOS:
                        menuFuncionarioController.menuGerenciadorFuncionario();
                        break;

                    default:
                        opcaoMenuPrincipal = ConstantesMenuPrincipalController.ENCERRAR_PROGRAMA;
                        break;

                }
            } while (opcaoMenuPrincipal != ConstantesMenuPrincipalController.ENCERRAR_PROGRAMA);
        } finally {
            entityManagerFactoryService.fechaEntityManagerFactory();
        }


    }


}
