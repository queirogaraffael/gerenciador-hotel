
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

## Estrutura do Projeto

O projeto segue a arquitetura **MVC (Model-View-Controller)** para garantir organização e separação de responsabilidades:

- **Model:** Representado pelas classes **Quarto, Hóspede, Reserva e Funcionário**, que definem a estrutura dos dados e implementam as regras de negócio associadas.
- **View:** Composta pelas telas e menus de interação com o usuário, permitindo a navegação entre os módulos do sistema.
- **Controller:** Inclui classes como **MenuPrincipalController, QuartosController, HospedesController, ReservasController e FuncionariosController**, responsáveis por gerenciar as interações entre a View e o Model, além de executar as operações necessárias.

Além disso, o projeto faz uso do **padrão de projeto Factory** para a criação de instâncias dos controllers e serviços, garantindo a centralização da lógica de instância e promovendo a reutilização de objetos.

## Como Executar

Para executar o sistema, siga os passos abaixo:

1. Clone o repositório:

   ``` bash
   git clone https://github.com/queirogaraffael/gerenciador-hotel.git
   ```

2. Execute a classe Main

4. Siga as instruções no menu principal para gerenciar os módulos disponíveis.

## Dependências

O sistema requer as seguintes dependências para sua execução:

- **Java 17**: Versão da linguagem utilizada no desenvolvimento.
- **Maven**: Gerenciador de dependências para facilitar a configuração do projeto.
- **PostgreSQL**: Banco de dados relacional utilizado para persistência dos dados.
- **Hibernate Core**: Framework ORM usado para o mapeamento objeto-relacional.
- **Hibernate EntityManager**: Implementação da JPA (Java Persistence API) para gerenciar a persistência de dados.
- **Swing**: Biblioteca gráfica empregada na criação da interface do usuário.
- **Lombok**: Biblioteca para redução de código boilerplate, fornecendo anotações como `@Getter`, `@Setter` e `@AllArgsConstructor`.

## Pré-Requisitos
* Java Development Kit (JDK) instalado na máquina.
* Banco de dados PostgreSQL configurado e acessível.
* IDE compatível com projetos Java para compilação e execução do código.

## Como Executar
* Clone o repositório para sua máquina local.
* Abra o projeto em sua IDE Java.
* Certifique-se de ter configurado corretamente o banco de dados PostgreSQL e as credenciais de acesso no arquivo persistence.xml.
* Compile e execute o projeto a partir da classe Main.

## Contribuições

Contribuições são bem-vindas! Se você quiser contribuir com o projeto.

## Licença

Este projeto está licenciado sob a licença MIT. Para mais informações, consulte o arquivo [LICENSE](https://github.com/queirogaraffael/gerenciador-hotel/blob/main/LICENSE).
