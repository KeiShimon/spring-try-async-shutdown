package com.example.asyncmethod.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;

@Slf4j
public class MyRetryListener extends RetryListenerSupport {
  @Override
  public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
    log.info("Error #{}: {}", 1 + context.getRetryCount(), context.getLastThrowable().getMessage());
  }
}
