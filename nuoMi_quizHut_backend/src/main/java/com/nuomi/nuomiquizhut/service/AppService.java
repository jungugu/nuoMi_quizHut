package com.nuomi.nuomiquizhut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nuomi.nuomiquizhut.model.dto.app.AppQueryRequest;
import com.nuomi.nuomiquizhut.model.dto.post.PostQueryRequest;
import com.nuomi.nuomiquizhut.model.entity.App;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nuomi.nuomiquizhut.model.entity.Post;
import com.nuomi.nuomiquizhut.model.vo.AppVO;
import com.nuomi.nuomiquizhut.model.vo.PostVO;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
public interface AppService extends IService<App> {
    /**
     * 校验
     *
     * @param app
     * @param add
     */
    void validApp(App app, boolean add);

    /**
     * 获取查询条件
     *
     * @param appQueryRequest
     * @return
     */
    QueryWrapper<App> getQueryWrapper(AppQueryRequest appQueryRequest);
}
