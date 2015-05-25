-Grupo: G10

-Constituído por:
André Humberto Trigo de Bordalo Morais
Bruno Coelho da Silva
João Alberto Trigo de Bordalo Morais
Pedro Dias Faria

-Tema: XML2SDL

Pasta 'src' contém o código fonte do projeto
Pasta 'bin' contém o projeto compilado
Pasta 'parserlexersemantic' contém os ficheiros teste

-Para iniciar o programa:
 Garantir a versão antlr 4.5
2 Formas possíveis para executar:
1ª: Compilar pelo IDE (exemplo Eclipse) pelo ficheiro bgl2sdl.java (onde se encontra a função main)
2ª: Localizar a pasta bin do projeto na linha de comandos;
    dentro da pasta escrever na linha de comandos: java bgl2sdl.bgl2sdl

Em seguida, na consola aparecerá um texto a perguntar por duas opções («(1) Console», «(2) File»). Devem ser ignoradas. O projeto está preparado para receber já o conteúdo a ser analizado.
Para isso copia-se o conteúdo a analizar para a consola e pressionar os botões ctrl+z para o conteúdo ser analizado.

Os ficheiros fridayharbor.xml e whidbey.xml
O ficheiro errors.xml é o que tem alguns erros despropositados os quais o nosso projeto deteta e sinaliza, indicado o tip ode erro e a linha (na maioria dos casos)


-Verificações feitas:
Ao valor de cada atributo: quer seja int, float, string, bool e ainda valores específicos
Aos atributos e elementos que têm ordem específica, obrigatoriedade e opcional.

Criação das tabelas de símbolos
