package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;

/**
 * Classe que representa um nó da árvore binária de busca.
 */
public class NoArvore {
    private Palavra palavra;
    private NoArvore esquerda;
    private NoArvore direita;

    /**
     * Construtor que inicializa o nó com uma palavra.
     * @param palavra Palavra a ser armazenada no nó
     */
    public NoArvore(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }

    /**
     * Obtém a palavra armazenada no nó.
     * @return Palavra armazenada no nó
     */
    public Palavra getPalavra() {
        return palavra;
    }

    /**
     * Define a palavra armazenada no nó.
     * @param palavra Nova palavra a ser armazenada
     */
    public void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }

    /**
     * Obtém o filho esquerdo do nó.
     * @return Filho esquerdo do nó
     */
    public NoArvore getEsquerda() {
        return esquerda;
    }

    /**
     * Define o filho esquerdo do nó.
     * @param esquerda Novo filho esquerdo
     */
    public void setEsquerda(NoArvore esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * Obtém o filho direito do nó.
     * @return Filho direito do nó
     */
    public NoArvore getDireita() {
        return direita;
    }

    /**
     * Define o filho direito do nó.
     * @param direita Novo filho direito
     */
    public void setDireita(NoArvore direita) {
        this.direita = direita;
    }
}
