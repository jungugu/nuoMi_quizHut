package com.nuomi.nuomiquizhut.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author NuoMi
 */
@Data
public class QuestionContent implements Serializable {

    /**
     * 题目
     */
    private String title;

    /**
     * 选项
     */
    private List<Option> options;
    private static final long serialVersionUID = 1L;
}
