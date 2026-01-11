# Aplicação de Monitorização de Voos

**Disciplina:** Computação Móvel

**Curso:** Engenharia Informática

**Ano:** 2025/2026

**Aluno:** Rafael Oliveira – a042611

---

## O que é a aplicação

É uma aplicação que permite consultar informações sobre voos através da **API AviationStack**, permitindo a visualização de voos, pesquisa por aeroportos e consulta de detalhes de cada voo.

---

## Como funciona

A aplicação está organizada em **3 ecrãs principais**:

### Voos ao Vivo

Lista paginada de voos obtidos através da API.

### Pesquisa

Para encontrar voos, é possível fazer uma pesquisa utilizando o código **IATA** do aeroporto. Por exemplo, se introduzir OPO ou LIS, que são os códigos para os aeroportos do Porto e de Lisboa, são apresentados voos que partem ou chegam a esses aeroportos. Esta funcionalidade permite filtrar e consultar voos de forma simples e organizada. 

### Detalhes do Voo

Este ecrã apresenta a informação detalhada do voo selecionado. São disponibilizados dados como o número do voo, a companhia aérea, o estado atual do voo, os horários de partida e chegada, bem como os aeroportos de origem e destino.

Esta funcionalidade permite ao utilizador consultar, de forma clara e organizada, todas as informações relevantes.

---

## Credenciais e API

- **API utilizada:** AviationStack

- **API Key:** incluída no código (plano gratuito)

A aplicação não tem um sistema de login para os utilizadores, por isso a autenticação de utilizadores não se aplica a este caso.

---
## Limitações e Problemas Conhecidos

- A API gratuita apenas fornece dados históricos (não totalmente em tempo real tem um atraso de 30 a 60 segundos);

- Limite de 100 pedidos por mês;

- Ausência de pesquisa de ida e volta;

- Impossibilidade de escolher datas e horários futuros, ao contrário de plataformas como Logitravel;

- Falta de funcionalidades avançadas como favoritos, uma vez que a API não suporta ida e volta.

Essas limitações acontecem porque o plano gratuito da API tem restrições.

---

## Ambiente de Desenvolvimento

- **Android Studio:** Otter 2 2025.2.2  
- **Kotlin:** 2.2.21  
- **kspVersion:** 2.2.21-2.0.4
