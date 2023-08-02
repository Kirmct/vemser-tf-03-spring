package br.com.dbc.vemser.walletlife.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractFormatoEmail {
    public static boolean formatadorEmail(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    ;
}
