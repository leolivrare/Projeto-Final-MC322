# Projeto MC322 - Clube do Hardware
196163 - Douglas Daisuke Kaneiwa Yoshioka

204759 - Pedro Strambeck Nogueira 

207267 - Willian Takayuki Ozako

218548 - João Alberto Moreira Seródio

220120 - Leonardo Livrare Martins

220650 - Lucas B. A. Farias

## Componente DataOrganizer
|Campo | Valor|
|------|--------|
|Classe|pt.clubedohardware.dataorganizer.DataOrganizer|
|Autores|João Seródio, Leonardo Livrare|
|Objetivo|Organizar os dados da tabela para facilitar o diagnóstico|
|Interface|IDataOrganizer(IDataFilter + ITreeMaker)|


### Interface IDataFilter
|Método| Objetivo|
|------|--------|
|`diseaseFilter`|Recebe como parâmetro a tabela de diagnósticos e retorna as possíveis doenças em forma de uma lista string(List<String>)|
|`symptomFilter`|O parâmetro é a tabela do componente DataSet e o vetor das doenças da tabela, retorna uma matriz que aparece a frequência de cada sintoma por doença, por exemplo apareceram 3 casos de pacientes com língua amarela que apresentaram a doença bite_deficiet. Nessa matriz as linhas simbolizam os sintomas, por exemplo na linha 0 é o sintoma lingua amarela e na linha 1 é o sintoma febre, e as colunas simbolizam as doenças na mesma ordem da lista de doenças dada pelo metódo acima, por exemplo a doença na coluna 0 é a doença na posição 0 da lista de doenças. Assim, se na posição [1][3] da matriz de frequencia temos o valor 5, quer dizer que o sintoma 1 aparece 5 vezes para a doença da posição 3. |


### Interface ITreeMaker
|Método| Objetivo|
|------|--------|
|`treeMaker`|Recebe o vetor de doenças e a matriz de frequência de sintomas e retorna uma árvore de diagnóstico (Tree), cujos nós internos são perguntas e os nós folhas são os diagnósticos finais. A árvore consiste de um sintoma por nível, ou seja, no nível 1 da arvore temos um mesmo sintoma em todos os nós desse nível e assim por diante. Nos nós folhas da arvore temos um verificador .getDiagnostic() que retorna true se o nó é folha e possui um diagnostico, então esse nó possui um vetor doenças, o qual contem todas as doenças que o paciente possuí (.getDiseases() retorna o vetor de doenças, onde cada posição desse vetor possui o index da doença na lista de doenças gerada pelo método diseaseFilter).|

### Classe Tree (pt.clubedohardware.node.Tree)
|Método| Objetivo|
|------|--------|
|`getRoot`|Retorna a raíz da árvore (Node).|
|`getKeySymptoms`|Retorna a List(Integer) de sintomas que aparecem em apenas uma doença.|
|`getKeyCorrespondent`|Retorna a List(Integer) na qual o inteiro na posição X representa a doença que é o diagnóstico do sintoma na posição X da List(Integer) keySymptoms, que pode ser acessada com getKeySymptoms().|
|`getKSDiagnostic`|Recebe o inteiro que representa o sintoma e retorna um inteiro que é a posição na List(String) de doenças, sendo a doença nessa posição o diagnóstico.|
|`getDiseases`|Retorna uma List(String) de doenças.|
|`getPriority`|Retorna a List(Integer) de sintomas na ordem que aparecem na árvore.|
|`setRoot`|Recebe um nó (Node) como parâmetro e muda o atributo root para esse novo valor.|
|`toHeap`|Retorna uma List(Node) que é a árvore no formato de Heap.|
|`DAO`|Recebe duas Strings sendo a primeira o nome do arquivo de saída e o segundo o nome do diretório em que será salvo o arquivo. O método serializa a árvore em um formato que é legível para o usuário, a fim de verificar o se o estado da árvore está correto além de possuir dados sobre a construção da árvore.|

### Classe Node (pt.clubedohardware.node.Node)
|Método| Objetivo|
|------|--------|
|`getEsquerdo`|Retorna o filho esquerdo (Node) do nó.|
|`getDireiro`|Retorna o filho direito (Node) do nó.|
|`getSymptom`|Retorna o inteiro que corresponde ao sintoma na no vetor String[] attributes do DatSet.|
|`getFilledPower`|Retorna o grau de preenchimento (int) utilizado pelo DiagnosticCompleter. Quanto maior for o FilledPower, menor é a precisão do diagnóstico.|
|`getDiagnostico`|Retorna um (boolean) indicando se o nó é um nó folha (possui diagnóstico) ou não. |
|`getFilled`|Retorna um (boolean) indicando se o nó foi preenchido pelo DiagnosticCompleter.|
|`getPath`|Retorna um (int[]) que corresponde ao caminho percorrido na árvore.|
|`getDiseases`|Retorna uma List(Integer) de possíveis doenças para esse quadro de sintomas.|
|`sets`|Métodos sets para cada get acima.|
|`DAO`|Recebe um (PrintWriter) e um (int) que é o índice do nó no heap e então serializa o nó. Esse método é chamado pelo método DAO da árvore.|

