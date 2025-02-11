## Sistema de Gerenciamento de Clínica ✨

Este projeto é um sistema de gerenciamento para clínicas médicas, permitindo que tanto pacientes quanto funcionários (secretários, médicos, etc.) possam interagir com a plataforma para registrar informações e realizar operações, como agendamento de consultas, prescrição de exames, cadastro de médicos e pacientes, entre outras funcionalidades.

## Funcionalidades

**Para o Cliente (Paciente)**

- Exibir Notificações: O paciente pode visualizar notificações relacionadas a consultas agendadas ou exames com validade próxima.
- Consultas: O paciente pode ser notificado sobre consultas agendadas com médicos, incluindo lembretes antes da consulta.
- Exames: O paciente também pode ser notificado sobre exames que devem ser realizados dentro de um prazo específico.

**Para o Funcionário (Secretário ou Administrador)**

- Cadastrar Pacientes e Médicos: O funcionário pode cadastrar novos pacientes e médicos no sistema.
- Agendamento de Consultas: O funcionário pode agendar consultas entre pacientes e médicos, garantindo que o horário e a data sejam válidos.
- Prescrição de Exames, Medicamentos e Tratamentos: O funcionário pode prescrever exames, medicamentos e tratamentos para os pacientes durante as consultas.
- Tipos de Exames os quais são ENUM: SANGUE, RAIO_X, ULTRASSOM.   
- Atualização de Banco de Dados: O funcionário pode atualizar, excluir ou buscar dados no banco de dados da clínica.
- Verificação de Pagamento: Antes de agendar uma consulta, o funcionário verifica se o paciente tem algum pagamento pendente.

## Tecnologias Utilizadas

- Java: Linguagem de programação principal para o desenvolvimento do sistema.
- JDK 17 ou superior: Requer a instalação do JDK para compilar e executar o código.
- Estruturas de Dados: O sistema utiliza listas para armazenar os dados de pacientes, médicos, consultas, etc.


## Estrutura do Projeto

O sistema está dividido nas seguintes camadas:

- entities: Contém as classes de entidades do sistema, como Paciente, Medico, Consulta, Exame, Tratamento, Medicamento, etc.
- services: Contém as classes de serviços responsáveis por operações como agendamento de consultas, prescrição de tratamentos, etc.
- menu: Contém as interfaces de menu que controlam a interação com o usuário.
- controller: Contém a classe principal do sistema (SistemaGerenciamentoClinica), que gerencia a lógica de negócio.

## Cliente (Paciente)

Escolha a opção Entrar como Cliente (Paciente) no menu inicial.
Selecione o seu nome da lista de pacientes cadastrados.
Visualize as notificações relacionadas a consultas e exames.

## Funcionário

Escolha a opção Entrar como Funcionário no menu inicial.
Utilize as opções para cadastrar pacientes e médicos, agendar consultas e prescrever tratamentos e exames.
Você pode também realizar a atualização e exclusão de dados no banco.

## Exceções Tratadas

O sistema trata várias exceções para garantir a integridade dos dados:

- Paciente já cadastrado: Quando tenta-se cadastrar um paciente que já existe.
- Médico já cadastrado: Quando tenta-se cadastrar um médico que já existe.
- Horário indisponível: Se um médico ou paciente não estiver disponível no horário escolhido.
- Pagamento pendente: Se o paciente ainda não efetuou o pagamento, o agendamento é bloqueado.
- Consulta fora do horário permitido: Impede o agendamento de consultas fora do horário comercial.

## Contribuições

- Faça um fork do repositório.
- Crie uma nova branch para sua feature (git checkout -b feature/MinhaNovaFeature).
- Faça as mudanças necessárias.
- Envie um pull request.

## Como baixar

**Como Executar**

1. Clone o repositório:

git clone https://github.com/Thales-Duarte/Trabalho_OO

2. Entre no diretório do projeto:

cd sistema-gerenciamento-clinica

3. Compile o projeto com o JDK 17 ou superior:


javac -d bin src/**/*.java

4. Execute o sistema:

java -cp bin main.Main

