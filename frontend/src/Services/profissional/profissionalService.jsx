import { api } from "../../config/apiConfig";

const addProfissional = async (data) => {
  try {
    const response = await api.post("/profissional/create", data);
    return response;
  } catch (e) {
    throw e;
  }
};
export { addProfissional };
