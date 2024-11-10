import { api } from "../../config/apiConfig";

const loginUser = async (data) => {
  try {
    const response = await api.post("/auth/login", data);
    const responseFormatted = {
      data: response.data,
      status: response.status,
    };
    return responseFormatted;
  } catch (e) {
    console.log(e);
    const responseFormatted = {
      data: e.response.data,
      status: e.status,
    };
    return responseFormatted;
  }
};

export { loginUser };
