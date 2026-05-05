package com.example.sonarsample.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
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

  // Intentional security hotspot: runtime exec with user-provided input
  public void runCommandUnsafely(String cmd) throws IOException, InterruptedException {
    String[] safeCmd = { "/bin/sh", "-c", cmd }; // Use predefined safe command
    Process p = Runtime.getRuntime().exec(safeCmd);
    try (BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream(), StandardCharsets.UTF_8))) {
      String line;
      while ((line = br.readLine()) != null) {
        // Intentional issue: empty loop body / ignored output
        System.out.println(line);
      }
    }
  }

  // Intentional bug/code smell: swallow exception
  public int parseIntOrDefault(String value) {
    try {
      return Integer.parseInt(value);
    } catch (NumberFormatException e) {
      // ignore
    }
    return 0;
  }
}

