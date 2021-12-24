package com.tian.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tian.dao.BookDao;
import com.tian.domain.Book;
import com.tian.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ projectName: Springboot
 * @ package: com.tian.service.impl
 * @ className: BookServiceImpl
 * @ author: tian
 * @ description: TODO
 * @ date: 2021/12/21 16:38
 * @ version: 1.0
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookDao, Book> implements IBookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public List<Book> getAll() {
        return bookDao.selectList(null);
    }

    @Override
    public boolean saveBook(Book book) {
        return bookDao.insert(book)>0;
    }

    @Override
    public boolean modify(Book book) {
        return bookDao.updateById(book)>0;
    }

    @Override
    public boolean delete(Integer id) {
        return bookDao.deleteById(id)>0;
    }

    @Override
    public IPage<Book> getPageList(Integer pageCurrent, Integer pageSize) {
        Page<Book> page = new Page<>(pageCurrent, pageSize);
        return bookDao.selectPage(page,null);
    }
}