Exemplo de uso: https://github.com/SerodioJ/mc322/blob/master/projeto/jarfiles/tutorial_como_usar_arvore.java

## Componente UserInterface
|Campo | Valor|
|------|--------|
|Classe|pt.clubedohardware.userinterface.UserInterface|
|Autores|Willian Ozako, Lucas, Douglas Yoshioka|
|Objetivo|Criar animação das interações entre o médico e o paciente|
|Interface|IAnimation|


### Interface IAnimation
|Método| Objetivo|
|------|--------|
|`story`|Recebe como parâmetro dois vetores, o primeiro que armazena as falas das personagens e o segundo registra quem fala na n-ésima fala. Exemplo: n-ésima posição do 1o vetor - "You have smallpox." - e na n-ésima posição do 2o vetor - "doctor" - logo fica: "Doctor: You have smallpox."**IMPORTANTE:** Para gerar destaque em uma expressão como o nome da doença ou o do sintoma, coloque a expressão entre asteriscos(*). EX: "You have \*smallpox\*." -> "You have **smallpox**." |
|`setWindowName`| Altera/adiciona título da janela da interface. Para isso ele recebe uma String `name` que armazena o nome desejado. |
|`setTempo`|Recebe como parâmetro String `v` que deve ser igual em valor à `slow`,`fast` ou  `default`. Ele configura a velocidade do texto "corrido"|
|`setPacientName`|Recebe como parâmetro a String `pacName` que é o nome do paciente, guarda esse nome para ser impresso na interface gráfica. |
|`setDocName`|Recebe como parâmetro a String `docName` que é o nome do doutor, guardando o nome para ser impresso na interface gráfica. |

Exemplo de uso: https://github.com/SerodioJ/mc322/blob/master/projeto/jarfiles/Tutorial_userInterface.java

## Componente FileUsage
|Campo | Valor|
|------|--------|
|Classe|pt.clubedohardware.fileusage.FileUsage|
|Autores|Leonardo Livrare|
|Objetivo|Salva dados obtidos pelo programa como a árvore diagnóstico, tabelas e path do arquivo CSV utilizado para poderem ser utilizados novamente pelo programa, não sendo necessário reprocessar todos os dados.|
|Interface|IFileUsage|


### Interface IFileUsage
|Método| Objetivo|
|------|--------|
|`save`| Pode receber 4 ou 2 parâmetros. No primeiro caso, o primeiro parâmetro é a lista de doenças(obtida pelo método diseaseFilter(instances) do DataOrganizer), o segundo é a matriz de frequencia (obtida pelo método symptomFilter(instances, diseases) do DataOrganizer), o terceiro é a árvore de diagnóstico (também obtida pelo DataOrganizer) e o último é a path do CSV usado. Esse método salva as informações desses 4 dados passados como parâmetros. Vale ressaltar que os arquivos da serialização ficam na pasta do projeto num arquivo chamado SerializedData/_NOMEdoCSV_. Com 2 parâmetro, o primeiro deve ser a árvore e o segundo a path do arquivo CSV, salvando apenas a árvore. |
|`getDiseases`| Recebe o nome da pasta dentro de SerializedbleData e retorna a List(String) de doenças |
|`getFrequency`| Recebe o nome da pasta dentro de SerializedbleData e retorna a matriz de frequência (int[][]) dos sintomas|
|`getTree`| Recebe o nome da pasta dentro de SerializedbleData e retorna a árvore de diagnóstico (Tree)|
|`getPathCSV`| Recebe o nome da pasta dentro de SerializedbleData e retorna a path (String) do arquivo CSV que foi base para geração dos dados|

Exemplo de uso: https://github.com/SerodioJ/mc322/blob/master/projeto/jarfiles/tutorial_fileUsage.java

## Componente DiagnosticCompleter
|Campo | Valor|
|------|--------|
|Classe|usado juntamente com o DataOrganizer|
|Autores|João Seródio, Pedro Strambeck|
|Objetivo|Completar os nós folha vazios da árvore|
|Interface|IDiagnosticCompleter|


### Interface IDiagnosticCompleter
|Método| Objetivo|
|------|--------|
|`dataFiller`|  Recebe como parâmetros a árvore (Tree) e o nó atual (Node) e retorna uma List(Integer) contendo os possíveis diagnoósticos.|


Os arquivos JAR dos componemtes podem ser encontrados em:

https://github.com/SerodioJ/mc322/tree/master/projeto/jarfiles

https://github.com/SerodioJ/ComponentesMC322
