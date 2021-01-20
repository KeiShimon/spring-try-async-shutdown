package com.example.asyncmethod.job;

import com.example.asyncmethod.Service.RetryableService;
import com.example.asyncmethod.exception.ShortTaskException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncJob {
    private final RetryableService retryableService;

    @Async
    public void runShort(int number) throws ShortTaskException {
        retryableService.runShortWithLongRetryBackoff(number);
    }

    @Async
    public void runLong(int number) throws InterruptedException {
        retryableService.runLongWithShortRetryBackoff(number);
    }

    @Async
    public void runComplete(int number) {
        retryableService.runCompletableTask(number);
    }
}
