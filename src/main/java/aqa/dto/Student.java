package aqa.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Student {
    private int id;
    private int age;
    private String name;
    private int groupNumber;

}
