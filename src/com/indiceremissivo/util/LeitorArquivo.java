package com.indiceremissivo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Utilitário para leitura de arquivos texto.
public class LeitorArquivo {

    // Lê todas as linhas de um arquivo texto e retorna em uma lista.
    public static List<String> lerLinhas(String caminhoArquivo) throws IOException {
        List<String> linhas = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            // Lê linha a linha até o fim do arquivo
            while ((linha = reader.readLine()) != null) {
                linhas.add(linha);
            }
        }

        return linhas;
    }

    // Lê palavras-chave de um arquivo, considerando uma palavra por linha.
    public static List<String> lerPalavrasChave(String caminhoArquivo) throws IOException {
        List<String> palavrasChave = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                linha = linha.trim();  // Remove espaços antes e depois
                if (!linha.isEmpty()) { // Ignora linhas em branco
                    palavrasChave.add(linha);
                }
            }
        }

        return palavrasChave;
    }
}
