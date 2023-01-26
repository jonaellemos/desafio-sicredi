# desafio-sicredi
Repositório com o Desafio Sicredi, 

## Breve Relato do desenvolvimento.
````
O projeto foi pensando para atender a demanda do desafio proposto, buscando uma abordagem mais descentralizada da Classe Pauta. 
#### A pauta pode ser criada, desde que o título seja diferente de branco e maior que 5 caraceteres. 
#### A Sessao pode ser criada apenas com a IdPauta, sendo o tempoLimite opcional, quando não informado será escolhido como default 1;
#### Para que seja computado um voto, é necessário que primeiro seja realizado o cadastro do usuário. Existe validação para Email 
e CPF internas na aplicação, no entanto conforme atividade bônus de integração, ao ser chamado uma nova url externa, que pode ser 
configurada no host, o mesmo devolverá os status : ABLE_TO_VOTE ou UNABLE_TO_VOTE. A url fornecida no teste encontra-se offline, 
por tanto criei uma outra api que quando acionada, será responsável por essa validação. Disponivel em: https://github.com/jonaellemos/validar-cpf.
#### O sumário de resultados poderá ser chamado via GET, para obter o resultados já processados para aquela id, e via POST irá disparar o evento de criação e sincronização com o Kafka. 
#### A aplicação foi submetida a carga de dados com utilização do ApacheJ Meter, tendo obtido bom desempenho quando o acesso simultâneo acima de 4 mil usuários. 

````
## Pré requisitos
- #### Maven
````
mvn -v

Apache Maven 3.5.2
````
- #### Docker

````
docker -v

Docker version 19.03.13, build 4484c46
  ````

- #### docker-compose

````
docker-compose -v

docker-compose version 1.27.4, build unknown
  ````


- #### Java 11
````
java -version

openjdk version "11.0.9.1" 2020-11-04
OpenJDK Runtime Environment AdoptOpenJDK (build 11.0.9.1+1)
OpenJDK 64-Bit Server VM AdoptOpenJDK (build 11.0.9.1+1, mixed mode)
````

## Ambiente Linux

### Empacotar dependencias
```
mvn package -DskipTests
``` 
### Construir imagem 
```
docker build -t desafio-pauta .
``` 
### Subir stack
```
docker-compose -f docker-compose.yml up -d
``` 

## Ambiente Windows

### Empacotar dependencias
```
mvn package -DskipTests
``` 
### Construir imagem
```
docker build -t desafio-pauta .
``` 
### Subir stack
```
docker-compose -f docker-compose.yml up -d
``` 

# Swagger
http://localhost:8080/api/v1/swagger-ui/index.html

# Postman
### link com requisicoes da api :
/postman --Dentro do Projeto. 

# Versionamento:

```
Foi selecionado como versionador o próprio git. Bem como o prefixo de contexto da aplicação /api/v1. 
```

### Cadastrar Pauta

```
POST http://localhost:8080/api/v1/pautas

{
  "titulo": "titulo da pauta"
}
``` 

### Abrir Sessão

```
POST http://localhost:8080/api/v1/sessoes

{
    "idPauta": 1,
    "minuto" : 15
}

OU

{
    "id_pauta": 1,
}

```
### Cadastrar um usuário
```

POST localhost:8080/api/v1/usuarios/
{
    "nome":"Jonael Lemos",
    "email":"jonaellemos@hotmail.com",
    "cpf":"cpfvalido"
}


```
### Validar CPF um usuário
```
POST localhost:8080/api/v1/votos/integracao

{
    "voto":"Sim",
    "idSessao": 1,
    "usuario":{
        "id": 1,
        "cpf":"03064136399"
    }
}

```

### Votar em uma pauta
```
POST http://localhost:8080/api/v1/votos

{
    "voto":"Sim",
    "idSessao": 1,
    "usuario":{
        "id": 1,
        "cpf":"03064136399"
    }
}

``` 
### Consultar resultado da pauta
```

GET localhost:8080/api/v1/resultadoVotacao/{id}

``` 

