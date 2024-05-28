package com.nuomi.nuomiquizhut.model.dto.question;

import lombok.Data;

import java.io.Serializable;

/**
 * 编辑请求
 *
 *@author nuomi
 */
@Data
public class QuestionEditRequest implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 题目内容（json格式）
     */
    private String questionContent;

    /**
     * 所属应用
     */
    private Long appId;

    private static final long serialVersionUID = 1L;
}