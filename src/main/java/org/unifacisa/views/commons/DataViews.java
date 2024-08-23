package org.unifacisa.views.commons;

import javax.swing.*;

public class DataViews {

    public static void exibirAlertaDataFormatoErrado() {
        JOptionPane.showMessageDialog(null, "Data no formato errado.", "Alerta"
                , JOptionPane.ERROR_MESSAGE);
    }
}
