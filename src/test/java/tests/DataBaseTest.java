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
        assertThat(students).hasSize(9);
    }

    @AfterMethod
    public void tearDown() {
        dbClient.close();
    }
}
