package ru.javacode.student;

import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ComplexTaskExecutor {

    private int taskNumber;
    private ComplexTask[] tasks;

    public ComplexTaskExecutor(int taskNumber) {
        this.taskNumber = taskNumber;
        this.tasks = new ComplexTask[taskNumber];
        for (int i = 0; i < taskNumber; ++i) {
            tasks[i] = new ComplexTask(i + 1);
        }
    }

    public void executeTasks(int numberOfTask) {
        ExecutorService executorService = Executors.newFixedThreadPool(taskNumber);
        CyclicBarrier barrier = new CyclicBarrier(taskNumber, () -> {
            int sum = Arrays.stream(tasks).mapToInt(ComplexTask::getResult).sum();
            System.out.println("Total result: " + sum);
        });

        for (ComplexTask task : tasks) {
            executorService.submit(() -> {
                task.execute();
                try {
                    barrier.await();
                } catch (InterruptedException e) {

                } catch (BrokenBarrierException e) {

                }
            });
        }

        executorService.shutdown();
    }
}
