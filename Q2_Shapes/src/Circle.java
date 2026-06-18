/**
 * Circle.java
 * Concrete subclass of Shape. Defined by a single radius.
 *
 * DESIGN CHOICE: resize() multiplies radius by factor; doubling the
 * radius quadruples the area, which is the mathematically correct
 * behaviour for a linear scaling factor.
 */
public class Circle extends Shape {

    // ── Private field ─────────────────────────────────────────────────────
    private double radius;

    // ── Constructor ───────────────────────────────────────────────────────
    /**
     * @param color   the colour of the circle
     * @param filled  true if the circle is filled
     * @param radius  must be > 0, else InvalidShapeException is thrown
     */
    public Circle(String color, boolean filled, double radius) {
        super(color, filled);
        if (radius <= 0) {
            throw new InvalidShapeException(
                "Circle radius must be positive. Got: " + radius);
        }
        this.radius = radius;
    }

    // ── Getters / Setters ──────────────────────────────────────────────────
    public double getRadius() { return radius; }

    public void setRadius(double radius) {
        if (radius <= 0) {
            throw new InvalidShapeException(
                "Circle radius must be positive. Got: " + radius);
        }
        this.radius = radius;
    }

    // ── Abstract method overrides ──────────────────────────────────────────
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new InvalidShapeException(
                "Resize factor must be positive. Got: " + factor);
        }
        this.radius *= factor;
    }

    // ── toString ───────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Circle[Color=" + color
             + ", Filled=" + filled
             + ", Radius=" + String.format("%.2f", radius)
             + ", Area=" + String.format("%.2f", getArea())
             + ", Perimeter=" + String.format("%.2f", getPerimeter())
             + "]";
    }
}
