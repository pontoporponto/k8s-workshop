package com.mindera.workshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/workshop")
public class BasicController {
    private final RestTemplate client;
    private boolean shutdown = Boolean.FALSE;

    @Autowired
    public BasicController() {
        this.client = new RestTemplate();
    }

    @GetMapping("/stress")
    public ResponseEntity<String> basicEndpoint(@RequestParam("load") Double load, @RequestParam("duration") Integer duration) {
        for (int thread = 0; thread < 4; thread++) {
            new BusyThread("Thread" + thread, load, duration * 1000).start();
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/shutdown")
    public String shutdown() {
        shutdown = Boolean.TRUE;
        return "Shutting down....";
    }

    /**
     * Thread that actually generates the given load
     *
     * @author Sriram
     */
    private static class BusyThread extends Thread {
        private double load;
        private long duration;

        /**
         * Constructor which creates the thread
         *
         * @param name     Name of this thread
         * @param load     Load % that this thread should generate
         * @param duration Duration that this thread should generate the load for
         */
        public BusyThread(String name, double load, long duration) {
            super(name);
            this.load = load;
            this.duration = duration;
        }

        /**
         * Generates the load when run
         */
        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            try {
                // Loop for the given duration
                while (System.currentTimeMillis() - startTime < duration) {
                    // Every 100ms, sleep for the percentage of unladen time
                    if (System.currentTimeMillis() % 100 == 0) {
                        Thread.sleep((long) Math.floor((1 - load) * 100));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
