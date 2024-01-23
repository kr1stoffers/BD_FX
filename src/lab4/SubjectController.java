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

public class SubjectController implements Initializable {
    @FXML
    TableView<Subject> SubjectTable;
    @FXML
    TableColumn<Subject, String> subjNameCol;
    @FXML
    TableColumn<Subject, Integer> hourCol;
    @FXML
    TableColumn<Subject, Integer> semesterCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Subject> students = new ArrayList<Subject>();
        Connection conn = null; // для соединения с БД
        Statement stmt = null; // для формирования выражений SQL
        ResultSet rs = null; // для результатов выполнения команд SQL

        subjNameCol.setCellValueFactory(new PropertyValueFactory<Subject, String>("subj_name"));
        hourCol.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("hour"));
        semesterCol.setCellValueFactory(new PropertyValueFactory<Subject, Integer>("semester"));

        subjNameCol.prefWidthProperty().bind(SubjectTable.widthProperty().divide(8));
        hourCol.prefWidthProperty().bind(SubjectTable.widthProperty().divide(15));
        semesterCol.prefWidthProperty().bind(SubjectTable.widthProperty().divide(9));
        try {

            conn = DriverManager
                    .getConnection("jdbc:sqlite:C:/Users/kr1stoffers/Desktop/t/fx/src/lab4/bd.sqlite");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "select subject.subj_name, subject.hour, subject.semester from subject;");
            while (rs.next()) {
                students.add(new Subject(rs.getString("subj_name"), rs.getInt("hour"),
                        rs.getInt("semester")));
            }

        } catch (SQLException ex) { // обрабатываем исключение для загрузки
            // Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null,
            // ex);
            System.out.println(ex);
        } finally { // по окончании работы корректно закрываем соединение с БД
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE,null,
                    // ex);
                }
            }
        }
        ObservableList<Subject> data = FXCollections.observableArrayList(students);
        SubjectTable.setItems(data);// загружаем все из data в таблицу на окне
    }
}
