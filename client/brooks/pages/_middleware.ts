import { successAuthResponse, verifyToken } from "@lib/auth";
import { LOGIN_PATH, SERVER_DOMAIN } from "@lib/clientConstants";
import { EXACT_PUBLIC_URIS, START_PUBLIC_URIS } from "@lib/serverConstants";
import { NextRequest, NextResponse } from "next/server";

export async function middleware(req: NextRequest) {
  let access_token = req.cookies["access_token"];
  let refresh_token = req.cookies["refresh_token"];

  if (matchPublicPath(req.nextUrl.pathname)) {
    // Pass auth check

    if (req.nextUrl.pathname === LOGIN_PATH && access_token && refresh_token) {
      return await doAuth(
        access_token,
        refresh_token,
        req,
        NextResponse.redirect(req.nextUrl.origin)
      );
    }

    return NextResponse.next();
  }

  if (!access_token || !refresh_token) {
    // Unauthorized -> Redirect to login page
    return NextResponse.redirect(req.nextUrl.origin + LOGIN_PATH);
  }

  return await doAuth(access_token, refresh_token, req, NextResponse.next());
}

async function doAuth(
  access_token: string,
  refresh_token: string,
  req: NextRequest,
  res: NextResponse
) {
  try {
    const decodedToken = await verifyToken(access_token);
  } catch (e) {
    // Invalid Access Token -> Try to Refresh access_token
    const refreshPromise = await fetch(`${SERVER_DOMAIN}/auth/token/refresh`, {
      method: "POST",
      headers: {
        Authorization: `Bearer ${refresh_token}`,
      },
    }).catch((e) => console.error(e.message));

    if (refreshPromise?.status != 200) {
      // Invalid Refresh Token -> Redirect to login page
      return NextResponse.redirect(req.nextUrl.origin + LOGIN_PATH);
    } else {
      // Refresh Successful
    }

    const refreshRes = (await refreshPromise.json()) as successAuthResponse;
    access_token = refreshRes.access_token;

    res.cookie("access_token", access_token, {
      path: "/",
      maxAge: 2592000 * 1000,
      httpOnly: true,
      domain: "localhost",
      secure: true,
    });
  }

  return res;
}

function matchPublicPath(pathname: string) {
  if (EXACT_PUBLIC_URIS.has(pathname)) return true;

  for (const uri of START_PUBLIC_URIS) {
    if (pathname.startsWith(uri)) return true;
  }
  return false;
}
