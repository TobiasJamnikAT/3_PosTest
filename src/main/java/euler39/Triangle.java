package euler39;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Triangle {
    private int [] sides;

    public Triangle(int a, int b, int c) {
        this.sides = new int[]{a, b, c};
        Arrays.sort(sides);
    }

    public int[] getSides() {
        return sides;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return Arrays.equals(sides, triangle.sides);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(sides);
    }

    @Override
    public String toString() {
        int perimeter = sides[0] + sides[1] + sides[2];
        return perimeter + " - " + Arrays.toString(sides);
    }
}
