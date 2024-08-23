package org.unifacisa.views.hospedes;

import org.unifacisa.utils.ManipulaData;
import org.unifacisa.utils.VerificaCPF;

import javax.swing.*;

public class LeDadosBasicosHospedeViews {

    private LeDadosBasicosHospedeViews() {
    }

    public static String leNomeHospede() {
        return JOptionPane.showInputDialog("Digite o nome do hospede: ");
    }

    public static String leCPFHospede() {
        return JOptionPane.showInputDialog("Digite o CPF do hospede no formato:" + VerificaCPF.padraoCPF);
    }


    public static String leDataNascimentoHospede() {
        return JOptionPane.showInputDialog("Digite a data de nascimento do hospede no formato: " + ManipulaData.FORMATO_DATA);
    }

    public static String leNumeroTelefoneHospede() {
        return JOptionPane.showInputDialog("Digite o numero de telefone do hospede: ");
    }


}
