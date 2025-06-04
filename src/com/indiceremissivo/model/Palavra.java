package com.indiceremissivo.model;

import com.indiceremissivo.estruturas.ListaEncadeada;

/**
 * Representa uma palavra e as linhas em que ela ocorre em um texto.
 * Utiliza uma lista encadeada para armazenar as ocorrências (linhas).
 */
public class Palavra {
    // Texto da palavra
    private String palavra;
    // Lista encadeada contendo os números das linhas onde a palavra ocorre
    private ListaEncadeada<Integer> ocorrencias;

    /**
     * Construtor que cria uma palavra com uma ocorrência inicial (linha).
     * 
     * @param palavra Palavra a ser armazenada.
     * @param linha   Linha do texto onde a palavra ocorreu.
     */
    public Palavra(String palavra, int linha) {
        this.palavra = palavra;
        this.ocorrencias = new ListaEncadeada<>();
        this.ocorrencias.adicionar(linha); // Adiciona a linha inicial na lista de ocorrências
    }

    /**
     * Construtor que cria uma palavra sem ocorrências inicialmente.
     * 
     * @param palavra Palavra a ser armazenada.
     */
    public Palavra(String palavra) {
        this.palavra = palavra;
        this.ocorrencias = new ListaEncadeada<>();
    }

    // Getter da palavra (texto)
    public String getPalavra() {
        return palavra;
    }

    // Setter para alterar a palavra (caso necessário)
    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    // Getter da lista de ocorrências (linhas)
    public ListaEncadeada<Integer> getOcorrencias() {
        return ocorrencias;
    }

    /**
     * Adiciona uma nova ocorrência da palavra (número da linha),
     * garantindo que não haja linhas duplicadas na lista.
     * 
     * @param linha Linha onde a palavra ocorreu.
     */
    public void adicionarOcorrencia(int linha) {
        // Verifica se a linha já está na lista para evitar duplicatas
        boolean existe = false;
        for (int i = 0; i < ocorrencias.tamanho(); i++) {
            if (ocorrencias.obter(i).equals(linha)) {
                existe = true;
                break;
            }
        }

        // Só adiciona a linha se ela ainda não existe na lista
        if (!existe) {
            ocorrencias.adicionar(linha);
        }
    }

    /**
     * Compara esta palavra com outra para fins de ordenação alfabética.
     * 
     * @param outra Palavra a comparar.
     * @return Resultado da comparação lexicográfica (int negativo, zero ou
     *         positivo).
     */
    public int compareTo(Palavra outra) {
        return this.palavra.compareTo(outra.getPalavra());
    }

    /**
     * Representação textual da palavra e suas ocorrências,
     * no formato: palavra: linha1, linha2, linha3...
     * 
     * @return String formatada para facilitar leitura.
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
     * Verifica se duas palavras são iguais, baseando-se no texto da palavra.
     * 
     * @param obj Objeto a ser comparado.
     * @return true se forem palavras iguais, false caso contrário.
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
     * Retorna o código hash da palavra para uso em coleções hash,
     * baseado na string da palavra.
     * 
     * @return código hash (int)
     */
    @Override
    public int hashCode() {
        return palavra.hashCode();
    }
}
