export const USER_TOKEN = "user-token";

const processEnvJwtSecret = process.env.JWT_SECRET;
if (!processEnvJwtSecret) throw new Error("Misisng env. for JWT_SECRET");

export const JWT_SECRET = processEnvJwtSecret;

// Match any paths that begin with these URIS, not exact
export const EXACT_PUBLIC_URIS = new Set(["/login"]);
export const START_PUBLIC_URIS = ["/static"];
