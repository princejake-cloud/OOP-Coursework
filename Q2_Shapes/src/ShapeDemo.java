/**
 * ShapeDemo.java
 * Driver class for Question 2. Demonstrates:
 *   - Creating shapes
 *   - Catching InvalidShapeException for an invalid Triangle
 *   - printAreas() showing polymorphism / dynamic binding
 *   - largest() returning the shape with the biggest area
 *   - resize() with a valid and invalid factor
 */
public class ShapeDemo {

    // ── printAreas: polymorphism via superclass reference ────────────────
    /**
     * DYNAMIC BINDING EXPLANATION:
     * The array type is Shape[]. At runtime, each element's actual class
     * (Circle, Rectangle, Triangle) determines which getArea() is called.
     * This is dynamic binding. The compiler only knows "Shape", but at
     * runtime Java dispatches to the correct subclass method.
     *
     * Example line from output:
     *   "Shape #1 area: 78.54"
     * Even though shapes[0] is declared as Shape, Java calls Circle.getArea()
     * because the object IS a Circle. That is dynamic binding in action.
     */
    public static void printAreas(Shape[] shapes) {
        System.out.println("\n--- printAreas() --- [demonstrates dynamic binding]");
        for (int i = 0; i < shapes.length; i++) {
            System.out.printf("  Shape #%d (%s) area: %.2f%n",
                (i + 1),
                shapes[i].getClass().getSimpleName(),
                shapes[i].getArea());
        }
    }

    // ── largest: returns the Shape with the biggest area ─────────────────
    public static Shape largest(Shape[] shapes) {
        if (shapes == null || shapes.length == 0) return null;
        Shape max = shapes[0];
        for (int i = 1; i < shapes.length; i++) {
            if (shapes[i].getArea() > max.getArea()) {
                max = shapes[i];
            }
        }
        return max;
    }

    // ── main ──────────────────────────────────────────────────────────────
    public static void main(String[] args) {

        System.out.println("============================================");
        System.out.println("       SHAPES DRAWING PACKAGE DEMO          ");
        System.out.println("============================================\n");

        // ── Step 1: Create valid shapes ──────────────────────────────────
        System.out.println(">>> Creating valid shapes...");
        Shape circle    = new Circle   ("red",   true,  5.0);
        Shape rectangle = new Rectangle("blue",  false, 4.0, 6.0);
        Shape triangle  = new Triangle ("green", true,  3.0, 4.0, 5.0);

        System.out.println("  " + circle);
        System.out.println("  " + rectangle);
        System.out.println("  " + triangle);

        // ── Step 2: Demonstrate InvalidShapeException (bad triangle) ─────
        System.out.println("\n>>> Attempting to create an INVALID triangle (1, 2, 10)...");
        try {
            Shape badTriangle = new Triangle("black", false, 1.0, 2.0, 10.0);
            System.out.println("  ERROR: This line should never print.");
        } catch (InvalidShapeException e) {
            System.out.println("  [CAUGHT InvalidShapeException] " + e.getMessage());
        }

        // ── Step 3: Demonstrate InvalidShapeException (bad resize) ───────
        System.out.println("\n>>> Attempting to resize circle with factor -2...");
        try {
            circle.resize(-2);
        } catch (InvalidShapeException e) {
            System.out.println("  [CAUGHT InvalidShapeException] " + e.getMessage());
        }

        // ── Step 4: Valid resize ──────────────────────────────────────────
        System.out.println("\n>>> Resizing rectangle by factor 2 (doubles each side)...");
        System.out.println("  Before: " + rectangle);
        rectangle.resize(2);
        System.out.println("  After : " + rectangle);

        // ── Step 5: printAreas (polymorphism demo) ────────────────────────
        Shape[] shapes = { circle, rectangle, triangle };
        printAreas(shapes);

        // ── Step 6: largest ───────────────────────────────────────────────
        System.out.println("\n--- largest() ---");
        Shape big = largest(shapes);
        System.out.println("  Largest shape: " + big);

        // ── Step 7: Design explanation ────────────────────────────────────
        System.out.println("\n============================================");
        System.out.println("  OOP / POLYMORPHISM NOTES");
        System.out.println("============================================");
        System.out.println("Dynamic Binding: In printAreas(), shapes[0].getArea() calls");
        System.out.println("  Circle.getArea() at runtime even though the array type is Shape[].");
        System.out.println("  Java dispatches to the correct override at runtime.");
        System.out.println();
        System.out.println("Why Shape is abstract: A bare 'shape' has no dimensions.");
        System.out.println("  Declaring it abstract forces each subclass to provide");
        System.out.println("  getArea(), getPerimeter(), and resize().");
        System.out.println("  'new Shape()' fails at compile time with:");
        System.out.println("  'Shape is abstract; cannot be instantiated'.");
        System.out.println();
        System.out.println("InvalidShapeException is UNCHECKED (extends RuntimeException).");
        System.out.println("  Reason: an invalid dimension is a programming error, not a");
        System.out.println("  recoverable I/O condition. Callers are not forced to declare");
        System.out.println("  'throws', but can still catch it in a try-catch block.");
        System.out.println("============================================\n");
    }
}
