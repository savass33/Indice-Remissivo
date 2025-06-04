package com.indiceremissivo.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilitário para leitura de arquivos texto.
 * Possui métodos para ler linhas genéricas e listas de palavras-chave.
 */

public class LeitorArquivo {

    /**
     * Lê todas as linhas de um arquivo texto e retorna em uma lista.
     * 
     * @param caminhoArquivo Caminho do arquivo a ser lido.
     * @return Lista de linhas do arquivo.
     * @throws IOException Em caso de erro na leitura.
     */

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

    /**
     * Lê palavras-chave de um arquivo, considerando uma palavra por linha.
     * Remove linhas vazias e espaços em branco.
     * 
     * @param caminhoArquivo Caminho do arquivo de palavras-chave.
     * @return Lista de palavras-chave.
     * @throws IOException Em caso de erro na leitura.
     */

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
