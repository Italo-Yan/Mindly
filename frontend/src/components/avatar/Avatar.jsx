import PropTypes from 'prop-types';
import styles from './Avatar.module.css';
import defaultAvatar from '../../assets/PersonIcon.svg';

export const Avatar = ({ src }) => {
  return (
    <div className={styles.avatar}>
      <img src={src || defaultAvatar} alt="Avatar" className={styles.avatarImage} />
    </div>
  );
};

Avatar.propTypes = {
  src: PropTypes.string,
};
