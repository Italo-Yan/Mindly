import { CheckCircle } from "@phosphor-icons/react";
import styles from "./Popup.module.css";

export const SuccessPopup = () => {
  return (
    <div className={styles.container}>
      <CheckCircle size={48} weight="bold" className={styles.icon} />
      <p>Conta criada com sucesso!</p>
    </div>
  )
}