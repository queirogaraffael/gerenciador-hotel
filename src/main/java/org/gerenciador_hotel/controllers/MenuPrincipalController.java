package org.gerenciador_hotel.controllers;

import org.gerenciador_hotel.constantes.controllers.ConstantesMenuPrincipalController;
import org.gerenciador_hotel.hibernate_connection.EntityManagerFactoryService;
import org.gerenciador_hotel.views.menuPrincipal.MenuPrincipalControllerView;

public class MenuPrincipalController {

    private final EntityManagerFactoryService entityManagerFactoryService;

    private final MenuQuartosController menuQuartosController;
    private final MenuHospedesController menuHospedesController;
    private final MenuReservasController menuReservasController;
    private final MenuFuncionarioController menuFuncionarioController;
    private final MenuCheckInOutController menuCheckInOutController;

    public MenuPrincipalController(EntityManagerFactoryService entityManagerFactoryService) {
        this.entityManagerFactoryService = entityManagerFactoryService;
        this.entityManagerFactoryService.inicializarEntityManagerFactory();

        var entityManagerFactory = this.entityManagerFactoryService.entityManagerFactory();
        this.menuQuartosController = new MenuQuartosController(entityManagerFactory);
        this.menuHospedesController = new MenuHospedesController(entityManagerFactory);
        this.menuReservasController = new MenuReservasController(entityManagerFactory);
        this.menuFuncionarioController = new MenuFuncionarioController(entityManagerFactory);
        this.menuCheckInOutController = new MenuCheckInOutController(entityManagerFactory);
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
