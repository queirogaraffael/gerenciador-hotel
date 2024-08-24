package org.unifacisa.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ManipulaData {

    public static final String FORMATO_DATA = "dd/MM/yyyy";

    public static final DateTimeFormatter formato = DateTimeFormatter.ofPattern(FORMATO_DATA);

    public static boolean verificaFormatoDataEstaCorreto(String stringData) {
        try {
            LocalDate.parse(stringData, formato);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static LocalDate retornaLocalDate(String data) {
        return LocalDate.parse(data, formato);
    }


    public static boolean dataInicialEPosteriorDataFinal(LocalDate dataInicial, LocalDate dataFinal) {
        return dataInicial.isAfter(dataFinal);
    }





}
