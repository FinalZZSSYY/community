package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.pojo.Question;

public interface QuestionService {
    PaginationDTO list(Integer pageNum, Integer pageSize);

    void create(Question question);
}
