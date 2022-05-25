package com.example.baked.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

@Slf4j
public class ResponseUtil {

  public static void sendError(
      HttpServletResponse response, int statusCode, String error, String message)
      throws IOException {
    log.error("Error {}: {}", error, message);
    response.setStatus(statusCode);

    HashMap<String, String> errorMap = new HashMap<>();
    errorMap.put("error", error);
    errorMap.put("message", message);
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    new ObjectMapper().writeValue(response.getOutputStream(), errorMap);
  }

  public static void sendTokenCookies(
      HttpServletResponse response, String access_token, String refresh_token) {
    Cookie access_tokenCookie = new Cookie("access_token", access_token);
    access_tokenCookie.setSecure(true);
    access_tokenCookie.setHttpOnly(true);
    access_tokenCookie.setMaxAge(2592000);
    access_tokenCookie.setPath("/");
    Cookie refresh_tokenCookie = new Cookie("refresh_token", refresh_token);
    refresh_tokenCookie.setSecure(true);
    refresh_tokenCookie.setHttpOnly(true);
    refresh_tokenCookie.setMaxAge(2592000);
    refresh_tokenCookie.setPath("/");
    response.addCookie(access_tokenCookie);
    response.addCookie(refresh_tokenCookie);
  }
}
