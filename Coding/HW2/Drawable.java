package HW2;

interface Drawable {
    void draw();
}

abstract class Shape {
    protected String color;

    public Shape(String color) {
        this.color = color;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    public String getColor() {
        return this.color;
    }
    
}

class Rectangle extends Shape implements Drawable{
    private double width;
    private double height;

    public Rectangle(String color, double width, double height) {
        super(color);
        this.width = width;
        this.height = height;
    }
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " rectangle");
    }
    @Override
    public double getArea() {
        return width * height;
    }
    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

}

class Circle extends Shape implements Drawable{
    private double radius;

    public Circle (String color, double radius) {
        super(color);
        this.radius = radius;
    }
    @Override
    public void draw() {
        System.out.println("Drawing a " + color + " circle");
    }
    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }
}

class Test {
    public static void main (String[] args) {
        Shape[] shapes = {
            new Rectangle("red", 4, 5),
            new Circle("Blue", 3)
        };

        for (Shape s : shapes) {
            System.out.println("Color: " + s.getColor());
            System.out.println("Area: " + s.getArea());
            System.out.println("Perimeter: " + s.getPerimeter());
            if (s instanceof Drawable) {
                ((Drawable)s).draw();
            }
        }
    }
}