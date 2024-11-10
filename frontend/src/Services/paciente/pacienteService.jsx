import { api } from "../../config/apiConfig";

const addPacient = async (data) => {
  try {
    const response = await api.post("/paciente/create", data);
    return response;
  } catch (e) {
    throw e;
  }
};

export {addPacient};
