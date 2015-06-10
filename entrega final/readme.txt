 - Identificação dos Autores:
.Grupo: G10
.Tema: XML2SDL
.Constituído por:
André Humberto Trigo de Bordalo Morais
Bruno Coelho da Silva
João Alberto Trigo de Bordalo Morais
Pedro Dias Faria

 - Instruções de Compilação:
2 Formas possíveis para executar:
1ª: Compilar pelo IDE (exemplo Eclipse) pelo ficheiro bgl2sdl.java (onde se encontra a função main)
2ª: Localizar a pasta bin do projeto na linha de comandos;
    dentro da pasta escrever na linha de comandos: java bgl2sdl.bgl2sdl

 - Descrição dos Exemplos:
.Ficheiro correto: deltaheritage.xml
.Ficheiro com erros: errors.xml tem alguns erros despropositados os quais o nosso projeto deteta e sinaliza, indicado o tip ode erro e a linha (na maioria dos casos)

 - Descrição de Utilização:
localizar pasta bin eescrever a seguintelinha: java bgl2sdl.bgl2sdl
Em seguida, na consola aparecerá um texto a perguntar por duas opções («(1) Console», «(2) File»). O projeto está preparado quer para receber já o conteúdo a ser analizado ou o nome do ficheiro a ser analizado ( se só for indicado o nome do ficheiro, este deve estar na raiz do projeto).
Para isso copia-se o conteúdo a analizar para a consola e pressionar os botões ctrl+z para o conteúdo ser analizado, para a opção 1, para a opção 2 basta escrever o nome do ficheiro.

 - Análise Lexical:
Ficheiro do lexer: XMLLexer.g4
No caso do ficheiro de entrada estiver com erros a níveis lexicais, serão indicadas as linhas do ficheiro onde nenhum token foi unificado.

 - Análise Sintática:
Ficheiro do parser: XMLParser.g4
No caso do ficheiro de entrada estiver com erros a níveis sintátios, serão indicadas as linhas do ficheiro onde não respeitam as regras da gramática.

 - Análise Semântica:
Ficheiro do listener:Listener.java
No caso do ficheiro de entrada estiver com erros a níveis semanticos,serão indicadas as linhas do ficheiro e ainda a regra que violam quando não respeitam as regras semânticas.

 - Representação(ões) Intermédia(s):
Ficheiro do listener:Listener.java
Ao decorrer das verificações semânticas bem suceddias, iam sendo criados mapas com o objetivo de ir criando a tabela de simbolos.

 - Geração de Código:
Ficheiro gerar .sdl: GenerateSDL.java
No caso de não haver erros a níveis lexicais, sintáticos nem semânticos, é gerado o ficheiro com extensão .sdl. Este ficheiro tem em conta as especificações que um ficheiro .sdl deve ter.
Recorremos à biblioteca JDOM paragerar o ficheiro .sdl tendo uma identação semelhante aos ficheiros com extensão .xml.
Um dos principais problemas com a geração de código foi haver atributos e elementos não especificados no .xml e serem necessários no .sdl.

 - Testes:
Não foram realizadas nenhuma infraestrutura nem bateria de testes. A nossoa forma de debugging foi atravéz de comparação de ficheiros.

 - Arquitetura:
São gerandos os tokens que serão utilizados na identificação da gramática. Posteriormente, será feita a validação da árvore gerada pela análise sintática para, na fase seguinte, já com a representação intermédia concluída, gerar o ficheiro de saída.
XMLLexer.g4 comunica com XMLParser.g4, enviando os tokens.
XMLParser.g4 comunica com Listener.java, enviando a árvore de análise.
Listener.java comunica com GenerateSDL.java enviando a tablea de símbolos.
O ficheiro Edge é útil pois é utilizado num algoritmos por nós criado para verificar se nos taxiways points e taxiways paths há conectividade, isto é é possível aceder a todos os pontos.

 - Pontos Positivos: 
Elaboração de algorítmo para verificar conectividade;
Ficheiro de saída bem identado e correto

 - Pontos Negativos:
Alguns aspetos não foram gerados no ficheiro de output. Está preparado para imprimir, só que está com pequenos erros.





