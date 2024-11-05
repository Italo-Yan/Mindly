
CREATE TABLE tb_profissional(
    cpf_prof VARCHAR (20)  NOT NULL UNIQUE,
    nome_prof VARCHAR(155) NOT NULL,
    crp VARCHAR(10) NOT NULL UNIQUE,
    email_prof VARCHAR(155) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    descricao_prof VARCHAR(500),
    abordagem_teorica VARCHAR(255),
    endereco_prof VARCHAR(255),
    telefone_prof VARCHAR(20),
    role ENUM('PROFISSIONAL') NOT NULL DEFAULT 'PROFISSIONAL',
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
    role ENUM('PACIENTE') NOT NULL DEFAULT 'PACIENTE',
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
    `tb_profissional`.`nome_prof` AS `nome_prof`,
    `tb_profissional`.`abordagem_teorica` AS `abordagem_teorica`,
    `tb_profissional`.`descricao_prof` AS `descricao_prof`,
    `tb_profissional`.`crp` AS `crp`
FROM
    `tb_profissional`;

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
    `tb_agendamento`.`status` AS `status`,
    `tb_sessao`.`quantidade_total` AS `quantidade_total`
FROM
    `tb_agendamento`
        JOIN
    `tb_paciente` ON `tb_agendamento`.`cpf_paciente` = `tb_paciente`.`cpf_paciente`
        JOIN
    `tb_sessao` ON `tb_agendamento`.`cpf_paciente` = `tb_sessao`.`cpf_paciente`;



CREATE
    ALGORITHM = UNDEFINED
    DEFINER = `root`@`localhost`
    SQL SECURITY DEFINER
    VIEW `vw_profissionais_cadastrados` AS
SELECT
    cpf_prof AS cpf_prof,
    nome_prof AS nome_prof,
    abordagem_teorica AS abordagem_teorica,
    descricao_prof AS descricao_prof,
    telefone_prof AS telefone_prof,
    email_prof AS email_prof,
    crp AS crp
FROM
    tb_profissional;



DELIMITER //
CREATE TRIGGER trg_link_update
    BEFORE UPDATE ON `tb_agendamento`
    FOR EACH ROW
BEGIN
    IF NEW.link_video IS NOT NULL AND OLD.link_video IS NULL THEN
        IF OLD.status <> 'APROVADO' THEN
            SIGNAL SQLSTATE '45000'
                SET MESSAGE_TEXT = 'O link de chamada de vídeo só pode ser adicionado para agendamentos aprovados.';
        END IF;
    END IF;
END //
DELIMITER ;

DELIMITER //

CREATE PROCEDURE create_roles_if_not_exists()
BEGIN
    DECLARE profissional_exists INT DEFAULT 0;
    DECLARE admin_exists INT DEFAULT 0;
    DECLARE paciente_exists INT DEFAULT 0;

    SELECT COUNT(*) INTO profissional_exists FROM mysql.user WHERE user = 'Profissional' AND host = '%';
    IF profissional_exists = 0 THEN
        CREATE ROLE 'Profissional';
    END IF;

    SELECT COUNT(*) INTO admin_exists FROM mysql.user WHERE user = 'Admin' AND host = '%';
    IF admin_exists = 0 THEN
        CREATE ROLE 'Admin';
    END IF;

    SELECT COUNT(*) INTO paciente_exists FROM mysql.user WHERE user = 'Paciente' AND host = '%';
    IF paciente_exists = 0 THEN
        CREATE ROLE 'Paciente';
    END IF;
END //

DELIMITER ;

CALL create_roles_if_not_exists();


GRANT SELECT ON tb_profissional TO Profissional;
GRANT SELECT ON tb_paciente TO Profissional;
GRANT SELECT, INSERT, UPDATE, DELETE ON tb_agenda TO Profissional;
GRANT SELECT, INSERT, UPDATE, DELETE ON tb_agendamento TO Profissional;
GRANT SELECT ON tb_sessao TO Profissional;

GRANT ALL PRIVILEGES ON tb_profissional TO Admin;
GRANT ALL PRIVILEGES ON tb_paciente TO Admin;
GRANT ALL PRIVILEGES ON tb_agenda TO Admin;
GRANT ALL PRIVILEGES ON tb_agendamento TO Admin;
GRANT ALL PRIVILEGES ON tb_sessao TO Admin;

GRANT SELECT ON vw_profissional_publico TO Paciente;
GRANT SELECT ON tb_agenda TO Paciente;
GRANT INSERT ON tb_agendamento TO Paciente;
GRANT SELECT ON tb_sessao TO Paciente;
