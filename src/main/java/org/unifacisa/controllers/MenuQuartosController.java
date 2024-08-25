package org.unifacisa.controllers;

import org.unifacisa.constantes.modificacoes.ConstantesMenuModificacaoQuarto;
import org.unifacisa.constantes.controllers.ConstantesMenuQuartoController;
import org.unifacisa.dtos.QuartoDTO;
import org.unifacisa.enums.TipoQuarto;
import org.unifacisa.model.domain.entities.Quarto;
import org.unifacisa.services.QuartoService;
import org.unifacisa.dtos.utils.SelecionaQuartoDTO;
import org.unifacisa.views.commons.DataViews;
import org.unifacisa.views.quartos.*;

import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;

import static org.unifacisa.utils.ManipulaData.dataInicialEPosteriorDataFinal;

public class MenuQuartosController {

    public final QuartoService quartoService;

    public MenuQuartosController(EntityManagerFactory entityManagerFactory) {
        this.quartoService = new QuartoService(entityManagerFactory);
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

                default:
                    break;

            }


        } while (opcaoMenuGerenciadoQuartos != ConstantesMenuQuartoController.VOLTAR);
    }


    private void cadastrarQuarto() {

        String numeroQuarto = LeDadosBasicosQuartoView.leNumeroQuarto();

        if (quartoService.verificaSeHaQuartoComMesmoNumero(numeroQuarto)) {
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


        String numeroQuarto = SelecionaQuartoDTO.selecionaNumeroQuarto(quartos);

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


        String numeroQuarto = SelecionaQuartoDTO.selecionaNumeroQuarto(quartos);

        Quarto quarto = quartoService.getQuartoByNumero(numeroQuarto);

        MostraQuarto.printaQuarto(quarto);

    }

    private void atualizarDadosQuarto() {

        String numeroQuarto = LeDadosBasicosQuartoView.leNumeroQuarto();

        Quarto quarto = quartoService.getQuartoByNumero(numeroQuarto);

        if (quarto == null) {
            return;
        }

        exibiOpcoesDeModificacaoDoQuartoEModifica(quarto);

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
                AlertasQuartoViews.exibirAlertaQuartoModificadoComSucesso();

            }

        } while (opcao != ConstantesMenuModificacaoQuarto.VOLTAR);
    }


}
