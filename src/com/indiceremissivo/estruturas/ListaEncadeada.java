package com.indiceremissivo.estruturas;

import java.util.function.Consumer;

// Lista para organizar as linhas
public class ListaEncadeada<T> {
    private No<T> inicio;
    private No<T> fim;
    private int tamanho;

    public ListaEncadeada() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }


    // Adiciona um elemento ao final da lista.
    public void adicionar(T elemento) {
        No<T> novoNo = new No<>(elemento);

        if (estaVazia()) {
            // Caso lista vazia, o novo nó é o início e fim da lista
            inicio = novoNo;
            fim = novoNo;
        } else {
            // Caso contrário, anexa o novo nó após o atual fim e atualiza fim
            fim.setProximo(novoNo);
            fim = novoNo;
        }
        tamanho++;
    }


    // Obtém o elemento no índice especificado.
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

    // Obtém o tamanho da lista.
    public int tamanho() {
        return tamanho;
    }

    // Verifica se a lista está vazia.
    public boolean estaVazia() {
        return tamanho == 0;
    }

    // Retorna uma representação em string da lista.
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
