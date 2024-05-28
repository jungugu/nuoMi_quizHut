package com.nuomi.nuomiquizhut.service.impl;
import java.util.Date;
import java.util.List;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuomi.nuomiquizhut.common.ErrorCode;
import com.nuomi.nuomiquizhut.constant.CommonConstant;
import com.nuomi.nuomiquizhut.exception.BusinessException;
import com.nuomi.nuomiquizhut.exception.ThrowUtils;
import com.nuomi.nuomiquizhut.model.dto.app.AppQueryRequest;
import com.nuomi.nuomiquizhut.model.dto.post.PostQueryRequest;
import com.nuomi.nuomiquizhut.model.entity.*;
import com.nuomi.nuomiquizhut.model.enums.AppReviewStatusTypeEnum;
import com.nuomi.nuomiquizhut.model.enums.AppScoringStrategyEnum;
import com.nuomi.nuomiquizhut.model.enums.AppTypeEnum;
import com.nuomi.nuomiquizhut.model.vo.PostVO;
import com.nuomi.nuomiquizhut.model.vo.UserVO;
import com.nuomi.nuomiquizhut.service.AppService;
import com.nuomi.nuomiquizhut.mapper.AppMapper;
import com.nuomi.nuomiquizhut.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author nizec
 */
@Service
public class AppServiceImpl extends ServiceImpl<AppMapper, App>
        implements AppService {

    @Override
    public void validApp(App app, boolean add) {
        if (app == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String appName = app.getAppName();
        String appDesc = app.getAppDesc();
        Integer appType = app.getAppType();
        Integer scoringStrategy = app.getScoringStrategy();
        Integer reviewStatus = app.getReviewStatus();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(appName), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(appName) && appName.length() > 30) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "名称过长");
        }
        if (StringUtils.isNotBlank(appDesc) && appDesc.length() > 8192) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "内容过长");
        }
        if (AppTypeEnum.getEnumByValue(appType) == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (AppScoringStrategyEnum.getEnumByValue(scoringStrategy) == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (AppReviewStatusTypeEnum.getEnumByValue(reviewStatus) == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    /**
     * 获取查询包装类
     *
     * @param appQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<App> getQueryWrapper(AppQueryRequest appQueryRequest) {
        QueryWrapper<App> queryWrapper = new QueryWrapper<>();
        if (appQueryRequest == null) {
            return queryWrapper;
        }
        Long id = appQueryRequest.getId();
        String appName = appQueryRequest.getAppName();
        String appDesc = appQueryRequest.getAppDesc();
        Integer appType = appQueryRequest.getAppType();
        Integer scoringStrategy = appQueryRequest.getScoringStrategy();
        Integer reviewStatus = appQueryRequest.getReviewStatus();
        Long reviewerId = appQueryRequest.getReviewerId();
        Long userId = appQueryRequest.getUserId();
        String sortField = appQueryRequest.getSortField();
        String sortOrder = appQueryRequest.getSortOrder();
        // 拼接查询条件
        queryWrapper.like(StringUtils.isNotBlank(appName), "appName", appName);
        queryWrapper.like(StringUtils.isNotBlank(appDesc), "content", appDesc);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(appType), "appType", appType);
        queryWrapper.eq(ObjectUtils.isNotEmpty(scoringStrategy), "scoringStrategy", scoringStrategy);
        queryWrapper.eq(ObjectUtils.isNotEmpty(reviewStatus), "reviewStatus", reviewStatus);
        queryWrapper.eq(ObjectUtils.isNotEmpty(reviewerId), "reviewerId", reviewerId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




