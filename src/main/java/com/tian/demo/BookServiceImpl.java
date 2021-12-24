package com.tian.demo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tian.utils.result.DefinitionException;
import com.tian.utils.result.ErrorEnum;
import com.tian.utils.token.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ projectName: Springboot
 * @ package: com.tian.service.impl
 * @ className: BookServiceImpl
 * @ author: tian
 * @ description: TODO
 * @ date: 2021/12/21 16:38
 * @ version: 1.0
 */
//@Service
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
    @Override
    public Map<String,Object> login(Book book) {
        //phone是除id外的唯一标志 需要进行检查
        if (book.getName() == null || book.getName().equals(""))
            throw new DefinitionException(ErrorEnum.NO_PARAM);
        QueryWrapper<Book> wrapper = new QueryWrapper<>();
        wrapper.eq("name",book.getName());
        Book selectBook = bookDao.selectOne(wrapper);
        String token = null;
        Map<String,Object> map = new HashMap<>();
        if (selectBook == null) {
            //注册用户
            int count = bookDao.insert(book);
            if (count < 1) throw new DefinitionException("注册异常");
            token = JWTUtils.createToken(book.getId().toString());
            map.put("user",book);

        }else {
            token = JWTUtils.createToken(selectBook.getId().toString());
            map.put("user",selectBook);
        }
        //将userId存入token中
        map.put("token",token);
        return map;
    }
}
