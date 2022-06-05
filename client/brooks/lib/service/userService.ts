import { SERVER_DOMAIN, SERVER_ERROR } from "@lib/clientConstants";
import { AuthUser } from "@lib/model/AuthUser";
import axios, { AxiosError } from "axios";
import { ErrorRes } from "./authService";

class UserService {
  async getTutors(access_token: string) {
    try {
      const fetchPromise = await axios.get(`${SERVER_DOMAIN}/api/tutors`, {
        headers: {
          Authorization: `Bearer ${access_token}`,
        },
      });
      return fetchPromise.data as AuthUser;
    } catch (error) {
      if (axios.isAxiosError(error)) {
        const serverError = error as AxiosError;
        if (serverError && serverError.response) {
          return serverError.response.data as ErrorRes;
        }
      }
      return {
        error: SERVER_ERROR,
        message: "Axios failed to fetch, " + error,
      } as ErrorRes;
    }
  }
}

export default UserService;
