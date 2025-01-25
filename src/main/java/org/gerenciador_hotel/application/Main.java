package org.gerenciador_hotel.application;

import org.gerenciador_hotel.controllers.MenuPrincipalController;
import org.gerenciador_hotel.factory.ApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ApplicationContext();
        MenuPrincipalController menuPrincipalController = context.getMenuPrincipalController();
        menuPrincipalController.exibirMenuPrincipal();
    }
}
