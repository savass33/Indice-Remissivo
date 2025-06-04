package com.indiceremissivo.estruturas;

import java.util.function.Consumer;

/**
 * Implementação de uma Lista Encadeada genérica.
 * @param <T> Tipo de dado armazenado na lista
 */
public class ListaEncadeada<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    /**
     * Construtor que inicializa uma lista vazia.
     */
    public ListaEncadeada() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    /**
     * Adiciona um elemento ao final da lista.
     * @param elemento Elemento a ser adicionado
     */
    public void adicionar(T elemento) {
        No<T> novoNo = new No<>(elemento);
        
        if (estaVazia()) {
            inicio = novoNo;
            fim = novoNo;
        } else {
            fim.setProximo(novoNo);
            fim = novoNo;
        }
        
        tamanho++;
    }

    /**
     * Verifica se a lista contém um elemento específico.
     * @param elemento Elemento a ser verificado
     * @return true se o elemento estiver na lista, false caso contrário
     */
    public boolean contem(T elemento) {
        No<T> atual = inicio;
        
        while (atual != null) {
            if (atual.getDado().equals(elemento)) {
                return true;
            }
            atual = atual.getProximo();
        }
        
        return false;
    }

    /**
     * Obtém o elemento no índice especificado.
     * @param indice Índice do elemento a ser obtido
     * @return Elemento no índice especificado
     * @throws IndexOutOfBoundsException se o índice estiver fora dos limites da lista
     */
    public T obter(int indice) {
        if (indice < 0 || indice >= tamanho) {
            throw new IndexOutOfBoundsException("Índice fora dos limites da lista");
        }
        
        No<T> atual = inicio;
        for (int i = 0; i < indice; i++) {
            atual = atual.getProximo();
        }
        
        return atual.getDado();
    }

    /**
     * Obtém o tamanho da lista.
     * @return Número de elementos na lista
     */
    public int tamanho() {
        return tamanho;
    }

    /**
     * Verifica se a lista está vazia.
     * @return true se a lista estiver vazia, false caso contrário
     */
    public boolean estaVazia() {
        return tamanho == 0;
    }

    /**
     * Percorre a lista aplicando uma ação a cada elemento.
     * @param acao Ação a ser aplicada a cada elemento
     */
    public void forEach(Consumer<T> acao) {
        No<T> atual = inicio;
        
        while (atual != null) {
            acao.accept(atual.getDado());
            atual = atual.getProximo();
        }
    }

    /**
     * Retorna uma representação em string da lista.
     * @return String representando a lista
     */
    @Override
    public String toString() {
        if (estaVazia()) {
            return "[]";
        }
        
        StringBuilder sb = new StringBuilder("[");
        No<T> atual = inicio;
        
        while (atual != null) {
            sb.append(atual.getDado());
            
            if (atual.getProximo() != null) {
                sb.append(", ");
            }
            
            atual = atual.getProximo();
        }
        
        sb.append("]");
        return sb.toString();
    }
}
