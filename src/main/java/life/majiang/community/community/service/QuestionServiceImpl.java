package life.majiang.community.community.service;

import life.majiang.community.community.dto.PaginationDTO;
import life.majiang.community.community.dto.QuestionDTO;
import life.majiang.community.community.exception.CustomizeErrorCode;
import life.majiang.community.community.exception.CustomizeException;
import life.majiang.community.community.mapper.QuestionMapper;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.pojo.Question;
import life.majiang.community.community.pojo.User;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private QuestionMapper questionMapper;

    public PaginationDTO list(Integer pageNum, Integer pageSize) {

        PaginationDTO paginationDTO = new PaginationDTO();
        //查询页面总数
        Integer totalCount = questionMapper.count();

        if(totalCount % pageSize == 0){
            paginationDTO.setTotalPage(totalCount / pageSize);
        }else{
            paginationDTO.setTotalPage(totalCount / pageSize + 1);
        }

        if(pageNum < 1){
            pageNum = 1;
        }
        if(pageNum > paginationDTO.getTotalPage()){
            pageNum = paginationDTO.getTotalPage();
        }
        paginationDTO.setPageNum(pageNum);
        //size*(page-1)
        Integer offset = pageSize*(pageNum-1);
        List<Question> questionList = questionMapper.list(offset,pageSize);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question中的值快速copy到questionDTO中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setPageList(questionDTOList);

        paginationDTO.setPagintion();


        return paginationDTO;
    }



    @Override
    public PaginationDTO list(int userId, Integer pageNum, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();
        //查询页面总数
        Integer totalCount = questionMapper.countByUserId(userId);

        if(totalCount % pageSize == 0){
            paginationDTO.setTotalPage(totalCount / pageSize);
        }else{
            paginationDTO.setTotalPage(totalCount / pageSize + 1);
        }

        if(pageNum < 1){
            pageNum = 1;
        }
        if(pageNum > paginationDTO.getTotalPage()){
            pageNum = paginationDTO.getTotalPage();
        }
        paginationDTO.setPageNum(pageNum);
        //size*(page-1)
        Integer offset = pageSize*(pageNum-1);
        List<Question> questionList = questionMapper.listByUserId(userId,offset,pageSize);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            //将question中的值快速copy到questionDTO中
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setPageList(questionDTOList);

        paginationDTO.setPagintion();


        return paginationDTO;
    }

    @Override
    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.findById(id);
        if (question == null){
            throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        //将question中的值快速copy到questionDTO中
        BeanUtils.copyProperties(question, questionDTO);
        User user = userMapper.findById(question.getCreator());
        questionDTO.setUser(user);
        return questionDTO;
    }

    @Override
    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtModified());
            questionMapper.create(question);
        }else{
            question.setGmtModified(System.currentTimeMillis());
            int updateIndex = questionMapper.update(question);
            if (updateIndex != 1){
                throw  new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }

    }

    @Override
    public void insView(Integer id) {
        questionMapper.updateViewById(id);
    }
}
