import { YoutubeLogo, FacebookLogo, InstagramLogo } from "@phosphor-icons/react";
import styles from "./Footer.module.css";

export function Footer() {
  return (
    <footer>
      <div className={styles.footerContainer}>
        <div className={styles.footerText}>
          <h3>Mindly</h3>
          <p> © 2024 - Mindly. <br /></p>
          <p>Todos os direitos reservados.</p>
        </div>

        <div className={styles.footerItems}>
          <InstagramLogo size={24} color="#FFFFFF" />
          <FacebookLogo size={24} color="#FFFFFF" />
          <YoutubeLogo size={24} color="#FFFFFF" />
        </div>
      </div>


    </footer>
  )
}