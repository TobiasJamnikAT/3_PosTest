package euler39_new;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class TriangleWorker implements Callable<Set<Triangle>> {
    int perimeter;

    public TriangleWorker(int perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public Set<Triangle> call() throws Exception {
        Set<Triangle> foundTriangles = new HashSet<>();

        for(int a = 0; a < perimeter; a++){
            for(int b = 0; b < perimeter; b++){
                for(int c = 0; c < perimeter; c++){
                    //rechtwinkeliges dreiekc das umfang gleich hat
                    if(a*a + b*b ==  c && a + b + c == perimeter){
                        foundTriangles.add(new Triangle(a, b, c));
                    }
                }
            }
        }

        return foundTriangles;
    }
}
