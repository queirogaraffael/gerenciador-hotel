package org.gerenciador_hotel.views.funcionarios;

import org.gerenciador_hotel.model.domain.entities.ExtratoFuncionario;
import org.gerenciador_hotel.utils.ManipulaData;
import org.gerenciador_hotel.utils.VerificaCPF;

import javax.swing.*;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;

public class LeDadosBasicosFuncionarioViews {

    private LeDadosBasicosFuncionarioViews() {
    }

    public static String leNomeFuncionario() {
        String nome;
        while (true) {
            nome = JOptionPane.showInputDialog("Nome do funcionario (obrigatorio):");
            if (nome != null && !nome.trim().isEmpty()) {
                return nome;
            } else {
                JOptionPane.showMessageDialog(null, "Nome do funcionario e obrigatorio. Por favor, insira um nome valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leCPFFuncionario() {
        String cpf;
        while (true) {
            cpf = JOptionPane.showInputDialog("Digite o CPF no formato: " + VerificaCPF.PADRAO_CPF);
            if (cpf != null && !cpf.trim().isEmpty()) {
                return cpf;
            } else {
                JOptionPane.showMessageDialog(null, "CPF e obrigatorio. Por favor, insira um CPF valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leDataNascimentoFuncionario() {
        String data;
        while (true) {
            data = JOptionPane.showInputDialog("Digite a data de nascimento no formato: " + ManipulaData.FORMATO_DATA);
            if (data != null && !data.trim().isEmpty()) {
                return data;
            } else {
                JOptionPane.showMessageDialog(null, "Data de nascimento e obrigatoria. Por favor, insira uma data valida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static String leNumeroTelefoneFuncionario() {
        String telefone;
        while (true) {
            telefone = JOptionPane.showInputDialog("Numero de telefone (obrigatorio):");

            if (telefone != null && !telefone.trim().isEmpty()) {
                return telefone;
            } else {
                JOptionPane.showMessageDialog(null, "O numero de telefone é obrigatorio. Por favor, insira um numero valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static String leCargoFuncionario() {
        String cargo;
        while (true) {
            cargo = JOptionPane.showInputDialog("Cargo do funcionario (obrigatorio):");

            if (cargo != null && !cargo.trim().isEmpty()) {
                return cargo;
            } else {
                JOptionPane.showMessageDialog(null, "O cargo do funcionario é obrigatorio. Por favor, insira um cargo valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static YearMonth leData() {
        String data;
        while (true) {
            data = JOptionPane.showInputDialog("Digite a data no formato: " + ExtratoFuncionario.FORMATO_DATA);

            try {
                return YearMonth.parse(data, ExtratoFuncionario.formato);
            } catch (DateTimeParseException e) {
                JOptionPane.showMessageDialog(null, "Data invalida. Por favor, insira a data no formato correto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


    public static double leHorasTrabalhadas() {
        String entrada;
        while (true) {
            entrada = JOptionPane.showInputDialog("Numero de horas trabalhadas (obrigatorio):");

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, insira um numero valido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static double leValorHora() {
        String entrada;
        while (true) {
            entrada = JOptionPane.showInputDialog("Valor da hora (obrigatorio):");

            try {
                return Double.parseDouble(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida. Por favor, insira um numero válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}
