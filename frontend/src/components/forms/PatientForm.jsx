import { useState } from "react";
import PropTypes from "prop-types";
import * as zod from "zod";
import styles from "./Form.module.css";
import {addPacient} from "../../Services/paciente/pacienteService"

const cadastroSchema = zod.object({
  nome_paciente: zod
    .string()
    .min(15, { message: "Deve conter no mínimo 15 caracteres." })
    .max(100, { message: "Deve conter no máximo 100 caracteres." }),
  cpf_paciente: zod
    .string()
    .length(11, { message: "O CPF deve conter 11 dígitos." })
    .regex(/^\d+$/, { message: "O CPF deve conter apenas números." }),
  endereco_paciente: zod
    .string()
    .min(1, { message: "Endereço é obrigatório." }),
  telefone_paciente: zod
    .string()
    .min(10, { message: "O telefone deve conter no mínimo 10 dígitos." })
    .max(11, { message: "O telefone deve conter no máximo 11 dígitos." })
    .regex(/^\d+$/, { message: "O telefone deve conter apenas números." }),
  email_paciente: zod
    .string()
    .email({ message: "Formato de e-mail inválido." }),
  senha_paciente: zod
    .string()
    .min(8, { message: "A senha deve ter pelo menos 8 caracteres." }),
  nascimento_paciente: zod
    .string()
    .regex(/^\d{4}-\d{2}-\d{2}$/, {
      message: "Data de nascimento deve estar no formato yyyy-mm-dd.",
    })
    .refine((date) => new Date(date) <= new Date(), {
      message: "A data de nascimento deve ser no passado.",
    }),
});

const InputField = ({
  label,
  type,
  name,
  value,
  placeholder,
  onChange,
  error,
}) => (
  <div>
    <label htmlFor={name}>{label}</label>
    <input
      className={styles.input}
      type={type}
      id={name}
      name={name}
      value={value}
      placeholder={placeholder}
      onChange={onChange}
    />
    {error && <p className={styles.errorText}>{error}</p>}
  </div>
);

InputField.propTypes = {
  label: PropTypes.string.isRequired,
  type: PropTypes.string,
  name: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired,
  placeholder: PropTypes.string,
  onChange: PropTypes.func.isRequired,
  error: PropTypes.string,
};

InputField.defaultProps = {
  type: "text",
  placeholder: "",
  error: null,
};

export const Patient = () => {
  const [formData, setFormData] = useState({
    nome_paciente: "",
    cpf_paciente: "",
    nascimento_paciente: "",
    endereco_paciente: "",
    telefone_paciente: "",
    email_paciente: "",
    senha_paciente: "",
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prevData) => ({
      ...prevData,
      [name]: value,
    }));
  };

  const resetForm = () => {
    setFormData({
      nome_paciente: "",
      cpf_paciente: "",
      nascimento_paciente: "",
      endereco_paciente: "",
      telefone_paciente: "",
      email_paciente: "",
      senha_paciente: "",
    });
    setErrors({});
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validation = cadastroSchema.safeParse(formData);

    if (validation.success) {
      try {
        
        const response = await addPacient(formData);
        console.log(response)
        if (response.status === 201) {
          console.log("Cadastro bem sucedido");
          resetForm();
        } else {
          console.error("Erro ao cadastrar paciente:", response.data);
        }
      } catch (error) {
        console.error("Erro de rede ao cadastrar paciente:", error);
      }
    } else {
      const fieldErrors = validation.error.errors.reduce((acc, error) => {
        acc[error.path[0]] = error.message;
        return acc;
      }, {});
      setErrors(fieldErrors);
      console.log("Erro de validação:", fieldErrors);
    }
  };

  return (
    <div>
      <form className={styles.formContainer} onSubmit={handleSubmit}>
        <InputField
          label="Nome Completo"
          type="text"
          name="nome_paciente"
          value={formData.nome_paciente}
          placeholder="Digite seu nome completo"
          onChange={handleChange}
          error={errors.nome_paciente}
        />
        <InputField
          label="CPF"
          type="text"
          name="cpf_paciente"
          value={formData.cpf_paciente}
          placeholder="Digite seu CPF"
          onChange={handleChange}
          error={errors.cpf}
        />
        <InputField
          label="Endereço"
          type="text"
          name="endereco_paciente"
          value={formData.endereco_paciente}
          placeholder="Digite seu endereço"
          onChange={handleChange}
          error={errors.endereco}
        />
        <InputField
          label="Data de Nascimento"
          type="date"
          name="nascimento_paciente"
          value={formData.nascimento_paciente}
          placeholder="Digite sua data de nascimento"
          onChange={handleChange}
          error={errors.dataNascimento}
        />
        <InputField
          label="Telefone"
          type="text"
          name="telefone_paciente"
          value={formData.telefone_paciente}
          placeholder="Digite seu telefone"
          onChange={handleChange}
          error={errors.telefone}
        />
        <InputField
          label="E-mail"
          type="email"
          name="email_paciente"
          value={formData.email_paciente}
          placeholder="Digite seu email"
          onChange={handleChange}
          error={errors.email}
        />
        <InputField
          label="Senha"
          type="password"
          name="senha_paciente"
          value={formData.senha_paciente}
          placeholder="Digite sua senha"
          onChange={handleChange}
          error={errors.senha}
        />
        <button className={styles.button} type="submit">
          Cadastrar
        </button>
      </form>
    </div>
  );
};
