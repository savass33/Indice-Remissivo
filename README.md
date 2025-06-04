# Indice-Remissivo
Projeto para AV3 de Estrutura de Dados
=======
# Projeto Índice Remissivo (Versão sem Build Tool)

Este projeto gera um índice remissivo a partir de um arquivo de texto e uma lista de palavras-chave, utilizando estruturas de dados como Tabela Hash, Árvore Binária de Busca e Lista Encadeada.

Esta versão foi reestruturada para não depender de ferramentas de build como o Maven. Você pode compilá-la e executá-la diretamente usando os comandos padrão do JDK (Java Development Kit).

## Estrutura de Diretórios

*   `/src`: Contém todo o código-fonte Java (`.java`), organizado em pacotes.
*   `/bin`: Diretório de saída para os arquivos compilados (`.class`). Este diretório será criado ou preenchido durante a compilação.
*   `/data`: Contém os arquivos de dados necessários para a execução:
    *   `texto.txt`: O texto principal a ser analisado.
    *   `palavras_chave.txt`: A lista de palavras a serem incluídas no índice.
    *   `indice_remissivo.txt`: O arquivo de saída onde o índice gerado será salvo.

## Como Compilar e Executar

**Pré-requisitos:**
*   Você precisa ter o JDK (Java Development Kit) instalado em seu sistema e configurado no PATH (para poder usar os comandos `javac` e `java` no terminal).

**Passos:**

1.  **Abra o Terminal ou Prompt de Comando:** Navegue até o diretório raiz deste projeto (`indice_remissivo_plain`).

2.  **Compile o Código:** Execute o seguinte comando para compilar todos os arquivos `.java` da pasta `src` e colocar os arquivos `.class` resultantes na pasta `bin`.

    ```bash
    javac -d bin $(find src -name "*.java")
    ```
    *Observação para Windows:* Se o comando `find` não funcionar, você pode listar os arquivos manualmente ou usar um comando equivalente do Windows, como:
    ```cmd
    javac -d bin src\com\indiceremissivo\estruturas\*.java src\com\indiceremissivo\model\*.java src\com\indiceremissivo\util\*.java src\com\indiceremissivo\main\*.java
    ```
    Ou, de forma mais robusta no PowerShell:
    ```powershell
    javac -d bin (Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName })
    ```

3.  **Execute o Programa:** Após a compilação bem-sucedida, execute o programa usando o comando `java`. Certifique-se de estar no diretório raiz (`indice_remissivo_plain`) para que os caminhos relativos para a pasta `data` funcionem corretamente. O comando especifica o classpath (`-cp bin`) para que o Java encontre as classes compiladas e o nome completo da classe principal (`com.indiceremissivo.main.IndiceRemissivo`).

    ```bash
    java -cp bin com.indiceremissivo.main.IndiceRemissivo
    ```

4.  **Verifique a Saída:** Se tudo ocorrer bem, a mensagem "Índice remissivo gerado com sucesso!" será exibida no terminal, e o arquivo `data/indice_remissivo.txt` será criado ou atualizado com o índice gerado.

**Observação:** Os caminhos para os arquivos de entrada (`texto.txt`, `palavras_chave.txt`) e saída (`indice_remissivo.txt`) estão definidos como relativos à pasta `data` dentro da classe `IndiceRemissivo.java`. O programa também aceita caminhos absolutos como argumentos de linha de comando, se necessário:

```bash
java -cp bin com.indiceremissivo.main.IndiceRemissivo /caminho/completo/texto.txt /caminho/completo/palavras_chave.txt /caminho/completo/saida.txt
```

