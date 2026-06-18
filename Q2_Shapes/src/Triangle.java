/**
 * Triangle.java
 * Concrete subclass of Shape. Defined by three sides a, b, c.
 *
 * Validation in the constructor:
 *   1. All sides must be positive.
 *   2. Triangle inequality: each side must be less than the sum of the
 *      other two (a < b+c, b < a+c, c < a+b).
 *
 * Area is computed using Heron's formula.
 * resize() scales all three sides by factor.
 */
public class Triangle extends Shape {

    // ── Private fields ────────────────────────────────────────────────────
    private double sideA;
    private double sideB;
    private double sideC;

    // ── Constructor ───────────────────────────────────────────────────────
    public Triangle(String color, boolean filled,
                    double sideA, double sideB, double sideC) {
        super(color, filled);

        // Validate: all sides positive
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new InvalidShapeException(
                "All triangle sides must be positive. Got: "
                + sideA + ", " + sideB + ", " + sideC);
        }

        // Validate: triangle inequality
        if (sideA >= sideB + sideC ||
            sideB >= sideA + sideC ||
            sideC >= sideA + sideB) {
            throw new InvalidShapeException(
                "Triangle inequality violated. Sides "
                + sideA + ", " + sideB + ", " + sideC
                + " cannot form a valid triangle.");
        }

        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    // ── Getters ───────────────────────────────────────────────────────────
    public double getSideA() { return sideA; }
    public double getSideB() { return sideB; }
    public double getSideC() { return sideC; }

    // ── Abstract method overrides ──────────────────────────────────────────
    @Override
    public double getArea() {
        // Heron's formula
        double s = (sideA + sideB + sideC) / 2.0;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double getPerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new InvalidShapeException(
                "Resize factor must be positive. Got: " + factor);
        }
        this.sideA *= factor;
        this.sideB *= factor;
        this.sideC *= factor;
    }

    // ── toString ───────────────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Triangle[Color=" + color
             + ", Filled=" + filled
             + ", Sides=" + String.format("%.2f", sideA)
             + "/" + String.format("%.2f", sideB)
             + "/" + String.format("%.2f", sideC)
             + ", Area=" + String.format("%.2f", getArea())
             + ", Perimeter=" + String.format("%.2f", getPerimeter())
             + "]";
    }
}
