package org.gerenciador_hotel.factory;

import org.gerenciador_hotel.controllers.*;
import org.gerenciador_hotel.services.FuncionarioService;
import org.gerenciador_hotel.services.HospedeService;
import org.gerenciador_hotel.services.QuartoService;
import org.gerenciador_hotel.services.ReservaService;

public class ServiceFactory {

    private final DaoFactory daoFactory;
    private ReservaService reservaService;
    private HospedeService hospedeService;
    private QuartoService quartoService;
    private FuncionarioService funcionarioService;

    public ServiceFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public ReservaService createReservaService() {
        if (reservaService == null) {
            reservaService = new ReservaService(daoFactory.createReservaDao());
        }
        return reservaService;
    }

    public HospedeService createHospedeService() {
        if (hospedeService == null) {
            hospedeService = new HospedeService(daoFactory.createHospedeDao());
        }
        return hospedeService;
    }

    public QuartoService createQuartoService() {
        if (quartoService == null) {
            quartoService = new QuartoService(daoFactory.createQuartoDao());
        }
        return quartoService;
    }

    public FuncionarioService createFuncionarioService() {
        if (funcionarioService == null) {
            funcionarioService = new FuncionarioService(daoFactory.createFuncionarioDao());
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
