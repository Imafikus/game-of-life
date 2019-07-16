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

    static Integer width = 300;
    static Integer height = 300;
    static Integer cellSize = 10;
    static Integer borderOffset = 1;
    static Color emptyCellColor = Color.WHITE;
    static Color fullCellColor = Color.BLUE;

    @Override
    public void start(Stage stage) {
        init(stage);
    }

    private void init(Stage stage) {
        Pane root = new Pane();

        Canvas canvas = new Canvas(width, height);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // drawEmptyRectangle(gc);

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

    private void drawRectangle(GraphicsContext gc) {

        gc.setFill(fullCellColor);
        gc.fillRect(0, 0, cellSize, cellSize);

    }

    public static void main(String[] args) {
        launch(args);
    }
}
