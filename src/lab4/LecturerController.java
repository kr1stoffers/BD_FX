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

public class LecturerController implements Initializable {
    @FXML
    TableView<Lecturer> LecturerTable;
    @FXML
    TableColumn<Lecturer, String> surnameCol;
    @FXML
    TableColumn<Lecturer, String> nameCol;
    @FXML
    TableColumn<Lecturer, String> cityCol;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Lecturer> students = new ArrayList<Lecturer>();
        Connection conn = null; // для соединения с БД
        Statement stmt = null; // для формирования выражений SQL
        ResultSet rs = null; // для результатов выполнения команд SQL

        surnameCol.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("surname"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("name"));
        cityCol.setCellValueFactory(new PropertyValueFactory<Lecturer, String>("city"));

        surnameCol.prefWidthProperty().bind(LecturerTable.widthProperty().divide(6));
        nameCol.prefWidthProperty().bind(LecturerTable.widthProperty().divide(6));
        cityCol.prefWidthProperty().bind(LecturerTable.widthProperty().divide(8));
        try {

            conn = DriverManager
                    .getConnection("jdbc:sqlite:C:/Users/kr1stoffers/Desktop/t/fx/src/lab4/bd.sqlite");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(
                    "select lecturer.surname, lecturer.name, lecturer.city from lecturer;");
            while (rs.next()) {
                students.add(new Lecturer(rs.getString("surname"), rs.getString("name"),
                        rs.getString("city")));
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
                    System.out.println(ex);

                }
            }
        }
        ObservableList<Lecturer> data = FXCollections.observableArrayList(students);
        LecturerTable.setItems(data);// загружаем все из data в таблицу на окне
    }
}
