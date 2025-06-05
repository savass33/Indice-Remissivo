package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;

import java.util.ArrayList;

import com.indiceremissivo.util.ProcessadorTexto;

import java.util.List;
import java.util.function.Consumer;

// Implementação de uma tabela hash específica para armazenar objetos do tipo Palavra
public class TabelaHash {
    private static final int TAMANHO_PADRAO = 26; // Uma posição para cada letra do alfabeto
    private ArvoreBinariaBusca[] tabela;

    public TabelaHash() {
        this(TAMANHO_PADRAO);
    }

    // Construtor que inicializa a tabela hash com um tamanho específico.
    public TabelaHash(int tamanho) {
        this.tabela = new ArvoreBinariaBusca[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArvoreBinariaBusca();
        }
    }

    // Calcula o índice na tabela hash a partir da palavra fornecida.
    // Utiliza a primeira letra da palavra para determinar o bucket
    private int calcularHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return 0;
        }

        // Usa a primeira letra da palavra como índice (a=0, b=1, ..., z=25)
        char primeiraLetra = Character.toLowerCase(palavra.charAt(0));

        if (primeiraLetra >= 'a' && primeiraLetra <= 'z') {
            return primeiraLetra - 'a';
        } else {
            // Para caracteres não alfabéticos
            return 0;
        }
    }

    /*
    Insere uma palavra na tabela hash, associando-a a uma linha onde foi encontrada.
    Se a palavra já existir na árvore do bucket, apenas adiciona a nova ocorrência (linha).
    */
    public void inserir(String palavra, int linha) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }
        int indice = calcularHash(palavra);
        tabela[indice].inserir(palavra, linha);
    }


    // Busca uma palavra na tabela hash.
    public Palavra buscar(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return null;
        }

        int indice = calcularHash(palavra);
        return tabela[indice].buscar(palavra);
    }


    //Gera o índice remissivo para um conjunto de palavras-chave.
    public List<Palavra> gerarIndiceRemissivo(List<String> palavrasChave) {
        List<Palavra> indice = new ArrayList<>();
        for (String palavraChave : palavrasChave) {
            String palavraNormalizada = ProcessadorTexto.limparPalavra(palavraChave); // Normaliza a palavra
            Palavra palavra = buscar(palavraNormalizada);
            if (palavra != null) {
                indice.add(palavra);
            }
        }
        return indice;
    }
}
