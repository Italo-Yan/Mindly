import { useState } from "react";
import PropTypes from "prop-types";
import * as zod from "zod";
import styles from "./Form.module.css";

const cadastroSchema = zod.object({
  nomeCompleto: zod
    .string()
    .min(15, { message: "Deve conter no mínimo 15 caracteres." })
    .max(100, { message: "Deve conter no máximo 100 caracteres." }),
  cpf: zod
    .string()
    .length(11, { message: "O CPF deve conter 11 dígitos." })
    .regex(/^\d+$/, { message: "O CPF deve conter apenas números." }),
  endereco: zod
    .string()
    .min(1, { message: "Endereço é obrigatório." }),
  telefone: zod
    .string()
    .min(10, { message: "O telefone deve conter no mínimo 10 dígitos." })
    .max(11, { message: "O telefone deve conter no máximo 11 dígitos." })
    .regex(/^\d+$/, { message: "O telefone deve conter apenas números." }),
  email: zod
    .string()
    .email({ message: "Formato de e-mail inválido." }),
  senha: zod
    .string()
    .min(8, { message: "A senha deve ter pelo menos 8 caracteres." }),
});

const InputField = ({ label, type, name, value, placeholder, onChange, error }) => (
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
    nomeCompleto: '',
    cpf: '',
    endereco: '',
    telefone: '',
    email: '',
    senha: '',
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
      nomeCompleto: '',
      cpf: '',
      endereco: '',
      telefone: '',
      email: '',
      senha: '',
    });
    setErrors({});
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const validation = cadastroSchema.safeParse(formData);

    if (validation.success) {
      console.log("Cadastro bem sucedido", formData);
      resetForm();
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
          name="nomeCompleto"
          value={formData.nomeCompleto}
          placeholder="Digite seu nome completo"
          onChange={handleChange}
          error={errors.nomeCompleto}
        />
        <InputField
          label="CPF"
          type="text"
          name="cpf"
          value={formData.cpf}
          placeholder="Digite seu CPF"
          onChange={handleChange}
          error={errors.cpf}
        />
        <InputField
          label="Endereço"
          type="text"
          name="endereco"
          value={formData.endereco}
          placeholder="Digite seu endereço"
          onChange={handleChange}
          error={errors.endereco}
        />
        <InputField
          label="Telefone"
          type="text"
          name="telefone"
          value={formData.telefone}
          placeholder="Digite seu telefone"
          onChange={handleChange}
          error={errors.telefone}
        />
        <InputField
          label="E-mail"
          type="email"
          name="email"
          value={formData.email}
          placeholder="Digite seu email"
          onChange={handleChange}
          error={errors.email}
        />
        <InputField
          label="Senha"
          type="password"
          name="senha"
          value={formData.senha}
          placeholder="Digite sua senha"
          onChange={handleChange}
          error={errors.senha}
        />
        <button className={styles.button} type="submit">Cadastrar</button>
      </form>
    </div>
  );
};
