package com.goose.center.service;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhangyoubao
 * @version 2021/4/30
 */
@RequestMapping("center-api")
public interface UltramanCenterService {

    @PostMapping("/war/final")
    void finalHolyWar();
}
