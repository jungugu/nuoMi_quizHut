package com.nuomi.nuomiquizhut.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author NuoMi
 */
@Data
public class Option implements Serializable {
    /**
     * 如果是测评类，则用 reslut 来保存答案属性
     */
    private String result;
    /**
     * 如果是得分类，则用 score 来设置本题分数
     */
    private String score;
    /**
     * 选项内容
     */
    private String value;
    /**
     * 选项
     */
    private String key;

    private static final long serialVersionUID = 1L;
}
