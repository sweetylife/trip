package com.tian.utils.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

import java.util.List;

/**
 * @ projectName: Springboot
 * @ package: com.tian.utils.result
 * @ className: PageInfo
 * @ author: tian
 * @ description: TODO
 * @ date: 2021/12/22 15:27
 * @ version: 1.0
 */
@Data
public class PageInfo {
    private Long listSize;
    private Long pageIndex;
    private Long pageSize;
    private Long total;
    private List list;

    public <T> PageInfo (IPage<T> iPage){
        this.listSize= iPage.getSize();
        this.pageIndex=iPage.getCurrent();
        this.pageSize=iPage.getSize();
        this.total=iPage.getTotal();
        this.list=iPage.getRecords();
    }
}
