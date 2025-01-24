package org.gerenciador_hotel.factory;

import org.gerenciador_hotel.controllers.*;
import org.gerenciador_hotel.hibernate_connection.EntityManagerFactoryService;
import org.gerenciador_hotel.services.FuncionarioService;
import org.gerenciador_hotel.services.HospedeService;
import org.gerenciador_hotel.services.QuartoService;
import org.gerenciador_hotel.services.ReservaService;

public class ServiceFactory {

    private final EntityManagerFactoryService entityManagerFactoryService;
    private ReservaService reservaService;
    private HospedeService hospedeService;
    private QuartoService quartoService;
    private FuncionarioService funcionarioService;

    public ServiceFactory(EntityManagerFactoryService entityManagerFactoryService) {
        this.entityManagerFactoryService = entityManagerFactoryService;
    }

    public ReservaService createReservaService() {
        if (reservaService == null) {
            reservaService = new ReservaService(entityManagerFactoryService.entityManagerFactory());
        }
        return reservaService;
    }

    public HospedeService createHospedeService() {
        if (hospedeService == null) {
            hospedeService = new HospedeService(entityManagerFactoryService.entityManagerFactory());
        }
        return hospedeService;
    }

    public QuartoService createQuartoService() {
        if (quartoService == null) {
            quartoService = new QuartoService(entityManagerFactoryService.entityManagerFactory());
        }
        return quartoService;
    }

    public FuncionarioService createFuncionarioService() {
        if (funcionarioService == null) {
            funcionarioService = new FuncionarioService(entityManagerFactoryService.entityManagerFactory());
        }
        return funcionarioService;
    }

    public MenuCheckInOutController createMenuCheckInOutController() {
        return new MenuCheckInOutController(createReservaService(), createHospedeService(), createQuartoService());
    }

    public MenuFuncionarioController createMenuFuncionarioController() {
        return new MenuFuncionarioController(createFuncionarioService());
    }

    public MenuHospedesController createMenuHospedesController() {
        return new MenuHospedesController(createHospedeService(), createReservaService());
    }

    public MenuQuartosController createMenuQuartosController() {
        return new MenuQuartosController(createQuartoService(), createReservaService());
    }

    public MenuReservasController createMenuReservasController() {
        return new MenuReservasController(createReservaService(), createQuartoService(), createHospedeService());
    }
}
