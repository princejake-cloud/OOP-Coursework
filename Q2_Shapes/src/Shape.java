/**
 * Shape.java
 * Abstract base class for all geometric shapes.
 *
 * DESIGN CHOICE: Shape is abstract because a "shape in general" has no
 * defined dimensions. You cannot compute the area of something you only
 * know is a shape. Declaring it abstract forces every subclass to supply
 * concrete implementations of getArea(), getPerimeter(), and resize().
 *
 * Attempting "new Shape()" produces a compile-time error:
 *   "Shape is abstract; cannot be instantiated"
 */
public abstract class Shape {

    // ── Protected fields (visible to subclasses) ─────────────────────────
    protected String  color;
    protected boolean filled;

    // ── Constructor 1: default ────────────────────────────────────────────
    public Shape() {
        this.color  = "white";
        this.filled = false;
    }

    // ── Constructor 2: full ───────────────────────────────────────────────
    public Shape(String color, boolean filled) {
        this.color  = color;
        this.filled = filled;
    }

    // ── Abstract methods (every subclass MUST implement these) ────────────
    public abstract double getArea();
    public abstract double getPerimeter();

    /**
     * Scales all linear dimensions by factor.
     * A non-positive factor throws InvalidShapeException.
     *
     * @param factor  scaling multiplier (must be > 0)
     */
    public abstract void resize(double factor);

    // ── Concrete getters/setters ──────────────────────────────────────────
    public String  getColor()           { return color; }
    public boolean isFilled()           { return filled; }
    public void    setColor(String c)   { this.color  = c; }
    public void    setFilled(boolean f) { this.filled = f; }

    // ── Concrete toString ─────────────────────────────────────────────────
    @Override
    public String toString() {
        return "Shape[Color=" + color
             + ", Filled=" + filled
             + ", Area=" + String.format("%.2f", getArea())
             + ", Perimeter=" + String.format("%.2f", getPerimeter())
             + "]";
    }
}
