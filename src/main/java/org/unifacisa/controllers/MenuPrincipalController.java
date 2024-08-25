package org.unifacisa.controllers;

import org.unifacisa.constantes.controllers.ConstantesMenuPrincipalController;
import org.unifacisa.hibernate_connection.EntityManagerFactoryService;
import org.unifacisa.views.CheckInOut.MenuCheckInOutControllerView;
import org.unifacisa.views.menuPrincipal.MenuPrincipalControllerView;

public class MenuPrincipalController {

    private final EntityManagerFactoryService entityManagerFactoryService;
    private final MenuQuartosController menuQuartosController;
    private final MenuHospedesController menuHospedesController;
    private final MenuReservasController menuReservasController;
    private final MenuFuncionarioController menuFuncionarioController;
    private final MenuCheckInOutController menuCheckInOutController;

    public MenuPrincipalController() {
        this.entityManagerFactoryService = new EntityManagerFactoryService();
        entityManagerFactoryService.inicializarEntityManagerFactory();

        this.menuQuartosController = new MenuQuartosController((entityManagerFactoryService.entityManagerFactory()));
        this.menuHospedesController = new MenuHospedesController(entityManagerFactoryService.entityManagerFactory());
        this.menuReservasController = new MenuReservasController(entityManagerFactoryService.entityManagerFactory());
        this.menuFuncionarioController = new MenuFuncionarioController(entityManagerFactoryService.entityManagerFactory());
        this.menuCheckInOutController = new MenuCheckInOutController(entityManagerFactoryService.entityManagerFactory());
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
                        menuCheckInOutController.exibirMenuCheckInOut();
                        break;

                    case ConstantesMenuPrincipalController.GERENCIADOR_FUNCIONARIOS:
                        menuFuncionarioController.menuGerenciadorFuncionario();
                        break;

                    default:
                        break;

                }
            } while (opcaoMenuPrincipal != ConstantesMenuPrincipalController.ENCERRAR_PROGRAMA);
        } finally {
            entityManagerFactoryService.fechaEntityManagerFactory();
        }


    }


}
