package com.tian.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ projectName:    trip
 * @ package:        com.tian.prepojo
 * @ className:      Userlogin
 * @ author:     tian
 * @ description:  TODO
 * @ date:    2021/12/24 14:48
 * @ version:    1.0
 */
@Data
public class Userlogin {
    @NotBlank
    private String phone;
    private String password;
    private String smsCode;
}
