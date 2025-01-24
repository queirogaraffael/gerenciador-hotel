package org.gerenciador_hotel;

import org.gerenciador_hotel.controllers.MenuPrincipalController;
import org.gerenciador_hotel.hibernate_connection.EntityManagerFactoryService;

public class Main {
    public static void main(String[] args) {

        MenuPrincipalController menuPrincipalController = new MenuPrincipalController(new EntityManagerFactoryService());
        menuPrincipalController.exibirMenuPrincipal();

    }
}