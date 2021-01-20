package com.example.asyncmethod;

import com.example.asyncmethod.exception.ShortTaskException;
import com.example.asyncmethod.job.AsyncJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class InitController {
    private final AsyncJob asyncJob;

    @GetMapping("/initShort")
    ResponseEntity<Void> initShort() throws ShortTaskException {
        for (int i = 0; i < 5; i++) {
            log.trace("Queueing #{}", i);
            asyncJob.runShort(i);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/initLong")
    ResponseEntity<Void> initLong() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            log.trace("Queueing #{}", i);
            asyncJob.runLong(i);
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/initComplete")
    ResponseEntity<Void> initComplete() {
        for (int i = 0; i < 5; i++) {
            log.trace("Queueing #{}", i);
            asyncJob.runComplete(i);
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/overload")
    ResponseEntity<Void> overload() throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            log.trace("Queueing #{}", i);
            asyncJob.runLong(i);
        }
        return ResponseEntity.ok().build();
    }
}
