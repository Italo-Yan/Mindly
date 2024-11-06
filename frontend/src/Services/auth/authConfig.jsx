import { api } from "../../config/apiConfig"


const loginPaciente = async (data) => {
  try {
    const response = await api.post("/paciente/login", data);
    return response;
  } catch (e) {
    throw e;
  }
};

const loginProfisisonal = async (data) => {
    try {
      const response = await api.post("/profissional/login", data);
      return response;
    } catch (e) {
      throw e;
    }
  };
export { loginPaciente, loginProfisisonal };