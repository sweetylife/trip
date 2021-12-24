package com.tian.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.tian.domain.Book;

import java.util.List;

public interface IBookService extends IService<Book> {
    List<Book> getAll();
    boolean saveBook(Book book);
    boolean modify(Book book);
    boolean delete(Integer id);
    IPage<Book> getPageList(Integer pageCurrent,Integer pageSize);
}
