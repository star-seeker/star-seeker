package com.goose.center.service.impl;

import com.goose.center.service.UltramanCenterService;
import com.goose.user.pojo.UltramanBase;
import com.goose.user.service.UltramanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyoubao
 * @version 2021/4/30
 */
@RestController
@Slf4j
@Api(value = "ultraman center api", tags = {"the related api about ultraman center"})
public class UltramanCenterServiceImpl implements UltramanCenterService {

    @Resource
    private UltramanService ultramanService;

    private static final String humanId = "21043002BG811MNC";

    @Override
    @ApiOperation(value = "final holy war", notes = "final holy war", httpMethod = "POST")
    public void finalHolyWar() {
        UltramanBase transform = ultramanService.transform(humanId);
        if (transform == null) {
            log.info("transform failed.");
            return;
        }
        log.info("transform succeed.");

        log.info("The Dark Master is too ferocious to defeat it, fighting failed...");

        ultramanService.remove(humanId);
        log.info("transform removed.");

        log.info("get light from people heart...");
        ultramanService.register(humanId, transform.getId());
        log.info("transform again.");

        log.info("defeat the Dark Master, we are victory!!!");

    }
}
