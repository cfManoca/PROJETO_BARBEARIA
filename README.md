# Barbearia Beco 7

Projeto Final da disciplina de Programação Web — IFBA.

## Integrantes

- Paulo Victor
- Emanoel Messias
- Erik Amorim

## Sobre o projeto

Sistema web para gestão de uma barbearia, permitindo o cadastro de clientes e barbeiros, além do agendamento de horários de atendimento.

**Stack:** Spring Boot + Thymeleaf (back-end e views), HTML/CSS/JS (front-end), H2 (banco de dados).

## Entidades

- **Cliente** — nome, telefone, e-mail (único)
- **Barbeiro** — nome, telefone, especialidade
- **Agendamento** — cliente, barbeiro, serviço, data, horário e status (Agendado, Cancelado)

Todas com CRUD completo (criar, listar, editar, excluir).

## Regras de negócio

- Não é permitido cadastrar dois clientes com o mesmo e-mail
- Não é permitido agendar dois horários para o mesmo barbeiro no mesmo dia e horário
- Cancelar um agendamento não remove o registro do banco — apenas altera o status para "Cancelado", preservando o histórico e liberando o horário para novo agendamento
- Não é possível excluir um cliente ou barbeiro que possua agendamentos ativos vinculados
