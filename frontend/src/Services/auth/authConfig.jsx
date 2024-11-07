import { api } from "../../config/apiConfig"


const loginUser = async (data) => {
  try {
    const response = await api.post("/auth/login", data);
    return response;
  } catch (e) {
    throw e;
  }
};

export { loginUser };