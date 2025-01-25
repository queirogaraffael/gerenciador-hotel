package org.gerenciador_hotel;

import org.gerenciador_hotel.controllers.*;
import org.gerenciador_hotel.factory.DaoFactory;
import org.gerenciador_hotel.factory.ServiceFactory;
import org.gerenciador_hotel.hibernate_connection.EntityManagerFactoryService;

public class Main {
    public static void main(String[] args) {
        final EntityManagerFactoryService entityManagerFactoryService = new EntityManagerFactoryService();

        DaoFactory daoFactory = new DaoFactory(entityManagerFactoryService.entityManagerFactory());

        final ServiceFactory serviceFactory = new ServiceFactory(daoFactory);

        MenuCheckInOutController menuCheckInOutController = serviceFactory.createMenuCheckInOutController();
        MenuFuncionarioController menuFuncionarioController = serviceFactory.createMenuFuncionarioController();
        MenuHospedesController menuHospedesController = serviceFactory.createMenuHospedesController();
        MenuQuartosController menuQuartosController = serviceFactory.createMenuQuartosController();
        MenuReservasController menuReservasController = serviceFactory.createMenuReservasController();

        MenuPrincipalController menuPrincipalController = new MenuPrincipalController(entityManagerFactoryService, menuCheckInOutController, menuFuncionarioController, menuHospedesController, menuQuartosController, menuReservasController);
        menuPrincipalController.exibirMenuPrincipal();
    }
}
