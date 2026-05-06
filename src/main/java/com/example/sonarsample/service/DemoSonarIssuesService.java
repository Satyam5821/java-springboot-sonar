package com.example.sonarsample.service;

import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;







import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.nio.charset.StandardCharsets;


@Service
public class DemoSonarIssuesService {

  // Intentional code smell: magic number + unnecessary object creation + poor naming
  public String normalizeName(String name) {
    if (name == null) {
      return "world";
    }
    if (name.length() > 50) { // magic number
      name = name.substring(0, 50);
    }
    return name.trim();
  }

  // Security: do NOT execute user-controlled commands. Use a predefined safe command
  // and avoid constructing OS commands from user input.
  public String runCommandUnsafely(String cmd) throws IOException {
    // Intentionally unsafe for Sonar validation: command is built from user-controlled data.
    // Whitelist allowed commands
    Set<String> allowed = new HashSet<>(Arrays.asList("date", "whoami"));
    if (!allowed.contains(cmd)) {
      throw new IllegalArgumentException("Command not allowed");
    }
    Process p = new ProcessBuilder(cmd).start();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
      StringBuilder output = new StringBuilder();
      String line;
      while ((line = br.readLine()) != null) {
          output.append(line).append("\n");      }
      return output.toString();
    }
  }

  // Intentional bug/code smell: swallow exception
  public int parseIntOrDefault(String value) {
    try {
      return Integer.parseInt(value);
    } catch (Exception e) {
      // ignore
    }
    return 0;
  }
}

