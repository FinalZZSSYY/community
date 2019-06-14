package life.majiang.community.community.mapper;

import life.majiang.community.community.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatar_url) values (#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl}) ")
    void insert(User user);

    @Select("select * from user where token=#{token}")
    User findByToken(@Param("token") String token);

    @Select("select count(*) from user where account_id=#{accountId}")
    int findByAccountId(@Param("accountId") String accountId);

    @Update("update user set token=#{token},gmt_create=#{gmtCreate},gmt_modified=#{gmtModified},avatar_url=#{avatarUrl} where account_id=#{accountId} ")
    void update(User user);

    @Select("select * from user where id=#{id}")
    User findById(@Param("id") Integer id);
}
