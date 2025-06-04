package com.indiceremissivo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivo {

    public static List<String> lerLinhas(String caminhoArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        }
        
        return linhas;
    }

    public static List<String> lerPalavrasChave(String caminhoArquivo) throws IOException {
        List<String> palavrasChave = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    palavrasChave.add(linha);
                }
            }
        }
        
        return palavrasChave;
    }
}
