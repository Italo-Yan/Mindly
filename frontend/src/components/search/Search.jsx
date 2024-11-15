import { useState } from "react";
import { Avatar } from "../avatar/Avatar";
import { MagnifyingGlass, X } from "@phosphor-icons/react";
import styles from "./Search.module.css";
import { getProfisisonalPublic } from "../../Services/profissional/profissionalService";

const professionals = async () => {
  const response = await getProfisisonalPublic();
  console.log(response);
  return response;
};

export const Search = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedProfessional, setSelectedProfessional] = useState(null);

  const filteredProfessionals = professionals.filter((professional) =>
    professional.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const openProfile = (professional) => {
    setSelectedProfessional(professional);
  };

  const closeProfile = () => {
    setSelectedProfessional(null);
  };

  return (
    <div className={styles.container}>
      <div className={styles.searchBar}>
        <input
          type="text"
          placeholder="Buscar profissionais"
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button className={styles.searchButton}>
          <MagnifyingGlass size={25} />
        </button>
      </div>

      <div className={styles.list}>
        {filteredProfessionals.map((professional) => (
          <div
            className={styles.listItem}
            key={professional.id}
            onClick={() => openProfile(professional)}
          >
            <Avatar />
            <div className={styles.info}>
              <span className={styles.name}>{professional.name}</span>
              <span className={styles.crp}>{professional.crp}</span>
              <span className={styles.phone}>{professional.phone}</span>
            </div>
          </div>
        ))}
      </div>

      {selectedProfessional && (
        <div className={styles.modalOverlay} onClick={closeProfile}>
          <div className={styles.modal} onClick={(e) => e.stopPropagation()}>
            <button className={styles.closeButton} onClick={closeProfile}>
              <X size={25} />
            </button>
            <Avatar className={styles.modalAvatar} />
            <h2 className={styles.modalName}>{selectedProfessional.name}</h2>
            <p className={styles.modalCRP}>{selectedProfessional.crp}</p>
            <p className={styles.modalPhone}>{selectedProfessional.phone}</p>
            <p className={styles.modalBio}>{selectedProfessional.bio}</p>
            <p className={styles.modalDescription}>
              {selectedProfessional.description}
            </p>
          </div>
        </div>
      )}
    </div>
  );
};
