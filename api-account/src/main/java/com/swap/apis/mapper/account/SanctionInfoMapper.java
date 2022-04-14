package com.swap.apis.mapper.account;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SanctionInfoMapper {

  Long selectLastNumberKeyValue(String keyName);

  int selectNumberKeyValueCount(@Param("keyName") String keyName, @Param("value") Long value);

  int selectStringKeyValueCount(@Param("keyName") String keyName, @Param("value") String value);

  void deleteNumberKeyByKeyMetaForTest(String keyName);
}
