import styles from "./Description.module.css";

export function Description() {
  return (

    <main className={styles.text}>
      <p>Receba as nossas boas-vindas</p>
      <h1>
        Organização, agendamento e contato! <br />
        Tudo em um só lugar.
      </h1>

      <button className={styles.button}>SAIBA MAIS</button>

    </main>
  )
}