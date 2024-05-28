package com.nuomi.nuomiquizhut.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nuomi.nuomiquizhut.common.ErrorCode;
import com.nuomi.nuomiquizhut.constant.CommonConstant;
import com.nuomi.nuomiquizhut.exception.BusinessException;
import com.nuomi.nuomiquizhut.mapper.QuestionMapper;
import com.nuomi.nuomiquizhut.model.dto.question.QuestionQueryRequest;
import com.nuomi.nuomiquizhut.model.entity.Question;
import com.nuomi.nuomiquizhut.service.QuestionService;
import com.nuomi.nuomiquizhut.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question>
        implements QuestionService {
    @Override
    public void validQuestion(Question question, boolean add) {
        if (question == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String questionContent = question.getQuestionContent();
        Long questionId = question.getAppId();
        Long userId = question.getUserId();
        // 创建时，参数不能为空
        if (StringUtils.isBlank(questionContent) || ObjectUtils.isEmpty(questionId) || ObjectUtils.isEmpty(userId)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
    }

    /**
     * 获取查询包装类
     *
     * @param questionQueryRequest
     * @return
     */
    @Override
    public QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest) {
        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
        if (questionQueryRequest == null) {
            return queryWrapper;
        }
        Long id = questionQueryRequest.getId();
        Long appId = questionQueryRequest.getAppId();
        Long userId = questionQueryRequest.getUserId();
        String sortField = questionQueryRequest.getSortField();
        String sortOrder = questionQueryRequest.getSortOrder();
        // 拼接查询条件);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(appId), "appId", appId);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }
}




