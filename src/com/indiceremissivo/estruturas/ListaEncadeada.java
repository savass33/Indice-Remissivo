package com.indiceremissivo.estruturas;

import java.util.function.Consumer;

/**
 * Implementação genérica de uma lista encadeada simples.
 * Permite adicionar, remover e acessar elementos sequencialmente.
 */

public class ListaEncadeada<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public ListaEncadeada() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    /**
     * Adiciona um elemento ao final da lista.
     * 
     * @param elemento Elemento a ser adicionado.
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
     * Verifica se a lista contém um elemento.
     * 
     * @param elemento Elemento a buscar.
     * @return true se encontrado, false caso contrário.
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
     * Obtém o elemento na posição dada (índice começando em zero).
     * 
     * @param indice Índice do elemento.
     * @return Elemento na posição.
     * @throws IndexOutOfBoundsException Se índice inválido.
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

    public int tamanho() {
        return tamanho;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    // Percorre todos elementos aplicando uma função
    public void forEach(Consumer<T> acao) {
        No<T> atual = inicio;

        while (atual != null) {
            acao.accept(atual.getDado());
            atual = atual.getProximo();
        }
    }

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
