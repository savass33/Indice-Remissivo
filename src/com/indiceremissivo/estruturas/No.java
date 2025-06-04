package com.indiceremissivo.estruturas;

/**
 * Classe que representa um nó genérico para uso em estruturas de dados
 * encadeadas,
 * como listas ligadas (simplesmente encadeadas).
 * 
 * Cada nó armazena um dado do tipo genérico T e uma referência para o próximo
 * nó na sequência.
 * 
 * Essa estrutura é fundamental para implementar listas encadeadas, pilhas,
 * filas e outras
 * estruturas baseadas em ponteiros.
 * 
 * @param <T> Tipo do dado armazenado no nó.
 */
public class No<T> {
    // Dado armazenado neste nó, de tipo genérico T.
    private T dado;

    // Referência para o próximo nó da estrutura encadeada.
    // Pode ser null caso este nó seja o último da sequência.
    private No<T> proximo;

    /**
     * Construtor que inicializa o nó com o dado fornecido.
     * A referência para o próximo nó é inicialmente nula,
     * indicando que o nó não está ligado a nenhum outro nó ainda.
     * 
     * @param dado Elemento que será armazenado neste nó.
     */
    public No(T dado) {
        this.dado = dado;
        this.proximo = null;
    }

    /**
     * Retorna o dado armazenado neste nó.
     * 
     * @return Dado do tipo T armazenado.
     */
    public T getDado() {
        return dado;
    }

    /**
     * Atualiza o dado armazenado neste nó.
     * 
     * @param dado Novo dado a ser armazenado.
     */
    public void setDado(T dado) {
        this.dado = dado;
    }

    /**
     * Retorna a referência para o próximo nó na estrutura encadeada.
     * 
     * @return Próximo nó ou null se este for o último nó.
     */
    public No<T> getProximo() {
        return proximo;
    }

    /**
     * Define o próximo nó na estrutura encadeada.
     * 
     * Usado para conectar este nó a outro, formando a sequência da lista ou outra
     * estrutura.
     * 
     * @param proximo Nó que deverá ser o próximo na sequência.
     */
    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }
}
