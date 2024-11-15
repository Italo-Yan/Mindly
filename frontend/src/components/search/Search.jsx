import { useState, useEffect } from "react";
import { Avatar } from "../avatar/Avatar";
import { MagnifyingGlass, X } from "@phosphor-icons/react";
import styles from "./Search.module.css";
import {
  getProfisisonalPublic,
  searchProfissionalPublicName,
} from "../../Services/profissional/profissionalService";

export const Search = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [professionals, setProfessionals] = useState([]);
  const [selectedProfessional, setSelectedProfessional] = useState(null);

  const filteredProfessionals = professionals.filter((professional) =>
    professional.nomeProf.toLowerCase().includes(searchTerm.toLowerCase())
  );

  useEffect(() => {
    getProfessionals();
    searhProfissionals();
  }, []);

  const getProfessionals = async () => {
    const response = await getProfisisonalPublic();
    setProfessionals(Array.from(response.data));
    return response;
  };

  console.log(professionals);
  console.log(searchTerm);

  const searhProfissionals = async (searchTerm) => {
    const response = await searchProfissionalPublicName(searchTerm);
    console.log(response)
    return response
  };

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
            key={professional.crp}
            onClick={() => openProfile(professional)}
          >
            <Avatar />
            <div className={styles.info}>
              <span className={styles.name}>{professional.nomeProf}</span>
              <span className={styles.crp}>{professional.crp}</span>
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
            <h2 className={styles.modalName}>
              {selectedProfessional.nomeProf}
            </h2>
            <p className={styles.modalCRP}>{selectedProfessional.crp}</p>
            <p className={styles.modalBio}>
              {selectedProfessional.abordagemTeorica}
            </p>
            <p className={styles.modalDescription}>
              {selectedProfessional.descricaoProf}
            </p>
          </div>
        </div>
      )}
    </div>
  );
};
