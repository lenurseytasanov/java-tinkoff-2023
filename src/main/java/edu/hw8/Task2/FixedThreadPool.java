package edu.hw8.Task2;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FixedThreadPool implements ThreadPool {

    private static final Logger LOGGER = LogManager.getLogger();

    private final int nThreads;

    private final List<Worker> workers;

    private final List<Runnable> taskQueue;

    private FixedThreadPool(int nThreads) {
        this.nThreads = nThreads;
        this.taskQueue = new ArrayList<>();
        this.workers = new ArrayList<>();
    }

    public static ThreadPool create(int nThreads) {
        return new FixedThreadPool(nThreads);
    }

    @Override
    public void start() {
        for (int i = 0; i < nThreads; i++) {
            Worker worker = new Worker();
            worker.start();
            workers.add(worker);
        }
    }

    @Override
    public void execute(Runnable runnable) {
        synchronized (taskQueue) {
            taskQueue.add(runnable);
            taskQueue.notify();
        }
    }

    @Override
    public void close() {
        for (Worker worker : workers) {
            worker.interrupt();
        }
    }

    private class Worker extends Thread {
        @Override
        public void run() {
            Runnable task;

            while (true) {
                synchronized (taskQueue) {
                    while (taskQueue.isEmpty()) {
                        try {
                            taskQueue.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }

                    task = taskQueue.remove(0);
                }

                try {
                    task.run();
                } catch (RuntimeException e) {
                    LOGGER.error("error: ", e);
                }
            }
        }
    }
}
