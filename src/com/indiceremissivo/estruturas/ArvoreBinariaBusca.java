package com.indiceremissivo.estruturas;

import com.indiceremissivo.model.Palavra;
import java.util.function.Consumer;

/**
 * Classe que representa uma Árvore Binária de Busca (ABB) para armazenar
 * objetos do tipo Palavra.
 * 
 * Uma ABB é uma estrutura de dados em forma de árvore onde cada nó possui no
 * máximo dois filhos:
 * esquerdo e direito. A propriedade fundamental é:
 * - Todos os elementos na subárvore esquerda são menores (segundo ordem
 * alfabética aqui) que o nó atual.
 * - Todos os elementos na subárvore direita são maiores que o nó atual.
 * 
 * Essa organização permite operações eficientes de busca, inserção e remoção,
 * com complexidade média
 * O(log n) para árvores balanceadas.
 * 
 * Funcionalidades principais implementadas:
 * - Inserir palavras novas ou atualizar as ocorrências de uma palavra já
 * existente.
 * - Buscar uma palavra, retornando seu objeto com todas as ocorrências
 * associadas.
 * - Percorrer a árvore em ordem crescente (alfabeticamente), aplicando uma ação
 * sobre cada palavra.
 * 
 * Essa estrutura é usada na TabelaHash para manter as palavras ordenadas,
 * facilitando geração do índice remissivo com rápida consulta e listagem
 * ordenada.
 */
public class ArvoreBinariaBusca {
    // Raiz da árvore, o nó de topo onde a busca/inserção inicia
    private NoArvore raiz;

    /**
     * Construtor que inicializa a árvore vazia, ou seja, sem nenhum nó.
     */
    public ArvoreBinariaBusca() {
        this.raiz = null;
    }

    /**
     * Insere uma palavra e sua linha associada na árvore.
     * Se a palavra já existir, adiciona a nova linha na lista de ocorrências da
     * palavra.
     * 
     * @param palavra String contendo a palavra a ser inserida.
     * @param linha   Número da linha onde a palavra ocorre no texto.
     * 
     *                Evita inserir palavras nulas ou vazias para manter integridade
     *                dos dados.
     */
    public void inserir(String palavra, int linha) {
        if (palavra == null || palavra.isEmpty()) {
            return; // Ignora palavras inválidas
        }

        // Cria objeto Palavra que encapsula a palavra e sua ocorrência inicial (linha)
        Palavra novaPalavra = new Palavra(palavra, linha);

        if (raiz == null) {
            // Caso a árvore esteja vazia, a nova palavra vira a raiz
            raiz = new NoArvore(novaPalavra);
            return;
        }

        // Caso contrário, insere recursivamente na posição correta
        inserirRecursivo(raiz, novaPalavra);
    }

    /**
     * Método recursivo para encontrar a posição correta na árvore para inserir a
     * nova palavra.
     * 
     * @param no          Nó atual da árvore onde está sendo avaliada a inserção.
     * @param novaPalavra Objeto Palavra que será inserido.
     * 
     *                    O método compara a palavra nova com a do nó atual:
     *                    - Se forem iguais, apenas adiciona a ocorrência (linha) na
     *                    palavra existente.
     *                    - Se for menor, tenta inserir à esquerda, criando nó se
     *                    vazio ou descendo na subárvore.
     *                    - Se for maior, tenta inserir à direita, com mesma lógica.
     */
    private void inserirRecursivo(NoArvore no, Palavra novaPalavra) {
        int comparacao = novaPalavra.getPalavra().compareTo(no.getPalavra().getPalavra());

        if (comparacao == 0) {
            // Palavra já existe na árvore: adiciona nova ocorrência (linha)
            // A lista de ocorrências da palavra é atualizada para não perder dados.
            no.getPalavra().adicionarOcorrencia(novaPalavra.getOcorrencias().obter(0));
        } else if (comparacao < 0) {
            // Palavra nova é alfabeticamente menor: deve estar na subárvore esquerda
            if (no.getEsquerda() == null) {
                // Posição vazia: cria novo nó à esquerda
                no.setEsquerda(new NoArvore(novaPalavra));
            } else {
                // Desce recursivamente para inserir na subárvore esquerda
                inserirRecursivo(no.getEsquerda(), novaPalavra);
            }
        } else {
            // Palavra nova é maior: subárvore direita
            if (no.getDireita() == null) {
                // Cria nó direito
                no.setDireita(new NoArvore(novaPalavra));
            } else {
                // Continua inserção recursiva na direita
                inserirRecursivo(no.getDireita(), novaPalavra);
            }
        }
    }

    /**
     * Busca uma palavra na árvore e retorna o objeto Palavra correspondente, se
     * existir.
     * 
     * @param palavra Palavra a ser buscada.
     * @return Objeto Palavra contendo a palavra e suas ocorrências, ou null se não
     *         encontrada.
     * 
     *         Utiliza método recursivo auxiliar para navegação eficiente pela ABB.
     */
    public Palavra buscar(String palavra) {
        return buscarRecursivo(raiz, palavra);
    }

    /**
     * Método recursivo auxiliar para busca na árvore.
     * 
     * @param no      Nó atual da árvore.
     * @param palavra Palavra que se deseja buscar.
     * @return Objeto Palavra encontrado ou null se não estiver na subárvore atual.
     * 
     *         A comparação determina se a busca continua à esquerda (menor),
     *         à direita (maior) ou retorna a palavra no nó atual (igual).
     */
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

    /**
     * Verifica se a palavra está contida na árvore.
     * 
     * @param palavra Palavra a verificar.
     * @return true se a palavra existe na árvore, false caso contrário.
     */
    public boolean contem(String palavra) {
        return buscar(palavra) != null;
    }

    /**
     * Percorre a árvore em ordem (in-order), ou seja, visita subárvore esquerda,
     * nó atual, subárvore direita, garantindo que as palavras sejam processadas em
     * ordem alfabética crescente.
     * 
     * @param acao Ação do tipo Consumer que será aplicada a cada objeto Palavra
     *             visitado.
     * 
     *             Permite aplicar operações variadas, como imprimir, coletar, ou
     *             modificar dados das palavras durante o percurso.
     */
    public void percorrerEmOrdem(Consumer<Palavra> acao) {
        percorrerEmOrdemRecursivo(raiz, acao);
    }

    /**
     * Método recursivo auxiliar para o percurso em ordem da árvore.
     * 
     * @param no   Nó atual que está sendo visitado.
     * @param acao Ação a executar no objeto Palavra contido no nó.
     */
    private void percorrerEmOrdemRecursivo(NoArvore no, Consumer<Palavra> acao) {
        if (no != null) {
            // Primeiro percorre a subárvore esquerda
            percorrerEmOrdemRecursivo(no.getEsquerda(), acao);
            // Executa a ação no nó atual
            acao.accept(no.getPalavra());
            // Depois percorre a subárvore direita
            percorrerEmOrdemRecursivo(no.getDireita(), acao);
        }
    }

    /**
     * Verifica se a árvore está vazia, ou seja, se não possui nenhum nó.
     * 
     * @return true se a raiz for null (árvore vazia), false caso contrário.
     */
    public boolean estaVazia() {
        return raiz == null;
    }
}
