package tests;

import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import util.DBClient;

import java.sql.ResultSet;
import java.sql.SQLException;

@Log4j2
public class DataBaseTest {

    DBClient dbClient;

    @BeforeMethod
    public void setUp(){
        dbClient = new DBClient();
        dbClient.connect();
    }

    @Test
    public void selectAllStudents() throws SQLException {
      ResultSet resultSet = dbClient.selectFrom("student");
        while (resultSet.next()){
            System.out.println("id: " + resultSet.getInt("id"));
            System.out.println("age: " + resultSet.getInt("age"));
            System.out.println("name: " + resultSet.getString("name"));
            System.out.println("groupNumber: " + resultSet.getInt("groupNumber"));
            System.out.println("===========================================");

        }
    }

    @AfterMethod
    public void tearDown(){
        dbClient.close();
    }
}
