package org.unifacisa.views.hospedes;

import javax.swing.*;

public class MenuModificacaoDadosHospedeView {


    private static final Object[] opcoesModificar = {"Nome", "Numero telefone", "Endereco", "Voltar"};

    private MenuModificacaoDadosHospedeView() {
    }

    public static int exibeOpcoesModificarDadosView() {
        return JOptionPane.showOptionDialog(
                null,
                "Escolha uma opcao: ",
                "Modificar dados do hospede",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opcoesModificar,
                opcoesModificar[0]
        );

    }
}
