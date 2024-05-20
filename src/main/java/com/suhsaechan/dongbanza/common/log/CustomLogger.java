package com.suhsaechan.dongbanza.common.log;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CustomLogger {
  private final Logger logger = LoggerFactory.getLogger(CustomLogger.class);
  private final Counter infoCounter;
  private final Counter errorCounter;
  private final Counter warnCounter;
  private final Counter debugCounter;

  public CustomLogger(MeterRegistry registry) {
    this.infoCounter = registry.counter("custom_logs_info_total");
    this.errorCounter = registry.counter("custom_logs_errors_total");
    this.warnCounter = registry.counter("custom_logs_warn_total");
    this.debugCounter = registry.counter("custom_logs_debug_total");
  }

  public void logInfo(String message) {
    logger.info(message);
    infoCounter.increment();
  }

  public void logError(String message) {
    logger.error(message);
    errorCounter.increment();
  }

  public void logWarn(String message) {
    logger.warn(message);
    warnCounter.increment();
  }

  public void logDebug(String message) {
    logger.debug(message);
    debugCounter.increment();
  }
}