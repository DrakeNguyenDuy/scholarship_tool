package com.matohela.scholarshipManage.utils;

import java.util.Calendar;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

import org.springframework.util.StopWatch;

public class Test {
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		Executors.newCachedThreadPool().submit(() -> {
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			Thread.sleep(500);
			stopWatch.stop();
			System.out.println(String.format("First thread took %sms to complete", stopWatch.getTotalTimeMillis()));
			return null;
		});
		CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
			Integer threadSleep1 = 1000;
			try {
				Thread.sleep(threadSleep1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			// Some long-running operation
//		    return String.format("Hello", threadSleep1);
			System.out.println(String.format("Future1 %s", Calendar.getInstance().getTime()));
			return String.valueOf(threadSleep1);
		});

		CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
//			String input2 = scanner.next();
			Integer threadSleep2 = 500;
			try {
				Thread.sleep(threadSleep2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		    return input2;
			System.out.println(String.format("Future2 %s", Calendar.getInstance().getTime()));
			return String.valueOf(threadSleep2);
		});

		CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
			Integer threadSleep3 = 3000;
			try {
				Thread.sleep(threadSleep3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//		    return input2;
			System.out.println(String.format("Future3 %s", Calendar.getInstance().getTime()));
			return String.valueOf(threadSleep3);
		});

		CompletableFuture<Void> allFutures = CompletableFuture.allOf(future1, future2, future3);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		allFutures.thenRun(() -> {
			// All futures completed
			String result1 = future1.join();
			String result2 = future2.join();
			String result3 = future3.join();
			System.out.println(result1 + ", " + result2 + ", " + result3);
			System.out.println(String.format("Dependency between 3 futures \n"
					+ "future1 number of dependents: %s\nfuture2 number of dependents: %s\nfuture3 number of dependents: %s",
					future1.getNumberOfDependents(), future2.getNumberOfDependents(), future3.getNumberOfDependents()));
			stopWatch.stop();
			System.out.println(String.format("3 futures took %sms to execute", stopWatch.getTotalTimeMillis()));
			System.out
					.println(String.format("CompleteableFuture is async: %s", 3000 >= stopWatch.getTotalTimeMillis()));
		});
	}
}
