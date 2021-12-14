package com.zyh.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.zyh.utils.Entity;
import java.util.Date;


/**
 * 
 * @author 596183363@qq.com
 * @time 2021-03-22 17:04:24
 */
public class UserMenu extends Entity{

	/**
	 * 
	 */
	private Integer userId;
	/**
	 * 
	 */
	private Integer menuId;

	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getMenuId() {
		return menuId;
	}
	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}
}