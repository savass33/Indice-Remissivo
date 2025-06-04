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
        // Abre um BufferedWriter para escrever no arquivo especificado.
        // O uso do try-with-resources garante que o recurso será fechado
        // automaticamente.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            // Para cada palavra da lista índice
            for (Palavra palavra : indice) {
                // Escreve no arquivo a representação textual da palavra e suas ocorrências
                // A representação vem do método toString() da classe Palavra
                writer.write(palavra.toString());
                // Escreve uma nova linha após cada palavra para separar as entradas no arquivo
                writer.newLine();
            }
            // Ao final do try, o BufferedWriter será fechado automaticamente
        }
    }
}
