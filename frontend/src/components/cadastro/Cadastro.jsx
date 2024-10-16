import styles from "./Cadastro.module.css";

export function Cadastro() {
    return (
<section className={styles.cadastroMain}>
        <h2>Cadastro</h2>
        <div className={styles.cadastroOptions}>
          <div className={styles.option}>
            <h3>ESPECIFICAÇÕES PACIENTE</h3>
            <button className={styles.pacienteBtn}>Paciente</button>
          </div>
          <div className={styles.option}>
            <h3>ESPECIFICAÇÕES PROFISSIONAL</h3>
            <button className={styles.profissionalBtn}>Profissional</button>
          </div>
        </div>
      </section>
    )
}