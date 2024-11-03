import { useState } from "react";
import * as zod from "zod";
import styles from "./Form.module.css";


const cadastroSchema = zod.object({
  nomeCompleto: zod
    .string()
    .min(15, { message: "Deve conter no mínimo 15 caracteres." })
    .max(100, { message: "Deve conter no máximo 100 caracteres." }),
  cpf: zod
    .string()
    .min(11, { message: "O CPF deve conter 11 dígitos." })
    .max(11, { message: "O CPF deve conter 11 dígitos." })
    .regex(/^\d+$/, { message: "O CPF deve conter apenas números." }),
  crp: zod
    .string()
    .min(8, { message: "O CRP deve conter 8 caracteres." })
    .max(8, { message: "O CRP deve conter 8 caracteres." }),
  abordagemTeorica: zod
    .string()
    .min(4, { message: "Deve conter no mínimo 4 caracteres." })
    .max(15, { message: "Deve conter no máximo 15 caracteres." }),
  email: zod
    .string()
    .email({ message: "Formato de e-mail inválido." }),
  senha: zod
    .string()
    .min(8, { message: "A senha deve ter pelo menos 8 caracteres." }),
  telefone: zod
    .string()
    .min(10, { message: "O telefone deve conter no mínimo 10 dígitos." })
    .max(11, { message: "O telefone deve conter no máximo 11 dígitos." })
    .regex(/^\d+$/, { message: "O telefone deve conter apenas números." }),
  descricao: zod
    .string()
    .optional(),
});

export const Professional = () => {
  const [formData, setFormData] = useState({
    nomeCompleto: '',
    cpf: '',
    crp: '',
    abordagemTeorica: '',
    email: '',
    senha: '',
    telefone: '',
    descricao: '',
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
      crp: '',
      abordagemTeorica: '',
      email: '',
      senha: '',
      telefone: '',
      descricao: '',
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
      const fieldErrors = {};
      validation.error.errors.forEach((error) => {
        fieldErrors[error.path[0]] = error.message;
      });
      setErrors(fieldErrors);
      console.log("Erro de validação:", fieldErrors);
    }
  };

  return (
    <div>
    <h2 className={styles.formTitle}>Cadastro</h2>
    <form className={styles.formContainer} onSubmit={handleSubmit}>
      <div>
        <label htmlFor="nomeCompleto">Nome Completo</label>
        <input
          className={styles.input}
          type="text"
          id="nomeCompleto"
          name="nomeCompleto"
          value={formData.nomeCompleto}
          onChange={handleChange}
        />
        {errors.nomeCompleto && <p className={styles.errorText}>{errors.nomeCompleto}</p>}
      </div>
      <div>
        <label htmlFor="cpf">CPF</label>
        <input
          className={styles.input}
          type="text"
          id="cpf"
          name="cpf"
          value={formData.cpf}
          onChange={handleChange}
        />
        {errors.cpf && <p className={styles.errorText}>{errors.cpf}</p>}
      </div>
      <div>
        <label htmlFor="crp">CRP</label>
        <input
          className={styles.input}
          type="text"
          id="crp"
          name="crp"
          value={formData.crp}
          onChange={handleChange}
        />
        {errors.crp && <p className={styles.errorText}>{errors.crp}</p>}
      </div>
      <div>
        <label htmlFor="abordagemTeorica">Abordagem Teórica</label>
        <input
          className={styles.input}
          type="text"
          id="abordagemTeorica"
          name="abordagemTeorica"
          value={formData.abordagemTeorica}
          onChange={handleChange}
        />
        {errors.abordagemTeorica && <p className={styles.errorText}>{errors.abordagemTeorica}</p>}
      </div>
      <div>
        <label htmlFor="email">E-mail</label>
        <input
          className={styles.input}
          type="email"
          id="email"
          name="email"
          value={formData.email}
          onChange={handleChange}
        />
        {errors.email && <p className={styles.errorText}>{errors.email}</p>}
      </div>
      <div>
        <label htmlFor="senha">Senha</label>
        <input
          className={styles.input}
          type="password"
          id="senha"
          name="senha"
          value={formData.senha}
          onChange={handleChange}
        />
        {errors.senha && <p className={styles.errorText}>{errors.senha}</p>}
      </div>
      <div>
        <label htmlFor="telefone">Telefone</label>
        <input
          className={styles.input}
          type="text"
          id="telefone"
          name="telefone"
          value={formData.telefone}
          onChange={handleChange}
        />
        {errors.telefone && <p className={styles.errorText}>{errors.telefone}</p>}
      </div>
      <div>
        <label htmlFor="descricao">Descrição (opcional)</label>
        <textarea
          className={styles.textarea}
          id="descricao"
          name="descricao"
          value={formData.descricao}
          onChange={handleChange}
        />
      </div>
      <button className={styles.button} type="submit">Cadastrar</button>
    </form>
  </div>
);
};