import { api } from "../../config/apiConfig";

const addPacient = async (data) => {
  try {
    const response = await api.post("/paciente/create", data);
    return response;
  } catch (e) {
    throw e;
  }
};
const getPacienteByEmail = async (data) => {
  try {
    const response = await api.get("/paciente/email/" + data);
    return response;
  } catch (e) {
    throw e;
  }
};

export { addPacient, getPacienteByEmail };
