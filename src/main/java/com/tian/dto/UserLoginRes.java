package com.tian.dto;

import lombok.Data;

/**
 * @ projectName:    trip
 * @ package:        com.tian.dto
 * @ className:      UserLoginRes
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/24 15:04
 * @ version:    1.0
 */
@Data
public class UserLoginRes {
    private Integer id;
    private String username;
    private String telephone;
    private String token;
}
