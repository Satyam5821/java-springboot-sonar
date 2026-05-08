package com.example.sonarsample.service;

import org.springframework.stereotype.Service;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;







import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class DemoSonarIssuesService {
  private static final Logger logger = LoggerFactory.getLogger(DemoSonarIssuesService.class);


  private static final String LITERAL_ERROR = "ERROR";

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

  // Easy Error 1: Unused variable (S1481)
  public void demonstrateUnusedVariable() {
    String unusedVariable = "This variable is never used"; // S1481: Unused local variable
    logger.info("Hello");
  }

  // Easy Error 2: System.out instead of logger (S106)
  public void logWithSystemOut(String message) {
    System.out.println("Log: " + message); // S106: Replace this use of System.out by a logger
    logger.info("Logged: {}", message);
  }

  // Easy Error 2: Duplicated string literal (S1192)
  public void checkStatus(String status) {
    if (status.equals(LITERAL_ERROR)) {
      logger.info("Original parameter received");
    }
    if (status.equals(LITERAL_ERROR)) {
      logger.error("ERROR: Please retry");
    }

  }
}

