package com.indiceremissivo.estruturas;

import java.util.function.Consumer;

/**
 * Implementação genérica de uma lista encadeada simples (lista ligada).
 * 
 * Essa estrutura de dados armazena elementos em nós sequenciais, onde cada nó
 * contém um dado do tipo genérico T e uma referência para o próximo nó da
 * lista.
 * 
 * Características principais:
 * - Permite armazenamento dinâmico de elementos, sem tamanho fixo.
 * - Adição de elementos no final da lista em tempo O(1) graças ao ponteiro
 * 'fim'.
 * - Busca sequencial para verificar presença de elemento ou obter elemento por
 * índice.
 * - Percurso sequencial para aplicar ações em todos os elementos (método
 * forEach).
 * 
 * Essa lista não suporta acesso aleatório eficiente (como arrays), pois para
 * acessar
 * um índice específico é necessário percorrer os nós desde o início.
 * 
 * Uso típico:
 * - Armazenar coleções com tamanho variável, especialmente quando inserções
 * sequenciais
 * são frequentes.
 * - Cenários onde a simplicidade da estrutura e a manipulação por ponteiros são
 * importantes.
 * 
 * @param <T> Tipo dos elementos armazenados na lista.
 */
public class ListaEncadeada<T> {
    // Referência para o primeiro nó da lista (cabeça da lista).
    private No<T> inicio;

    // Referência para o último nó da lista, permite inserção eficiente no fim.
    private No<T> fim;

    // Contador do número de elementos presentes na lista.
    private int tamanho;

    /**
     * Construtor padrão que inicializa uma lista vazia,
     * com referências nulas e tamanho zero.
     */
    public ListaEncadeada() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }

    /**
     * Adiciona um novo elemento ao final da lista.
     * 
     * A operação cria um novo nó com o elemento e atualiza a referência 'fim' para
     * apontar
     * para esse novo nó. Se a lista estiver vazia, o novo nó será tanto o início
     * quanto o fim.
     * 
     * Essa inserção ocorre em tempo constante O(1).
     * 
     * @param elemento Elemento a ser inserido na lista.
     */
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

        // Incrementa o tamanho após a inserção
        tamanho++;
    }

    /**
     * Verifica se a lista contém o elemento especificado.
     * 
     * A busca é feita sequencialmente, percorrendo os nós do início até o fim,
     * comparando o elemento armazenado com o parâmetro usando equals().
     * 
     * Caso o elemento seja encontrado, retorna true, caso contrário false.
     * 
     * Complexidade de tempo O(n) no pior caso, onde n é o tamanho da lista.
     * 
     * @param elemento Elemento a ser buscado na lista.
     * @return true se o elemento está presente na lista, false caso contrário.
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
     * Obtém o elemento na posição (índice) especificada.
     * 
     * Como a lista é encadeada, o acesso por índice exige percorrer os nós desde o
     * início,
     * avançando um nó por vez até alcançar a posição desejada.
     * 
     * Se o índice for inválido (menor que zero ou maior ou igual ao tamanho), lança
     * uma
     * exceção IndexOutOfBoundsException.
     * 
     * Complexidade O(n), onde n é o índice (em média O(n/2) para acesso aleatório).
     * 
     * @param indice Índice do elemento desejado (começa em 0).
     * @return Elemento armazenado no índice fornecido.
     * @throws IndexOutOfBoundsException Se o índice estiver fora do intervalo
     *                                   válido.
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
     * Retorna o número de elementos atualmente armazenados na lista.
     * 
     * @return Tamanho atual da lista.
     */
    public int tamanho() {
        return tamanho;
    }

    /**
     * Verifica se a lista está vazia, ou seja, não possui elementos.
     * 
     * @return true se a lista estiver vazia, false caso contrário.
     */
    public boolean estaVazia() {
        return tamanho == 0;
    }

    /**
     * Executa a ação fornecida para cada elemento da lista, na ordem do início ao
     * fim.
     * 
     * Utiliza um laço que percorre todos os nós e chama o método accept() do
     * Consumer
     * para cada elemento. Isso permite processar, imprimir, ou modificar os
     * elementos
     * de forma funcional e flexível.
     * 
     * @param acao Ação a ser aplicada em cada elemento da lista.
     */
    public void forEach(Consumer<T> acao) {
        No<T> atual = inicio;

        while (atual != null) {
            acao.accept(atual.getDado());
            atual = atual.getProximo();
        }
    }

    /**
     * Retorna uma representação em string da lista, no formato [elem1, elem2,
     * elem3, ...].
     * 
     * Caso a lista esteja vazia, retorna "[]".
     * 
     * O método percorre todos os nós concatenando os elementos em uma
     * StringBuilder,
     * separando-os por vírgula e espaço.
     * 
     * Útil para depuração e visualização do conteúdo da lista.
     * 
     * @return String representando os elementos da lista.
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
