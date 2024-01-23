package lab4;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExamController implements Initializable {
    @FXML
    TableView<Exam> ExamTable;
    @FXML
    TableColumn<Exam, Integer> StudentIdCol;
    @FXML
    TableColumn<Exam, String> ExamDateCol;
    @FXML
    TableColumn<Exam, Integer> MarkCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Exam> students = new ArrayList<Exam>();
        Connection conn = null; // для соединения с БД
        Statement stmt = null; // для формирования выражений SQL
        ResultSet rs = null; // для результатов выполнения команд SQL

        StudentIdCol.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("student_id"));
        MarkCol.setCellValueFactory(new PropertyValueFactory<Exam, Integer>("mark"));
        ExamDateCol.setCellValueFactory(new PropertyValueFactory<Exam, String>("exam_date"));

        StudentIdCol.prefWidthProperty().bind(ExamTable.widthProperty().divide(6));
        ExamDateCol.prefWidthProperty().bind(ExamTable.widthProperty().divide(6));
        MarkCol.prefWidthProperty().bind(ExamTable.widthProperty().divide(8));
        try {

            conn = DriverManager
                    .getConnection("jdbc:sqlite:C:/Users/kr1stoffers/Desktop/t/fx/src/lab4/bd.sqlite");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "select exam_marks.student_id, exam_marks.mark, exam_marks.exam_date from exam_marks;");
            while (rs.next()) {
                students.add(new Exam(rs.getInt("student_id"), rs.getInt("mark"), rs.getString("exam_date")));
            }

        } catch (SQLException ex) {
        } finally { // по окончании работы корректно закрываем соединение с БД
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    System.out.println(ex);

                }
            }
        }
        ObservableList<Exam> data = FXCollections.observableArrayList(students);
        ExamTable.setItems(data);// загружаем все из data в таблицу на окне
    }
}