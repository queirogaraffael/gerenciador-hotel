package org.gerenciador_hotel.controllers;

import org.gerenciador_hotel.constantes.controllers.ConstantesManutencaoQuarto;
import org.gerenciador_hotel.constantes.controllers.ConstantesMenuQuartoController;
import org.gerenciador_hotel.constantes.modificacoes.ConstantesMenuModificacaoQuarto;
import org.gerenciador_hotel.dtos.QuartoDTO;
import org.gerenciador_hotel.dtos.QuartoReservaDTO;
import org.gerenciador_hotel.utils.dtos.SelecionaQuartoDTO;
import org.gerenciador_hotel.utils.dtos.SelecionaQuartoReservaDTO;
import org.gerenciador_hotel.enums.StatusQuarto;
import org.gerenciador_hotel.enums.StatusReserva;
import org.gerenciador_hotel.enums.TipoQuarto;
import org.gerenciador_hotel.model.domain.entities.Quarto;
import org.gerenciador_hotel.model.domain.entities.Reserva;
import org.gerenciador_hotel.services.QuartoService;
import org.gerenciador_hotel.services.ReservaService;
import org.gerenciador_hotel.views.commons.DataViews;
import org.gerenciador_hotel.views.quartos.*;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

import static org.gerenciador_hotel.utils.ManipulaData.dataInicialEPosteriorDataFinal;

public class MenuQuartosController {

    public final QuartoService quartoService;
    public final ReservaService reservaService;

    public MenuQuartosController(EntityManagerFactory entityManagerFactory) {
        this.quartoService = new QuartoService(entityManagerFactory);
        this.reservaService = new ReservaService(entityManagerFactory);
    }


    public void menuGerenciadorQuartos() {
        int opcaoMenuGerenciadoQuartos;

        do {
            opcaoMenuGerenciadoQuartos = MenuQuartosControllerView.exibirMenuGerenciadorQuartosView();

            switch (opcaoMenuGerenciadoQuartos) {

                case (ConstantesMenuQuartoController.CADASTRAR_QUARTO):
                    cadastrarQuarto();
                    break;

                case (ConstantesMenuQuartoController.VISUALIZAR_QUARTOS_POR_TIPO):
                    visualizarQuartosPorTipo();
                    break;

                case (ConstantesMenuQuartoController.VISUALIZAR_QUARTOS_POR_TIPOS_DISPONIVEIS_POR_DATA):
                    visualizaQuartosDisponiveisPorTipoEData();
                    break;

                case (ConstantesMenuQuartoController.ATUALIZAR_DADOS_QUARTO):
                    atualizarDadosQuarto();
                    break;
                case (ConstantesMenuQuartoController.MANUTENCAO_QUARTO):
                    manutencaoQuarto();
                    break;

                default:
                    break;

            }


        } while (opcaoMenuGerenciadoQuartos != ConstantesMenuQuartoController.VOLTAR);
    }

    private void cadastrarQuarto() {

        int numeroQuarto = LeDadosBasicosQuartoView.leNumeroQuarto();

        if (quartoService.haQuartoComMesmoNumero(numeroQuarto)) {
            AlertasQuartoViews.exibirAlertaQuartoJaCadastrado();
            return;
        }

        TipoQuarto tipoQuarto = EscolheTipoQuartoView.exibeEEscolheTipoQuartoView();

        int capacidadeQuarto = LeDadosBasicosQuartoView.leCapacidadeQuarto();

        double precoDiariaQuarto = LeDadosBasicosQuartoView.lePrecoQuarto();

        Quarto quarto = new Quarto();

        quarto.setNumeroQuarto(numeroQuarto);
        quarto.setTipoQuarto(tipoQuarto);
        quarto.setCapacidade(capacidadeQuarto);
        quarto.setPrecoDiaria(precoDiariaQuarto);
        quarto.setStatusQuarto(StatusQuarto.DISPONIVEL);

        quartoService.cadastrarQuarto(quarto);

        AlertasQuartoViews.exibirAlertaQuartoCadastradoComSucesso();

    }

