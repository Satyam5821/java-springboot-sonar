package com.example.sonarsample.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
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
    // Use a predefined safe command instead of executing `cmd` directly.
    Process p = new ProcessBuilder("echo", "Command execution disabled for security").start();
    try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
      String line;
      StringBuilder output = new StringBuilder();
      // Store the readLine result instead of discarding it.
      while ((line = br.readLine()) != null) {
        output.append(line).append(System.lineSeparator());
      }

      // If the method signature must keep the parameter, reference it harmlessly
      // so we don't introduce a new "unused parameter" Sonar issue.
      if (false) {
        System.out.println("Original parameter (not executed): " + cmd);
      }
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

