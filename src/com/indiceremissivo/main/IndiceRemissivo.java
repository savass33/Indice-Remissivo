package com.indiceremissivo.main;

import com.indiceremissivo.estruturas.TabelaHash;
import com.indiceremissivo.model.Palavra;
import com.indiceremissivo.util.EscritorArquivo;
import com.indiceremissivo.util.LeitorArquivo;
import com.indiceremissivo.util.ProcessadorTexto;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Classe principal para gerar o índice remissivo a partir de um arquivo de
 * texto e uma lista de palavras-chave.
 * O índice remissivo mostra para cada palavra-chave as linhas onde ela ocorre
 * no texto.
 */
public class IndiceRemissivo {

    /**
     * Método principal que executa o programa.
     * Aceita argumentos opcionais para os caminhos dos arquivos: texto,
     * palavras-chave e saída.
     * Caso não sejam fornecidos, usa os caminhos padrão.
     * 
     * @param args argumentos da linha de comando (caminhos dos arquivos)
     */
    public static void main(String[] args) {
        try {
            // Define os caminhos padrão dos arquivos
            String arquivoTexto = "data/texto.txt";
            String arquivoPalavrasChave = "data/palavras_chave.txt";
            String arquivoSaida = "data/indice_remissivo.txt";

            // Se o usuário passou argumentos, usa esses caminhos em vez dos padrões
            if (args.length >= 3) {
                arquivoTexto = args[0];
                arquivoPalavrasChave = args[1];
                arquivoSaida = args[2];
            }

            // Executa o processamento do texto e geração do índice remissivo
            processarTexto(arquivoTexto, arquivoPalavrasChave, arquivoSaida);

            System.out.println("Índice remissivo gerado com sucesso!");

        } catch (IOException e) {
            // Captura erros relacionados a leitura e escrita de arquivos
            System.err.println("Erro ao processar os arquivos: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // Captura outros erros inesperados
            System.err.println("Erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método que lê o arquivo de texto, processa as palavras e suas ocorrências,
     * e gera o índice remissivo com base nas palavras-chave fornecidas.
     * 
     * @param arquivoTexto         Caminho do arquivo de texto original.
     * @param arquivoPalavrasChave Caminho do arquivo com as palavras-chave.
     * @param arquivoSaida         Caminho onde será salvo o índice remissivo
     *                             gerado.
     * @throws IOException Se ocorrer erro na leitura ou escrita dos arquivos.
     */
    public static void processarTexto(String arquivoTexto, String arquivoPalavrasChave, String arquivoSaida)
            throws IOException {
        // Lê todas as linhas do arquivo de texto para uma lista
        List<String> linhasTexto = LeitorArquivo.lerLinhas(arquivoTexto);

        // Cria uma tabela hash para armazenar as palavras e suas ocorrências
        TabelaHash tabelaHash = new TabelaHash();

        // Percorre cada linha do texto
        for (int i = 0; i < linhasTexto.size(); i++) {
            String linha = linhasTexto.get(i);

            // Divide a linha em palavras (ex: separando por espaços, pontuações, etc)
            String[] palavras = ProcessadorTexto.dividirEmPalavras(linha);

            // Para cada palavra encontrada na linha
            for (String palavra : palavras) {
                // Limpa a palavra (remove pontuações, converte para minúsculo, etc)
                String palavraLimpa = ProcessadorTexto.limparPalavra(palavra);

                // Se a palavra não ficou vazia após limpeza
                if (!palavraLimpa.isEmpty()) {
                    // Insere a palavra na tabela hash junto com o número da linha (começa em 1)
                    tabelaHash.inserir(palavraLimpa, i + 1);
                }
            }
        }

        // Lê a lista de palavras-chave do arquivo especificado
        List<String> palavrasChave = LeitorArquivo.lerPalavrasChave(arquivoPalavrasChave);

        // Gera o índice remissivo baseado na tabela hash e palavras-chave
        gerarIndiceRemissivo(tabelaHash, palavrasChave, arquivoSaida);
    }

    /**
     * Método que gera o índice remissivo, ordena as palavras e salva em arquivo.
     * 
     * @param tabelaHash    A tabela hash que contém as palavras e suas ocorrências.
     * @param palavrasChave Lista de palavras-chave para incluir no índice.
     * @param arquivoSaida  Caminho do arquivo onde o índice será salvo.
     * @throws IOException Se ocorrer erro na escrita do arquivo.
     */
    public static void gerarIndiceRemissivo(TabelaHash tabelaHash, List<String> palavrasChave, String arquivoSaida)
            throws IOException {
        // Busca as palavras na tabela hash e gera a lista do índice remissivo
        List<Palavra> indice = tabelaHash.gerarIndiceRemissivo(palavrasChave);

        // Ordena a lista de palavras em ordem alfabética
        Collections.sort(indice, new Comparator<Palavra>() {
            @Override
            public int compare(Palavra p1, Palavra p2) {
                return p1.getPalavra().compareTo(p2.getPalavra());
            }
        });

        // Escreve o índice remissivo ordenado no arquivo de saída
        EscritorArquivo.escreverIndiceRemissivo(arquivoSaida, indice);
    }
}
