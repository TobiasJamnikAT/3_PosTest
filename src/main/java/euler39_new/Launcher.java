package euler39_new;

import java.util.Set;
import java.util.concurrent.*;

public class Launcher {
    public Set<Triangle> getBiggestTriangle(){
        ExecutorService pool = Executors.newFixedThreadPool(16);
        CompletionService<Set<Triangle>> service = new ExecutorCompletionService<>(pool);

        for(int i = 0; i <= 1000; i++){
            service.submit(new TriangleWorker(i));
        }
        pool.shutdown();

        Set<Triangle> largestTriangle = null;

        for (int i = 0; i <= 1000; i++){
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

        return largestTriangle;
    }

    public static void main(String[] args) {
        Launcher launcher = new Launcher();
        System.out.println(launcher.getBiggestTriangle());
    }
}
