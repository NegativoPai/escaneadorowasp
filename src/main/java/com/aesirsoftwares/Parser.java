package com.aesirsoftwares;

import java.net.URL;
import java.util.regex.Pattern;

public class Parser {

    //Fiz um regex simples pra validar Ips do tipo IPV4
    private static final Pattern
    IP_PATTERN = Pattern.compile("^((25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[0-1]?[0-9][0-9]?)$"
    );

    // validando pra identificar se a entrada é uma URL ou uma IP
    public static String parseInput(String input){
        try{
            //tentando criar uma URL
            new URL(input);
            return "URL válida";
        } catch (Exception e) {
            if (IP_PATTERN.matcher(input).matches()) {
                return "IP válido";
            } else {
                return "Entrada inválida: não é URL nem IP";
            }
        }
    }
}