# SafeSoft

SafeSoft é um sistema de gestão de manutenção de hardware projetado para facilitar a comunicação e o acompanhamento de reparos entre gestores, técnicos e clientes. O objetivo é garantir um fluxo de trabalho eficiente e organizado, com informações claras sobre o status das manutenções e o histórico de atendimento.

## Funcionalidades

### Para Gestores
- **Gestão de Técnicos**: Cadastrar, editar e remover técnicos.
- **Atribuição de Manutenções**: Alocar manutenções de hardware aos técnicos disponíveis.
- **Acompanhamento de Progresso**: Visualizar o status de cada manutenção e seu histórico.
- **Relatórios**: Gerar relatórios sobre o desempenho dos técnicos e o andamento das manutenções.

### Para Técnicos
- **Manutenções Atribuídas**: Visualizar e atualizar o status das manutenções que foram atribuídas.
- **Registro de Atividades**: Registrar detalhes sobre o que foi feito em cada manutenção.
- **Histórico de Atendimentos**: Acessar o histórico completo das manutenções realizadas.

### Para Clientes
- **Solicitação de Manutenção**: Solicitar manutenções de hardware, informando os detalhes do problema.
- **Acompanhamento de Manutenções**: Acompanhar o status das manutenções solicitadas.
- **Feedback**: Avaliar os serviços prestados após a conclusão do atendimento.

## Tecnologias Utilizadas

- **Backend**: Java
- **Frontend**: Java
- **Banco de Dados**: MySQL com JPA (Hibernate)
- **Infraestrutura**: AWS (Amazon Web Services) para hospedagem
- **Controle de Versão**: Git

## Pré-requisitos

- Java 21 ou superior
- MySQL

## Como Iniciar
    - Inicie SafeSoft.java


1. **Banco de Dados na AWS RDS**:
    - Crie uma instância MySQL no AWS RDS e configure o endpoint.
    - Atualize a URL de conexão no arquivo `application.properties` para apontar para o endpoint da instância RDS.

## Contribuições

Contribuições são bem-vindas! Se você tem sugestões ou melhorias, sinta-se à vontade para abrir um pull request ou criar um issue no repositório.

