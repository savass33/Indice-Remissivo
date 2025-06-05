package com.indiceremissivo.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.indiceremissivo.model.Palavra;

//Classe utilitária para escrita de arquivos.
public class EscritorArquivo {


    //Escreve o índice remissivo em um arquivo.
    public static void escreverIndiceRemissivo(String caminhoArquivo, List<Palavra> indice) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo))) {
            for (Palavra palavra : indice) { // Para cada palavra da lista índice
                writer.write(palavra.toString()); // A representação vem do método toString() da classe Palavra
                writer.newLine(); // Escreve uma nova linha após cada palavra para separar as entradas no arquivo
            }
        }
    }
}
