package com.vbusiness.entity;
/**
 * 用户实体类
 * @author mango
 *
 */
public class User {
	private int id;// 自动增长id
	private String user_id;// 登录账号
	private String user_pass;// 登录密码
	private int user_level;// 树形结构所在层
	private int left_num;// 左侧数值
	private int right_num;// 右侧数值
	private String user_name;// 真实姓名
	private String user_phone;// 手机号码
	private String user_birth;// 出生日期
	private int user_secq;// 密保问题
	private String user_answer;// 密保答案
	private String user_city;// 所在城市
	private String user_referee;// 推荐人
	private long created_time;// 创建时间

	public User() {
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_pass() {
		return user_pass;
	}

	public void setUser_pass(String user_pass) {
		this.user_pass = user_pass;
	}

	public int getUser_level() {
		return user_level;
	}

	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}

	public int getLeft_num() {
		return left_num;
	}

	public void setLeft_num(int left_num) {
		this.left_num = left_num;
	}

	public int getRight_num() {
		return right_num;
	}

	public void setRight_num(int right_num) {
		this.right_num = right_num;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getUser_birth() {
		return user_birth;
	}

	public void setUser_birth(String user_birth) {
		this.user_birth = user_birth;
	}

	public int getUser_secq() {
		return user_secq;
	}

	public void setUser_secq(int user_secq) {
		this.user_secq = user_secq;
	}

	public String getUser_answer() {
		return user_answer;
	}

	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
	}

	public String getUser_city() {
		return user_city;
	}

	public void setUser_city(String user_city) {
		this.user_city = user_city;
	}

	public String getUser_referee() {
		return user_referee;
	}

	public void setUser_referee(String user_referee) {
		this.user_referee = user_referee;
	}

	public long getCreated_time() {
		return created_time;
	}

	public void setCreated_time(long created_time) {
		this.created_time = created_time;
	}
}
