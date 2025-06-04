package com.indiceremissivo.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.indiceremissivo.model.Palavra;

/**
 * Utilitário para escrever o índice remissivo em arquivo texto.
 */

public class EscritorArquivo {

    /**
     * Escreve o índice remissivo em arquivo.
     * Cada linha do arquivo conterá uma palavra seguida das linhas em que ocorre.
     * 
     * @param caminhoArquivo Caminho do arquivo de saída.
     * @param indice         Lista de palavras com suas ocorrências.
     * @throws IOException Em caso de erro na escrita do arquivo.
     */

    public static void escreverIndiceRemissivo(String caminhoArquivo, List<Palavra> indice) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Palavra palavra : indice) {
                writer.write(palavra.toString());
                writer.newLine();
            }
        }
    }
}
