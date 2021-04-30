package com.goose.user.service.impl;

import com.goose.idworker.Sid;
import com.goose.user.mapper.UltramanBaseMapper;
import com.goose.user.mapper.UltramanHumanMapper;
import com.goose.user.pojo.UltramanBase;
import com.goose.user.pojo.UltramanHuman;
import com.goose.user.pojo.bo.UltramanBaseBO;
import com.goose.user.pojo.bo.UltramanHumanBO;
import com.goose.user.service.UltramanService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Date;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyoubao
 * @version 2021/4/29
 */
@RestController
@Slf4j
@Api(value = "ultraman api", tags = {"the related api about ultraman"})
public class UltramanServiceImpl implements UltramanService {

    @Resource
    private UltramanHumanMapper humanMapper;

    @Resource
    private UltramanBaseMapper baseMapper;

    @Override
    @ApiOperation(value = "create human", notes = "create human", httpMethod = "POST")
    public boolean createHuman(@RequestBody UltramanHumanBO humanBO) {
        UltramanHuman human = new UltramanHuman();
        Date now = new Date();

        log.info("human creating...");

        BeanUtils.copyProperties(humanBO, human);
        human.setId(Sid.nextShort());
        human.setCreateTime(now);
        human.setUpdateTime(now);

        int result = humanMapper.insert(human);
        log.info("human created.");

        return result > 0;
    }

    @Override
    @ApiOperation(value = "create ultraman", notes = "create ultraman", httpMethod = "POST")
    public boolean createUltraman(@RequestBody UltramanBaseBO baseBO) {
        UltramanBase ultraman = new UltramanBase();
        Date now = new Date();

        log.info("ultraman creating...");

        BeanUtils.copyProperties(baseBO, ultraman);
        ultraman.setId(Sid.nextShort());
        ultraman.setCreateTime(now);
        ultraman.setUpdateTime(now);

        int result = baseMapper.insert(ultraman);
        log.info("ultraman created.");

        return result > 0;
    }

    @Override
    @ApiOperation(value = "register ultraman to human", notes = "register ultraman to human", httpMethod = "POST")
    public UltramanBase register(
            @ApiParam(name = "humanId", value = "human id", required = true)
            @RequestParam("humanId") String humanId,
            @ApiParam(name = "ultramanId", value = "ultramanId id", required = true)
            @RequestParam("ultramanId") String ultramanId) {
        log.info("register start...");

        UltramanHuman human = humanMapper.selectByPrimaryKey(humanId);
        if (human == null) {
            log.info("human disappeared.");
            return null;
        }

        UltramanBase ultraman = baseMapper.selectByPrimaryKey(ultramanId);
        if (ultraman == null) {
            log.info("ultraman disappeared.");
            return null;
        }

        human.setUltramanId(ultramanId);
        human.setUpdateTime(new Date());
        humanMapper.updateByPrimaryKey(human);
        log.info("register finished.");

        return ultraman;
    }

    @Override
    @ApiOperation(value = "transform human to ultraman", notes = "transform human to ultraman", httpMethod = "GET")
    public UltramanBase transform(
            @ApiParam(name = "humanId", value = "human id", required = true)
            @RequestParam("humanId") String humanId) {
        log.info("transform start...");

        UltramanHuman human = humanMapper.selectByPrimaryKey(humanId);
        if (human == null) {
            log.info("human disappeared.");
            return null;
        }

        UltramanBase ultraman = baseMapper.selectByPrimaryKey(human.getUltramanId());
        if (ultraman == null) {
            log.info("ultraman disappeared.");
            return null;
        }

        log.info("transform finished.");

        return ultraman;
    }

    @Override
    @ApiOperation(value = "remove ultraman from human", notes = "remove ultraman from human", httpMethod = "DELETE")
    public boolean remove(
            @ApiParam(name = "humanId", value = "human id", required = true)
            @RequestParam("humanId") String humanId) {
        log.info("remove start...");

        UltramanHuman human = humanMapper.selectByPrimaryKey(humanId);
        if (human != null) {
            human.setUltramanId("");
            human.setUpdateTime(new Date());
            humanMapper.updateByPrimaryKey(human);
        }
        log.info("remove finished.");

        return true;
    }
}
