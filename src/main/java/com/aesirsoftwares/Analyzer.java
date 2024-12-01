package com.aesirsoftwares;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Pattern;

public class Analyzer {

    private static final Pattern PRIVATE_IP_PATTERN = Pattern.compile(
        "^(10\\.|172\\.(1[6-9]|2[0-9]|3[0-1])\\.|192\\.168\\.).*");

    //Código pra verificar a conectividade da URL
    public static String analyzeUrl(String urlString)
    {
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("HEAD");

            connection.setConnectTimeout(5000);

            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 400)
            {
                return "URL acessível: código HTTP " + responseCode;
            } else {
                return "URL inacessível: código HTTP " + responseCode;
            }
        } catch (Exception e)
{
    return "Erro ao analisar a URL: " + e.getMessage();
        }
    }

    //Verificando ip privado
    public static String analyzeIp(String ip) {
        if (PRIVATE_IP_PATTERN.matcher(ip).matches()) {
            return "IP é privado";
        }
        return "IP público";
    }
}