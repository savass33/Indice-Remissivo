package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;
import java.util.function.Consumer;


/**
 * Classe que representa uma Árvore Binária de Busca (ABB) para armazenar objetos do tipo Palavra.
 * 
 * A árvore é organizada de forma que, para cada nó, as palavras menores (alfabeticamente) 
 * ficam na subárvore esquerda, e as maiores ficam na subárvore direita. 
 * Isso permite buscas, inserções e percursos eficientes.
 * 
 * Funcionalidades principais:
 * - Inserir uma palavra na árvore ou atualizar sua lista de ocorrências caso já exista.
 * - Buscar uma palavra específica na árvore, retornando seu objeto Palavra com as linhas onde aparece.
 * - Percorrer a árvore em ordem (in-order), aplicando uma ação (Consumer) para cada palavra.
 * 
 * Esta estrutura é usada internamente na TabelaHash para armazenar as palavras de forma ordenada
 * e facilitar a recuperação e geração do índice remissivo.
 */

public class ArvoreBinariaBusca {
    private NoArvore raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    // Insere uma nova palavra ou atualiza suas ocorrências se já existir
    // Se a palavra já existir na árvore, a linha é adicionada à lista de
    // ocorrências.
    public void inserir(String palavra, int linha) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }

        Palavra novaPalavra = new Palavra(palavra, linha);

        if (raiz == null) {
            raiz = new NoArvore(novaPalavra);
            return;
        }

        inserirRecursivo(raiz, novaPalavra);
    }

    // Método recursivo que percorre a árvore para encontrar posição de inserção
    private void inserirRecursivo(NoArvore no, Palavra novaPalavra) {
        int comparacao = novaPalavra.getPalavra().compareTo(no.getPalavra().getPalavra());

        if (comparacao == 0) {
            // A palavra já existe, adiciona a ocorrência
            no.getPalavra().adicionarOcorrencia(novaPalavra.getOcorrencias().obter(0));
        } else if (comparacao < 0) {
            // A palavra deve ir para a esquerda
            if (no.getEsquerda() == null) {
                no.setEsquerda(new NoArvore(novaPalavra));
            } else {
                inserirRecursivo(no.getEsquerda(), novaPalavra);
            }
        } else {
            // A palavra deve ir para a direita
            if (no.getDireita() == null) {
                no.setDireita(new NoArvore(novaPalavra));
            } else {
                inserirRecursivo(no.getDireita(), novaPalavra);
            }
        }
    }

    // Busca uma palavra na árvore
    public Palavra buscar(String palavra) {
        return buscarRecursivo(raiz, palavra);
    }

    // Método recursivo auxiliar para buscar a palavra na árvore.
    private Palavra buscarRecursivo(NoArvore no, String palavra) {
        if (no == null) {
            return null;
        }

        int comparacao = palavra.compareTo(no.getPalavra().getPalavra());

        if (comparacao == 0) {
            return no.getPalavra();
        } else if (comparacao < 0) {
            return buscarRecursivo(no.getEsquerda(), palavra);
        } else {
            return buscarRecursivo(no.getDireita(), palavra);
        }
    }

    public boolean contem(String palavra) {
        return buscar(palavra) != null;
    }

    // Percorre a árvore em ordem (in-order) e executa a ação fornecida para cada
    // palavra.

    public void percorrerEmOrdem(Consumer<Palavra> acao) {
        percorrerEmOrdemRecursivo(raiz, acao);
    }

    // Método recursivo auxiliar para percorrer a árvore em ordem.

    private void percorrerEmOrdemRecursivo(NoArvore no, Consumer<Palavra> acao) {
        if (no != null) {
            percorrerEmOrdemRecursivo(no.getEsquerda(), acao);
            acao.accept(no.getPalavra());
            percorrerEmOrdemRecursivo(no.getDireita(), acao);
        }
    }

    public boolean estaVazia() {
        return raiz == null;
    }
}
