package com.example.asyncmethod.job;

import com.example.asyncmethod.Service.AlwaysFailingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncJob {
  private final AlwaysFailingService alwaysFailingService;
  private final RetryTemplate retryTemplateLongBackoffPeriod;
  private final RetryTemplate retryTemplateShortBackoffPeriod;

  @Async
  public void runLongWithShortRetryBackoff(String taskName) throws InterruptedException {
    log.info("Kicking LONG job {}", taskName);
    retryTemplateShortBackoffPeriod.execute(
        retryContext -> alwaysFailingService.runLongAndFail(taskName),
        retryContext -> {
          log.info("Recovery process running after #{} throws", retryContext.getRetryCount(), retryContext.getLastThrowable());
          return null;
        }
    );
  }

  @Async
  public void runShortWithLongRetryBackoff(String taskName) {
    log.info("Kicking short job {}", taskName);
    retryTemplateLongBackoffPeriod.execute(
        retryContext -> alwaysFailingService.runShortAndFail(String.format("AsyncJob #%s", taskName)),
        retryContext -> {
          log.info("Recovery process running after #{} throws", retryContext.getRetryCount(), retryContext.getLastThrowable());
          return null;
        }
    );
  }
}
