import {
  CLIENT_DOMAIN,
  CLIENT_ERROR,
  SERVER_DOMAIN,
  SERVER_ERROR,
} from "@lib/clientConstants";
import { AuthUser } from "@lib/model/AuthUser";
import axios, { AxiosError } from "axios";

export interface ErrorRes {
  error: string;
  message: string;
}

export interface LoginRes {
  access_token: string;
  refresh_token: string;
}

class authService {
  async login(username: string, password: string) {
    try {
      const fetchPromise = await axios.post(
        `${SERVER_DOMAIN}/auth/signin`,
        {
          username,
          password,
        },
        {
          withCredentials: true,
        }
      );
      return fetchPromise.data as LoginRes;
    } catch (error) {
      if (axios.isAxiosError(error)) {
        const serverError = error as AxiosError;
        if (serverError && serverError.response) {
          return serverError.response.data as ErrorRes;
        }
      }
      return {
        error: CLIENT_ERROR,
        message: "Axios failed to fetch, " + error,
      } as ErrorRes;
    }
  }

  async getUser(access_token: string) {
    try {
      const fetchPromise = await axios.get(`${SERVER_DOMAIN}/auth/user`, {
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

  async logout() {
    return await axios.post(
      `${CLIENT_DOMAIN}/api/logout`,
      {},
      {
        withCredentials: true,
      }
    );
  }
}

export default new authService();
