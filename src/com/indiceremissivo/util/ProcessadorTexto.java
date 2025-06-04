package com.indiceremissivo.util;

/**
 * Classe utilitária para processamento de texto.
 */
public class ProcessadorTexto {
    
    /**
     * Limpa uma palavra removendo caracteres especiais e convertendo para minúsculo.
     * @param palavra Palavra a ser limpa
     * @return Palavra limpa
     */
    public static String limparPalavra(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return "";
        }
        
        // Remove caracteres especiais no início e fim da palavra
        String palavraLimpa = palavra.trim().toLowerCase();
        
        // Remove pontuação no final da palavra (ponto, vírgula, etc.)
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
     * Divide uma linha de texto em palavras.
     * @param linha Linha de texto
     * @return Array de palavras
     */
    public static String[] dividirEmPalavras(String linha) {
        if (linha == null || linha.isEmpty()) {
            return new String[0];
        }
        
        // Divide a linha por espaços em branco
        return linha.split("\\s+");
    }
}
