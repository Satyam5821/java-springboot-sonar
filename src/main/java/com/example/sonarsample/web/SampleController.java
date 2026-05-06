package com.example.sonarsample.web;

import com.example.sonarsample.service.DemoSonarIssuesService;
import java.lang.String;
import java.io.IOException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class SampleController {

  private final DemoSonarIssuesService demoSonarIssuesService;

  public SampleController(DemoSonarIssuesService demoSonarIssuesService) {
    this.demoSonarIssuesService = demoSonarIssuesService;
  }

  @GetMapping("/api/hello")
  public ResponseEntity<String> hello(@RequestParam(defaultValue = "world") String name) {
    return ResponseEntity.ok("Hello " + demoSonarIssuesService.normalizeName(name));
  }

  // Intentional Sonar issue: command injection/security hotspot
  // Sonar should complain about executing a system command from request input.
  @GetMapping("/api/run")
  public ResponseEntity<String> run(@RequestParam String cmd) throws SecurityException, IOException {
    demoSonarIssuesService.runCommandUnsafely(cmd);
    return ResponseEntity.ok("executed");
  }
}

