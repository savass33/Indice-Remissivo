package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Implementação de uma tabela hash específica para armazenar objetos do tipo
 * Palavra.
 * 
 * A tabela hash usa um array de Árvores Binárias de Busca (ABB) para armazenar
 * as palavras,
 * onde cada posição do array representa um "balde" (bucket) que contém uma ABB.
 * Essa abordagem usa árvores para resolver colisões, mantendo as palavras
 * ordenadas em cada bucket.
 * 
 * A ideia principal:
 * - A função hash calcula o índice no array baseado na primeira letra da
 * palavra (de 'a' a 'z').
 * - Cada bucket é uma ABB que armazena as palavras que começam com aquela
 * letra.
 * - Inserções, buscas e percursos são delegados à ABB do bucket correspondente.
 * 
 * Essa estrutura é útil para armazenar e recuperar palavras e suas ocorrências
 * de forma eficiente, facilitando a criação do índice remissivo.
 */
public class TabelaHash {
    // Array de árvores binárias que armazenam as palavras.
    private ArvoreBinariaBusca[] tabela;

    // Tamanho padrão da tabela: 26 posições, uma para cada letra do alfabeto.
    private static final int TAMANHO_PADRAO = 26;

    /**
     * Construtor padrão que inicializa a tabela com o tamanho padrão.
     */
    public TabelaHash() {
        this(TAMANHO_PADRAO);
    }

    /**
     * Construtor que inicializa a tabela hash com tamanho especificado.
     * Cada posição do array é inicializada com uma ABB vazia.
     * 
     * @param tamanho tamanho do array da tabela hash.
     */
    public TabelaHash(int tamanho) {
        this.tabela = new ArvoreBinariaBusca[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArvoreBinariaBusca();
        }
    }

    /**
     * Calcula o índice na tabela hash a partir da palavra fornecida.
     * 
     * Utiliza a primeira letra da palavra para determinar o bucket,
     * convertendo-a para minúscula e mapeando 'a' para 0, 'b' para 1, ..., 'z' para
     * 25.
     * 
     * Se a palavra começar com caractere não alfabético, retorna 0.
     * 
     * @param palavra Palavra a ser inserida ou buscada.
     * @return Índice da tabela hash correspondente.
     */
    private int calcularHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return 0;
        }

        char primeiraLetra = Character.toLowerCase(palavra.charAt(0));

        if (primeiraLetra >= 'a' && primeiraLetra <= 'z') {
            return primeiraLetra - 'a';
        } else {
            return 0;
        }
    }

    /**
     * Insere uma palavra na tabela hash, associando-a a uma linha onde foi
     * encontrada.
     * 
     * Se a palavra já existir na árvore do bucket, apenas adiciona a nova
     * ocorrência (linha).
     * Caso contrário, insere a palavra na ABB do bucket correspondente.
     * 
     * @param palavra Palavra a ser inserida.
     * @param linha   Linha onde a palavra foi encontrada.
     */
    public void inserir(String palavra, int linha) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }

        int indice = calcularHash(palavra);
        tabela[indice].inserir(palavra, linha);
    }

    /**
     * Busca e retorna o objeto Palavra associado à palavra fornecida.
     * Retorna null se não encontrada.
     * 
     * @param palavra Palavra a buscar.
     * @return Objeto Palavra contendo as ocorrências ou null se não existir.
     */
    public Palavra buscar(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return null;
        }

        int indice = calcularHash(palavra);
        return tabela[indice].buscar(palavra);
    }

    /**
     * Verifica se a tabela contém a palavra especificada.
     * 
     * @param palavra Palavra a verificar.
     * @return true se encontrada, false caso contrário.
     */
    public boolean contem(String palavra) {
        return buscar(palavra) != null;
    }

    /**
     * Percorre todas as árvores da tabela em ordem (in-order),
     * aplicando a ação fornecida para cada palavra armazenada.
     * 
     * @param acao Ação a ser executada para cada palavra.
     */
    public void percorrerEmOrdem(Consumer<Palavra> acao) {
        for (ArvoreBinariaBusca arvore : tabela) {
            arvore.percorrerEmOrdem(acao);
        }
    }

    /**
     * Gera uma lista ordenada das palavras-chave encontradas na tabela,
     * buscando cada palavra-chave na tabela e adicionando o objeto Palavra
     * à lista resultado.
     * 
     * Essa lista pode ser usada para gerar o índice remissivo final.
     * 
     * @param palavrasChave Lista de palavras-chave a buscar.
     * @return Lista ordenada das palavras com suas ocorrências.
     */
    public List<Palavra> gerarIndiceRemissivo(List<String> palavrasChave) {
        List<Palavra> indice = new ArrayList<>();

        for (String palavraChave : palavrasChave) {
            Palavra palavra = buscar(palavraChave);
            if (palavra != null) {
                indice.add(palavra);
            }
        }

        return indice;
    }
}
