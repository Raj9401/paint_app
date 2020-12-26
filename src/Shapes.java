/**
 * This is the main class where shapes are created.
 * @author Raj Prajapati
 *Student id = 000797433
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This is the abstrat class of shapes
 */
public abstract class Shapes {
    private double x,y;
    private double x1,y1;

    /**
     *
     * @param x Starting x coordinates of shapes
     * @param y Starting y coordinates of shapes
     * @param x1 Ending x coordinates of shapes
     * @param y1 Ending y coordinates of shapes
     */
    Shapes(double x, double y, double x1, double y1) {
        this.x = x;
        this.y = y;
        this.x1 = x1;
        this.y1 = y1;
    }

    /**
     *
     * @return Returns the first x coordinates
     */
    public double getX() {
        return x;
    }

    /**
     *
     * @return Returns the first y coordinates
     */
    public double getY() {
        return y;
    }

    /**
     *
     * @return Returns the last position x coordinates
     */
    public double getX1() {
        return x1;
    }

    /**
     *
     * @return Returns the last position of y coordinates
     */
    public double getY1() {
        return y1;
    }

    /**
     *  This is the abstract method which will be used in their child class
     * @param gc Graphics context for drawing shapes
     */
    public abstract void draw(GraphicsContext gc);

}
