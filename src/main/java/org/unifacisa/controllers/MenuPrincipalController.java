package org.unifacisa.controllers;

import org.unifacisa.commons.constantes.ConstantesMenuPrincipalController;
import org.unifacisa.hibernate_connection.EntityManagerFactoryService;
import org.unifacisa.views.MenuPrincipalControllerView;

public class MenuPrincipalController {

    private final EntityManagerFactoryService entityManagerFactoryService;
    private final MenuFuncionarioController menuFuncionarioController;

    public MenuPrincipalController() {
        this.entityManagerFactoryService = new EntityManagerFactoryService();
        entityManagerFactoryService.inicializarEntityManagerFactory();

        this.menuFuncionarioController = new MenuFuncionarioController();
    }


    public void exibirMenuPrincipal() {

        int opcaoMenuPrincipal;

        try {
            do {
                opcaoMenuPrincipal = MenuPrincipalControllerView.exibeViewMenuPrincipal();

                switch (opcaoMenuPrincipal) {

                    case ConstantesMenuPrincipalController.GERENCIADOR_QUARTOS:
                        break;

                    case ConstantesMenuPrincipalController.GERENCIADOR_HOSPEDES:
                        break;

                    case ConstantesMenuPrincipalController.GERENCIADOR_RESERVAS:
                        break;

                    case ConstantesMenuPrincipalController.CHECK_IN_OUT:
                        break;

                    case ConstantesMenuPrincipalController.GERENCIADOR_FUNCIONARIOS:
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
