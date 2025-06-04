package com.indiceremissivo.main;

import com.indiceremissivo.estruturas.TabelaHash;
import com.indiceremissivo.model.Palavra;
import com.indiceremissivo.util.EscritorArquivo;
import com.indiceremissivo.util.LeitorArquivo;
import com.indiceremissivo.util.ProcessadorTexto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IndiceRemissivo {

    public static void main(String[] args) {
        try {
            // Caminhos dos arquivos
            String arquivoTexto = "data/texto.txt";
            String arquivoPalavrasChave = "data/palavras_chave.txt";
            String arquivoSaida = "data/indice_remissivo.txt";
            
            // Verifica se foram fornecidos argumentos para os caminhos dos arquivos
            if (args.length >= 3) {
                arquivoTexto = args[0];
                arquivoPalavrasChave = args[1];
                arquivoSaida = args[2];
            }
            
            // Processa o texto e gera o índice remissivo
            processarTexto(arquivoTexto, arquivoPalavrasChave, arquivoSaida);
            
            System.out.println("Índice remissivo gerado com sucesso!");
            
        } catch (IOException e) {
            System.err.println("Erro ao processar os arquivos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void processarTexto(String arquivoTexto, String arquivoPalavrasChave, String arquivoSaida) throws IOException {
        // Lê o texto
        List<String> linhasTexto = LeitorArquivo.lerLinhas(arquivoTexto);
        
        // Cria a tabela hash
        TabelaHash tabelaHash = new TabelaHash();
        
        // Processa o texto e armazena as palavras na tabela hash
        for (int i = 0; i < linhasTexto.size(); i++) {
            String linha = linhasTexto.get(i);
            String[] palavras = ProcessadorTexto.dividirEmPalavras(linha);
            
            for (String palavra : palavras) {
                String palavraLimpa = ProcessadorTexto.limparPalavra(palavra);
                if (!palavraLimpa.isEmpty()) {
                    tabelaHash.inserir(palavraLimpa, i + 1); // Linha começa em 1, não em 0
                }
            }
        }
        
        // Lê as palavras-chave
        List<String> palavrasChave = LeitorArquivo.lerPalavrasChave(arquivoPalavrasChave);
        
        // Gera o índice remissivo
        gerarIndiceRemissivo(tabelaHash, palavrasChave, arquivoSaida);
    }


    public static void gerarIndiceRemissivo(TabelaHash tabelaHash, List<String> palavrasChave, String arquivoSaida) throws IOException {
        // Obtém as palavras do índice remissivo
        List<Palavra> indice = tabelaHash.gerarIndiceRemissivo(palavrasChave);
        
        // Ordena as palavras em ordem alfabética
        Collections.sort(indice, new Comparator<Palavra>() {
            @Override
            public int compare(Palavra p1, Palavra p2) {
                return p1.getPalavra().compareTo(p2.getPalavra());
            }
        });
        
        // Escreve o índice remissivo no arquivo de saída
        EscritorArquivo.escreverIndiceRemissivo(arquivoSaida, indice);
    }
}
