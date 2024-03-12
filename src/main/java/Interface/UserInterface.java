package Interface;

import BoardAndGameLogic.*;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Arrays;

public class UserInterface extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        GridPane grid = new GridPane();
        Game game = new Game();
        updateBoard(grid, game);
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void updateBoard(GridPane grid, Game game) {
        // Clear the GridPane
        grid.getChildren().clear();
        // Instantiate the board
        Board board = game.getBoard();
        // Variables for creating the board
        int count = 0;
        double s = 100;
        // Going through the board
        for (int i = 0; i < 8; i++) {
            count++;
            for (int j = 0; j < 8; j++) {
                // Color rectangles
                Rectangle r = new Rectangle(s, s, s, s);
                // Clickable action rectangles
                Rectangle r2 = new Rectangle(s, s, s, s);
                // Getting the current case from the board
                String caseName = board.getCases()[i][j].getName();
                Case currentCase = board.getCases()[i][j];
                r2.setId(caseName);
                // Mouse clicked event
                r2.setOnMouseClicked(
                        mouseEvent -> {
                            // Game logic calling
                            game.click(caseName);
                            System.out.println(r2);
                            // Updating the board
                            updateBoard(grid, game);
                        }
                );
                // Colors filling
                if (count % 2 == 0)
                    r.setFill(Color.WHITE);
                else
                    r.setFill(Color.rgb(118,150,86));
                // Adding the color rectangles in the GridPane
                grid.add(r, i , j);
                // Adding the Piece image
                if(currentCase.getPiece() != null) {
                    Image img = new Image(currentCase.getPiece().getImage());
                    ImageView imgView = new ImageView(img);
                    imgView.setVisible(true);
                    imgView.fitWidthProperty().bind(r.widthProperty().subtract(2));
                    imgView.fitHeightProperty().bind(r.heightProperty().subtract(2));
                    grid.add(imgView, i, j);
                }
                // Filling the action rectangles transparent
                r2.setFill(Color.TRANSPARENT);
                // Adding the action rectangles to the GridPane
                grid.add(r2, i, j);
                count++;
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}