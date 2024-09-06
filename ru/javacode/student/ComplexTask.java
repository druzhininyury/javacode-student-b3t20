package ru.javacode.student;

import java.util.Random;

public class ComplexTask {

    private final int id;
    private int result;

    public ComplexTask(int id) {
        this.id = id;
    }

    public void execute() {
        try {
            System.out.println("Task " + id + " started.");
            Thread.sleep(1000L);
            result = 9 + new Random().nextInt(91); // generating two digit number
            System.out.println("Task " + id + " finished(" + result + ").");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getResult() {
        return result;
    }

}
