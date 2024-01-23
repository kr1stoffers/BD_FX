package lab4;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXMLdbWindowController {
    @FXML
    private BorderPane mainPane;

    @FXML
    private void handleShowStudents(ActionEvent event) throws IOException {
        BorderPane dbPane = new BorderPane(); // базовый контейнер в подгружаемом интерфейсе
        dbPane = (BorderPane) FXMLLoader.load(getClass().getResource("Students.fxml"));
        TableView myTable = (TableView) dbPane.getCenter();
        mainPane.setCenter(myTable);
    }

    @FXML
    private void handleShowLecturer(ActionEvent event) throws IOException {
        BorderPane dbPane = new BorderPane(); // базовый контейнер в подгружаемом интерфейсе
        dbPane = (BorderPane) FXMLLoader.load(getClass().getResource("Lecturer.fxml"));
        TableView myTable = (TableView) dbPane.getCenter();
        mainPane.setCenter(myTable);
    }

    @FXML
    private void handleShowExam(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Exam.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainPane.getScene().getWindow());
        stage.show();
    }

    @FXML
    private void handleShowSubject(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Subject.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(mainPane.getScene().getWindow());
        stage.show();
    }

}
