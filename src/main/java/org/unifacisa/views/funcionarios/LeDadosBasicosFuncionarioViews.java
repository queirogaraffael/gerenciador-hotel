package org.unifacisa.views.funcionarios;

import org.unifacisa.commons.utils.ManipulaData;
import org.unifacisa.commons.utils.VerificaCPF;
import org.unifacisa.model.domain.entities.ExtratoFuncionario;

import javax.swing.*;
import java.time.YearMonth;

public class LeDadosBasicosFuncionarioViews {

    private LeDadosBasicosFuncionarioViews() {
    }

    public static String leNomeFuncionario() {
        return JOptionPane.showInputDialog("Digite o nome do funcionario: ");
    }

    public static String leCPFFuncionario() {
        return JOptionPane.showInputDialog("Digite o CPF do funcionario no formato:" + VerificaCPF.padraoCPF);
    }

    public static String leDataNascimentoFuncionario() {
        return JOptionPane.showInputDialog("Digite a data de nascimento do funcionario no formato: " + ManipulaData.FORMATO_DATA);
    }

    public static String leNumeroTelefoneFuncionario() {
        return JOptionPane.showInputDialog("Digite o numero de telefone do funcionario: ");
    }

    public static String leCargoFuncionario() {
        return JOptionPane.showInputDialog("Escreva o cargo do funcionario: ");
    }

    public static YearMonth leData() {

        String data = JOptionPane.showInputDialog("Digite a data no formato: " + ExtratoFuncionario.FORMATO_DATA);
        return YearMonth.parse(data, ExtratoFuncionario.formato);
    }


    public static double leHorasTrabalhadas() {
        return Double.parseDouble(JOptionPane.showInputDialog("Digite o numero de horas trabalhadas: "));
    }

    public static double leValorHora() {
        return Double.parseDouble(JOptionPane.showInputDialog("Digite o valor da hora: "));
    }

}
