# Simian Checker API

Em um futuro distante, na cadeia de evolução, os símios e os humanos estão
cada vez mais próximos. Por esse motivo ficou muito difícil distinguir quem é
humano e quem é símio.

A **Simian Checker API** é uma API que consegue determinar se uma amostra de DNA pertence à um símio ou humano.

Ela tem como função principal, identificar as bases nitrogenadas da amostra de DNA, verificar as sequencias de base e assim classificar seu espécime de origem.

## Recursos

A API conta com duas URLs expostas:

- [https://simianchecker-api.azurewebsites.net/simian](https://simianchecker-api.azurewebsites.net/simian) para verificar uma sequencia de DNA;
- [https://simianchecker-api.azurewebsites.net/stats](https://simianchecker-api.azurewebsites.net/stats) para obter métricas de todas as sequencias já verificadas;

## Utilização
**POST /simian**:

O recurso **/simian** necessita de uma amostra de DNA para ser verificada, conforme o exemplo abaixo:

```javascript
{
  "dna": ["ATGCGA", "CAGTGC", "TTATGT", "AGAAGG", "CCCCTA", "TCACTG"]
}
```

Neste exemplo, a amostra de DNA apresentada possui a seguinte aparencia:
![Exemplo da matriz de DNA](https://iili.io/23rjh7.png)

Como podemos observar, existem certos padrões na amostra de DNA apresentada:
![Exemplo da matriz de DNA com padrões evidenciados](https://iili.io/23rwQ9.png)

Quando isso acontece, a API reconhece que o DNA é proveniente de um **símio**.

Nesse cenário, obtemos como resposta: ```HTTP STATUS 200```

Caso o DNA não possua tal padrão, a API classificará a amostra como proveniente de um **humano** e retornará: ```HTTP STATUS 403```
##
**GET /stats**:

O recurso **/stats** apresentará os resultados coletados pela API conforme o exemplo abaixo:

```javascript
{
  "count_mutant_dna": 40, "count_human_dna": 100, "ratio": 0.4
}
```

**count_mutant_dna**: representa a quantidade de DNAs de símios identificados pela API;

**count_human_dna**: representa a quantidade de DNAs de humanos identificados pela API;

**ratio**: representa a proporção de símios para humanos identificados;

