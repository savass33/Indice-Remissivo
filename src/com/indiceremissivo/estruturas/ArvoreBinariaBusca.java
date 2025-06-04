package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;
import java.util.function.Consumer;

/**
 * Implementação de uma Árvore Binária de Busca para armazenar palavras.
 */
public class ArvoreBinariaBusca {
    private NoArvore raiz;

    /**
     * Construtor que inicializa uma árvore vazia.
     */
    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    /**
     * Insere uma palavra na árvore ou adiciona uma ocorrência se a palavra já existir.
     * @param palavra Palavra a ser inserida
     * @param linha Linha onde a palavra ocorre
     */
    public void inserir(String palavra, int linha) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }

        Palavra novaPalavra = new Palavra(palavra, linha);
        
        if (raiz == null) {
            raiz = new NoArvore(novaPalavra);
            return;
        }
        
        inserirRecursivo(raiz, novaPalavra);
    }

    /**
     * Método auxiliar recursivo para inserção de palavras na árvore.
     * @param no Nó atual
     * @param novaPalavra Palavra a ser inserida
     */
    private void inserirRecursivo(NoArvore no, Palavra novaPalavra) {
        int comparacao = novaPalavra.getPalavra().compareTo(no.getPalavra().getPalavra());
        
        if (comparacao == 0) {
            // A palavra já existe, adiciona a ocorrência
            no.getPalavra().adicionarOcorrencia(novaPalavra.getOcorrencias().obter(0));
        } else if (comparacao < 0) {
            // A palavra deve ir para a esquerda
            if (no.getEsquerda() == null) {
                no.setEsquerda(new NoArvore(novaPalavra));
            } else {
                inserirRecursivo(no.getEsquerda(), novaPalavra);
            }
        } else {
            // A palavra deve ir para a direita
            if (no.getDireita() == null) {
                no.setDireita(new NoArvore(novaPalavra));
            } else {
                inserirRecursivo(no.getDireita(), novaPalavra);
            }
        }
    }

    /**
     * Busca uma palavra na árvore.
     * @param palavra Palavra a ser buscada
     * @return A palavra encontrada ou null se não existir
     */
    public Palavra buscar(String palavra) {
        return buscarRecursivo(raiz, palavra);
    }

    /**
     * Método auxiliar recursivo para busca de palavras na árvore.
     * @param no Nó atual
     * @param palavra Palavra a ser buscada
     * @return A palavra encontrada ou null se não existir
     */
    private Palavra buscarRecursivo(NoArvore no, String palavra) {
        if (no == null) {
            return null;
        }
        
        int comparacao = palavra.compareTo(no.getPalavra().getPalavra());
        
        if (comparacao == 0) {
            return no.getPalavra();
        } else if (comparacao < 0) {
            return buscarRecursivo(no.getEsquerda(), palavra);
        } else {
            return buscarRecursivo(no.getDireita(), palavra);
        }
    }

    /**
     * Verifica se a árvore contém uma palavra.
     * @param palavra Palavra a ser verificada
     * @return true se a palavra estiver na árvore, false caso contrário
     */
    public boolean contem(String palavra) {
        return buscar(palavra) != null;
    }

    /**
     * Percorre a árvore em ordem (in-order) aplicando uma ação a cada palavra.
     * @param acao Ação a ser aplicada a cada palavra
     */
    public void percorrerEmOrdem(Consumer<Palavra> acao) {
        percorrerEmOrdemRecursivo(raiz, acao);
    }

    /**
     * Método auxiliar recursivo para percorrer a árvore em ordem.
     * @param no Nó atual
     * @param acao Ação a ser aplicada a cada palavra
     */
    private void percorrerEmOrdemRecursivo(NoArvore no, Consumer<Palavra> acao) {
        if (no != null) {
            percorrerEmOrdemRecursivo(no.getEsquerda(), acao);
            acao.accept(no.getPalavra());
            percorrerEmOrdemRecursivo(no.getDireita(), acao);
        }
    }

    /**
     * Verifica se a árvore está vazia.
     * @return true se a árvore estiver vazia, false caso contrário
     */
    public boolean estaVazia() {
        return raiz == null;
    }
}
