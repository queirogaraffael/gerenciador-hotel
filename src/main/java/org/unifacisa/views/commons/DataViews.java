package org.unifacisa.views.commons;

import org.unifacisa.utils.ManipulaData;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class DataViews {

    public static void exibirAlertaDataFormatoErrado() {
        JOptionPane.showMessageDialog(null, "Data no formato errado.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlertaDataEntradaEPosteriorDataSaida() {
        JOptionPane.showMessageDialog(null, "Data de entrada é porterior a data de saida.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }

    public static void exibirAlertaNaoPodeDataAnteriorAAtual() {

        JOptionPane.showMessageDialog(null, "Data de entrada antiga.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }

    public static LocalDate leDataEntrada() {
        try {
            String dataEntrada = JOptionPane.showInputDialog("Digite a data de entrada no formato: " + ManipulaData.FORMATO_DATA);
            return LocalDate.parse(dataEntrada, ManipulaData.formato);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Data invalida. Por favor, use o formato: " + ManipulaData.FORMATO_DATA);
            return null;
        }
    }


    public static LocalDate leDataSaida() {
        try {
            String dataSaida = JOptionPane.showInputDialog("Digite a data de saida no formato: " + ManipulaData.FORMATO_DATA);
            return LocalDate.parse(dataSaida, ManipulaData.formato);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(null, "Data invalida. Por favor, use o formato: " + ManipulaData.FORMATO_DATA);
            return null;
        }
    }


}
