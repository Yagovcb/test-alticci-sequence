# Hi! i am Yago do Valle Castelo Branco :man_technologist:


### About me

#### I am a Senior Software Developer and Software Architect

- My name is Yago, I am a software engineer and currently live in Brazil.
- I work with development for more than 6 years, I have experience with many technologies.

##

<br>
<h1 align="center">
Calculadora Alticci Sequence (Backend)
</h1>
<br>

## 💬 Sobre o repositório

A Calculadora Alticci Sequence calcula um valor da sequência Alticci com base em um índice passado, na qual é definida da seguinte forma:

n=0 => a(0) = 0

n=1 => a(1) = 1

n=2 => a(2) = 1

n>2 => a(n) = a(n-3) + a(n-2)

O serviço principal tira partido de cálculos passados para acelerar cálculos futuros por meio do caching (utilizando memoização). Além do serviço principal, há também um serviço pelo Spring Cache, para demonstrar a diferença de performance quando não é utilizada a memoização.

## ⚠ Pré-requisitos para execução do projeto

* Java 11 ou versões superiores
* Maven

## 📌 Como utilizar?

Para utilizar a Calculadora Alticci Sequence em produção, basta acessá-lo diretamente no seu navegador. [Clique aqui!](https://wienerdev.github.io/alticci-sequence-angular/)

Para executar o projeto, digite o seguinte comando no diretório raiz:

```
mvn spring-boot:run 
```

Após o build da aplicação, acesse o [Swagger](http://localhost:8099/swagger-ui/index.html#/) para testar os endpoints.

## 📲 Serviços disponíveis para testes

### Alticci Sequence Number com Memoization
```
Method: GET
URL: http://localhost:8099/alticci/memoizationCache/{n}
{n}: índice a ser passado.
```

### Checar Cache Memoizado
```
Method: GET
URL: http://localhost:8099/alticci/checkMemoCache
```

### Limpar Cache Memoizado
```
Method: PUT
URL: http://localhost:8099/alticci/clearMemoCache
```

### Alticci Sequence Number com Spring Cache
```
Method: GET
URL: http://localhost:8099/alticci/springCache/{n}
{n}: índice a ser passado.
```

##
<h4><b><samp>Connect with me:</samp></b></h4>

[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/Yagovcb)](https://github.com/Yagovcb)
[![Gmail](https://img.shields.io/badge/yago.vcb@hotmail.com-FFFEEE?style=flat-square&logo=gmail&logoColor=red)](mailto:yago.vcb@hotmail.com)
[![Twitter](https://img.shields.io/badge/@Yagovcb-1DA1F2?style=flat-square&logo=twitter&logoColor=white)](https://twitter.com/Yagovcb)
[![Linkedin](https://img.shields.io/badge/Yago_do_Valle_Castelo_Branco-0077b5?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/yagovcb/)
[![Medium](https://img.shields.io/badge/@yagovcb-black?style=flat-square&logo=medium&logoColor=white)](https://medium.com/@yagovcb)


![](https://visitor-badge.glitch.me/badge?page_id=Yagovcb.Yagovcb)
