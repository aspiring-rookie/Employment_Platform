package com.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.zyh.entity.Company;

public interface CompanyMapper {

	public int create(Company company);

	public int delete(Integer id);

	public int update(Company company);

	public int updateSelective(Company company);

	public List<Company> query(Company company);

	public Company detail(Integer id);

	public int count(Company company);

}