package com.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.zyh.entity.Send;

public interface SendMapper {

	public int create(Send send);

	public int delete(Integer id);

	public int update(Send send);

	public int updateSelective(Send send);

	public List<Send> query(Send send);

	public Send detail(Integer id);

	public int count(Send send);

}