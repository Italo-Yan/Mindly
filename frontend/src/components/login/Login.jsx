import { useState } from "react";
import { loginUser } from "../../Services/auth/authConfig";
import { useNavigate } from "react-router-dom";
import * as zod from "zod";

import styles from "./Login.module.css";

const loginSchema = zod.object({
  email: zod.string().min(1, { message: "O campo Login é obrigatório." }),
  password: zod
    .string()
    .min(4, { message: "A senha deve ter pelo menos 4 caracteres." }),
});

export function Login() {
  const [formData, setFormData] = useState({ email: "", password: "" });
  const [errors, setErrors] = useState({ email: "", password: "" });
  const { generalError, setGeneralError } = useState("");
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.id]: e.target.value });
  };

  const resetForm = () => {
    setFormData({ email: "", password: "" });
    setErrors({ email: "", password: "" });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validation = loginSchema.safeParse(formData);

    if (validation.success) {
      try {
        const response = await loginUser(formData);
        console.log(response);

        if (response.status == 200) {
          console.log("Cadastro bem-sucedido: ", response.data);
          resetForm();
          navigate("/perfil");
        } else if (response.status === 401) {
          setGeneralError("Email ou senha incorretos.");
        }
        else {
          console.error("Erro ao fazer login:", response.statusText);
          setGeneralError("Erro ao tentar fazer login. Tente novamente.");
        }
      } catch (error) {
        console.error("Erro de rede:", error);
        setGeneralError("Erro de rede. Verifique sua conexão.");
      }
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
    <div className={styles.loginContainer}>
      <div className={styles.loginBox}>
        <h2 className={styles.title}>LOGIN</h2>
        <form onSubmit={handleSubmit}>
          <div className={styles.inputGroup}>
            <label htmlFor="email" className={styles.label}>
              Login
            </label>
            <input
              type="text"
              id="email"
              className={styles.input}
              value={formData.email}
              onChange={handleChange}
            />
            {errors.email && <p className={styles.error}>{errors.email}</p>}
          </div>
          <div className={styles.inputGroup}>
            <label htmlFor="password" className={styles.label}>
              Senha
            </label>
            <input
              type="password"
              id="password"
              className={styles.input}
              value={formData.password}
              onChange={handleChange}
            />
            {errors.password && (
              <p className={styles.error}>{errors.password}</p>
            )}
          </div>
          {generalError && <p className={styles.error}>General Error</p>}
          <button type="submit" className={styles.button}>
            LOGIN
          </button>
        </form>
      </div>
    </div>
  );
}
