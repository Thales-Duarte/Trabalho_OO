## Getting Started

Projeto java de programacao orientada a objetos.
Versão 1.0

Resolver controler e funções de sistema par funcionario

Ainda necessário rever o enunciado do trabalho e melhora-lo.

Implementações: 

registro de históricos médicos


Além disso, deve garantir regras de negócio como:

Limite de consultas por paciente/médico por dia.


Notificações para exames agendados ou consultas próximas.

Notificações para exames agendados ou consultas próximas.
modularidade, encapsulamento, herança, polimorfismo e tratamento de exceções personalizadas.
Paciente - - Histórico médico (lista de objetos Consulta e Exame).

1.1.2 Funcionalidades:
- CRUD completo de pacientes
- Adicionar uma nova consulta ao histórico.
- Bloquear cadastro se CPF já estiver registrado.
[15:32, 28/01/2025] Thales Duarte: Médico - - Histórico médico (lista de objetos Consulta e Exame).

1.2.2 Funcionalidades:
- CRUD completo de Médicos.
- Adicionar uma nova consulta ao histórico.
- Bloquear cadastro se CPF já estiver registrado.


Agendamento de Consultas

2.1 O sistema deverá implementar um CRUD completo para consultas.
2.2 O sistema deve permitir agendar consultas apenas se:

O médico estiver disponível no horário.
O paciente não tiver outra consulta no mesmo dia.
O médico tiver a especialidade requerida.

2.3 Para cada consulta médica deverão ser informados, obrigatoriamente:

Data da consulta (ex: 26-01-2025).
Horário de início (ex: 14:30).
Duração em minutos (padrão: 30 minutos).
Status consulta (Ex.: AGENDADA, CANCELADA, REALIZADA)
Paciente associado à consulta.
Médico responsável pela consulta.
Lista de exames prescritos durante a consulta.
Lista de medicamentos prescritos.
Valor da consulta (ex: R$ 150,00).

Médicos podem prescrever exames (ex: sangue, raio-X) ou medicamentos, associando-os à consulta.

No caso de Exame, os atributos obrigatórios são:
- tipo (ex: SANGUE, RAIO_X, ULTRASSOM).
- dataPrescricao: data em que foi prescrito.
- dataRealizacao: data em que foi realizado.
- resultado: detalhes do resultado, ex: "Colesterol total: 180 mg/dL".
- custo: valor base do exame.

No caso de Prescrições (medicamentos, tratamentos, exames), os atributos obrigatórios são:
- consultaAssociada: associação com a Consulta que gerou a prescrição.
- examesPrescritos: lista de exames a serem realizados.
- medicamentos: lista de medicamentos prescritos.
- dataValidade: prazo para realização dos exames.

Gestão de Pagamentos
4.1 Cada consulta/exame tem um valor associado.
4.2 Pacientes com pagamentos pendentes não podem agendar novas consultas.

Tratamento de Exceções

Exceções deverão ser utilizadas para lidar com as seguintes situações:

Agendamento em horário indisponível.
Paciente com pagamento pendente.
Médico não encontrado para uma especialidade.

Tratamento de Exceções

Exceções deverão ser utilizadas para lidar com as seguintes situações:

Agendamento em horário indisponível.
Paciente com pagamento pendente.
Médico não encontrado para uma especialidade.

Classes e Objetos / Atributos e Métodos / Associações entre Objetos
Defina classes como Paciente, Medico, Consulta, Exame, Pagamento, Notificacao, e outras que se fizerem necessárias.
Realize as associações entre as classes de modo a considerar o contexto da aplicação. Defina, para cada associação, seu nome e suas multiplicidades.
Apresente, através de um diagrama de Classes UML, as classes, seus atributos e métodos, suas associações e multiplicidades, seus pacotes.
Explore, o quanto for possível, relações de herança entre as classes que compõem seu projeto.