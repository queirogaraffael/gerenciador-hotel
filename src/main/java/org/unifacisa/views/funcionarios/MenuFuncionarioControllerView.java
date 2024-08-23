package org.unifacisa.views.funcionarios;

import javax.swing.*;

public class MenuFuncionarioControllerView {

    private static final String MENU_TITLE = "Gerenciador de Funcionarios";
    private static final String MENU_PROMPT = "Escolha uma opcao: ";

    private MenuFuncionarioControllerView() {
    }


    private static final Object[] menuOptions = {
            "Cadastrar Funcionario",
            "Editar Dados do Funcionario",
            "Visualizar Funcionario pelo CPF",
            "Buscar e Visualizar Funcionario por Nome",
            "Extrato(s)",
            "Voltar"
    };


    public static String exibirMenuTarefasView() {
        Object opcaoSelecionada = JOptionPane.showInputDialog(
                null,
                MENU_PROMPT,
                MENU_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuOptions,
                menuOptions[0]
        );

        if (opcaoSelecionada != null) {
            return opcaoSelecionada.toString();
        }

        return menuOptions[menuOptions.length-1].toString();
    }

}
