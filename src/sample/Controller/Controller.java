package sample.Controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import sample.Model.*;



public class Controller {

    private static int HEIGHT;
    private static int WIDTH;
    private static int RESOLUTION;

    @FXML
    private Canvas canvas;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField iterationTextField;
    @FXML
    private Button resetButton;
    @FXML
    private TextField counterTextField;
    private Draw draw;
    private GraphicsContext gc;
    private Grid grid;
    private Life life;
    private Timeline timeline;


    public void initialize() {
        HEIGHT = (int) canvas.getHeight();
        WIDTH = (int) canvas.getWidth();
        RESOLUTION =20;


        gc = canvas.getGraphicsContext2D();
        grid = new Grid(WIDTH, HEIGHT, RESOLUTION);
        draw = new Draw(gc, RESOLUTION);
        life = new Life();
        drawGridLines(WIDTH, HEIGHT);

        startButton.setOnAction(event -> {
            boolean iterationNumberFixed = iterationsNumber();
            if (!iterationNumberFixed) {
                timeline.setCycleCount(Timeline.INDEFINITE);
            } else
                timeline.setCycleCount(1);
            timeline.play();
            canvas.setOnMouseClicked(event1 -> {
            });
        });
        stopButton.setOnAction(event -> {
            timeline.stop();
            timeline.getKeyFrames().clear();
            activateCanvas();
        });
        resetButton.setOnAction((event -> {
            clear();
            drawGridLines(WIDTH, HEIGHT);
        }));

        fillChoice();
        activateCanvas();
        timeline = new Timeline();
        timeline.setOnFinished(event -> {
            timeline.stop();
            timeline.getKeyFrames().clear();
        });

    }


    private boolean iterationsNumber() {
        String input = iterationTextField.getText();
        boolean inputEmpty = input.isEmpty();
        int iterations = inputEmpty ? 1 : Integer.parseInt(input);
        Duration delayBetweenMessages = Duration.seconds(1);
        Duration frame = delayBetweenMessages;
        for (int i = 0; i < iterations; i++) {
            timeline.getKeyFrames().add(new KeyFrame(frame, e -> life.nextGen(grid)));
            timeline.getKeyFrames().add(new KeyFrame(frame, e -> draw.drawCells(grid)));
            String counter = "Generation "+ (i+1);
            timeline.getKeyFrames().add(new KeyFrame(frame, e -> counterTextField.setText(counter)));
            frame = frame.add(delayBetweenMessages);
        }
        return !inputEmpty;
    }
    


    private void fillChoice(){
        ObservableList<String> cursors = FXCollections.observableArrayList("Own","Constant","Glider", "Oscilator", "Random");
        choiceBox.setItems(cursors);
        choiceBox.setValue(0);
        choiceBox.setOnAction(event -> {
            String value = (String) choiceBox.getValue();
            try{
                grid = StartPatterns.getGridForTemplate(value,grid.getWidth(),grid.getHeight());
                draw.drawCells(grid);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

    }

    private void activateCanvas() {
        canvas.setOnMouseClicked(event -> {
            int x = (int) event.getX();
            int y = (int) event.getY();
            draw.drawFromClick(grid, x, y);
        });
    }

    private void drawGridLines(double width, double height) {
        gc.setStroke(Color.BLACK);
        for (int x = 0; x <= width; x += RESOLUTION) {
            gc.strokeLine(x, 0, x, height);
        }

        for (int y = 0; y <= height; y += RESOLUTION) {
            gc.strokeLine(0, y, width, y);
        }
    }



    private void clear() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0,
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
    }



}