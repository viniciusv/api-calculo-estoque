# Desafio

Criar uma api que faça o calculo de distrubuição de produtos pela quantidade de lojistas e retorne um Json estruturado.

# Componentes 

* [Spring Boot](https://spring.io/projects/spring-boot) - Para criar uma api REST.

# Let's go
### Clone o projeto:

```
git clone https://github.com/viniciusv/api-calculo-estoque.git
```

### Build com Maven:
```
mvn clean package
```

Após o build vá para pasta target.
```
calculo-api
|-- target/
|   |-- file_name.jar/
```
Execute o seguinte comando para executar:
```
java -jar target/file_name.jar
```

### API:
Acessar a url:
```
http://localhost:8080/estoque/calculo
```
* Para realizar esses testes na api aconselho usar o [Postman](https://www.getpostman.com/).

Há dois endpoints:

```http
GET /estoque/calculo/?produto=EMMS&lojistas=2
```

OU

```http
GET /estoque/calculo/{produto}/{lojistas} - /estoque/calculo/EMMS/3
```

| Parametro | Tipo | Descrição |
| :--- | :--- | :--- |
| `produto` | `string` | **Required**. Nome do produto que será pesquisado. |
| `lojistas` | `number` | **Required**. Quantidade de lojistas que será usado no calculo. |

Exemplo Json:
```

```
