package com.example.asyncmethod.Service;

import com.example.asyncmethod.exception.ShortTaskException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RetryableService {
    private final RetryTemplate retryTemplateLongBackoffPeriod;
    private final RetryTemplate retryTemplateShortBackoffPeriod;

    public Void runLongWithShortRetryBackoff(int number) throws InterruptedException {
        log.info("Kicking LONG EXECUTION with SHORT BACKOFF #{}", number);
        return retryTemplateShortBackoffPeriod.execute(retryContext -> longTask(number));
    }

    public Void runShortWithLongRetryBackoff(int number) throws ShortTaskException {
        log.info("Kicking SHORT EXECUTION with LONG BACKOFF #{}", number);
        return retryTemplateLongBackoffPeriod.execute(retryContext -> shortTask(number));
    }

    private Void longTask(int number) throws InterruptedException {
        long start = System.currentTimeMillis();
        log.debug("Started a long task #{}", number);

        for (int i = 0; i < 3; i++) {
            Thread.sleep(10000);
            log.debug("Running for {}s: task {}", (System.currentTimeMillis() - start) / 1000, number);
        }

        return null;
    }

    private Void shortTask(int number) throws ShortTaskException {
        log.debug("Started a short task #{}", number);
        throw new ShortTaskException(String.format("ShortTaskException Thrown by job #%s", number), number);
    }

    public void runCompletableTask(int number) {
        log.debug("Run a completable task #{}", number);
    }
}
