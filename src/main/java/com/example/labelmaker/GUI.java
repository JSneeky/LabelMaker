package com.example.labelmaker;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class GUI extends Application {
    private Label l1;               /// Input FilePath Label
    private Label l2;               /// Output FilePath Label
    private TextField file;         /// Input FilePath Field
    private TextField filePath;     /// Output FilePath Field
    private Button create;
    private TextArea output;

    @Override
    public void start(@NotNull Stage stage) {
        GridPane pane = new GridPane();
        create();
        layout(pane);
        adjust(pane);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("LabelMaker");
        stage.show();
    }

    private void create() {
        file = new TextField("");
        filePath = new TextField("");
        l1 = new Label("Enter input filepath: ");
        l2 = new Label("Enter output filepath: ");
        create = new Button("Create");
        output = new TextArea("");
    }

    private void layout(GridPane pane) {
        pane.add(l1, 1, 2);
        pane.add(l2, 1, 3);
        pane.add(file, 2, 2);
        pane.add(filePath, 2, 3);
        pane.add(create, 3, 3);
        pane.add(output, 2, 5);
    }

    private void adjust(GridPane pane) {
        pane.setPadding(new Insets(10));
        pane.setHgap(10);
        pane.setVgap(10);
        output.setWrapText(true);
        create.setOnMouseClicked(this::click);
    }

    /// On click run the create method in the LabelCreator class
    private void click(MouseEvent event) {
        try {
            String inputfp = file.textProperty().getValue();
            String outputfp = filePath.textProperty().getValue();

            LabelCreator creator = new LabelCreator(inputfp, outputfp);
            creator.create(output);

            output.setText("Labels Created!");
        }
        catch (Exception e) {
            output.setText("Error Creating Labels!");
        }
    }

    public static void main(String[] args) { launch(); }
}
