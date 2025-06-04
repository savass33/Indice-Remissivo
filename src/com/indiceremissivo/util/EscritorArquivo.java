package com.indiceremissivo.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.indiceremissivo.model.Palavra;

/**
 * Classe utilitária para escrita de arquivos.
 */
public class EscritorArquivo {
    
    /**
     * Escreve o índice remissivo em um arquivo.
     * @param caminhoArquivo Caminho do arquivo de saída
     * @param indice Lista de palavras do índice remissivo
     * @throws IOException Se ocorrer um erro na escrita do arquivo
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
