package euler39;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;

public class TriangleWorker implements Callable<Set<Triangle>> {//wie Runnable aber man kann wert zurück gegen (in diesem Fall ein Set)
    private int perimeter;

    public TriangleWorker(int perimeter) {
        this.perimeter = perimeter;
    }

    @Override
    public Set<Triangle> call() throws Exception {
        Set<Triangle> foundTriangles = new HashSet<>();
        for(int a = 1; a < perimeter; a++){
            for(int b = 1; b < perimeter; b++){
                for(int c = 1; c < perimeter; c++){
                    if(a*a + b*b == c && a + b+ c == perimeter){
                        foundTriangles.add(new Triangle(a, b, c));
                    }
                }
            }
        }

        return foundTriangles;
    }
}
