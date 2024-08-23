package org.unifacisa.views.funcionarios;

import javax.swing.*;

public class MenuModificacaoDadosFuncionarioView {
    private static final Object[] opcoesModificar = {"Nome", "Numero telefone", "Turno", "Cargo", "Endereco", "Voltar"};

    private MenuModificacaoDadosFuncionarioView() {
    }

    public static int exibeOpcoesModificarDadosView() {
        return JOptionPane.showOptionDialog(
                null,
                "Escolha uma opcao: ",
                "Modificar dados do funcionario",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesModificar,
                opcoesModificar[0]
        );

    }
}
