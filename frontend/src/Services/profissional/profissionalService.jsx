import { api } from "../../config/apiConfig";

const addProfissional = async (data) => {
  try {
    const response = await api.post("/profissional/create", data);
    return response;
  } catch (e) {
    throw e;
  }
};

const getProfisisonalPublic = async () => {
  try {
    const response = await api.get("/profissionais/publico");
    return response;
  } catch (e) {
    throw e;
  }
};

const searchProfissionalPublicName = async (data) => {
  try {
    const response = await api.get("/profissionais/publico/nome/" + data);
    return response;
  } catch (e) {
    throw e;
  }
};
export { addProfissional, getProfisisonalPublic, searchProfissionalPublicName };
