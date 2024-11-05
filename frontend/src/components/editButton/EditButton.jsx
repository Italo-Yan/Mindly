import PropTypes from 'prop-types';
import styles from './EditButton.module.css';

export function EditButton({ onClick }) {
  return (
    <button className={styles.editButton} onClick={onClick}>
      Editar
    </button>
  );
}

EditButton.propTypes = {
  onClick: PropTypes.func.isRequired,
};
