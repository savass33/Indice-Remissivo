package com.indiceremissivo.model;

import com.indiceremissivo.estruturas.ListaEncadeada;

/**
 * Representa uma palavra e as linhas em que ela ocorre em um texto.
 * Utiliza uma lista encadeada para armazenar as ocorrências (linhas).
 */

public class Palavra {
    private String palavra;
    private ListaEncadeada<Integer> ocorrencias;

    /**
     * Construtor que cria uma palavra e adiciona uma ocorrência inicial.
     * 
     * @param palavra Palavra a ser armazenada.
     * @param linha   Linha do texto onde a palavra ocorreu.
     */

    public Palavra(String palavra, int linha) {
        this.palavra = palavra;
        this.ocorrencias = new ListaEncadeada<>();
        this.ocorrencias.adicionar(linha);
    }

    /**
     * Construtor que cria uma palavra sem ocorrências iniciais.
     * 
     * @param palavra Palavra a ser armazenada.
     */

    public Palavra(String palavra) {
        this.palavra = palavra;
        this.ocorrencias = new ListaEncadeada<>();
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public ListaEncadeada<Integer> getOcorrencias() {
        return ocorrencias;
    }

    /**
     * Adiciona uma nova ocorrência da palavra (linha),
     * garantindo que não haja duplicatas.
     * 
     * @param linha Linha onde a palavra ocorreu.
     */

    public void adicionarOcorrencia(int linha) {
        // Verifica se a ocorrência já existe para evitar duplicatas
        boolean existe = false;
        for (int i = 0; i < ocorrencias.tamanho(); i++) {
            if (ocorrencias.obter(i).equals(linha)) {
                existe = true;
                break;
            }
        }

        if (!existe) {
            ocorrencias.adicionar(linha);
        }
    }

    /**
     * Compara esta palavra com outra para fins de ordenação alfabética.
     * 
     * @param outra Palavra a comparar.
     * @return Resultado da comparação lexicográfica.
     */

    public int compareTo(Palavra outra) {
        return this.palavra.compareTo(outra.getPalavra());
    }

    /**
     * Representação textual da palavra e suas ocorrências,
     * no formato: palavra: linha1, linha2, linha3...
     * 
     * @return String formatada.
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(palavra);
        sb.append(": ");

        for (int i = 0; i < ocorrencias.tamanho(); i++) {
            sb.append(ocorrencias.obter(i));
            if (i < ocorrencias.tamanho() - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }

    /**
     * Verifica se duas palavras são iguais baseando-se no texto da palavra.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Palavra outra = (Palavra) obj;
        return palavra.equals(outra.palavra);
    }

    /**
     * Retorna o código hash da palavra para uso em coleções hash.
     */

    @Override
    public int hashCode() {
        return palavra.hashCode();
    }
}
