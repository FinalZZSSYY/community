package life.majiang.community.community.mapper;

import life.majiang.community.community.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified}) ")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select count(*) from user where account_id=#{accountId}")
    int findByAccountId(@Param("accountId") String accountId);

    @Update("update user set gmt_create=#{gmtCreate},gmt_modified=#{gmtModified} where account_id=#{accountId} ")
    void update(User user);
}
