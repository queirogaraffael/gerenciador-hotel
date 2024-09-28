
# Gerenciador de Hotel

Este é um sistema simples para gerenciamento de um hotel, permitindo a administração de **quartos**, **hóspedes**, **reservas**, **funcionários**, além de controlar o **check-in** e **check-out**.

## Índice
- [Visão Geral](#visão-geral)
- [Funcionalidades](#funcionalidades)
- [Como Executar](#como-executar)
- [Dependências](#dependências)
- [Contribuições](#contribuições)
- [Licença](#licença)

## Visão Geral

O sistema é dividido em diferentes módulos para facilitar o gerenciamento. A seguir estão as telas principais do sistema:

### Menu Principal
![Menu Principal](https://raw.githubusercontent.com/queirogaraffael/gerenciador-hotel/main/imagens/menuPrincipal.png)

No menu principal, você pode escolher entre gerenciar:
- **Quartos**
- **Hóspedes**
- **Reservas**
- **Funcionários**
- **Check-in/Check-out**
- Além de poder encerrar o programa.

### Gerenciador de Quartos
![Gerenciador de Quartos](https://raw.githubusercontent.com/queirogaraffael/gerenciador-hotel/main/imagens/gerenciadorQuartos.png)

Este módulo permite:
- **Cadastrar** um novo quarto.
- **Ver por Tipo**: listar quartos de acordo com o tipo (solteiro, casal, suíte).
- **Ver Disponibilidade por Data**: verificar a disponibilidade de quartos em uma data específica.
- **Atualizar Dados** de um quarto.
- Colocar quartos em **Manutenção**.
- Voltar ao menu principal.

### Gerenciador de Hóspedes
![Gerenciador de Hóspedes](https://raw.githubusercontent.com/queirogaraffael/gerenciador-hotel/main/imagens/gerenciadorHospedes.png)

Este módulo oferece opções para:
- **Cadastrar** um novo hóspede.
- **Ver** a lista de hóspedes.
- **Ver Reservas** de um hóspede.
- **Ver Histórico** de hospedagem.
- **Atualizar** os dados do hóspede.

### Gerenciador de Reservas
![Gerenciador de Reservas](https://raw.githubusercontent.com/queirogaraffael/gerenciador-hotel/main/imagens/gerenciadorReservas.png)

Aqui você pode:
- **Criar uma nova reserva**.
- **Cancelar uma reserva**.
- Voltar ao menu principal.

### Gerenciador de Funcionários
![Gerenciador de Funcionários](https://raw.githubusercontent.com/queirogaraffael/gerenciador-hotel/main/imagens/gerenciadorFuncionarios.png)

Permite gerenciar os funcionários com as opções:
- **Cadastrar** um novo funcionário.
- **Editar** os dados de um funcionário existente.
- **Buscar por CPF** ou **Buscar por Nome**.
- Ver **Extratos** de um funcionário.

### Check-in/Check-out
![Check-in/Check-out](https://raw.githubusercontent.com/queirogaraffael/gerenciador-hotel/main/imagens/gerenciadorCheckInOut.png)

Este módulo permite realizar o:
- **Check-in** de hóspedes.
- **Check-out** de hóspedes.

## Funcionalidades

O sistema oferece as seguintes funcionalidades:

1. Gerenciamento de Quartos: Cadastro, listagem, verificação de disponibilidade e manutenção.
2. Gerenciamento de Hóspedes: Cadastro, visualização, atualização e consulta de histórico.
3. Gerenciamento de Reservas: Criação, cancelamento e consulta.
4. Gerenciamento de Funcionários: Cadastro, edição e busca.
5. Check-in/Check-out: Processos de check-in e check-out.

## Como Executar

Para executar o sistema, siga os passos abaixo:

1. Clone o repositório:

   ``` bash
   git clone https://github.com/queirogaraffael/gerenciador-hotel.git
   ```

2. Execute a classe Main

4. Siga as instruções no menu principal para gerenciar os módulos disponíveis.

## Dependências

O sistema requer as seguintes dependências para execução:

- **Java 17**.
- **Maven** para gerenciamento de dependências.
- **PostgreSQL** como banco de dados relacional.
- **Hibernate Core**.
- **Hibernate EntityManager**.
- **Lombok**.


## Contribuições

Contribuições são bem-vindas! Se você quiser contribuir com o projeto.

## Licença

Este projeto está licenciado sob a licença MIT. Para mais informações, consulte o arquivo [LICENSE](https://github.com/queirogaraffael/gerenciador-hotel/blob/main/LICENSE).
