public class Vector {
    public double x;
    public double y;
    public double z;

    public Vector() {
        x = 0;
        y = 0;
        z = 0;
    }

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return x + ", " + y + ", " + z;
    }

    public static Vector cross(Vector u, Vector v) {
        return new Vector(
                u.y * v.z - u.z * v.y,
                u.z * v.x - u.x * v.z,
                u.x * v.y - u.y * v.x
        );
    }

    public static Vector add(Vector u, Vector v) {
        return new Vector(
                u.x + v.x,
                u.y + v.y,
                u.z + v.z
        );
    }

    public static Vector sub(Vector u, Vector v) {
        return new Vector(
                u.x - v.x,
                u.y - v.y,
                u.z - v.z
        );
    }

    public static Vector divide(Vector u, double f) {
        return new Vector (
            u.x / f,
            u.y / f,
            u.z / f
        );
    }

    public static boolean equals(Vector u, Vector v) {
        return u.x == v.x && u.y == v.y && u.z == v.z;
    }

    public static double magnitude(Vector v) {
        return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }

    public static Vector normalize(Vector v) {
        return Vector.divide(v, Vector.magnitude(v));
    }

    public static double dot(Vector u, Vector v) {
        return u.x * v.x + u.y * v.y + u.z * v.z;
    }

    public Vector cross(Vector other) {
        return Vector.cross(this, other);
    }

    public Vector add(Vector other) {
        return Vector.add(this, other);
    }

    public Vector sub(Vector other) {
        return Vector.sub(this, other);
    }

    public Vector divide(double f) {
        return Vector.divide(this, f);
    }

    public boolean equals(Vector other) {
        return Vector.equals(this, other);
    }

    public double magnitude() {
        return Vector.magnitude(this);
    }

    public Vector normalized() {
        return Vector.normalize(this);
    }

    public double dot(Vector other) {
        return Vector.dot(this, other);
    }
}
