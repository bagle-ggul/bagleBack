package com.suhsaechan.dongbanza.common.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class SampleController {

  private final CustomLogger customLogger;

  @Autowired
  public SampleController(CustomLogger customLogger) {
    this.customLogger = customLogger;
  }

  @GetMapping("/test")
  public String testEndpoint() {
    customLogger.logInfo("This is a test info log message.");
    customLogger.logError("This is a test error log message.");
    customLogger.logWarn("This is a test warn log message.");
    customLogger.logDebug("This is a test debug log message.");
    return "Test endpoint hit!";
  }
}