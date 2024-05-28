package com.nuomi.nuomiquizhut.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nuomi.nuomiquizhut.model.dto.app.AppQueryRequest;
import com.nuomi.nuomiquizhut.model.dto.question.QuestionQueryRequest;
import com.nuomi.nuomiquizhut.model.entity.App;
import com.nuomi.nuomiquizhut.model.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 *
 */
public interface QuestionService extends IService<Question> {
    /**
     * 校验
     *
     * @param question
     * @param add
     */
    void validQuestion(Question question, boolean add);

    /**
     * 获取查询条件
     *
     * @param questionQueryRequest
     * @return
     */
    QueryWrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);
}
