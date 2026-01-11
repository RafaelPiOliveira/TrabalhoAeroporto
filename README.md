Aplicação de Monitorização de Voos

Disciplina: Computação Móvel

Curso: Engenharia Informática

Ano: 2025/2026

Aluno: Rafael Oliveira-a042611

O que é a aplicação

Essa é uma aplicação para o sistema operacional Android, desenvolvida utilizando a linguagem de programação Kotlin e a ferramenta Jetpack Compose. Ela nos permite consultar informações sobre voos por meio da API AviationStack.

A app permite visualizar voos, pesquisar por aeroportos e consultar detalhes completos de cada voo, utilizando uma interface moderna e simples.

Como funciona

A aplicação está organizada em 3 ecrãs principais:

Voos ao Vivo

Lista paginada de voos obtidos através da API.

Pesquisa

Agora você pode procurar voos de uma maneira mais fácil. Basta digitar o código IATA do aeroporto, como OPO ou LIS, e escolher se quer ver os voos que partem ou os que chegam. Isso facilita muito a busca pelo voo que você precisa.

Aqui estão os detalhes do voo. É importante verificar todos os detalhes do voo para garantir que você tenha todas as informações necessárias antes de viajar. Os detalhes do voo incluem o número do voo, o horário de partida e o horário de chegada, o aeroporto de partida e o aeroporto de destino, bem como outros detalhes importantes sobre o voo. Verificar os detalhes do voo com antecedência pode ajudar a evitar problemas ou surpresas desagradáveis durante a viagem. Os detalhes do voo são fundamentais para uma viagem tranquila e bem planejada.

Aqui está a informação detalhada do voo que você selecionou. Isso inclui a companhia aérea, o estado atual do voo, os horários de partida e chegada, e os aeroportos de origem e destino. Você pode encontrar todas essas informações reunidas em um lugar para facilitar a sua consulta.

A aplicação utiliza a arquitetura MVVM, com comunicação à API através de Retrofit e carregamento eficiente de listas com Paging 3.

Credenciais e API

API utilizada: AviationStack

API Key: incluída no código (plano gratuito)

A autenticação de utilizadores não se aplica a este caso, pois a aplicação não possui um sistema de login para os utilizadores.

Limitações e Problemas Conhecidos

A API gratuita apenas fornece dados históricos (não totalmente em tempo real)

Limite de 100 pedidos por mês

Não existe cache offline

Não foram implementados testes unitários. Isso significa que o código não foi verificado por meio de testes específicos para cada unidade dele. Esses testes são importantes porque ajudam a garantir que cada parte do código funciona corretamente. Sem esses testes, é mais difícil saber se o código está funcionando como deveria.

Essas limitações ocorrem devido às restrições do plano gratuito da API e ao escopo do projeto académico.

Como executar

Abrir o projeto no Android Studio

Aguardar sincronização do Gradle

Executar a aplicação num emulador ou dispositivo físico

