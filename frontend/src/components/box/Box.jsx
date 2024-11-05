import PropTypes from "prop-types";
import styles from "./Box.module.css";

Box.propTypes = {
  name: PropTypes.isRequired
}

export function Box({ name }) {
  return (
    <div className={styles.infoBox}>
      <h1>{name}</h1>
    </div>
  )
}