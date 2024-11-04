import java.util.Random;

public class Lab_8 {
    private static class MonteCarloTask implements Runnable {
        private final long iterations;
        private long pointsInsideCircle;

        public MonteCarloTask(long iterations) {
            this.iterations = iterations;
        }

        @Override
        public void run() {
            Random random = new Random();
            long count = 0;

            for (long i = 0; i < iterations; i++) {
                double x = random.nextDouble();
                double y = random.nextDouble();
                if (x * x + y * y <= 1) {
                    count++;
                }
            }

            pointsInsideCircle = count;
        }

        public long getPointsInsideCircle() {
            return pointsInsideCircle;
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Lab_8 <number_of_threads>");
            return;
        }
        System.out.println("Очікуйте .... \n");

        int numThreads = Integer.parseInt(args[0]);
        long totalIterations = 1_000_000_000; // Total number of iterations
        long iterationsPerThread = totalIterations / numThreads;

        Thread[] threads = new Thread[numThreads];
        MonteCarloTask[] tasks = new MonteCarloTask[numThreads];

        long startTime = System.currentTimeMillis();

        // Start threads
        for (int i = 0; i < numThreads; i++) {
            tasks[i] = new MonteCarloTask(iterationsPerThread);
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }

        // Wait for all threads to finish
        long totalPointsInsideCircle = 0;
        try {
            for (int i = 0; i < numThreads; i++) {
                threads[i].join();
                totalPointsInsideCircle += tasks[i].getPointsInsideCircle();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        double piApproximation = 4.0 * totalPointsInsideCircle / totalIterations;
        long endTime = System.currentTimeMillis();

        System.out.printf("PI is %.5f\n", piApproximation);
        System.out.printf("THREADS %d\n", numThreads);
        System.out.printf("ITERATIONS %d\n", totalIterations);
        System.out.printf("TIME %.2fms\n", (endTime - startTime) / 1000.0);
    }
}
