
INSERT INTO tb_profissional (cpf_prof, nome_prof, crp, email_prof, senha, descricao_prof, abordagem_teorica, endereco_prof, telefone_prof)
VALUES ('12345678900', 'Eugenia Campos Barros', '123456', 'eugenia.campos@example.com', '$2y$10$EXAMPLE_HASHED_PASSWORD', 'Psicóloga com 10 anos de experiência.', 'Psicologia Clínica', 'Rua das Flores, 123, São Paulo, SP', '(11) 91234-5678');

INSERT INTO tb_paciente (cpf_paciente, nome_paciente, email_paciente, senha, nascimento, medicacao, endereco_paciente, telefone_paciente)
VALUES ('98765432100', 'Maria Ferreira Silva', 'maria.ferreira@example.com', '$2y$10$EXAMPLE_HASHED_PASSWORD', '1988-10-14', 'Ansiolítico Clonazepam', 'Rua das Acácias, 456, Rio de Janeiro, RJ', '(21) 99876-5432');

INSERT INTO tb_agenda (cpf_prof, dia_da_semana, hora_inicio, hora_fim, duracao, ativo)
VALUES ('12345678900', 'Segunda-Feira', '09:00:00', '12:00:00', 50, 1);

INSERT INTO tb_agenda (cpf_prof, dia_da_semana, hora_inicio, hora_fim, duracao, ativo)
VALUES ('12345678900', 'Terça-Feira', '09:00:00', '12:00:00', 50, 1);

INSERT INTO tb_agenda (cpf_prof, dia_da_semana, hora_inicio, hora_fim, duracao, ativo)
VALUES ('12345678900', 'Quarta-Feira', '15:00:00', '19:00:00', 50, 1);

INSERT INTO tb_agendamento (cpf_prof, cpf_paciente, data_agendamento, hora_inicio, duracao, link_video, lembrete_enviado, observacoes, status)
VALUES ('12345678900', '98765432100', '2024-10-16', '16:00:00', 50, NULL, 0, 'Sessão agendada conforme horário disponível', 'PENDENTE');

INSERT INTO tb_sessao (cpf_prof, cpf_paciente, id_agendamento, data_sessao, quantidade_total)
VALUES ('12345678900', '98765432100', 1, '2024-10-16', 0);