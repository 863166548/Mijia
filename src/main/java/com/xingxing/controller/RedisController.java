package com.xingxing.controller;

import com.xingxing.bean.dto.Result;
import com.xingxing.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/redis")
public class RedisController {


    @Autowired
    private RedisService redisService;

    @RequestMapping(value ="/getPhone", method = RequestMethod.POST)
    public Result getPhone(@RequestParam("phone") String phone){
        try {
            Result success = Result.getSuccess();
            redisService.getPhone(phone);
            return success;
        } catch (Exception exception) {
            exception.printStackTrace();
            Result faild =Result.getFaild();
            return faild;
        }

    }

    @RequestMapping(value = "/setPhoneAndCode" , method = RequestMethod.POST)
    public Result setPhoneAndCode(@RequestParam("phone") String phone,@RequestParam("code") String code) {

        try {
            Result success = Result.getSuccess();
            int f = redisService.setPhoneAndCode(phone, code);

            if (f == 1) {
                success.setMsg("验证码验证成功");
                redisService.delCode(phone);
                return success;
            } else {
                Result getresult = Result.getResult();
                getresult.setMsg("验证码验证失败");
                getresult.setState(500);
                return getresult;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Result faild = Result.getFaild();
            return faild;
        }


    }






}
