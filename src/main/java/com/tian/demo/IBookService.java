package com.tian.demo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface IBookService extends IService<Book> {
    List<Book> getAll();
    boolean saveBook(Book book);
    boolean modify(Book book);
    boolean delete(Integer id);
    IPage<Book> getPageList(Integer pageCurrent,Integer pageSize);
    Map<String,Object> login(Book book);
}
