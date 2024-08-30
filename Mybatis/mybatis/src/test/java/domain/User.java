package domain;
import lombok.Data;
import java.sql.Date;

@Data
public class User {
    private String name;
    private String pwd;
    private String head_img;
    private String id;
    private String phone;
    private Date create_time;
}
