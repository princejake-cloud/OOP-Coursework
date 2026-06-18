/**
 * Rectangle.java
 * Concrete subclass of Shape. Defined by width and height.
 *
 * resize() scales BOTH dimensions by factor so that area scales by
 * factor^2 (correct for a 2-D linear scaling).
 */
public class Rectangle extends Shape {

    // ── Private fields ────────────────────────────────────────────────────
    private double width;
    private double height;

    // ── Constructor ───────────────────────────────────────────────────────
    public Rectangle(String color, boolean filled, double width, double height) {
        super(color, filled);
        if (width <= 0 || height <= 0) {
            throw new InvalidShapeException(
                "Rectangle width and height must both be positive. Got: "
                + width + " x " + height);
        }
        this.width  = width;
        this.height = height;
    }

    // ── Getters / Setters ──────────────────────────────────────────────────
    public double getWidth()  { return width; }
    public double getHeight() { return height; }

    public void setWidth(double width) {
        if (width <= 0) throw new InvalidShapeException("Width must be positive.");
        this.width = width;
    }

    public void setHeight(double height) {
        if (height <= 0) throw new InvalidShapeException("Height must be positive.");
        this.height = height;
    }

    // ── Abstract method overrides ──────────────────────────────────────────
    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new InvalidShapeException(
                "Resize factor must be positive. Got: " + factor);
        }
        this.width  *= factor;
        this.height *= factor;
    }

    // ── toString ───────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Rectangle[Color=" + color
             + ", Filled=" + filled
             + ", Width=" + String.format("%.2f", width)
             + ", Height=" + String.format("%.2f", height)
             + ", Area=" + String.format("%.2f", getArea())
             + ", Perimeter=" + String.format("%.2f", getPerimeter())
             + "]";
    }
}
