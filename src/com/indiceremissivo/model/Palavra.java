package com.indiceremissivo.model;

import com.indiceremissivo.estruturas.ListaEncadeada;


public class Palavra {
    private String palavra;
    private ListaEncadeada<Integer> ocorrencias;

    public Palavra(String palavra, int linha) {
        this.palavra = palavra;
        this.ocorrencias = new ListaEncadeada<>();
        this.ocorrencias.adicionar(linha);
    }

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

    public int compareTo(Palavra outra) {
        return this.palavra.compareTo(outra.getPalavra());
    }

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        Palavra outra = (Palavra) obj;
        return palavra.equals(outra.palavra);
    }

    @Override
    public int hashCode() {
        return palavra.hashCode();
    }
}
