package com.xingxing.service;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import org.springframework.boot.autoconfigure.data.redis.JedisClientConfigurationBuilderCustomizer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

@Service
public class RedisService {



    Jedis jedis = new Jedis("localhost");

    public void getPhone(String phone) {

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI4G5x3VFaWQLt89Nto5RH", "Jfn44y0gWK92EMB5zPjfVdmzrdAq6e");
        IAcsClient client = new DefaultAcsClient(profile);

        Integer a = (int) ((Math.random() * 9 + 1) * 100000);

        String code = a.toString();

        jedis.set(phone,code);
        jedis.expire(phone,300);


        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "不加冰滴可乐");
        request.putQueryParameter("TemplateCode", "SMS_205400140");
        request.putQueryParameter("TemplateParam", "{\"code\":" + code + "}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    public int setPhoneAndCode(String phone,String code){
        String s = jedis.get(phone);
        if (code.equals(s)){
            return 1;
        }else {
            return 0;
        }


    }
    public void delCode(String phone){
        jedis.del(phone);
    }



}
