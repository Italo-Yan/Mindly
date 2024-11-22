import styles from "./About.module.css";
import Doctor from "../../assets/Doctor.svg";

export function About() {
  return (
    <section className={styles.aboutSection}>

      <div className={styles.aboutContent}>

        <div className={styles.aboutImg}>
          <img src={Doctor} alt="Imagem de um psicologo e dois pacientes" />
        </div>

        <div className={styles.aboutText}>
          <h4>SOBRE NÓS</h4>
          <h2>Entenda quem somos <br /> e por que existimos</h2>
          <p>
            Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.
            Velit officia consequat duis enim. Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.
            Velit officia consequat duis enim. Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.
            Velit officia consequat duis enim. Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.
            Velit officia consequat duis enim. Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint.
          </p>
        </div>

      </div>
    </section>
  )
}