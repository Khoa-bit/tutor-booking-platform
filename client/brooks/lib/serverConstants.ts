import { LOGIN_PATH, SIGNUP_PATH } from "./clientConstants";

const processEnvJwtSecret = process.env.JWT_SECRET;
if (!processEnvJwtSecret) {
  throw new Error("Misisng env. for JWT_SECRET: " + processEnvJwtSecret);
}

export const JWT_SECRET = processEnvJwtSecret;

// Match any paths that begin with these URIS, not exact
export const EXACT_PUBLIC_URIS = new Set(["/", LOGIN_PATH, SIGNUP_PATH]);
export const START_PUBLIC_URIS = ["/static"];
