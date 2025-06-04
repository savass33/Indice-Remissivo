package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;
import java.util.function.Consumer;

/**
 * Representa um nó de uma Árvore Binária de Busca (ABB) contendo uma palavra.
 * Cada nó possui uma palavra, e referências para os nós filhos esquerdo e
 * direito.
 */

public class NoArvore {
    private Palavra palavra;
    private NoArvore esquerda;
    private NoArvore direita;

    /**
     * Construtor que inicializa o nó com uma palavra.
     * Os filhos esquerdo e direito são inicialmente nulos.
     * 
     * @param palavra A palavra armazenada neste nó.
     */

    public NoArvore(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }

    public Palavra getPalavra() {
        return palavra;
    }

    public void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }

    public NoArvore getEsquerda() {
        return esquerda;
    }

    public void setEsquerda(NoArvore esquerda) {
        this.esquerda = esquerda;
    }

    public NoArvore getDireita() {
        return direita;
    }

    public void setDireita(NoArvore direita) {
        this.direita = direita;
    }
}
