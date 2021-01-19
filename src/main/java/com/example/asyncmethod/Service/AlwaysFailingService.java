package com.example.asyncmethod.Service;

import com.example.asyncmethod.exception.AlwaysThrown;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlwaysFailingService {
  public int runShortAndFail(String taskName) {
    throw new AlwaysThrown(String.format("Thrown during executing SHORT by %s", taskName));
  }

  public int runLongAndFail(String taskName) throws InterruptedException {
    Thread.sleep(20_000L);
    throw new AlwaysThrown(String.format("Thrown during executing SHORT by %s", taskName));
  }
}
