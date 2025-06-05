package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;

import java.util.function.Consumer;


//Implementação de uma Árvore Binária de Busca para armazenar palavras.

public class ArvoreBinariaBusca {
    private NoArvore raiz;

    public ArvoreBinariaBusca() {
        this.raiz = null;
    }


    //Insere uma palavra na árvore ou adiciona uma ocorrência se a palavra já existir.


    public void inserir(String palavra, int linha) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }

        Palavra novaPalavra = new Palavra(palavra, linha);
        // Caso a árvore esteja vazia, a nova palavra vira a raiz
        if (raiz == null) {
            raiz = new NoArvore(novaPalavra);
            return;
        }
        // Caso contrário, insere recursivamente na posição correta
        inserirRecursivo(raiz, novaPalavra);
    }


    //Método auxiliar recursivo para inserção de palavras na árvore.
    private void inserirRecursivo(NoArvore no, Palavra novaPalavra) {
        int comparacao = novaPalavra.getPalavra().compareTo(no.getPalavra().getPalavra());

        if (comparacao == 0) {
            // A palavra já existe, adiciona a ocorrência
            no.getPalavra().adicionarOcorrencia(novaPalavra.getOcorrencias().obter(0));
        } else if (comparacao < 0) {
            // A palavra deve ir para a esquerda (alfabeticamente menor)
            if (no.getEsquerda() == null) {
                no.setEsquerda(new NoArvore(novaPalavra));
            } else {
                inserirRecursivo(no.getEsquerda(), novaPalavra);
            }
        } else {
            // A palavra deve ir para a direita (alfabeticamente maior)
            if (no.getDireita() == null) {
                no.setDireita(new NoArvore(novaPalavra));
            } else {
                inserirRecursivo(no.getDireita(), novaPalavra);
            }
        }
    }


    // Busca uma palavra na árvore.
    public Palavra buscar(String palavra) {
        return buscarRecursivo(raiz, palavra);
    }


    // Método auxiliar recursivo para busca de palavras na árvore.
    private Palavra buscarRecursivo(NoArvore no, String palavra) {
        if (no == null) {
            // Chegou a uma folha sem encontrar a palavra
            return null;
        }

        int comparacao = palavra.compareTo(no.getPalavra().getPalavra());

        if (comparacao == 0) {
            // Palavra encontrada no nó atual
            return no.getPalavra();
        } else if (comparacao < 0) {
            // Continua busca na subárvore esquerda
            return buscarRecursivo(no.getEsquerda(), palavra);
        } else {
            // Continua busca na subárvore direita
            return buscarRecursivo(no.getDireita(), palavra);
        }
    }

    // Verifica se a árvore contém uma palavra.
    public boolean contem(String palavra) {
        return buscar(palavra) != null;
    }


    // Percorre a árvore em ordem (esq-nó-dir) aplicando uma ação a cada palavra.
    public void percorrerEmOrdem(Consumer<Palavra> acao) {
        percorrerEmOrdemRecursivo(raiz, acao);
    }

    // Método auxiliar recursivo para percorrer a árvore em ordem.
    private void percorrerEmOrdemRecursivo(NoArvore no, Consumer<Palavra> acao) {
        if (no != null) {
            percorrerEmOrdemRecursivo(no.getEsquerda(), acao);
            acao.accept(no.getPalavra());
            percorrerEmOrdemRecursivo(no.getDireita(), acao);
        }
    }

    // Verifica se a árvore está vazia.
    public boolean estaVazia() {
        return raiz == null;
    }
}
