package com.goldcn.test.dao;


import com.goldcn.test.model.QuotationMin5;

public interface QuotationMin5Mapper {
    int deleteByPrimaryKey(Integer id);

    int insert(QuotationMin5 record);

    int insertSelective(QuotationMin5 record);

    QuotationMin5 selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(QuotationMin5 record);

    int updateByPrimaryKey(QuotationMin5 record);
}