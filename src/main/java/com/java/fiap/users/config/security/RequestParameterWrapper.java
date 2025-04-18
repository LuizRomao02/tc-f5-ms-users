package com.java.fiap.users.config.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.*;
import org.springframework.stereotype.Component;

@Component
public class RequestParameterWrapper {

  public static HttpServletRequest wrapper(
      final HttpServletRequest request, final Map<String, String[]> injectedParams) {

    return new HttpServletRequestWrapper(request) {
      @Override
      public String getParameter(final String name) {
        return Optional.ofNullable(request.getParameter(name))
            .orElseGet(
                () -> {
                  String[] value = injectedParams.get(name);
                  return value == null ? null : value[0];
                });
      }

      @Override
      public Map<String, String[]> getParameterMap() {
        final Map<String, String[]> enhancedParameterMap = new HashMap<>();
        enhancedParameterMap.putAll(request.getParameterMap());
        enhancedParameterMap.putAll(injectedParams);
        return enhancedParameterMap;
      }

      @Override
      public Enumeration<String> getParameterNames() {
        return Collections.enumeration(getParameterMap().keySet());
      }

      @Override
      public String[] getParameterValues(String name) {
        return getParameterMap().get(name);
      }
    };
  }
}
