package life.majiang.community.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

//分页类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationDTO {
    private List<?> pageList = new ArrayList<>();//存放数据
    private boolean showPrevious;//是否有向前按钮
    private boolean showFirstPage;//是否有快速回到第一页按钮按钮
    private boolean showNext;//是否有向后按钮
    private boolean showEndPage;//是否有快速回到最后一页按钮按钮

    private Integer totalPage;
    private Integer pageNum;//当前页
    private List<Integer> pagesNum = new ArrayList<>();//显示的页面数

    public void setPagintion( ) {
        pagesNum.add(pageNum);

        for(int i=1;i<3;i++){
            if (pageNum-i>0){
                //头部插入
                pagesNum.add(0,pageNum-i);
            }

            if(pageNum+i <= totalPage){
                //尾部插入
                pagesNum.add(pageNum+i);
            }
        }

        //是否有向前按钮
        if (pageNum == 1){
            showPrevious = false;
        }else{
            showPrevious = true;
        }
        //是否有向后按钮
        if(pageNum == totalPage){
            showNext = false;
        }else{
            showNext = true;
        }


        //是否有快速回到第一页按钮按钮
        if(pagesNum.contains(1)){
            showFirstPage = false;
        }else{
            showFirstPage = true;
        }
        //是否有快速回到最后一页按钮按钮
        if(pagesNum.contains(totalPage)){
            showEndPage = false;
        }else{
            showEndPage = true;
        }


    }
}
