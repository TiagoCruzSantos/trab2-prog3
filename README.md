# TP2 - PROG3 - Sistema PPGI (interface web)

Autores: Atílio Antônio Dadalto e Tiago da Cruz Santos.

## Estrutura do projeto

>`online-ppgi/` Contém todo o projeto da interface web; 
> `src` Contém `test`, utilizado pelo Maven e `main`; 
> `main` Contém `resources`, que contém os arquivos relativos ao front-end, como .html e .css, e `java`;
> `java` Contém `sisPPGI`, que contém o projeto original, capaz de processar os arquivos, e o diretório `com`, que contém os arquivos .java relativos à interface web de fato.
    
>`testes` Contém as entradas fornecidas pela especificação do trabalho (podem ser utilizadas pela interface web). 
>`entradas_autorais` Contém as entradas solicitadas pela especificação do trabalho, nas subpastas `a1` e `a2`. 

## Build com o Maven e Spring Boot
Para buildar todo o projeto, basta entrar no diretório `online-ppgi` com o terminal e enviar o comando `mvn package`. Daí, ao finalizar, abra a aplicação Java através de `java -jar /target/onlineppgi-0.0.1-SNAPSHOT.jar`. O Spring Boot irá iniciar a aplicação em instantes e será possível acessar a interface através do http://localhost:8080.

## Serviço hospedado no Heroku

O projeto também pode ser encontrado hospedado no Heroku, através do link https://online-ppgi.herokuapp.com/
