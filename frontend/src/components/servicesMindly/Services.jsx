import { CheckCircle } from "@phosphor-icons/react";
import styles from "./Services.module.css";

export function Services() {

  const services = [
    { title: 'Consultas', description: 'Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim.' },
    { title: 'Grade de horários', description: 'Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim.' },
    { title: 'Bem-estar mental', description: 'Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim.' },
    { title: 'Agendamento online', description: 'Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim.' },
    { title: 'Profissionais', description: 'Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim.' },
    { title: 'Saúde Mental', description: 'Amet minim mollit non deserunt ullamco est sit aliqua dolor do amet sint. Velit officia consequat duis enim.' },
  ];

  return (
    <div className={styles.servicesContainer}>
      <h3>SERVIÇOS</h3>
      <h2>O que oferecemos?</h2>
      <div className={styles.servicesGrid}>
        {services.map((service, index) => (
          <div key={index} className={styles.serviceBox}>
            <div className={styles.icon}>
              <CheckCircle size={24} color="#6482AD"/>
              <h3>{service.title}</h3>
            </div>
            <p className={styles.description}>{service.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
}