    private void visualizarQuartosPorTipo() {

        TipoQuarto tipoQuarto = EscolheTipoQuartoView.exibeEEscolheTipoQuartoView();
        List<QuartoDTO> quartos = quartoService.getQuartosDTOByTipo(tipoQuarto);

        if (quartos == null || quartos.isEmpty()) {
            AlertasQuartoViews.exibirAlertaSemQuartoDesseTipoCadastrados();
            return;
        }


        int numeroQuarto = SelecionaQuartoDTO.selecionaNumeroQuarto(quartos);

        Quarto quarto = quartoService.getQuartoByNumero(numeroQuarto);

        MostraQuarto.printaQuarto(quarto);


    }


    private void visualizaQuartosDisponiveisPorTipoEData() {


        TipoQuarto tipoQuarto = EscolheTipoQuartoView.exibeEEscolheTipoQuartoView();


        LocalDate dataEntrada = DataViews.leDataEntrada();


        if (dataEntrada == null) {
            DataViews.exibirAlertaDataFormatoErrado();
            return;
        }


        if (dataInicialEPosteriorDataFinal(LocalDate.now(), dataEntrada)) {
            DataViews.exibirAlertaNaoPodeDataAnteriorAAtual();

            return;
        }


        LocalDate dataSaida = DataViews.leDataSaida();


        if (dataSaida == null) {
            DataViews.exibirAlertaDataFormatoErrado();
            return;
        }


        if (dataInicialEPosteriorDataFinal(dataEntrada, dataSaida)) {
            DataViews.exibirAlertaDataEntradaEPosteriorDataSaida();
            return;
        }


        List<QuartoDTO> quartos = quartoService.getQuartosDisponiveisPorTipo(tipoQuarto, dataEntrada, dataSaida);

        if (quartos == null || quartos.isEmpty()) {
            AlertasQuartoViews.exibirAlertaSemQuartoDesseTipoDisponiveisParaEssaData();

            return;
        }


        int numeroQuarto = SelecionaQuartoDTO.selecionaNumeroQuarto(quartos);

        Quarto quarto = quartoService.getQuartoByNumero(numeroQuarto);

        MostraQuarto.printaQuarto(quarto);

    }

    private void atualizarDadosQuarto() {

        int numeroQuarto = LeDadosBasicosQuartoView.leNumeroQuarto();

        Quarto quarto = quartoService.getQuartoByNumero(numeroQuarto);

        if (quarto == null) {
            return;
        }

        exibiOpcoesDeModificacaoDoQuartoEModifica(quarto);

    }


    private void manutencaoQuarto() {

        int opcao;

        do {
            opcao = MenuManutencaoQuartoView.exibirMenuManutencaoQuartosView();

            switch (opcao) {
                case (ConstantesManutencaoQuarto
                        .QUARTOS_EM_MANUTENCAO):
                    quartosEmManutencao();
                    break;

                case (ConstantesManutencaoQuarto.COLOCAR_EM_MANUTENCAO):
                    colocarEmManutencao();
                    break;

                case (ConstantesManutencaoQuarto.RETIRAR_MANUTENCAO):
                    retirarManutencao();
                    break;

                default:
                    break;
            }

        } while (opcao != ConstantesManutencaoQuarto.VOLTAR);


    }

    private void quartosEmManutencao() {

        List<QuartoDTO> quartosEmManutencao = quartoService.getQuartosEmManutencao();

        if (quartosEmManutencao == null || quartosEmManutencao.isEmpty()) {
            AlertasQuartoViews.exibirAlertaSemQuartoEmManutencao();
            return;
        }

        int numeroQuarto = SelecionaQuartoDTO.selecionaNumeroQuarto(quartosEmManutencao);

        Quarto quarto = quartoService.getQuartoByNumero(numeroQuarto);

        MostraQuarto.printaQuarto(quarto);

    }

