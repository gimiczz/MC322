# Projeto Invasão ao Rio de Janeiro
> O Jogo Invasão ao Rio de Janeiro é inspirado no modelo Plants vs Zombies, em que o jogador posiciona defesas para impedir o ataque inimigo. No caso do Invasão ao Rio de Janeiro, o jogador posiciona tanques de guerra para se defender de navios piratas, onde cada categoria de tanque e de navio possui suas propriedades específicas, para os tanques é considerado a quantidade de tiros disponível e o dano que cada tiro causa em um navio, já para os navios é considerado a resistência aos tiros e a velocidade que ele se desloca no mapa.
O fluxo de jogo é simples, o jogo é inciado com 300 créditos e o jogador tem acesso a uma loja onde pode comprar três categorias de tanques de guerra, com cada tanque consumindo uma quantidade de créditos diferente. Uma vez que o jogador compra um tanque ele deve posicioná-lo em uma célula de terra que está vazia, ao fazer isso o tanque começa disparar tiros automaticamente em linha reta que atingem os navios que estão indo em direção as células de terra. Ao destruir um navio os créditos disponíveis aumentam para que o jogador possa comprar novos tanques.

# Equipe
* Giovanni Mesquita Micaroni 231702
* Jonathan do Ouro 248364
# Arquivo Executável do jogo
* [InvasãoAoRio](assets/InvasãoAoRio.jar)
* [InvasãoAoRio (Download)](https://drive.google.com/file/d/1t6f4L4nUpfXtKJUYKl2H6JAOP-ITxwBr/view?usp=sharing)
# Slides da Prévia
* [Slides da Prévia](assets/MC322-PropostaInicial.pptx)
* [Slides da Prévia (Apresentação)](https://docs.google.com/presentation/d/1VWDClihfaZlSJoE1NP40EWEMPne-HWDpHEoTdNXvNc4/edit?usp=sharing)

# Slides da Apresentação Final
* [Slides Finais](assets/MC322-SlidesFinais.pptx)
* [Slides Finais (Apresentação)](https://docs.google.com/presentation/d/1A17BexgyANxj-jKuBHobm8QDrC6Caz9vlF-jknswN2U/edit?usp=sharing) 

## Relatório de Evolução
> O nosso projeto final utiliza a arquitetura Model View Controller, todavia no começo do desenvolvimento não estávamos conseguindo organizar o projeto de tal forma que encaixa-se na arquitetura, pois começamos o projeto sem utilizar componentes e a visualização do modelo ficava bastante bagunçada com as classes misturadas em uma única pasta. Além disso, não estvamos conseguindo utilizar as interfaces para comunicação entre classes de maneira correta, visto que a padronização de interface requerida e interface ofertada se encaixa melhor quando se utiliza componentes. Dessa forma, reestruturamos o projeto para utilizarmos componentes.

![Arquitetura1](https://user-images.githubusercontent.com/102101030/178039047-137c4975-c7d1-4b9a-876a-a936924231bc.png)

> O diagrama acima representa o primeiro esboço do projeto, com componentes que se comunicam através de interfaces. Porém, esse diagrama ainda não estava suficiente, pois faltava agrupar os componentes no modelo Model View Controller. Para isso, alteramos a estrutura de pastas do projeto, como visto abaixo.

![pastas](https://user-images.githubusercontent.com/102101030/178039103-24221d74-eca3-496c-bb7e-6c804f1c123d.png)

> Essa estrutura de pastas nos permitiu separar os elementos do jogo em componentes e agrupar eles de acordo com sua coesão, assim deixando o projeto de acordo com o modelo de arquitetura escolhido. 

# Destaques de Código

# Destaques de Orientação a Objetos

## Código de Herança e Sobrecarga de Métodos
~~~JAVA
public class Barco {
	...
	
	public boolean atingir(int dano) throws InterruptedException {return false;}
	...
}

public class Navio extends Barco{
	...
	
	@Override
	public boolean atingir(int dano) throws InterruptedException {
		//retorna true se o barco for destruído
		if (this.resistencia > 0){
			this.resistencia -= dano;
			return false;
		}
		else {
			this.existe = false;
			this.mapa.removerBarco(this);
			return true;
		}
	}
}
~~~

> Nesse caso, foi importante usarmos herança pois, caso precisássemos criar novos tipos de barcos, poderíamos fazer outras classes filhas de Barco e fazer uma nova sobrecarga de métodos nessas novas classes, para que elas tivessem comportamentos únicos.

# Destaques de Pattern

## Diagrama do Pattern Observer

![observer](https://user-images.githubusercontent.com/102101030/178056439-2a832010-353e-49a2-af07-c499936bfddd.jpeg)

## Código do Pattern Observer

~~~JAVA
public class Mapa implements IMapa{
    ...
    private IAtualizaTela atualizaTela;
    ...
    @Override
    public void connect(IAtualizaTela atualizaTela) {
	this.atualizaTela = atualizaTela;
    }
    @Override
    public void removerTanque(Tanque tanque) {
	mapa[tanque.getL()][tanque.getC()].removerElemento();
	atualizaTela.removerTanque(tanque);
    }
    ...
}
~~~
~~~JAVA
public class AtualizaTela implements IAtualizaTela{
    private IJanelaPadrao janela;
    public void connect(IJanelaPadrao janela) {
	this.janela = janela;
    }
    public void removerTanque(Tanque tanque) {
	janela.removerTanque(tanque);
    }
    ...
}
~~~
~~~JAVA
public class JanelaPadrao extends JFrame implements IJanelaPadrao {
    private Container contentPane;
    private ArrayList<Imagem> tanques;
    ...
    public void removerTanque(Tanque tanque){
        for(Imagem itanque: tanques){
            if(itanque.getL() == tanque.getL() && itanque.getC() == tanque.getC()){
                contentPane.remove(itanque);
                tanques.remove(itanque);
                break;
            }
        }
    }
   ...
}
~~~
> O pattern destacado é o pattern Observer. O componente AtualizaTela é um observador do mapa, assim, toda vez que ocorre uma modificação do estado do mapa, ele notifica o AtualizaTela qual modificação foi feita. Além disso, a JanelaPadrão é um observador do AtualizaTela, desse modo a JanelaPadrão, ao identificar uma atualização, modifica a vizualização da interface gráfica.
A utilização do componente AtualizaTela como intermediario entre o mapa e a janela é feito para poder suportar possíveis expansões de plataforma, visto que para implementar o jogo para rodar, por exemplo, em um Android, seria necessário criar uma classe JanelaAndroid com métodos de interface gráfica apropriados para o Android.

## Diagrama do Pattern Strategy

![Celulas](https://user-images.githubusercontent.com/102101030/178052986-38c27cf0-cb7f-4b31-af5d-251ec1f04f1e.jpeg)

## Código do destaque de Strategy

~~~JAVA
public class Mapa implements IMapa{
	private ICelula[][] mapa;
	...
	public Mapa() {
		mapa = new ICelula[6][16];
		for (int i = 0; i < mapa.length; i++) {
			for (int j = 0; j < mapa[i].length; j++) {
			   if (j < 3)
				mapa[i][j] = new Terra();
			   else
				mapa[i][j] = new Agua();
			}
		}
	}
	...
}
~~~

> O pattern Strategy foi utilizado nesse caso pois os tipos de célula (Terra e Água) precisariam ter comportamentos diferentes para as mesmas funções da interface ICelula. A adoção desse pattern possibilitou que tratássemos objetos de dois tipos diferentes da mesma maneira, se diferenciando apenas nas implementações das funções.

# Conclusões e Trabalhos Futuros

> Após o término do nosso projeto, podemos dizer que atendemos a grande parte dos objetivos propostos por ele, como utilizar uma arquitetura bem planejada e que tira vantagem de ideias da orientação a objetos, como as interfaces e o polimorfismo, além dos design patterns. Também tivemos a oportunidade de utilizar o conceito de Threads, o que possibilitou o aprendizado de uma nova maneira de programar e colaborou para o funcionamento correto do jogo. Um ponto que, devido ao tempo curto, não recebeu a atenção necessária foi o plano de exceções. Devido à arquitetura montada pelo nosso grupo, no futuro poderíamos criar facilmente novos tipos de navios e de tanques, além de extender o total de ondas de navios e criar um boss final. Com esse projeto, experienciamos na prática a importância de um bom planejamento e de uma arquitetura bem estruturada, que fazem toda a diferença em um projeto de grande porte.

# Documentação dos Componentes

# Diagrama Geral da Arquitetura do Jogo

> Este é o diagrama geral representando a arquitetura do jogo:

![Projeto Final POO 2022 - Page 6](https://user-images.githubusercontent.com/69171865/178084309-2aba8bf3-4ace-45ad-b28d-2e2f6f291f9f.jpeg)

> *Na parte do model, as setas representam uma referência e não uma herança*

## Diagrama Geral de Componentes

> Este é o diagrama representando os componentes do jogo:

![Projeto Final POO 2022 - Page 1](https://user-images.githubusercontent.com/69171865/178077070-7933fefe-19e7-430d-a991-2e7728692478.jpeg)


## Componente `<Controle>`

> Tem o papel de posicionar os tanques de guerra no mapa e gerar os tiros dos tanques.

![Projeto Final POO 2022 - Page 2](https://user-images.githubusercontent.com/69171865/178072142-ed9b11a9-307b-4a5a-9b80-e04dd67c72e0.jpeg)


**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `invasaoAoRio.src.invasaoAoRio.Controller.controle`
Autores | `<Jonathan do Ouro e Giovanni Mesquita Micaroni>`
Interfaces | `<IRmapa> <IcontrolePropriedades>`

### Interfaces

> Interfaces associadas a esse componente:

![Projeto Final POO 2022 - Page 2(4)](https://user-images.githubusercontent.com/69171865/178073242-9177f7ad-e6dc-4210-a083-17edad4b13a1.jpeg)

> Interface agregadora do componente em Java:

~~~java
public interface Icontrole extends IRmapa, IcontrolePropriedades{
}
~~~

## Detalhamento das Interfaces

### Interface `<IRmapa>`

`<Permite um componente se conectar com o mapa>`

~~~JAVA
public interface IRmapa {
    public void connect(IMapa mapa);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com uma interface IMapa`

### Interface `<IcontrolePropriedades>`

`<Padroniza as propriedades oferecidas pelo controle>`

~~~JAVA
public interface IcontrolePropriedades {
    public void conectarTanque(Tanque tanque);
    public boolean addTanque(int x, int y) throws InterruptedException;
}
~~~

Método | Objetivo
-------| --------
`conectarTanque` | `passa para o controle um tanque que será adcionado em uma posição de terra`
`addTanque` | `adiciona um tanque na posicao informada`

## Componente `<GameStart>`

> É o responsável por iniciar a Janela e controlar o fluxo do jogo.

![Projeto Final POO 2022 - Page 3(2)](https://user-images.githubusercontent.com/69171865/178080151-10fc7a16-99d8-4ab4-941d-416dbd1d0d73.jpeg)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `invasaoAoRio.src.invasaoAoRio.GameStart`
Autores | `<Jonathan do Ouro e Giovanni Mesquita Micaroni>`
Interfaces | `<IRcontrole> <IRGeradorDeOndas> <IRLoja> <IGameStartProperties> <IRAtualizaTela>`

### Interfaces

> Interfaces associadas a esse componente:

![Projeto Final POO 2022 - Page 2(7)](https://user-images.githubusercontent.com/69171865/178080097-ec7ce024-e8f4-4e74-81ec-8db78f444552.jpeg)

> Interface agregadora do componente em Java:

~~~java
public interface IGameStart extends IRcontrole, IRGeradorDeOndas, IRLoja, IGameStartProperties, IRAtualizaTela {
}
~~~

## Detalhamento das Interfaces

### Interface `<IRGeradorDeOndas>`

`<Permite um componente se conectar com o gerador de ondas de navios>`

~~~JAVA
public interface IRGeradorDeOndas {
    public void connect(IgeradorDeOndas geradorOndas);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com uma interface IgeradorDeOndas`

### Interface `<IRcontrole>`

`<Permite um componente se conectar com o controle do jogo>`

~~~JAVA
public interface IRcontrole {
    public void connect(Icontrole controle);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com uma interface IControle`

### Interface `<IRLoja>`

`<Permite um componente se conectar com a loja>`

~~~JAVA
public interface IRLoja {
    public void connect(Iloja loja);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com uma interface ILoja`

### Interface `<IRAtualizaTela>`

`<Permite um componente se conectar com a interface IAtualizaTela>`

~~~JAVA
public interface IRAtualizaTela {
    public void connect(IAtualizaTela atualiza);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com a interface IAtualizaTela`

### Interface `<IGameStartProperties>`

`<Padroniza os metodos do  gameStart>`

~~~JAVA
public interface IGameStartProperties {
	public void iniciarJogo(IMapa mapa, IAtualizaTela atualizaTela);
	public boolean addTanque(int x, int y) throws InterruptedException;
	public boolean comprarTanque(int i);
	public void acabarJogo();
	public void start();
}
~~~

Método | Objetivo
-------| --------
`iniciarJogo` | `cria, configura e abre a janela padrão para o usuario`
`addTanque` | `solicita para o controle adicionar o tanque passado como parâmetro`
`comprarTanque` | `compra na loja um tanque de tipo i e conecta o tanque no controle`
`acabarJogo` | `faz os elementos do jogo pararem e fecha a janela`
`start` | `inicia as ondas de navios`

## Componente `<Mapa>`

> Responsável por representar o mapa, adicionar, remover e movimentar elementos que estão no mapa.

![Projeto Final POO 2022 - Page 3(1)](https://user-images.githubusercontent.com/69171865/178080200-7bde0fb2-6de0-4968-ab35-d7d003c0c828.jpeg)


**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `invasaoAoRio.src.invasaoAoRio.Model.Mapa`
Autores | `<Jonathan do Ouro e Giovanni Mesquita Micaroni>`
Interfaces | `<IRLoja> <IRAtualizaTela>`

### Interfaces

> Interfaces associadas a esse componente:

![Projeto Final POO 2022 - Page 2(6)](https://user-images.githubusercontent.com/69171865/178080172-07ec133b-c55b-482e-8c99-191d20f73b9f.jpeg)

> Interface agregadora do componente em Java:

~~~java
public interface IMapa extends IRLoja, IRAtualizaTela{
	public boolean addTanque(Tanque tanque);
	public ICelula[][] getMapa();
	public void addBarco(Barco barco);
	public void removerElemento(int l, int c);
	public void movimentarBarco(int origemL, int origemC, int destinoL, int destinoC) throws InterruptedException;
	public boolean moverTiro(Tiro tiro) throws InterruptedException;
	public void addTiro(Tiro tiro, boolean addNaTela);
	public void removerTanque(Tanque tanque);
	public void removerBarco(Barco barco) throws InterruptedException;
}
~~~

Método | Objetivo
-------| --------
`addTanque` | `adiciona um tanque na matriz de cululas do mapa`
`getMapa` | `retorna a matriz de celulas`
`addBarco` | `adiciona um barco na matriz de cululas do mapa`
`removerElemento` | `remove um elemento da matriz que está na linha L e coluna C`
`movimentarBarco` | `move um barco de uma celula para a celula ao lado`
`moverTiro` | `move um tiro para a celula que está a direita de onde o tiro se econtra`
`addTiro` | `adiciona um tiro em uma celula da matriz`
`removerTanque` | `remove da matriz de celulas o tanque passado como parâmetro`
`removerBarco` | `remove da matriz de celulas o barco passado como parâmetro`

## Detalhamento das Interfaces

### Interface `<IRLoja>`

`<Permite um componente se conectar com a loja>`

~~~JAVA
public interface IRLoja {
    public void connect(Iloja loja);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com uma interface ILoja`

### Interface `<IRAtualizaTela>`

`<Permite um componente se conectar com a interface IAtualizaTela>`

~~~JAVA
public interface IRAtualizaTela {
    public void connect(IAtualizaTela atualiza);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com a interface IAtualizaTela`

## Componente `<GeradorOnda>`

> É o responsavel por gerar as ondas de navios no mapa.

![Projeto Final POO 2022 - Page 2(1)](https://user-images.githubusercontent.com/69171865/178072351-ca0199de-d2eb-4d56-b1da-1913b1379a08.jpeg)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `invasaoAoRio.src.invasaoAoRio.Controller.GeradorDeOndas`
Autores | `<Jonathan do Ouro e Giovanni Mesquita Micaroni>`
Interfaces | `<IRmapa> <IgeradorOndasPropriedades>`

### Interfaces

> Interfaces associadas a esse componente:

![Projeto Final POO 2022 - Page 2(2)](https://user-images.githubusercontent.com/69171865/178072971-9768847e-ebf0-490e-99d9-9348275b93f8.jpeg)

> Interface agregadora do componente em Java:

~~~java
public interface IgeradorDeOndas extends IRmapa, IgeradorOndasPropriedades{	
}
~~~

## Detalhamento das Interfaces

### Interface `<IRmapa>`

`<Permite um componente se conectar com o mapa>`

~~~JAVA
public interface IRmapa {
	public void connect(IMapa mapa);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com uma interface IMapa`

### Interface `<IgeradorOndasPropriedades>`

`<Padroniza a função de gerar navios oferecida pelo Gerador de Ondas>`

~~~JAVA
public interface IgeradorOndasPropriedades {
	public void gerarOnda(int quantidadeNavios, long duracao, int dificuldade) throws InterruptedException;
}
~~~

Método | Objetivo
-------| --------
`gerarOnda` | `recebe a quantidade de navios a serem gerados, a duração da onda e a dificuldade dela e gera essa onda no mapa.`

## Componente `<JanelaPadrao>`

> É o componente responsavel por criar a janela que apresenta a interface gráfica do jogo.

![Projeto Final POO 2022 - Page 3](https://user-images.githubusercontent.com/69171865/178077147-dbd9d812-7319-4c8b-8ff2-ec86ccc29eb3.jpeg)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `invasaoAoRio.src.invasaoAoRio.View.JanelaPadrao`
Autores | `<Jonathan do Ouro e Giovanni Mesquita Micaroni>`
Interfaces | `<IRmapa> <IRGameStart> <MouseInputListener>`

### Interfaces

> Interfaces associadas a esse componente:

![Projeto Final POO 2022 - Page 2(5)](https://user-images.githubusercontent.com/69171865/178077128-6837f4f4-d54b-42a0-bd87-9b69e61b6bcb.jpeg)

> Interface agregadora do componente em Java:

~~~java
public interface IJanelaPadrao extends IRGameStart, MouseInputListener, IRMapa{
    	public void removerTanque(Tanque tanque);
	public void moverBarco(Barco barco, int destinoL, int destinoC);
	public void addBarco(Imagem barcoGerado);
	public void removerBarco(Barco barco) throws InterruptedException;
	public void acabarJogo(int flag);
	public void addTiro(Imagem itiro);
	public void moverTiro(Tiro tiro);
	public void removerTiro(Tiro tiro);
	public void trocaImagemOnda(int ondaAnterior, int novaOnda);
	public void atualizaCreditos(int nCreditos);
}
~~~

Método | Objetivo
-------| --------
`removerTanque` | `remove a imagem de um tanque da tela`
`moverBarco` | `move a imagem de um barco para uma determinada posição`
`addBarco` | `adiciona a imagem de um barco na tela`
`removerBarco` | `remove a imagem de um barco da tela`
`acabarJogo` | `faz os objetos pararem de se mexer e mostra na tela que o jogo acabou`
`addTiro` | `adiciona a imagem de um tiro na tela`
`moverTiro` | `move a imagem de um tiro na tela`
`removerTiro` | `remove a imagem de um tiro da tela`
`trocaImagemOnda` | `altera o número da onda atual`
`atualizaCreditos` | `atualiza a quantidade de créditos que o jogar possui`


## Detalhamento das Interfaces

### Interface `<IRmapa>`

`<Permite um componente se conectar com o mapa>`

~~~JAVA
public interface IRmapa {
	public void connect(IMapa mapa);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com uma interface IMapa`

### Interface `<IRGameStart>`

`<Permite um componente se conectar com o GameStart>`

~~~JAVA
public interface IRGameStart {
	public void conectaGame(IGameStart gameStart);
}
~~~

Método | Objetivo
-------| --------
`conectaGame` | `conectar um objeto a uma interface IGameStart`
## Componente `<AtualizaTela>`

> É o componente responsável por fazer alterações na janela que apresenta a interface gráfica do jogo.

![Projeto Final POO 2022 - Page 4](https://user-images.githubusercontent.com/69171865/178080973-815143e8-47d4-46fe-a5b7-fecc82c9aa45.jpeg)

**Ficha Técnica**
item | detalhamento
----- | -----
Classe | `invasaoAoRio.src.invasaoAoRio.View.AtualizaTela`
Autores | `<Jonathan do Ouro e Giovanni Mesquita Micaroni>`
Interfaces | `<IRJanelaPadrao> <IAtualizaTelaProperties>`

### Interfaces

> Interfaces associadas a esse componente:

![Projeto Final POO 2022 - Page 5](https://user-images.githubusercontent.com/69171865/178081095-a75b4b66-6c1f-48ae-9161-9d6da59bd3c7.jpeg)

> Interface agregadora do componente em Java:

~~~java
public interface IAtualizaTela extends IAtualizaTelaProperties, IRJanelaPadrao {
}
~~~

## Detalhamento das Interfaces

### Interface `<IRJanelaPadrao>`

`<Permite um componente se conectar com a JanelaPadrao>`

~~~JAVA
public interface IRJanelaPadrao {
    public void connect(IJanelaPadrao janela);
}
~~~

Método | Objetivo
-------| --------
`connect` | `conectar um objeto com uma interface IJanelaPadrao`

### Interface `<IAtualizaTelaProperties>`

`<Padroniza as funções oferecidas pela classe AtualizaTela>`

~~~JAVA
public interface IAtualizaTelaProperties {
	public void removerTanque(Tanque tanque);
	public void adicionarBarco(Barco barco);
	public void moverBarco(Barco barco, int l, int c);
	public void removerBarco(Barco barco) throws InterruptedException;
	public void addTiro(Tiro tiro);
	public void removerTiro(Tiro tiro);
	public void moverTiro(Tiro tiro);
	public void trocaImagemOnda(int ondaAnterior, int novaOnda);
	public void atualizaCreditos(int nCreditos);
	public void acabarJogo(int flag);
}
~~~

Método | Objetivo
-------| --------
`removerTanque` | `fala para o view remover a imagem de um tanque da tela`
`moverBarco` | `fala para o view mover a imagem de um barco para uma determinada posição`
`addBarco` | `fala para o view adicionar a imagem de um barco na tela`
`removerBarco` | `fala para o view remover a imagem de um barco da tela`
`acabarJogo` | `fala para o view fazer os objetos pararem de se mexer e mostra na tela que o jogo acabou`
`addTiro` | `fala para o view adicionar a imagem de um tiro na tela`
`moverTiro` | `fala para o view mover a imagem de um tiro na tela`
`removerTiro` | `fala para o view remover a imagem de um tiro da tela`
`trocaImagemOnda` | `fala para o view alterar o número da onda atual`
`atualizaCreditos` | `fala para o view atualizar a quantidade de créditos que o jogar possui`
