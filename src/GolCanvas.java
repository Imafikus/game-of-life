import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class GolCanvas extends Application {

    static int width = 300;
    static int height = 300;
    static int cellSize = 10;
    static int borderOffset = 1;
    static Color emptyCellColor = Color.WHITE;
    static Color fullCellColor = Color.BLUE;
    static int numberOfCells = 10;

    @Override
    public void start(Stage stage) {
        init(stage);
    }

    private void init(Stage stage) {
        Pane root = new Pane();

        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        setRandomCells(gc, numberOfCells);
        drawGrid(gc);

        root.getChildren().addAll(canvas);

        Scene scene = new Scene(root, width, height, Color.WHITE);

        stage.setTitle("Game of Life");
        stage.setScene(scene);
        stage.show();
    }

    private void drawGrid(GraphicsContext gc) {
        gc.setLineWidth(1.0);

        for(int x = 0; x < width; x += cellSize) {
            gc.moveTo(x, 0);
            gc.lineTo(x, height);
            gc.stroke();
        }

        for(int y = 0; y < width; y += cellSize) {
            gc.moveTo(0, y);
            gc.lineTo(width, y);
            gc.stroke();
        }

    }

    private void drawRectangle(GraphicsContext gc, int x, int y) {

        gc.setFill(fullCellColor);
        gc.fillRect(x, y, cellSize, cellSize);

    }

    private void setRandomCells(GraphicsContext gc, int numberOfCells) {
        for(int i = 0; i < numberOfCells; i++) {

            int[] coords = getRandomCoordinates();
            int x = coords[0];
            int y = coords[1];

            drawRectangle(gc, x, y);
        }
    }

    private int[] getRandomCoordinates() {
        int x = (int)(Math.random() * 30) * 10;
        int y = (int)(Math.random() * 30) * 10;

        int[] toReturn = {x, y};
        return  toReturn;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
