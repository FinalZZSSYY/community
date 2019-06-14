package life.majiang.community.community.service;

import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.pojo.Question;

import java.util.List;

public interface QuestionService {
    List<QuestionDTO> list();

    void create(Question question);
}
