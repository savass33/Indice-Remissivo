package com.indiceremissivo.util;

/**
 * Utilitário para processamento de texto,
 * como limpeza e divisão de linhas em palavras.
 */
public class ProcessadorTexto {

    /**
     * Limpa uma palavra removendo espaços, pontuações e caracteres especiais,
     * mas **mantém acentos** e converte para minúsculas.
     *
     * @param palavra Palavra original.
     * @return Palavra limpa e normalizada, pronta para indexação.
     */
    public static String limparPalavra(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return "";
        }

        // Remove caracteres que não sejam letras (inclusive acentuadas) ou hífen
        String palavraLimpa = palavra.trim()
                .replaceAll("[^\\p{L}-]", "") // Mantém letras Unicode e hífen
                .toLowerCase();

        // Remove pontuação no final da palavra
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
