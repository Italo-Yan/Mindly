
import styles from "./Profile.module.css";

export function Profile() {
  return (
    <div className={styles.container}>
      <div className={styles.profileBox}>
        <div className={styles.profilePicture}></div>
        <div></div>
      </div>
    </div>
  )
}