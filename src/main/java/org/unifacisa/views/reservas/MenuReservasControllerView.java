package org.unifacisa.views.reservas;

import javax.swing.*;

public class MenuReservasControllerView {

    private static final String MENU_TITLE = "Gerenciador de Reservas";
    private static final String MENU_PROMPT = "Escolha uma opcao: ";


    private MenuReservasControllerView() {
    }

    private static final Object[] menuOptions = {
            "Criar Reserva", // tipo quarto // data(1) // disponiveis // listar // selecionar // hospede // repetição
            "Cancelar Reserva", // busca reservas de um cpf // data inicial-final e quarto
            "Voltar"


            //"Verificar a Disponibilidade de Quartos para uma data especifica"
            //"Verificar Quartos disponiveis"
            // verificar a disponibilidade para uma data especidifca
            //uma reserva pode ter varios quartos

    };


    public static String exibirMenuGerenciadorReservasView() {
        Object opcao = JOptionPane.showInputDialog(
                null,
                MENU_PROMPT,
                MENU_TITLE,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                menuOptions,
                menuOptions[0]
        );

        if (opcao != null) {
            return opcao.toString();
        }

        return menuOptions[menuOptions.length-1].toString();

    }
}
