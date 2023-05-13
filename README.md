# API Encoder
<div align="center">


<p align="center">
   <img src="http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=RED&style=for-the-badge" />
</p>
</div>
<div align="center" class="row">
<img src="readme/img.png" width="900" height="550"/>
</div>

## Tópicos

<div align="center">
  • <a href="#Descrição do Projeto">Criação de cliente</a> •
  <a href="#tecnicas-e-tecnologias-utilizadas">Consultas de cliente</a> •
  <a href="#tecnicas-e-tecnologias-utilizadas">Login de cliente</a> •
  <a href="#abrir-e-rodar">Exclusão de cliente</a> •
  <a href="#acesso-ao-projeto">Criação de HASH</a> •  
  <a href="#ajustes-e-melhorias">Consultas de HASH</a> •
  <a href="#licenca">Exclusão de HASH</a> 
</div>

## Patterns

<div align="center">
  • <a href="#Descrição do Projeto">Builder</a> •
  <a href="#tecnicas-e-tecnologias-utilizadas">Singleton</a> 
</div>

## Builder
O padrão Builder (Builder Pattern) é um padrão de projeto de software criacional (creational design pattern) que tem como objetivo separar a construção de um objeto complexo da sua representação, permitindo que o mesmo processo de construção possa criar diferentes representações.

Em outras palavras, o padrão Builder oferece uma maneira de construir objetos complexos passo a passo, permitindo que diferentes tipos de objetos possam ser criados usando o mesmo processo de construção. Isso torna o código mais flexível e modular, facilitando a manutenção e evolução do software.

O padrão Builder é particularmente útil quando você precisa criar objetos complexos que exigem muitos parâmetros ou quando você deseja criar diferentes variações de um mesmo objeto, mantendo o mesmo processo de construção.

## Singleton
Singleton é um padrão de projeto de software criacional que garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global para essa instância.

Em outras palavras, o padrão Singleton garante que uma classe possa ter apenas uma instância durante a execução do programa e fornece um meio para acessar essa instância globalmente em toda a aplicação. Isso é útil quando uma única instância de uma classe é necessária para coordenar ações em todo o sistema.

O padrão Singleton é amplamente utilizado em situações em que apenas uma instância de uma classe é necessária, como em objetos de conexão com banco de dados, objetos de log e gerenciadores de recursos.

Ao usar o padrão Singleton, é possível controlar o acesso à instância única e garantir que ela seja inicializada apenas uma vez, além de garantir que a instância seja facilmente acessível em toda a aplicação.

O padrão Singleton pode ser implementado de várias maneiras, como usando um construtor privado, uma variável estática privada e um método público estático para acessar a instância.
## Descrição do Projeto

<p align="justify">Aplicação Java com SPRING BOOT, desenvolvida na aula de Development and Design Patters da 4a etapa do curso de Sistemas de informação da Univem.</p>
<p align="justify"> * API gerando HASH a partir da URL informada</p>
<p align="justify"> * Balanceador de carga (Nginx)</p>
<p align="justify"> * Cache (spring-boot-starter-cache e Redis)</p>
<p align="justify"> * Banco de dados (H2)</p>


## Integrantes da equipe
<p align="justify">Gabriel Menoi - RA:602655</p>
<p align="justify">Gabriel Mielo - RA:601128</p>
<p align="justify">Matheus Araújo - RA:603236</p>
<p align="justify">Tiago Santos - RA:603181</p>
<p align="justify">Vitor Studzieski - RA:608661</p>