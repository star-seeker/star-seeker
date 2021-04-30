package com.goose.user.service;

import com.goose.user.pojo.UltramanBase;
import com.goose.user.pojo.bo.UltramanBaseBO;
import com.goose.user.pojo.bo.UltramanHumanBO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhangyoubao
 * @version 2021/4/29
 */
@FeignClient("tiga-user-service")
@RequestMapping("user-api")
public interface UltramanService {

    @PostMapping("human")
    boolean createHuman(@RequestBody UltramanHumanBO humanBO);

    @PostMapping("ultraman")
    boolean createUltraman(@RequestBody UltramanBaseBO baseBO);

    @PostMapping("register")
    UltramanBase register(@RequestParam("humanId") String humanId, @RequestParam("ultramanId") String ultramanId);

    @GetMapping("transform")
    UltramanBase transform(@RequestParam("humanId") String humanId);

    @DeleteMapping("human")
    boolean remove(@RequestParam("humanId") String humanId);
}
