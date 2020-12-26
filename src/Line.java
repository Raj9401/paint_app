/**
 * This is the main class where line is created
 * @author Raj Prajapati
 *Student id = 000797433
 */
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.*;

/**
 * This line class extends shapes class and implements its abstract nethid
 */
public class Line extends Shapes {
    private double x,y,x1,y1;
    private int w;
    private Color linecolor = Color.BLACK;

    /**
     *
     * @param x Starting x coordinates of line
     * @param y Starting y coordinates of line
     * @param x Ending x coordinates of line
     * @param y Ending y coordinates of line
     * @param w  Width of line
     * @param linecolor Fill color of line
     */
    public Line(double x, double y, double x1, double y1, int w, Color linecolor) {
        super(x, y, x1, y1);
        this.x=x;
        this.y=y;
        this.x1=x1;
        this.y1=y1;

        this.w = w;
        this.linecolor = linecolor;
    }
    /**
     *
     * @param w setting width of the line
     */
    public void setW(int w) {
        this.w = w;
    }

    /**
     *
     * @param linecolor setting color for the line
     */
    public void setLinecolor(Color linecolor) {
        this.linecolor = linecolor;
    }

    /**
     * This is the abstract method of shapes class
     * @param gc Graphics context for drawing shapes
     */
    @Override
    public void draw(GraphicsContext gc) {
        gc.setStroke(linecolor);
        gc.setLineWidth(w);
        gc.strokeLine(x,y,x1,y1);
    }
}
