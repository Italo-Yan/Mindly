import styles from "./Header.module.css";

export function Header() {
  return (
    <header className={styles.header}>
      <h1>Mindly</h1>

      <ul>
        <li><a href="#home">Início</a></li>
        <li><a href="#about">Sobre</a></li>
        <li><a href="#services">Serviços</a></li>
        <li><a href="#support">Suporte</a></li>
      </ul>

      <div className={styles.buttonContainer}>
        <button>CADASTRE-SE</button>
        <button>LOGIN</button>
      </div>
    </header>
  );
}
