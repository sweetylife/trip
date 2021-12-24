package com.tian.demo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ projectName: Springboot
 * @ package: com.tian.domain
 * @ className: Book
 * @ author: tian
 * @ description: TODO
 * @ date: 2021/12/21 14:48
 * @ version: 1.0
 */

@Data
public class Book {
    private Integer id;
    private String type;

    @NotBlank
    private String name;

    @TableField("description")
    private String des;
}
