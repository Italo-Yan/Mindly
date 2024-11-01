import PropTypes from 'prop-types';
import styles from './UserProfile.module.css';

UserProfile.propTypes = {
  name: PropTypes.string.isRequired,
  id: PropTypes.string.isRequired,
  crp: PropTypes.string.isRequired,
  description: PropTypes.string.isRequired,
};

export function UserProfile({ name, id, crp, description }) {
  return (
    <div className={styles.profileBox}>
      <div className={styles.profilePicture}></div>
      <div className={styles.info}>
        <h2>{name}</h2>
        <p>{id}</p>
        <p>{crp}</p>
      </div>
      <button className={styles.editButton}>Editar</button>

      <div className={styles.descriptionBox}>
        <h3>DESCRIÇÃO</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}
