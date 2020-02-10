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
[
    {
        "lojista": "lojista-0",
        "quantidadeTotal": 135,
        "financeiro": 749.27,
        "precoMedio": 5.550148148148148148148148148148148,
        "produtos": [
            {
                "produto": "EMMS",
                "quantidade": 37,
                "preco": 3.75,
                "volume": 138.75
            },
            {
                "produto": "EMMS",
                "quantidade": 18,
                "preco": 5.39,
                "volume": 97.02
            },
            {
                "produto": "EMMS",
                "quantidade": 50,
                "preco": 5.8,
                "volume": 290
            },
            {
                "produto": "EMMS",
                "quantidade": 30,
                "preco": 7.45,
                "volume": 223.5
            }
        ]
    },
    {
        "lojista": "lojista-1",
        "quantidadeTotal": 135,
        "financeiro": 750.92,
        "precoMedio": 5.56237037037037037037037037037037,
        "produtos": [
            {
                "produto": "EMMS",
                "quantidade": 37,
                "preco": 3.75,
                "volume": 138.75
            },
            {
                "produto": "EMMS",
                "quantidade": 18,
                "preco": 5.39,
                "volume": 97.02
            },
            {
                "produto": "EMMS",
                "quantidade": 49,
                "preco": 5.8,
                "volume": 284.2
            },
            {
                "produto": "EMMS",
                "quantidade": 31,
                "preco": 7.45,
                "volume": 230.95
            }
        ]
    }
]
```
