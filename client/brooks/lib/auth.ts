// eslint-disable-next-line @next/next/no-server-import-in-page
import { jwtVerify, SignJWT } from "jose";
import { JWT_SECRET } from "./serverConstants";

interface UserJwtPayload {
  sub: string;
  roles?: string;
  iss: string;
  exp: number;
  type?: string;
}

export interface successAuthResponse {
  access_token: string;
  refresh_token: string;
}

export async function verifyToken(token: string) {
  if (!token) {
    throw new Error("Empty token");
  }

  try {
    const verified = await jwtVerify(token, Buffer.from(JWT_SECRET));
    return verified.payload as UserJwtPayload;
  } catch (e) {
    const error = e as Error;
    throw new Error(error.message);
  }
}

export async function createToken() {
  const token = await new SignJWT({})
    .setProtectedHeader({ typ: "JWT", alg: "HS256" })
    .setIssuedAt()
    .setExpirationTime("2h")
    .sign(new TextEncoder().encode(JWT_SECRET));

  return token;
}
