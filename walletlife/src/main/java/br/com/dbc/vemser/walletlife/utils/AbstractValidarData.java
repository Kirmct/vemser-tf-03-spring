package br.com.dbc.vemser.walletlife.utils;

public abstract class AbstractValidarData {

    public static boolean validarData(String data) {
        String[] partes = data.split("/");
        if (partes.length != 3) {
            return false;
        }
        try {
            String diaStr = partes[0];
            int dia = Integer.parseInt(diaStr);

            if (diaStr.length() != 2 || dia > 31 || dia < 1) {
                return false;
            }
            String mesStr = partes[1];
            int mes = Integer.parseInt(mesStr);

            if (mesStr.length() != 2 || mes > 12 || mes < 1) {
                return false;
            }
            String anoStr = partes[2];
            int ano = Integer.parseInt(anoStr);
            if (anoStr.length() != 4) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
