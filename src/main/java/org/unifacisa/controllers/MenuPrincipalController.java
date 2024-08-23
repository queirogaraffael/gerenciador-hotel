package org.unifacisa.controllers;

import org.unifacisa.constantes.ConstantesMenuPrincipalController;
import org.unifacisa.hibernate_connection.EntityManagerFactoryService;
import org.unifacisa.views.menuPrincipal.MenuPrincipalControllerView;

public class MenuPrincipalController {

    private final EntityManagerFactoryService entityManagerFactoryService;
    private final MenuFuncionarioController menuFuncionarioController;
    private final MenuQuartosController menuQuartosController;

    public MenuPrincipalController() {
        this.entityManagerFactoryService = new EntityManagerFactoryService();
        entityManagerFactoryService.inicializarEntityManagerFactory();

        this.menuFuncionarioController = new MenuFuncionarioController(entityManagerFactoryService.entityManagerFactory());
        this.menuQuartosController = new MenuQuartosController((entityManagerFactoryService.entityManagerFactory()));
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
                        break;

                    case ConstantesMenuPrincipalController.GERENCIADOR_RESERVAS:
                        break;

                    case ConstantesMenuPrincipalController.CHECK_IN_OUT:
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
