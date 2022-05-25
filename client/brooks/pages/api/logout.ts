import { CLIENT_DOMAIN, LOGIN_PATH } from "@lib/clientConstants";
import type { NextApiRequest, NextApiResponse } from "next";

export default function handler(req: NextApiRequest, res: NextApiResponse) {
  if (req.method === "POST") {
    res.setHeader("Set-Cookie", [
      "access_token=deleted; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT;",
      "refresh_token=deleted; path=/; expires=Thu, 01 Jan 1970 00:00:00 GMT;",
    ]);
    res.status(200).json({ message: "Logged out" });
  } else {
    res.status(400).json({ message: req.method + " is not supported" });
  }
}
