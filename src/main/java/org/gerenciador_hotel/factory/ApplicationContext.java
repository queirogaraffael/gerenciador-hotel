package org.gerenciador_hotel.factory;

import org.gerenciador_hotel.controllers.MenuPrincipalController;
import org.gerenciador_hotel.hibernate_connection.EntityManagerFactoryService;

public class ApplicationContext {
    private final EntityManagerFactoryService entityManagerFactoryService;
    private final ServiceFactory serviceFactory;

    public ApplicationContext() {
        this.entityManagerFactoryService = new EntityManagerFactoryService();
        this.serviceFactory = new ServiceFactory(new DaoFactory(entityManagerFactoryService.entityManagerFactory()));
    }

    public MenuPrincipalController getMenuPrincipalController() {
        return new MenuPrincipalController(entityManagerFactoryService,
                serviceFactory.createMenuCheckInOutController(),
                serviceFactory.createMenuFuncionarioController(),
                serviceFactory.createMenuHospedesController(),
                serviceFactory.createMenuQuartosController(),
                serviceFactory.createMenuReservasController());
    }
}

