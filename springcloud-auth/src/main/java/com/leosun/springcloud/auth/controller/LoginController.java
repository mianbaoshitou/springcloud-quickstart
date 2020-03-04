package com.leosun.springcloud.auth.controller;

import com.leosun.springcloud.auth.utils.AuthJwtUtils;
import com.leosun.springcloud.common.ds.TronDataResult;
import com.leosun.springcloud.common.ds.TronDataResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.leosun.springcloud.common.utils.RedissonRepo;
/**
 * @program: linked-base-gateway
 * @description: 登录Controller
 * @author: Yueling
 * @create: 2019-12-06 15:13
 **/
@RestController("/login")
public class LoginController {

    @Autowired
    RedissonRepo redissonRepo;
    @Autowired
    AuthJwtUtils authJwtUtils;
    @GetMapping("/login")
    public TronDataResult login(){
        TronDataResult result = new TronDataResult(TronDataResultCode.SUCCESS);
        result.fillDat("LOGIN SUCCESS");
        //TODO 登录成功后,将信息存入redis
        //key存放格式为 login.username.iss   例如 login.sunyueling.rz.trondata.cn
        //value存放权限信息

        String sign = authJwtUtils.sign("sun");
        redissonRepo.setString("login", sign);
        result.fillDat(sign);
        return result;
    }
}
