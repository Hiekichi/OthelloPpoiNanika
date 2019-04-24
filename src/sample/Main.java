package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    private int[][] board = new int[10][10];
    int currentStone = 1;


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("オセロっぽい何か");
        Group root = new Group();
        Canvas canvas = new Canvas(600, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        initBoard(gc);
        drawBoard(gc);
        canvas.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent e) {
                        int posX = ((int)e.getX()) / 50;
                        int posY = ((int)e.getY()) / 50;
                        System.out.println(posX + "," + posY);
                        if (board[posX][posY] == 0) {
                            board[posX][posY] = currentStone;
                            changeBoard(posX, posY);
                            currentStone = 0 - currentStone;
                            drawBoard(gc);
                        }
                    }

                });

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void initBoard(GraphicsContext gc) {
        board[4][4] = 1;
        board[4][5] = -1;
        board[5][4] = -1;
        board[5][5] = 1;
    }

    private void drawBoard(GraphicsContext gc) {
        // 背景
        gc.setFill(Color.LIMEGREEN);
        gc.fillRect(50, 50, 400, 400);
        // マスの線
        gc.setStroke(Color.GREEN);
        gc.setLineWidth(3);
        for (int y = 0; y < 9; y++) {
            gc.strokeLine(50, y * 50 + 50, 450, y * 50 + 50);
        }
        for (int x = 0; x < 9; x++) {
            gc.strokeLine(x * 50 + 50, 50, x * 50 + 50, 450);
        }
        // 石の描画
        for (int y = 1; y < 9; y++) {
            for (int x = 1; x < 9; x++) {
                if (board[x][y] == 1) {
                    gc.setFill(Color.BLACK);
                    gc.fillOval(x * 50 + 5, y * 50 + 5, 40, 40);
                }
                else if (board[x][y] == -1) {
                    gc.setFill(Color.WHITE);
                    gc.setStroke(Color.BLACK);
                    gc.setLineWidth(3);
                    gc.fillOval(x * 50 + 5, y * 50 + 5, 40, 40);
                    gc.strokeOval(x * 50 + 5, y * 50 + 5, 40, 40);
                }
            }
        }
        // クリック時に置かれる石をアナウンス
        gc.setLineWidth(1);
        gc.setFont(Font.font ("Verdana", 24));
        gc.strokeText("Next", 490, 100);
        if (currentStone == 1) {
            gc.setFill(Color.BLACK);
            gc.fillOval(495, 110, 40, 40);
        }
        else {
            gc.setFill(Color.WHITE);
            gc.setStroke(Color.BLACK);
            gc.setLineWidth(3);
            gc.fillOval(495, 110, 40, 40);
            gc.strokeOval(495, 110, 40, 40);
        }
    }

    private void changeBoard(int posX, int posY) {
        int x, y;
        int stone = board[posX][posY];
        // 上方向
        x = posX;    y = posY - 1;
        while (board[x][y] == 0 - stone) {
            y -= 1;
        }
        if (board[x][y] == stone) {
            y += 1;
            while (board[x][y] == 0 - stone) {
                board[x][y] = stone;
                y += 1;
            }
        }
        // 下方向
        x = posX;    y = posY + 1;
        while (board[x][y] == 0 - stone) {
            y += 1;
        }
        if (board[x][y] == stone) {
            y -= 1;
            while (board[x][y] == 0 - stone) {
                board[x][y] = stone;
                y -= 1;
            }
        }
        // 左方向
        x = posX - 1;    y = posY;
        while (board[x][y] == 0 - stone) {
            x -= 1;
        }
        if (board[x][y] == stone) {
            x += 1;
            while (board[x][y] == 0 - stone) {
                board[x][y] = stone;
                x += 1;
            }
        }
        // 右方向
        x = posX + 1;    y = posY;
        while (board[x][y] == 0 - stone) {
            x += 1;
        }
        if (board[x][y] == stone) {
            x -= 1;
            while (board[x][y] == 0 - stone) {
                board[x][y] = stone;
                x -= 1;
            }
        }
        // 左上方向
        x = posX - 1;    y = posY - 1;
        while (board[x][y] == 0 - stone) {
            x -= 1;    y -= 1;
        }
        if (board[x][y] == stone) {
            x += 1;    y += 1;
            while (board[x][y] == 0 - stone) {
                board[x][y] = stone;
                x += 1;    y += 1;
            }
        }
        // 左下方向
        x = posX - 1;    y = posY + 1;
        while (board[x][y] == 0 - stone) {
            x -= 1;    y += 1;
        }
        if (board[x][y] == stone) {
            x += 1;    y -= 1;
            while (board[x][y] == 0 - stone) {
                board[x][y] = stone;
                x += 1;    y -= 1;
            }
        }
        // 右上方向
        x = posX + 1;    y = posY - 1;
        while (board[x][y] == 0 - stone) {
            x += 1;    y -= 1;
        }
        if (board[x][y] == stone) {
            x -= 1;    y += 1;
            while (board[x][y] == 0 - stone) {
                board[x][y] = stone;
                x -= 1;    y += 1;
            }
        }
        // 右下方向
        x = posX + 1;    y = posY + 1;
        while (board[x][y] == 0 - stone) {
            x += 1;    y += 1;
        }
        if (board[x][y] == stone) {
            x -= 1;    y -= 1;
            while (board[x][y] == 0 - stone) {
                board[x][y] = stone;
                x -= 1;    y -= 1;
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
