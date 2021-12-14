package com.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.zyh.entity.Type;

public interface TypeMapper {

	public int create(Type type);

	public int delete(Integer id);

	public int update(Type type);

	public int updateSelective(Type type);

	public List<Type> query(Type type);

	public Type detail(Integer id);

	public int count(Type type);

}