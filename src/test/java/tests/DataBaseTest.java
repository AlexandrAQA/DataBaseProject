package tests;

import aqa.dto.Student;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.DBClient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
public class DataBaseTest {

    DBClient dbClient;

    @BeforeMethod
    public void setUp() {
        dbClient = new DBClient();
        dbClient.connect();
    }

    @Test
    public void selectAllStudents() throws SQLException {
        ResultSet resultSet = dbClient.selectFrom("student");
        List<Student> students = new ArrayList<>();
        while (resultSet.next()) {
            students.add(new Student().setId(resultSet.getInt("id"))
                                      .setName(resultSet.getString("name"))
                                      .setAge(resultSet.getInt("age"))
                                      .setGroupNumber(resultSet.getInt("groupNumber")));
        }
        assertThat(students).hasSize(11);
    }

    @Test
    public void updateAndSelectAllStudents() throws SQLException {
        dbClient.executeUpdate
                        ("INSERT INTO student (name, age) VALUES ('Vasa', 28)");
        ResultSet resultSetAfterUpdate = dbClient.selectFrom("student");
        List<Student> students2 = new ArrayList<>();
        while (resultSetAfterUpdate.next()) {
            students2.add(new Student().setId(resultSetAfterUpdate.getInt("id"))
                                       .setName(resultSetAfterUpdate.getString("name"))
                                       .setAge(resultSetAfterUpdate.getInt("age"))
                                       .setGroupNumber(resultSetAfterUpdate.getInt("groupNumber")));
        }
        assertThat(students2).hasSize(10);
    }

    @Test
    public void updateAndSelectAllStudentsWithPrepared() throws SQLException {
        dbClient.executeUpdateWithPreparedStatement
                        ("INSERT INTO student (name, age) VALUES (?, ?)");
        ResultSet resultSetAfterUpdate = dbClient.selectFrom("student");
        List<Student> students2 = new ArrayList<>();
        while (resultSetAfterUpdate.next()) {
            students2.add(new Student().setId(resultSetAfterUpdate.getInt("id"))
                                       .setName(resultSetAfterUpdate.getString("name"))
                                       .setAge(resultSetAfterUpdate.getInt("age"))
                                       .setGroupNumber(resultSetAfterUpdate.getInt("groupNumber")));
        }
        assertThat(students2).hasSize(11);
    }


    @AfterMethod
    public void tearDown() {
        dbClient.close();
    }
}
