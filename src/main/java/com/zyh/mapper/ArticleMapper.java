package com.zyh.mapper;

import java.util.List;
import java.util.Map;

import com.zyh.entity.Article;
import org.omg.CORBA.ARG_IN;

public interface ArticleMapper {

	public int create(Article article);

	public int delete(Integer id);

	public int update(Article article);

	public int updateSelective(Article article);

	public List<Article> query(Article article);

	public List<Article> getArticlesByChannelId(Integer channelId);

	public Article detail(Integer id);

	public int count(Article article);

}