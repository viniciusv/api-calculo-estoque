# Desafio

Criar uma api que faça o calculo de distrubuição de um produto pela quantidade de lojistas e retorne um Json estruturado.

# Business Rules 
Podemos ver que temos em estoque 270 quantidades do produto EMMS. Podemos ver também que este total de 270 unidades de EMMS
foram adquiridas em 4 transações diferentes, cada transação tem sua quantidade e seu preço.

| File | Produto | Quantidade | Preço | Volume
| :--- | :--- | :--- | :--- | :--- |
| data_1.json | EMMS | 74 | 3.75 | 277.50 |
| data_1.json | EMMS | 36 | 5.39 | 194.04 |
| data_1.json | EMMS | 99 | 5.80 | 574.20 |
| data_1.json | EMMS | 61 | 7.45 | 454.45 |

O valor financeiro total do nosso estoque é R$ 1500,19 e o preço médio que gastamos para adquirir este estoque (270 unidades) foi de $5,56 por unidade.

| Somatório da Quantidade | Somatório do Volume | Média de Preço |
| :--- | :--- | :--- | 
| 270 | 1500.19 | 5.56 |

O próximo passo é vender este estoque para lojistas, com o preço mais justo possível entre eles. :thumbsup:

Considerando 2 lojistas, o resultado final seria:

*  Lojista 1 

Quantidade = 135 | Financeiro = 750.92 | Preço Médio = 5.5624

| Produto | Quantidade | Preço | Volume |
| :--- | :--- | :--- | :--- |
| EMMS | 37 | 3.75 | 138.75 |
| EMMS | 18 | 5.39 | 97.02 |
| EMMS | 49 | 5.80 | 284.20 |
| EMMS | 31 | 7.45 | 230.95 |


*  Lojista 2

Quantidade = 135 | Financeiro = 749.27 | Preço Médio = 5.5501

| Produto | Quantidade | Preço | Volume |
| :--- | :--- | :--- | :--- |
| EMMS | 37 | 3.75 | 138.75 |
| EMMS | 18 | 5.39 | 97.02 |
| EMMS | 50 | 5.80 | 290 |
| EMMS | 30 | 7.45 | 223.50 |

### Note que
* Ambos os lojistas receberam 135 unidades. :thumbsup:
* O preço que cada um pagou ficou próximo do preço médio do estoque. Em alguns casos
não será possível cravar o preço médio, porém, deve ser a distribuição mais justa possível.  :triumph:
* A soma dos financeiros e a soma das quantidades batem com o quadro anterior que exibe
os totais do arquivo data_1.json.  :sunglasses:

### O que precisamos fazer
Criar um endpoint que faz o cálculo acima, recebendo como parâmetros:
* O **produto** que será distribuído.
* A **quantidade de lojas** que iremos vender nosso estoque.


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
