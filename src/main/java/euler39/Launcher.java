package euler39;

import java.util.Set;
import java.util.concurrent.*;

public class Launcher {
    public void runWorkers() {
        ExecutorService pool = Executors.newFixedThreadPool(16);
        CompletionService<Set<Triangle>> service = new ExecutorCompletionService<>(pool);//CompletionService<!return wert der callable klasse!>

        for(int i = 10; i <= 1000; i++){
            service.submit(new TriangleWorker(i));
        }
        pool.shutdown();//man kann nichts mehr in pool submitten

        Set<Triangle> largestTriangle = null;

        for(int i = 10; i <= 1000; i++){
            try {
                Future<Set<Triangle>> future = service.take();
                Set<Triangle> triangleSet = future.get();
                if(largestTriangle == null || largestTriangle.size() < triangleSet.size()){
                    largestTriangle = triangleSet;
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Largest Triangle = " +largestTriangle);
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        launcher.runWorkers();
    }
}
