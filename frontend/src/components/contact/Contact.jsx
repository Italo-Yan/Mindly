import { MapPin, EnvelopeSimple, Phone } from "@phosphor-icons/react";
import Support from "../../assets/Support.svg";
import styles from "./Contact.module.css";

export function Contact() {
  return (
    <section className={styles.contactSection}>
      <div className={styles.contactContainer}>

        <div className={styles.contactInfo}>
          <h2>
            Entre em contato <br />
            com a gente!
          </h2>

          <div className={styles.contactDetails}>
            <div className={styles.contactItem}>
              <MapPin size={24} color="#6482AD" />
              <span>R. Unex, 666</span>
            </div>

            <div className={styles.contactItem}>
              <EnvelopeSimple size={24} color="#6482AD" />
              <span>mindly@unex.com</span>
            </div>
          </div>

          <button className={styles.contactButton}>
            <Phone size={24} color="#FFFFFF" />
            <span>Agende sua consulta</span>
          </button>
        </div>

        <div className={styles.contactImage}>
          <img src={Support} alt="Homem utilizando o celular" />
        </div>

      </div>
    </section>
  )
}