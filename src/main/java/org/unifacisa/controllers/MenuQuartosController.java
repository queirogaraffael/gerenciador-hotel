package org.unifacisa.controllers;

import org.unifacisa.commons.utils.SelecionaQuartoDTO;
import org.unifacisa.constantes.ConstantesMenuModificacaoQuarto;
import org.unifacisa.constantes.ConstantesMenuQuartoController;
import org.unifacisa.dtos.QuartoDTO;
import org.unifacisa.enums.TipoQuarto;
import org.unifacisa.model.domain.entities.Quarto;
import org.unifacisa.services.QuartoService;
import org.unifacisa.views.quartos.*;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class MenuQuartosController {

    public final QuartoService quartoService;

    public MenuQuartosController(EntityManagerFactory entityManagerFactory) {
        this.quartoService = new QuartoService(entityManagerFactory);
    }


    public void menuGerenciadorQuartos() {
        String opcaoMenuGerenciadoQuartos;

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
                    break;

                case (ConstantesMenuQuartoController.ATUALIZAR_DADOS_QUARTO):
                    atualizarDadosQuarto();
                    break;

                default:
                    break;

            }


        } while (!opcaoMenuGerenciadoQuartos.equals(ConstantesMenuQuartoController.VOLTAR));
    }

    private void cadastrarQuarto() {

        String numeroQuarto = LeDadosBasicosQuartoView.leNumeroQuarto();

        if (quartoService.verificaSeHaQuartoComMesmoNumero(numeroQuarto)) {
            AlertasQuartoViews.exibirAlertaQuartoJaCadastrado();
            return;
        }

        TipoQuarto tipoQuarto = EscolheTipoQuartoView.exibeEEscolheTipoQuartoView();

        int capacidadeQuarto = LeDadosBasicosQuartoView.leCapacidadeQuarto();

        double precoQuarto = LeDadosBasicosQuartoView.lePrecoQuarto();

        Quarto quarto = new Quarto();

        quarto.setNumeroQuarto(numeroQuarto);
        quarto.setTipoQuarto(tipoQuarto);
        quarto.setCapacidade(capacidadeQuarto);
        quarto.setPreco(precoQuarto);

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
                    double preco = LeDadosBasicosQuartoView.lePrecoQuarto();
                    quarto.setPreco(preco);
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
