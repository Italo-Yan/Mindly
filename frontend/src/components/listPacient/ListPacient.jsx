import { Avatar } from '../avatar/Avatar';
import styles from './ListPacient.module.css';

export const ListPacients = () => {
  const pacients = [
    { id: 1, name: 'Joana Silva', phone: '(75)12345-6789', image: '' },
    { id: 2, name: 'Joana Silva', phone: '(75)12345-6789', image: '' },
    { id: 3, name: 'Joana Silva', phone: '(75)12345-6789', image: '' },
    { id: 4, name: 'Joana Silva', phone: '(75)12345-6789', image: '' },
    { id: 5, name: 'Joana Silva', phone: '(75)12345-6789', image: '' },
    { id: 6, name: 'Joana Silva', phone: '(75)12345-6789', image: '' },
  ];

  return (
    <div className={styles.container}>
      {pacients.map((pacient) => (
        <div
          key={pacient.id}
          className={`${styles.pacientCard} ${pacient.id % 2 === 0 ? styles.highlighted : ''
            }`}
        >
          <Avatar src={pacient.image} />
          <div className={styles.info}>
            <h3>{pacient.name}</h3>
            <p>{pacient.phone}</p>
          </div>
          <button className={styles.contactButton}>Contato</button>
        </div>
      ))}
    </div>
  );
};
