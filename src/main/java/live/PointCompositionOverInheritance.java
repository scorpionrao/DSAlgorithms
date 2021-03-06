package live;

public class PointCompositionOverInheritance
{

    /*
        The main reason why inheritance is problematic in this case is that `PointCompositionOverInheritance`
        is a non-abstract super class that implements equals. It is impossible to implement
        equals() correctly in Java with a non-abstract super class as the general contract
        of equals cannot be satisfied under all conditions.
     */
    public static class PointOnScreen {
        int x;
        int y;
        public PointOnScreen(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            boolean isNull = o == null;
            if(isNull) {
                return false;
            }
            boolean isSameMemoryReference = this == o;
            if(isSameMemoryReference) {
                return true;
            }
            boolean isInstance = o instanceof PointOnScreen;
            System.out.println(o.getClass().getName() + " is instance of " +
                    this.getClass().getName() + " = " + isInstance);
            if(!isInstance) {
                return false;
            }
            PointOnScreen pointOnScreen = (PointOnScreen) o;
            return x == pointOnScreen.x && y == pointOnScreen.y;
        }
    }

    public static class Color {
        int red;
        int blue;
        int green;
        public Color(int red, int blue, int green) {
            this.red = red;
            this.blue = blue;
            this.green = green;
        }

        @Override
        public boolean equals(Object o) {
            boolean isNull = o == null;
            if(isNull) {
                return false;
            }
            boolean isSameMemoryReference = this == o;
            if(isSameMemoryReference) {
                return true;
            }
            boolean isInstance = o instanceof Color;
            if(!isInstance) {
                return false;
            }
            Color color = (Color) o;
            return red == color.red && blue == color.blue && green == color.green;
        }
    }

    public static class ColorPointOnScreen extends PointOnScreen {
        Color color;
        public ColorPointOnScreen(int x, int y, int red, int green, int blue) {
            super(x, y);
            this.color = new Color(red, green, blue);
        }

        @Override
        public boolean equals(Object o) {
            boolean isNull = o == null;
            if(isNull) {
                return false;
            }
            boolean isSameMemoryReference = this == o;
            if(isSameMemoryReference) {
                return true;
            }
            boolean isInstance = o instanceof ColorPointOnScreen;
            System.out.println(o.getClass().getName() + " is instance of " +
                    this.getClass().getName() + " = " + isInstance);
            if(!isInstance) {
                return false;
            }
            ColorPointOnScreen colorPointOnScreen = (ColorPointOnScreen) o;
            return colorPointOnScreen.color.equals(color)
                    && colorPointOnScreen.x == this.x
                    && colorPointOnScreen.y == this.y;
        }
    }

    public static void main(String[] args) {
        PointOnScreen pointOnScreen = new PointOnScreen(5, 10);
        ColorPointOnScreen colorPointOnScreen = new ColorPointOnScreen(5, 10, 255, 0, 0);

        // sub class instance of superclass = true
        pointOnScreen.equals(colorPointOnScreen);
        // super class is NOT instance of subclass
        colorPointOnScreen.equals(pointOnScreen);

        // Lesson learnt - When combining two classes, inheritance makes resultant and one class unequal.
    }
}