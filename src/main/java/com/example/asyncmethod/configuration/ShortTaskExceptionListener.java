package com.example.asyncmethod.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.listener.RetryListenerSupport;

@Slf4j
public class ShortTaskExceptionListener extends RetryListenerSupport {
  @Override
  public <T, E extends Throwable> void close(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
    log.debug("Closing retry after exception: {}", context.getLastThrowable().getMessage());
    super.close(context, callback, throwable);
  }

  @Override
  public <T, E extends Throwable> boolean open(RetryContext context, RetryCallback<T, E> callback) {
    if (null == context.getLastThrowable()) {
      log.debug("Opening retry before exception");
    } else {
      log.debug("Opening retry after exception: {}", context.getLastThrowable().getMessage());
    }
    return super.open(context, callback);
  }

  @Override
  public <T, E extends Throwable> void onError(RetryContext context, RetryCallback<T, E> callback, Throwable throwable) {
    log.info("Error for {} time(s), {}", context.getRetryCount(), context.getLastThrowable().getMessage());
  }
}
