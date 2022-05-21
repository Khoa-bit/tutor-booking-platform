import { createToken, successAuthResponse, verifyToken } from "@lib/auth";
import { EXACT_PUBLIC_URIS, START_PUBLIC_URIS } from "@lib/constants";
import { NextRequest, NextResponse } from "next/server";

export async function middleware(req: NextRequest) {
  console.log(req.nextUrl.pathname);
  const token = await createToken();
  console.log(token);

  if (matchPath(req.nextUrl.pathname)) {
    console.log("Pass auth check");
    return NextResponse.next();
  }

  let access_token = req.cookies["access_token"];
  let refresh_token = req.cookies["refresh_token"];
  if (!access_token || !refresh_token) {
    console.error("Unauthorized -> Redirect to login page");
    return NextResponse.redirect(req.nextUrl.origin + "/login");
  }

  const pingPromise = await fetch("http://localhost:8080/auth/ping", {
    method: "GET",
    headers: {
      Authorization: `Bearer ${access_token}`,
    },
  }).catch((e) => console.error(e.message));

  if (pingPromise?.status != 200) {
    console.error(`Invalid Access Token -> Try to Refresh access_token`);

    const refreshPromise = await fetch(
      "http://localhost:8080/auth/token/refresh",
      {
        method: "POST",
        headers: {
          Authorization: `Bearer ${refresh_token}`,
        },
      }
    ).catch((e) => console.error(e.message));

    if (refreshPromise?.status != 200) {
      console.error("Invalid Refresh Token -> Redirect to login page");
      return NextResponse.redirect(req.nextUrl.origin + "/login");
    } else {
      console.log("Refresh Successful");
    }

    const refreshRes = (await refreshPromise.json()) as successAuthResponse;
    access_token = refreshRes.access_token;
    refresh_token = refreshRes.refresh_token;
  } else {
    console.log("Ping Successful");
  }

  const res = NextResponse.next();
  res.cookie("access_token", access_token);
  res.cookie("refresh_token", refresh_token);
  // req.headers.set("Authorization", `Bearer ${access_token}`);
  return res;
}

function matchPath(pathname: string) {
  if (EXACT_PUBLIC_URIS.has(pathname)) return true;

  for (const uri of START_PUBLIC_URIS) {
    if (pathname.startsWith(uri)) return true;
  }
  return false;
}
