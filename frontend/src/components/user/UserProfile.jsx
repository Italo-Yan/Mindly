import { EditButton } from "../editButton/EditButton";
import PropTypes from 'prop-types';
import styles from './UserProfile.module.css';

UserProfile.propTypes = {
  name: PropTypes.string.isRequired,
  id: PropTypes.string.isRequired,
  crp: PropTypes.string.isRequired,
  description: PropTypes.string.isRequired,
};

export function UserProfile({ name, id, crp, description }) {
  const handleEditClick = () => {
    console.log('Edit button clicked')
  }

  return (
    <div className={styles.profileContainer}>
      <div className={styles.profileBox}>
        <div className={styles.profileContent}>
          <div className={styles.profilePicture}></div>
          <div className={styles.info}>
            <h2>{name}</h2>
            <p>{id}</p>
            <p>{crp}</p>
          </div>
          <div className={styles.descriptionBox}>
            <h3>DESCRIÇÃO</h3>
            <p>{description}</p>
          </div>
        </div>
        <div className={styles.editButtonContainer}>
          <EditButton onClick={handleEditClick} />
        </div>
      </div>
    </div>
  );
}
