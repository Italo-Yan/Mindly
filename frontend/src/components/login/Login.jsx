import { useState } from "react";
import * as zod from "zod";

import styles from "./Login.module.css";

const loginSchema = zod.object({
  username: zod.string().min(1, { message: "O campo Login é obrigatório." }),
  password: zod.string().min(6, { message: "A senha deve ter pelo menos 6 caracteres." }),
});

export function Login() {
  const [formData, setFormData] = useState({ username: "", password: "" });
  const [errors, setErrors] = useState({ username: "", password: "" });

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value, })
  }

  const resetForm = () => {
    setFormData({ username: "", password: "" });
    setErrors({ username: "", password: "" });
  }

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validation = loginSchema.safeParse(formData);

    if (validation.success) {
      try {
        const response = await fetch("", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(formData),
        });

        if (response.ok) {
          const data = await response.json();
          console.log("Cadastro bem-sucedido:", data);
          resetForm();
        } else {
          console.error("Erro ao cadastrar:", response.statusText);
        }
      } catch (error) {
        console.error("Erro de rede:", error);
      }
    } else {
      const fieldErrors = {};
      validation.error.errors.forEach((error) => {
        fieldErrors[error.path[0]] = error.message;
      });
      setErrors(fieldErrors);
      console.log("Erro de validação:", fieldErrors);
    }
  }

  return (
    <div className={styles.loginContainer}>
      <div className={styles.loginBox}>
        <h2 className={styles.title}>LOGIN</h2>
        <form onSubmit={handleSubmit}>
          <div className={styles.inputGroup}>
            <label htmlFor="username" className={styles.label}>Login</label>
            <input
              type="text"
              id="username"
              className={styles.input}
              value={formData.username}
              onChange={handleChange}
            />
            {errors.username && <p className={styles.error}>{errors.username}</p>}
          </div>
          <div className={styles.inputGroup}>
            <label htmlFor="password" className={styles.label}>Senha</label>
            <input
              type="password"
              id="password"
              className={styles.input}
              value={formData.password}
              onChange={handleChange}
            />
            {errors.password && <p className={styles.error}>{errors.password}</p>}
          </div>
          <button type="submit" className={styles.button}>LOGIN</button>
        </form>
      </div>
    </div>
  )
}