package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;
import java.util.ArrayList;
import com.indiceremissivo.util.ProcessadorTexto;
import java.util.List;
import java.util.function.Consumer;

/**
 * Implementação de uma Tabela Hash que utiliza Árvores Binárias de Busca para resolver colisões.
 */
public class TabelaHash {
    private ArvoreBinariaBusca[] tabela;
    private static final int TAMANHO_PADRAO = 26; // Uma posição para cada letra do alfabeto

    /**
     * Construtor que inicializa a tabela hash com o tamanho padrão.
     */
    public TabelaHash() {
        this(TAMANHO_PADRAO);
    }

    /**
     * Construtor que inicializa a tabela hash com um tamanho específico.
     * @param tamanho Tamanho da tabela hash
     */
    public TabelaHash(int tamanho) {
        this.tabela = new ArvoreBinariaBusca[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ArvoreBinariaBusca();
        }
    }

    /**
     * Calcula o índice hash para uma palavra.
     * @param palavra Palavra para calcular o hash
     * @return Índice hash
     */
    private int calcularHash(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return 0;
        }
        
        // Usa a primeira letra da palavra como índice (a=0, b=1, ..., z=25)
        char primeiraLetra = Character.toLowerCase(palavra.charAt(0));
        
        if (primeiraLetra >= 'a' && primeiraLetra <= 'z') {
            return primeiraLetra - 'a';
        } else {
            // Para caracteres não alfabéticos, usa o primeiro compartimento
            return 0;
        }
    }

    /**
     * Insere uma palavra na tabela hash ou adiciona uma ocorrência se a palavra já existir.
     * @param palavra Palavra a ser inserida
     * @param linha Linha onde a palavra ocorre
     */
    public void inserir(String palavra, int linha) {
        if (palavra == null || palavra.isEmpty()) {
            return;
        }
        
        int indice = calcularHash(palavra);
        tabela[indice].inserir(palavra, linha);
    }

    /**
     * Busca uma palavra na tabela hash.
     * @param palavra Palavra a ser buscada
     * @return A palavra encontrada ou null se não existir
     */
    public Palavra buscar(String palavra) {
        if (palavra == null || palavra.isEmpty()) {
            return null;
        }
        
        int indice = calcularHash(palavra);
        return tabela[indice].buscar(palavra);
    }

    /**
     * Verifica se a tabela hash contém uma palavra.
     * @param palavra Palavra a ser verificada
     * @return true se a palavra estiver na tabela, false caso contrário
     */
    public boolean contem(String palavra) {
        return buscar(palavra) != null;
    }

    /**
     * Percorre a tabela hash em ordem alfabética aplicando uma ação a cada palavra.
     * @param acao Ação a ser aplicada a cada palavra
     */
    public void percorrerEmOrdem(Consumer<Palavra> acao) {
        for (ArvoreBinariaBusca arvore : tabela) {
            arvore.percorrerEmOrdem(acao);
        }
    }

    /**
     * Gera o índice remissivo para um conjunto de palavras-chave.
     * @param palavrasChave Lista de palavras-chave
     * @return Lista de palavras do índice remissivo
     */
    public List<Palavra> gerarIndiceRemissivo(List<String> palavrasChave) {
        List<Palavra> indice = new ArrayList<>();
        for (String palavraChave : palavrasChave) {
            String palavraNormalizada = ProcessadorTexto.limparPalavra(palavraChave); // Normaliza aqui!
            Palavra palavra = buscar(palavraNormalizada);
            if (palavra != null) {
                indice.add(palavra);
            }
        }
        return indice;
    }
}
