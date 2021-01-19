package com.example.asyncmethod.runner;

import com.example.asyncmethod.job.AsyncJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AsyncJobStartingCommandLineRunner implements CommandLineRunner {
	private final AsyncJob asyncJob;

	@Override
	public void run(String... args) throws Exception {
		for (int i = 0; i < 4;) {
			if ((i & 1) == 0) {
				String taskName = String.format("Async job #%d", ++i);
				asyncJob.runShortWithLongRetryBackoff(taskName);
			} else {
				String taskName = String.format("Async job #%d", ++i);
				asyncJob.runLongWithShortRetryBackoff(taskName);
			}
		}
	}
}
