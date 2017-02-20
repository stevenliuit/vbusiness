package com.vbusiness.entity;

/**
 * 系统设置信息实体类
 * 
 * @author mango
 *
 */
public class Syssetting {
	private int id;// 自动增长Id
	private String level;// 级别
	private int maximum;// 最大人数
	private int minimum;// 最小人数
	private float money;// 金额
	private long created_time;// 创建时间
	private String referee;// 推荐人

	public Syssetting() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public int getMaximum() {
		return maximum;
	}

	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public long getCreated_time() {
		return created_time;
	}

	public void setCreated_time(long created_time) {
		this.created_time = created_time;
	}

	public String getReferee() {
		return referee;
	}

	public void setReferee(String referee) {
		this.referee = referee;
	}
}
