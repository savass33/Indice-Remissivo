package com.indiceremissivo.util;

import java.text.Normalizer;

/**
 * Utilitário para processamento de texto,
 * como limpeza e divisão de linhas em palavras.
 */
public class ProcessadorTexto {

    /**
     * Normaliza uma palavra removendo acentos e caracteres especiais,
     * convertendo para minúsculas e mantendo apenas letras e hífens.
     * 
     * @param palavra Palavra original.
     * @return Palavra normalizada.
     */
    private static String normalizarPalavra(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return "";
        }

        return Normalizer.normalize(palavra, Normalizer.Form.NFD)
                .replaceAll("[^a-zA-Z-]", "") // Remove tudo exceto letras e hífens
                .toLowerCase(); // Converte para minúsculo
    }

    /**
     * Limpa uma palavra removendo espaços, pontuações, caracteres especiais
     * e acentos, além de converter para minúsculas.
     * 
     * @param palavra Palavra original.
     * @return Palavra limpa e normalizada, pronta para indexação.
     */
    public static String limparPalavra(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return "";
        }

        // Aplica normalização primeiro (remove acentos e caracteres especiais)
        String palavraLimpa = normalizarPalavra(palavra);

        // Remove pontuação no final da palavra (para casos não tratados pela
        // normalização)
        while (!palavraLimpa.isEmpty() && !Character.isLetterOrDigit(palavraLimpa.charAt(palavraLimpa.length() - 1))) {
            palavraLimpa = palavraLimpa.substring(0, palavraLimpa.length() - 1);
        }

        // Remove pontuação no início da palavra
        while (!palavraLimpa.isEmpty() && !Character.isLetterOrDigit(palavraLimpa.charAt(0))) {
            palavraLimpa = palavraLimpa.substring(1);
        }

        return palavraLimpa;
    }

    /**
     * Divide uma linha de texto em palavras, separando por espaços em branco.
     * 
     * @param linha Linha de texto.
     * @return Array de palavras.
     */
    public static String[] dividirEmPalavras(String linha) {
        if (linha == null || linha.isEmpty()) {
            return new String[0];
        }

        // Divide a linha por espaços em branco
        return linha.split("\\s+");
    }
}