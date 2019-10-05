package com.mindera.workshop;

public class App {
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("Exiting.....");
            }
        });

        while (true) {
            System.out.println("Hello World!");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Continue.....");
            }
        }
    }
}