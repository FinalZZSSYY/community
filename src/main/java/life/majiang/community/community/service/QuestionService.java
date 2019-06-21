package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.pojo.Question;

public interface QuestionService {
    PaginationDTO list(Integer pageNum, Integer pageSize);



    PaginationDTO list(int id, Integer pageNum, Integer pageSize);

    QuestionDTO getById(Integer id);

    void createOrUpdate(Question question);

    void insView(Integer id);
}
