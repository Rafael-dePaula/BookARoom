package com.example.bookaroom;

import com.example.bookaroom.dados.DataSource;
import com.example.bookaroom.views.terminal.Terminal;

public class Main {
    public static void main(String[] args) {
        DataSource.resetDados();
        Terminal.run();
    }
}

/*
1. O sistema deve apresentar um console para possibilitar a iteração do usuário com o código
desenvolvido na AA1. Além disso, a solução apresentada deve facilitar a substituição do console de
iteração por outro mais avançado;

2. O código do projeto deve manter um acoplamento baixo em relação ao domínio e demais classes de
projeto, assim, a classe de visão do usuário deve ter acesso somente a uma interface simples e
padronizada dos serviços que o sistema pode ofertar;

3. A inicialização do sistema deve garantir que todos os objetos necessários estejam disponíveis, sendo
que essa responsabilidade deve ser de uma fábrica ou injetada no sistema;

4. O sistema deve garantir que os dados sobre as salas livres e ocupadas possuam apenas uma
instância, mesmo que mais de um usuário do mesmo campus esteja utilizando o sistema;

5. O sistema deve permitir que equipamentos sejam cadastrados, sendo possível equipamentos de
áudio e vídeo, notebook, apontadores a laser e passadores de slides, controles de ar condicionado,
caixas de som, impressoras 3D e mesas.

Os equipamentos passam a ter característica de acordo com o seu tipo,
ampliando a descrição inicial do domínio de ter somente um nome e patrimônio.
Agora, equipamentos com patrimônio são a instância cadastrada de um equipamento cadastrado,
podendo existir mais de um equipamento com patrimônio para um mesmo tipo de equipamento cadastrado.
Ademais, ainda não há certeza de todos os tipos de equipamentos e materiais, assim, é necessário
que a solução permita que novos equipamentos sejam adicionados de maneira simples no projeto.

6. O sistema deve permitir que os horários de aula sejam adicionados no sistema, reservando salas por
um determinado período e dias da semana num mesmo semestre. Assim, o projeto deve considerar
que as reservas ocorrem num determinado semestre de um ano. As reservas de aula são por todo
semestre. Por fim, é necessário que o sistema lide com as questões da regra de negócio apresentada
*/