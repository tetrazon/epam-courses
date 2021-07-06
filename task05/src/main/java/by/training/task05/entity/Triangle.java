package by.training.task05.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Triangle {

    private Point a;
    private Point b;
    private Point c;

    private String name;
    private int id;

    {
        name = "noname";
    }

    public Triangle() {
    }

    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
        return id == triangle.id &&
                Objects.equals(a, triangle.a) &&
                Objects.equals(b, triangle.b) &&
                Objects.equals(c, triangle.c) &&
                Objects.equals(name, triangle.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c, name, id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Triangle{");
        sb.append("\n name='").append(name);
        sb.append("\n a=").append(a);
        sb.append("\n b=").append(b);
        sb.append("\n c=").append(c);
        sb.append("\n id=").append(id);
        sb.append('}');
        return sb.toString();
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Point{
        private Double x;
        private Double y;
        private Double z;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return Objects.equals(x, point.x) && Objects.equals(y, point.y) && Objects.equals(z, point.z);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, z);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Point{");
            sb.append("x=").append(x);
            sb.append(", y=").append(y);
            sb.append(", z=").append(z);
            sb.append('}');
            return sb.toString();
        }
    }
}
