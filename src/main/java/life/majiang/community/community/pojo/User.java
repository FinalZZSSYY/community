package life.majiang.community.community.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//注解@Data 生成get set toString  @NoArgsConstructor 是生成一个无参的构造函数，@AllArgsContructor生成一个有参构造函数
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;


}
