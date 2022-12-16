# Hi! i am Yago do Valle Castelo Branco :man_technologist:


### About me

#### I am a Senior Software Developer and Software Architect

- My name is Yago, I am a software engineer and currently live in Brazil.
- I work with development for more than 6 years, I have experience with many technologies.

##

<br>
<h1 align="center">
Calculator Alticci Sequence (Backend)
</h1>
<br>

## 💬 About the repository

The Alticci Sequence Calculator calculates an Alticci sequence value based on a passed index, which is defined as follows:

n=0 => a(0) = 0

n=1 => a(1) = 1

n=2 => a(2) = 1

n>2 => a(n) = a(n-3) + a(n-2)

The core service takes advantage of past calculations to speed up future calculations through caching (using memoization). In addition to the main service, there is also a service by Spring Cache, to demonstrate the performance difference when memoization is not used.

## ⚠ Pre Requirements for project execution

* Java 11 or higher versions
* Maven

## 📌 How to user?

To run the project, enter the following command in the root directory:

```
mvn spring-boot:run 
```

After building the application, access the [Swagger](http://localhost:8099/swagger-ui/index.html#/) to test the endpoints.

## ⚠ Super Important

This API has an updated Spring Security configuration and is ready to be coupled with the front-end. The authentication methodology is through Barear tokens with JWT encoding.

To simplify the process, we are using an H2 database in memory with a previously registered user, this user's information is located in the components folder, in the config folder.

To generate a JWT token, direct the request with username and password, in JSON format, to the following endpoint:

```
POST http://localhost:8099/login
```

That said, we can proceed!

## 📲 Services available for testing

### Alticci Sequence Number with Memoization
```
Method: GET
URL: http://localhost:8099/alticci/memoizationCache/{n}
{n}: index to be passed.
```

### Check Memoized Cache
```
Method: GET
URL: http://localhost:8099/alticci/checkMemoCache
```

### Clear Memoized Cache
```
Method: PUT
URL: http://localhost:8099/alticci/clearMemoCache
```

### Alticci Sequence Number with Spring Cache
```
Method: GET
URL: http://localhost:8099/alticci/springCache/{n}
{n}: índice a ser passado.
```
##

Thanks for testing my API!

##
<h4><b><samp>Connect with me:</samp></b></h4>

[![Github Badge](https://img.shields.io/badge/-Github-000?style=flat-square&logo=Github&logoColor=white&link=https://github.com/Yagovcb)](https://github.com/Yagovcb)
[![Gmail](https://img.shields.io/badge/yago.vcb@hotmail.com-FFFEEE?style=flat-square&logo=gmail&logoColor=red)](mailto:yago.vcb@hotmail.com)
[![Twitter](https://img.shields.io/badge/@Yagovcb-1DA1F2?style=flat-square&logo=twitter&logoColor=white)](https://twitter.com/Yagovcb)
[![Linkedin](https://img.shields.io/badge/Yago_do_Valle_Castelo_Branco-0077b5?style=flat-square&logo=Linkedin&logoColor=white)](https://www.linkedin.com/in/yagovcb/)
[![Medium](https://img.shields.io/badge/@yagovcb-black?style=flat-square&logo=medium&logoColor=white)](https://medium.com/@yagovcb)


![](https://visitor-badge.glitch.me/badge?page_id=Yagovcb.Yagovcb)
