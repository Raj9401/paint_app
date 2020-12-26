/**
 * This is the main class where rectangle is created.
 * @author Raj Prajapati
 *Student id = 000797433
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This rectangle class extends shapes class
 */
public class Rectangle extends Shapes {
    private double x, y, x1, y1, w; // Coordinates for the starting and ending position
    private Color rectangleColor = Color.BLUE;
    private Color strokeColor = Color.BLACK;

    /**
     *
     * @param sx Starting x coordinates of rectangle
     * @param sy Starting y coordinates of rectangle
     * @param ex Ending x coordinates of rectangle
     * @param ey Ending y coordinates of rectangle
     * @param w  Width of rectangle
     * @param rectangleColor Fill color of rectangle
     */

    public Rectangle(double sx, double sy, double ex, double ey, int w, Color rectangleColor) {
        super(sx, sy, ex, ey);
        this.x = sx;
        this.y = sy;
        this.x1 = ex;
        this.y1 = ey;
        this.w = w;
        this.rectangleColor = rectangleColor;
    }

    /**
     *
     * @param w setting width of the rectangle
     */
    public void setW(double w) {
        this.w = w;
    }

    /**
     *
     * @param rectangleColor setting color for the rectangle
     */
    public void setRectangleColor(Color rectangleColor) {
        this.rectangleColor = rectangleColor;
    }

    /**
     *  Setting color for the stroke
     * @param strokeColor
     */
    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    /**
     *  This method draws the rectangle and its an abstract method
     * @param gc Graphics context for drawing the rectangle
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(rectangleColor);
        double abx = Math.abs(x1 - x);
        double aby = Math.abs(y1 - y);
        double minX = Math.min(x1, x);
        double minY = Math.min(y1, y);
        gc.setStroke(strokeColor);
        gc.setLineWidth(w);
        gc.fillRect(minX, minY, abx, aby);

    }
}




