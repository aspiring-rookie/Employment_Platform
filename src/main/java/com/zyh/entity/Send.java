package com.zyh.entity;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import com.zyh.utils.Entity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;


/**
 * 
 * @author 596183363@qq.com
 * @time 2021-03-22 17:04:24
 */
public class Send extends Entity{

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private Integer companyId;
	/**
	 * 
	 */
	private Integer postId;
	/**
	 * 
	 */
	private Integer resumeId;
	/**
	 * 
	 */
	private Date sendDate;
	/**
	 * 0待查看|1已查看|2有意向|3不合适
	 */
	private Integer status;
	/**
	 * 
	 */
	private Integer studentId;

	private Post post;

	private Company company;

	private Resume resume;

	private Student student;


	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}



	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getResumeId() {
		return resumeId;
	}
	public void setResumeId(Integer resumeId) {
		this.resumeId = resumeId;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
}