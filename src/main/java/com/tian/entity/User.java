package com.tian.entity;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.sql.Date;

/**
 * @ projectName:    trip
 * @ package:        com.tian.pojo
 * @ className:      User
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/24 14:21
 * @ version:    1.0
 */
@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String telephone;
    private String createTime;
    private String sex;
    private String idCard;
    private Date birthday;
    @TableLogic
    private Integer deleted;
}
