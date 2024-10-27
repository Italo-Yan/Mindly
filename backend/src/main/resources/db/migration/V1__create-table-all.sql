
CREATE TABLE tb_profissional(
cpf_prof VARCHAR (20)  NOT NULL UNIQUE,
nome_prof VARCHAR(155) NOT NULL,
crp VARCHAR(10) NOT NULL UNIQUE,
email_prof VARCHAR(155) NOT NULL UNIQUE,
senha VARCHAR(255) NOT NULL,
descricao_prof VARCHAR(500),
especialidade VARCHAR(255),
endereco_prof VARCHAR(255),
telefone_prof VARCHAR(20),
PRIMARY KEY (cpf_prof)
);

CREATE TABLE tb_paciente (
	cpf_paciente VARCHAR(11) NOT NULL,
    nome_paciente VARCHAR(155) NOT NULL,
    email_paciente VARCHAR(155) NOT NULL UNIQUE,
	senha VARCHAR(255) NOT NULL,
	nascimento DATE NOT NULL,
	medicacao VARCHAR(255),
	endereco_paciente VARCHAR(255),
	telefone_paciente VARCHAR(20),
	PRIMARY KEY (cpf_paciente)
);

CREATE TABLE tb_agenda (
	id INT AUTO_INCREMENT PRIMARY KEY,
	cpf_prof VARCHAR(11) NOT NULL,
	dia_da_semana VARCHAR(15) NOT NULL,
	hora_inicio TIME NOT NULL,
	hora_fim TIME NOT NULL,
	duracao INT NOT NULL ,
	ativo TINYINT(1) DEFAULT 1,
	FOREIGN KEY (cpf_prof) REFERENCES tb_profissional(cpf_prof)
);

CREATE TABLE tb_agendamento (
	id INT AUTO_INCREMENT PRIMARY KEY,
	cpf_prof VARCHAR(11) NOT NULL,
	cpf_paciente VARCHAR(11) NOT NULL,
    data_agendamento DATE NOT NULL,
	hora_inicio TIME NOT NULL,
	duracao INT NOT NULL,
	link_video VARCHAR(255),
	lembrete_enviado TINYINT(1) DEFAULT 0,
	observacoes VARCHAR(500),
	status ENUM('PENDENTE', 'APROVADO', 'RECUSADO', 'CANCELADO') NOT NULL DEFAULT 'PENDENTE',
	FOREIGN KEY (cpf_prof) REFERENCES tb_profissional(cpf_prof),
	FOREIGN KEY (cpf_paciente) REFERENCES tb_paciente(cpf_paciente)
);

CREATE TABLE tb_sessao (
	id INT AUTO_INCREMENT PRIMARY KEY,
	cpf_prof VARCHAR(11) NOT NULL,
	cpf_paciente VARCHAR(11) NOT NULL,
    id_agendamento INT NOT NULL,
	data_sessao DATE NOT NULL,
    quantidade_total INT NOT NULL DEFAULT 0,
	FOREIGN KEY (cpf_prof) REFERENCES tb_profissional(cpf_prof),
	FOREIGN KEY (cpf_paciente) REFERENCES tb_paciente(cpf_paciente),
	FOREIGN KEY (id_agendamento) REFERENCES tb_agendamento(id)
);

CREATE
    ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
    VIEW `vw_profissional_publico` AS
SELECT
    `tb_profissional`.`cpf_prof` AS `cpf_prof`,
    `tb_profissional`.`nome_prof` AS `nome_prof`,
    `tb_profissional`.`email_prof` AS `email_prof`,
    `tb_profissional`.`especialidade` AS `especialidade`,
    `tb_profissional`.`descricao_prof` AS `descricao_prof`,
    `tb_profissional`.`telefone_prof` AS `telefone_prof`,
    `tb_profissional`.`crp` AS `crp`
FROM
    `tb_profissional`;


CREATE OR REPLACE VIEW `vw_agendamentos_profissional` AS
SELECT
    `tb_agendamento`.`cpf_paciente` AS `cpf_paciente`,
    `tb_paciente`.`nome_paciente` AS `nome_paciente`,
    `tb_paciente`.`email_paciente` AS `email_paciente`,
    `tb_paciente`.`telefone_paciente` AS `telefone_paciente`,
    `tb_agendamento`.`data_agendamento` AS `data_agendamento`,
    `tb_agendamento`.`hora_inicio` AS `hora_inicio`,
    `tb_agendamento`.`duracao` AS `duracao`,
    `tb_agendamento`.`link_video` AS `link_video`,
    `tb_agendamento`.`lembrete_enviado` AS `lembrete_enviado`,
    `tb_agendamento`.`observacoes` AS `observacoes`,
    `tb_agendamento`.`status` AS `status`
FROM
    `tb_agendamento`
        JOIN
    `tb_paciente` ON `tb_agendamento`.`cpf_paciente` = `tb_paciente`.`cpf_paciente`;


CREATE OR REPLACE VIEW `vw_perfil_paciente_profissional` AS
SELECT
    `cpf_paciente`,
    `nome_paciente`,
    `email_paciente`,
    `nascimento`,
    `medicacao`,
    `telefone_paciente`
FROM
    `tb_paciente`;


CREATE OR REPLACE VIEW `vw_agendamentos_profissional` AS
SELECT
    `tb_agendamento`.`id` AS `id_agendamento`,
    `tb_agendamento`.`cpf_prof` AS `cpf_prof`,
    `tb_paciente`.`cpf_paciente` AS `cpf_paciente`,
    `tb_paciente`.`nome_paciente` AS `nome_paciente`,
    `tb_agendamento`.`data_agendamento` AS `data_agendamento`,
    `tb_agendamento`.`hora_inicio` AS `hora_inicio`,
    `tb_agendamento`.`duracao` AS `duracao`,
    `tb_agendamento`.`status` AS `status`,
    `tb_agendamento`.`link_video` AS `link_video`
FROM
    `tb_agendamento`
        JOIN
    `tb_paciente` ON `tb_agendamento`.`cpf_paciente` = `tb_paciente`.`cpf_paciente`
WHERE
    `tb_agendamento`.`cpf_prof` = cpf_prof;


CREATE VIEW vw_agenda_profissional AS
SELECT
    p.cpf_prof,
    p.nome_prof,
    a.data_agendamento,
    a.hora_inicio,
    a.duracao,
    a.status,
    CASE
        WHEN a.status IS NULL THEN 'DISPONÍVEL'
        ELSE a.status
        END AS status_agendamento
FROM
    tb_profissional p
        LEFT JOIN
    tb_agendamento a ON p.cpf_prof = a.cpf_prof
WHERE
    a.data_agendamento >= CURDATE()  -- Exibir apenas agendamentos futuros
ORDER BY
    a.data_agendamento, a.hora_inicio;