    private void colocarEmManutencao() {

        LocalDate dataAtual = LocalDate.now();

        TipoQuarto tipoQuarto = EscolheTipoQuartoView.exibeEEscolheTipoQuartoView();
        List<QuartoDTO> quartosDisponiveisHojeParaManutencao = quartoService.getQuartosDisponiveisPorTipo(tipoQuarto, dataAtual, dataAtual);

        if (quartosDisponiveisHojeParaManutencao == null || quartosDisponiveisHojeParaManutencao.isEmpty()) {
            AlertasQuartoViews.exibirAlertaSemQuartosDisponiveisParaManutencao();
            return;
        }

        int numeroQuarto = SelecionaQuartoDTO.selecionaNumeroQuarto(quartosDisponiveisHojeParaManutencao);

        Quarto quarto = quartoService.getQuartoByNumero(numeroQuarto);

        quarto.setStatusQuarto(StatusQuarto.MANUTENCAO);

        quartoService.atualizaDadosQuarto(quarto);

        Reserva reservaManutencao = new Reserva();

        reservaManutencao.setQuarto(quarto);
        reservaManutencao.setDataEntrada(dataAtual);
        reservaManutencao.setDataSaida(dataAtual);
        reservaManutencao.setStatusReserva(StatusReserva.MANUTENCAO);

        reservaService.criaReserva(reservaManutencao);

        AlertasQuartoViews.exibirAlertaManutencaoCriadaComSucesso();

    }

    private void retirarManutencao() {

        List<QuartoReservaDTO> quartosReservasManutencao = quartoService.getQuartosReservasEmManutencao();

        if (quartosReservasManutencao == null || quartosReservasManutencao.isEmpty()) {
            AlertasQuartoViews.exibirAlertaSemQuartoEmManutencao();
            return;
        }

        Long idReserva = SelecionaQuartoReservaDTO.selecionaIdReservaQuartoReserva(quartosReservasManutencao);

        Reserva reserva = reservaService.getReservaById(idReserva);
        reserva.setStatusReserva(StatusReserva.FINALIZADO);

        Quarto quarto = reserva.getQuarto();
        quarto.setStatusQuarto(StatusQuarto.DISPONIVEL);

        quartoService.atualizaDadosQuarto(quarto);
        reservaService.atualizaReserva(reserva);

        AlertasQuartoViews.exibirAlertaQuartoRetiradoDeManutencaoComSucesso();

    }


    public void exibiOpcoesDeModificacaoDoQuartoEModifica(Quarto quarto) {
        int opcao;

        do {
            opcao = MenuModificacaoDadosQuartoView.exibeOpcoesModificarDadosView();

            switch (opcao) {
                case (ConstantesMenuModificacaoQuarto
                        .TIPO):
                    TipoQuarto tipoQuarto = EscolheTipoQuartoView.exibeEEscolheTipoQuartoView();
                    quarto.setTipoQuarto(tipoQuarto);
                    break;

                case (ConstantesMenuModificacaoQuarto.PRECO):
                    double precoDiariaQuarto = LeDadosBasicosQuartoView.lePrecoQuarto();
                    quarto.setPrecoDiaria(precoDiariaQuarto);
                    break;

                case (ConstantesMenuModificacaoQuarto.CAPACIDADE):
                    int capacidadeQuarto = LeDadosBasicosQuartoView.leCapacidadeQuarto();
                    quarto.setCapacidade(capacidadeQuarto);
                    break;

                default:
                    break;
            }

            if (opcao != ConstantesMenuModificacaoQuarto.VOLTAR) {
                quartoService.atualizaDadosQuarto(quarto);
                AlertasQuartoViews.exibirAlertaQuartoAtualizadoComSucesso();

            }

        } while (opcao != ConstantesMenuModificacaoQuarto.VOLTAR);
    }


}
