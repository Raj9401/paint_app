/**
 * This is the main class where all shapes are being created.
 * @author Raj Prajapati
 *Student id = 000797433
 */
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * This class creates shapes and handles exception
 */
public class Main extends Application {
        // Final value of width, height and canvas height
        final double w = 1000;
        final double h = 800;
        final double cH = 100;
        //Graphics context for gc and draggc
        private GraphicsContext gc, draggc;
        private double x, y; // Starting points of canvas
        private double x1, y1; // Ending points of canvas
        private int shape; // Instance variable for shape to be selected
        private TextField width1; // Textfield for stroke width of line
        private Button line, rectangle, clear; // Buttons for line, rectangle and clear
        private Label colorLabel,widthLabel; // Labels for the colorpicker and line stroke
        private Color lineColor = Color.BLUE;// Default color of th shapes
        private ColorPicker lineColorPicker;// Color picker to pick colors
        private ArrayList<Shapes> shapeArrayList;// Declaring array list of shapes



        @Override
        public void start(Stage stage) throws Exception {
            Pane root = new Pane();
            Scene scene = new Scene(root, w, h, Color.DARKGRAY); // set the size here
            stage.setTitle("Paint"); // set the window title here
            stage.setScene(scene);
            Canvas canvas = new Canvas(w, h - cH);// New canvas for drawing shapes
            canvas.relocate(0, cH); //  Relocating canvas
            Canvas transc = new Canvas(w,h);//  New canvas for the dragging
            transc.relocate(0,cH); // Relocating trans canvas

            shapeArrayList = new ArrayList<>();// Creating arraylist
            colorLabel = new Label("Line Color");// Labelling the color label
            lineColorPicker = new ColorPicker(lineColor);// Creating the color picker
            line = new Button("line"); //Input line button to draw line
            rectangle = new Button("Rectangle");//Input rectangle button to draw rectangle
            widthLabel = new Label ("Stroke Width");//New wlabel for width
            width1 = new TextField("5");
            clear = new Button("Clean Screen");// // Clear button for clearing the screen

            // Relocating all the buttons, labels and textfields
            colorLabel.relocate(20, 50);
            lineColorPicker.relocate(100, 45);
            line.relocate(300,45);
            rectangle.relocate(400,45);
            width1.relocate(600,45);
            widthLabel.relocate(500,50);
            clear.relocate(850,45);

            //  Adding components to the root
            root.getChildren().addAll(canvas, transc, lineColorPicker, colorLabel, line, rectangle, width1, widthLabel, clear);

            // Creating the two graphics contexts
            gc = canvas.getGraphicsContext2D();
            draggc = transc.getGraphicsContext2D();
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(0, 0, w,h);

            // Adding Event Handlers and do final setup
            transc.addEventHandler(MouseEvent.MOUSE_PRESSED, this::pressHandler);// Press Handler works when mbutton is pressed
            transc.addEventHandler(MouseEvent.MOUSE_RELEASED, this::releaseHandler);// Released handler works when mouse button releases
            transc.addEventHandler(MouseEvent.MOUSE_DRAGGED, this::dragHandler);// Dragged handler works when mouse button drages
            line.setOnAction(this::line);
            rectangle.setOnAction(this::rectangle);
            clear.setOnAction(this::Clear);

            // Showing the stage
            stage.show();

        }

    /**
     *
     * @param me When press it handles the pressHandler
     */
    private void pressHandler(MouseEvent me) {
            x = me.getX();
            y = me.getY();
        }

    /**
     *
     * @param e Action event for creating line
     */
    private void line(ActionEvent e){
            shape = 1;
        }

    /**
     *
     * @param e Action Event for creating the rectangle
     */
    private void rectangle(ActionEvent e){
            shape = 2;
        }

    /**
     *
     * @param me Mouse event for dragging in canvas
     */
    private void dragHandler(MouseEvent me) {
            draggc.clearRect(0, 0, w, h);// Clear the canvas
        //Exception handling for going out of canvas
            if(me.getY()>h|| me.getX()>w){
                new Alert (Alert.AlertType.ERROR, "Sorry, Thats Out Of Canvas").showAndWait();
            }
            else {
                int wid = 0;
                //Exception handling through try and catch for invalid line width
                try {
                    wid = Integer.parseInt(width1.getText());
                } catch (NumberFormatException ne) {
                    new Alert(Alert.AlertType.ERROR, "Invalid line width").showAndWait();// Error message box for invalid line width
                }
                //If shape is 1 then it creates line
                if (shape == 1) {
                    x1 = me.getX();
                    y1 = me.getY();
                    Line tempLine = new Line(x, y, x1, y1, wid, lineColorPicker.getValue());// Creating line object
                    tempLine.draw(draggc);

                }
                //If shape is 2 then it creates rectangle
                else if (shape == 2) {
                    x1 = me.getX();
                    y1 = me.getY();
                    Rectangle tempRec = new Rectangle(x, y, x1, y1, wid, lineColorPicker.getValue());// Creating rectangle object
                    tempRec.draw(draggc);
                }
            }
        }

    /**
     *
     * @param me Mouse event when pressed , it works when mouse button is released
     */
    private void releaseHandler(MouseEvent me) {
            // Converts texts in integer
            int w = Integer.parseInt(width1.getText());
            //Get the coordinates of ending x and y
            x1 = me.getX();
            y1 = me.getY();
                if (shape == 1) {
                    Line l = new Line(x, y, x1, y1, w, lineColorPicker.getValue());
                    shapeArrayList.add(l);
                    gc.setFill(Color.LIGHTGREEN);
                    gc.fillRect(0, 0, w, h - cH);
                    draggc.clearRect(0, 0, w, h);
                }
            else if (shape == 2) {
                Rectangle r = new Rectangle(x, y, x1, y1, w,lineColorPicker.getValue());
                shapeArrayList.add(r);
                gc.setFill(Color.LIGHTGREEN);
                gc.fillRect(0, 0, w, h - cH);
                draggc.clearRect(0, 0, w, h);
            }

            for (Shapes s1 : shapeArrayList) s1.draw(gc);// Drawing shapes whichever are there in array list
        }

    /**
     *
     * @param e Action Event for cleaing the whole canvas
     */
    public void Clear(ActionEvent e)
        {
            draggc.clearRect(0,0,w,h);
            shapeArrayList.clear();
            for (Shapes s : shapeArrayList) s.draw(gc);
            gc.clearRect(0,0,w,h-cH);
            gc.setFill(Color.LIGHTGREEN);
            gc.fillRect(0,0,w,h-cH);
        }


    public static void main(String[] args){launch(args);}
}