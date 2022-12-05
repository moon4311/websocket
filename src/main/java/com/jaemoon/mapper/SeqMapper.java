package com.jaemoon.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.jaemoon.model.Seq;
/**
 * 
 * 
 * @author KimJaeMoon
 *
 */
@Mapper
public interface SeqMapper {

  public List<Seq> selectList(Map<String,Object> map);
  public Seq selectOne(String id);
  
  public int insert(Map<String,Object> map);
  public int update(Map<String,Object> map);
  public void delete(Map<String,Object> map);
  
  public int selectCnt(Map<String,Object> map);
  public boolean selectExist(String userId);
  
}
