package org.unifacisa.views.funcionarios;

import org.unifacisa.utils.ManipulaData;
import org.unifacisa.utils.VerificaCPF;
import org.unifacisa.model.domain.entities.ExtratoFuncionario;

import javax.swing.*;
import java.time.YearMonth;

public class LeDadosBasicosFuncionarioViews {

    private LeDadosBasicosFuncionarioViews() {
    }

    public static String leNomeFuncionario() {
        return JOptionPane.showInputDialog("Nome do funcionario: ");
    }

    public static String leCPFFuncionario() {
        return JOptionPane.showInputDialog("Digite o CPF no formato: " + VerificaCPF.PADRAO_CPF);
    }

    public static String leDataNascimentoFuncionario() {
        return JOptionPane.showInputDialog("Digite a data de nascimento no formato: " + ManipulaData.FORMATO_DATA);
    }

    public static String leNumeroTelefoneFuncionario() {
        return JOptionPane.showInputDialog("Numero de telefone: ");
    }

    public static String leCargoFuncionario() {
        return JOptionPane.showInputDialog("Cargo do funcionario: ");
    }

    public static YearMonth leData() {
        String data = JOptionPane.showInputDialog("Digite a data no formato: " + ExtratoFuncionario.FORMATO_DATA);
        return YearMonth.parse(data, ExtratoFuncionario.formato);
    }


    public static double leHorasTrabalhadas() {
        return Double.parseDouble(JOptionPane.showInputDialog("Numero de horas trabalhadas: "));
    }

    public static double leValorHora() {
        return Double.parseDouble(JOptionPane.showInputDialog("Valor da hora: "));
    }

}
