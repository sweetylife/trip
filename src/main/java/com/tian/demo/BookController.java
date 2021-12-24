package com.tian.demo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.tian.config.interceptor.OSSService;
import com.tian.utils.result.PageInfo;
import com.tian.utils.result.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;

/**
 * @ projectName: Springboot
 * @ package: com.tian.controller
 * @ className: BookController
 * @ author: tian
 * @ description: TODO
 * @ date: 2021/12/21 17:04
 * @ version: 1.0
 */

//这里如果是除了400或者500以外的异常，直接抛出DefinitionException异常就好
//如果是400的异常，调用fail
//如果是成功，调用success
//@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private IBookService bookService;
    @Autowired
    private OSSService ossService;

    @GetMapping("list")
    public ResultResponse<JSONObject> getAll(){
        return ResultResponse.success(bookService.list());
    }

    @GetMapping("pageList")
    public ResultResponse<JSONObject> getPageList(@RequestParam(defaultValue  = "1") Integer pageCurrent, @RequestParam(defaultValue  = "5") Integer pageSize){
        IPage<Book> pageList = bookService.getPageList(pageCurrent, pageSize);
        return ResultResponse.success(new PageInfo(pageList));
    }

    @GetMapping("getone")
    public Book getOne(Integer id){
        return bookService.getById(id);
    }

    @PostMapping("save")
    public ResultResponse<JSONObject> save(@RequestBody @Valid Book book){
        return ResultResponse.success(bookService.save(book));
    }

    @PostMapping("update")
    public Boolean update(@RequestBody Book book){
        return bookService.updateById(book);
    }

    @PostMapping("delete")
    public Boolean delete(@RequestBody JSONObject jsonObject){
        Object id = jsonObject.get("id");
        System.out.println(id);
        return (Integer)id>0;
    }

    @PostMapping("upload")
    public ResultResponse<JSONObject> upload(@RequestParam("file") MultipartFile file,@RequestParam("id") Integer id) throws IOException {
        InputStream inputStream = file.getInputStream();
        MultipartFile multipartFile = new MockMultipartFile(file.getOriginalFilename(), inputStream);
        String tiantiantest = ossService.uploadFile(multipartFile, "tiantiantest");
        return ResultResponse.success(tiantiantest);
    }
//    =========================================================================================
//    成功：调用success
//    @GetMapping("list")
//    public ResultResponse<JSONObject> getAll(){
//        return ResultResponse.success(bookService.list());
//    }

//    400：调用fail
//    @GetMapping("list")
//    public ResultResponse<JSONObject> getAll(){
//        return ResultResponse.fail("没有登录哦");
//    }

//    处理系统异常(走拦截器）
//    @GetMapping("list")
//    public ResultResponse<JSONObject> getAll(){
//        int result= 12/0;
//        return ResultResponse.success("成功");
//    }
//    抛出自定义的异常（走拦截器）
//    @GetMapping("list")
//    public ResultResponse<JSONObject> getAll(){
//        if(true) throw new DefinitionException(111,"自定义异常");
//        return ResultResponse.success("成功");
//    }


//   返回已经定义过的异常（没有走拦截器）
//    @GetMapping("list")
//    public ResultResponse<JSONObject> getAll(){
//        return ResultResponse.otherError(ErrorEnum.NO_PERMISSION);
//    }
//   返回没有定义过的异常（没有走拦截器）
//    @GetMapping("list")
//    public ResultResponse<JSONObject> getAll(){
//        return ResultResponse.defineError(new DefinitionException(111,"失败了呀呀呀呀"));
//    }



}
