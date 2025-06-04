package com.indiceremissivo.estruturas;

/**
 * Classe que representa um nó da lista encadeada.
 * @param <T> Tipo de dado armazenado no nó
 */
public class No<T> {
    private T dado;
    private No<T> proximo;

    /**
     * Construtor que inicializa o nó com um dado.
     * @param dado Dado a ser armazenado no nó
     */
    public No(T dado) {
        this.dado = dado;
        this.proximo = null;
    }

    /**
     * Obtém o dado armazenado no nó.
     * @return Dado armazenado no nó
     */
    public T getDado() {
        return dado;
    }

    /**
     * Define o dado armazenado no nó.
     * @param dado Novo dado a ser armazenado
     */
    public void setDado(T dado) {
        this.dado = dado;
    }

    /**
     * Obtém o próximo nó da lista.
     * @return Próximo nó da lista
     */
    public No<T> getProximo() {
        return proximo;
    }

    /**
     * Define o próximo nó da lista.
     * @param proximo Novo próximo nó
     */
    public void setProximo(No<T> proximo) {
        this.proximo = proximo;
    }
}
