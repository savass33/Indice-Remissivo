package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;


// Classe que representa um nó da árvore binária de busca.
public class NoArvore {
    private Palavra palavra;
    private NoArvore esquerda;
    private NoArvore direita;

    // Construtor que inicializa o nó com uma palavra.
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
