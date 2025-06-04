package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;

/**
 * Representa um nó da Árvore Binária de Busca (ABB) que armazena um objeto do
 * tipo Palavra.
 * 
 * Cada nó possui uma referência para um objeto Palavra, que contém o texto da
 * palavra e suas
 * ocorrências (ex: linhas em que aparece no texto).
 * 
 * Além disso, cada nó possui referências para dois filhos:
 * - esquerda: subárvore contendo palavras alfabeticamente menores que a palavra
 * do nó atual.
 * - direita: subárvore contendo palavras alfabeticamente maiores que a palavra
 * do nó atual.
 * 
 * Essa estrutura permite manter a árvore ordenada, facilitando operações como
 * busca,
 * inserção e percurso ordenado das palavras.
 * 
 * A árvore resultante representa um índice remissivo, onde é possível localizar
 * rapidamente
 * as ocorrências das palavras no texto.
 */
public class NoArvore {
    // Objeto Palavra armazenado neste nó, que contém a palavra em si e suas
    // ocorrências.
    private Palavra palavra;

    // Referência para o filho esquerdo da árvore, contendo palavras menores.
    private NoArvore esquerda;

    // Referência para o filho direito da árvore, contendo palavras maiores.
    private NoArvore direita;

    /**
     * Construtor que inicializa o nó com o objeto Palavra informado.
     * Os filhos esquerdo e direito são inicialmente nulos, indicando que
     * o nó é uma folha até que nós filhos sejam adicionados.
     * 
     * @param palavra Objeto Palavra a ser armazenado neste nó.
     */
    public NoArvore(Palavra palavra) {
        this.palavra = palavra;
        this.esquerda = null;
        this.direita = null;
    }

    /**
     * Retorna o objeto Palavra armazenado neste nó.
     * 
     * @return Palavra associada a este nó da árvore.
     */
    public Palavra getPalavra() {
        return palavra;
    }

    /**
     * Atualiza o objeto Palavra armazenado neste nó.
     * 
     * @param palavra Novo objeto Palavra a ser associado a este nó.
     */
    public void setPalavra(Palavra palavra) {
        this.palavra = palavra;
    }

    /**
     * Retorna o nó filho esquerdo da árvore.
     * 
     * @return Referência para o nó esquerdo (subárvore com palavras menores).
     */
    public NoArvore getEsquerda() {
        return esquerda;
    }

    /**
     * Define o nó filho esquerdo da árvore.
     * 
     * @param esquerda Nó a ser associado como filho esquerdo.
     */
    public void setEsquerda(NoArvore esquerda) {
        this.esquerda = esquerda;
    }

    /**
     * Retorna o nó filho direito da árvore.
     * 
     * @return Referência para o nó direito (subárvore com palavras maiores).
     */
    public NoArvore getDireita() {
        return direita;
    }

    /**
     * Define o nó filho direito da árvore.
     * 
     * @param direita Nó a ser associado como filho direito.
     */
    public void setDireita(NoArvore direita) {
        this.direita = direita;
    }
}
