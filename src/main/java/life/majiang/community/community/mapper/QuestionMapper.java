package life.majiang.community.community.mapper;

import life.majiang.community.community.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuestionMapper {

    @Insert("insert into question (title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{pageSize}")
    List<Question> list(@Param("offset") Integer offset,@Param("pageSize") Integer pageSize);

    @Select("select count(1) from question")
    Integer count();

}
