EXERCÍCIO PRÁTICO 02 - OO
=========================

## PROPOSTA
Propõem-se nesse EP2 o desenvolvimento de um jogo de batalha naval para apenas um jogador com o intermédio do IDE Netbeans, java.

Mais informações:  [WIKI - EP2](https://gitlab.com/oofga/eps_2018_1/ep2/wikis/home).

Game developed for educational purposes.

## ACESSO À APLICAÇÃO
Para testar a aplicação, execute os seguintes passos:
1. Faça uma cópia do repositório para o seu computador em um lugar de sua preferência. 
	* Faça o "download" manualmente pela página do projeto; ou
	* Através do "git", faça um "git clone":

```
    $ git clone https://gitlab.com/xRegis/EP2_BATALHA_NAVAL.git
```

2. Abra o IDE Netbeans e proceda da seguinte maneira:
    > File  >  Open Project > EP2_BATALHA_NAVAL

3. Com o projeto aberto, execute-o:
    > Run > Run Project

## FUNCIONAMENTO
A aplicação possui as seguintes características:
1. Para começar uma partida, no menu principal:
	* Digite o seu nome de jogador;
	* Clique em jogar e, em seguida, selecione o mapa de jogo.
    > Há mapas disponíveis para uso em "src/gameMaps" no próprio projeto.

2. No menu principal, o usuário também poderá visualizar o "ranking" e um tutorial do jogo.

3. Quanto às habilidades, o jogador poderá:
	* Atacar uma posição: aperte em uma área 1x1;
	* Atacar uma área: clique e arraste pela diagonal de modo a formar um quadrado 2x2;
	* Atacar uma linha ou coluna: clique e arraste de um extremo até o outro desejado.

4. Quanto aos recursos:
	* O jogador iniciará com 4500 pontos;
	* Cada habilidade citada anteriormente terá um preço dinâmico de acordo com as características do mapa.

5. Quanto às embarcações:
	* Na tela do jogo, os barcos estão dispostos em ordem crescente de tamanho, variando o tamanho entre [1,5] de cima para baixo.
	* Os traços vermelhos abaixo dos barcos indicará quantas embarcações do respectivo barco ainda restam no mapa para serem destruídas.

6. Quanto a condição de vitória ou derrota:
	* Vence o jogo aquele que destruir todas as embarcações no mapa.
	* Perde o jogo aquele que não possuir mais recursos para realizar ataques.

## OBSERVAÇÃO
O jogo propriamente dito está predefinido para uma resolução de 900x600, caso seja desejado outro valor, proceda da seguinte forma:
1. Acesse na pasta do projeto a classe "MainGameFrame" disponível em "src".
2. Altere os valores de "FRAME_WIDTH" e "FRAME_HEIGHT" para os valores desejados.

## IDEALIZADOR
Welison Lucas Almeida Regis - 2018.1.
