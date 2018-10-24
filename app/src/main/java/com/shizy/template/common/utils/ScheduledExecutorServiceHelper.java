package com.shizy.template.common.utils;

import android.support.annotation.NonNull;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description
 * 用于替代{@link java.util.Timer}
 *
 * @author dahu
 * time 2018/10/24 17:01.
 */
public class ScheduledExecutorServiceHelper {

	private ScheduledExecutorService executorService;
	private static final int CORE_POOL_SIZE = 1;

	public ScheduledExecutorServiceHelper(String name) {
		executorService = new ScheduledThreadPoolExecutor(CORE_POOL_SIZE, new SelfThreadFactory(name));
	}

	public void schedule(int delay, int period, Runnable runnable) {
		executorService.scheduleAtFixedRate(runnable, delay, period, TimeUnit.MILLISECONDS);
	}

	public void shutdown() {
		if (executorService != null) {
			executorService.shutdown();
		}
	}

	static class SelfThreadFactory implements ThreadFactory {
		final AtomicInteger threadNumber = new AtomicInteger(CORE_POOL_SIZE);
		final String name;

		public SelfThreadFactory(String name) {
			this.name = name + "-";
		}

		@Override
		public Thread newThread(@NonNull Runnable r) {
			Thread thread = new Thread(r, name + threadNumber.getAndIncrement());
			if (thread.getPriority() != Thread.NORM_PRIORITY) {
				thread.setPriority(Thread.NORM_PRIORITY);
			}
			return thread;
		}
	}
}
