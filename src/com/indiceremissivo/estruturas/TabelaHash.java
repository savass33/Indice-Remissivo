package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Implementação de uma tabela hash para armazenar palavras,
 * onde cada posição do array é uma árvore binária para resolver colisões.
 */

public class TabelaHash {
    private ArvoreBinariaBusca[] tabela;
    private static final int TAMANHO_PADRAO = 26; // Uma posição para cada letra do alfabeto

    public TabelaHash() {
        this(TAMANHO_PADRAO);
    }

    // Construtor que cria a tabela hash com tamanho especificado.

    public TabelaHash(int tamanho) {
        this.tabela = new ArvoreBinariaBusca[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArvoreBinariaBusca();
        }
    }

    // Calcula o indice da tabela a partir da palavra

    private int calcularHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return 0;
        }

        // Usa a primeira letra da palavra como índice (a=0, b=1, ..., z=25)
        char primeiraLetra = Character.toLowerCase(palavra.charAt(0));

        if (primeiraLetra >= 'a' && primeiraLetra <= 'z') {
            return primeiraLetra - 'a';
        } else {
            // Para caracteres não alfabéticos, usa o primeiro compartimento
            return 0;
        }
    }

    // * Insere uma palavra e sua linha de ocorrência na tabela hash. Se a palavra
    // já existir, adiciona a linha na lista de ocorrências.

    public void inserir(String palavra, int linha) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }

        int indice = calcularHash(palavra);
        tabela[indice].inserir(palavra, linha);
    }

    public Palavra buscar(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return null;
        }

        int indice = calcularHash(palavra);
        return tabela[indice].buscar(palavra);
    }

    public boolean contem(String palavra) {
        return buscar(palavra) != null;
    }

    public void percorrerEmOrdem(Consumer<Palavra> acao) {
        for (ArvoreBinariaBusca arvore : tabela) {
            arvore.percorrerEmOrdem(acao);
        }
    }

    // Gera lista ordenada de palavras-chave com ocorrências

    public List<Palavra> gerarIndiceRemissivo(List<String> palavrasChave) {
        List<Palavra> indice = new ArrayList<>();

        for (String palavraChave : palavrasChave) {
            Palavra palavra = buscar(palavraChave);
            if (palavra != null) {
                indice.add(palavra);
            }
        }

        return indice;
    }
}
