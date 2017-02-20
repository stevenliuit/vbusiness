package com.vbusiness.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.vbusiness.dao.IUserDao;
import com.vbusiness.db.DBUtil;
import com.vbusiness.db.IResultSetUtil;
import com.vbusiness.entity.User;

/**
 * 用户操作接口实现类
 * 
 * @author mango
 *
 */
public class UserDaoImpl implements IUserDao {

	/**
	 * 登录的实现方法 （1:用户名错误；2：密码错误；3:正确）
	 */
	@Override
	public String login(String user_id, String user_pass) {
		// 判断用户是否存在
		String sql = "SELECT user_pass FROM user WHERE user_id = ? ";// 查询单个值方法
		Object obj = DBUtil.executeQuery(sql, user_id);
		if (obj == null) {
			return "1"; // 显示“用户不存在”
		} else {
			if (obj.toString().equals(user_pass)) {
				return "3"; // 密码正确
			} else {
				return "2"; // 显示“密码错误”
			}
		}
	}

	/**
	 * 根据用户账号获取用户信息
	 */
	@Override
	public User getUserByUserId(final String user_id) {
		String sql = "SELECT user_referee,user_name,user_birth,user_city,user_pass,user_secq,user_answer,left_num,right_num,id,user_level,created_time FROM user WHERE user_id=?  ";
		return (User) DBUtil.executeQuery(sql, new IResultSetUtil() {
			@Override
			public Object doHandler(ResultSet rs) throws SQLException {
				if (rs.next()) {
					User user = new User();
					user.setUser_referee(rs.getString(1));
					user.setUser_name(rs.getString(2));
					user.setUser_birth(rs.getString(3));
					user.setUser_city(rs.getString(4));
					user.setUser_pass(rs.getString(5));
					user.setUser_secq(rs.getInt(6));
					user.setUser_answer(rs.getString(7));
					user.setUser_id(user_id);
					user.setLeft_num(rs.getInt(8));
					user.setRight_num(rs.getInt(9));
					user.setId(rs.getInt(10));
					user.setUser_level(rs.getInt(11));
					user.setCreated_time(rs.getLong(12));
					return user;
				}
				return null;
			}
		}, user_id);
	}

	/**
	 * 保存用户信息
	 */
	@Override
	public boolean saveUserInfo(User user) {
		String sql = "UPDATE user SET user_name=?,user_birth=?,user_city=?,user_pass=?,user_secq=?,user_answer=? WHERE user_id = ?";

		return DBUtil.executeUpdate(sql, user.getUser_name(), user.getUser_birth(), user.getUser_city(),
				user.getUser_pass(), user.getUser_secq(), user.getUser_answer(), user.getUser_id()) > 0;
	}
	
	/**
	 * 添加用户
	 */
	public boolean addUser(User user) {
		String sql = "INSERT INTO user (user_id,user_name,user_birth,user_phone,user_city,user_pass,user_referee,created_time,left_num,right_num) VALUES (?,?,?,?,?,?,?,?,?,?) ";

		return DBUtil.executeUpdate(sql, user.getUser_id(),
				user.getUser_name(), user.getUser_birth(),user.getUser_phone(),
				user.getUser_city(), user.getUser_pass(),
				user.getUser_referee(), user.getCreated_time(),
				user.getLeft_num(), user.getRight_num()) > 0;
	}
	/**
	 * 更新左下标值
	 */
	@Override
	public boolean updateLeft_num(int right_num, int id) {
		// 大于新增节点的右下标值的左下标值，都加2
		String sql = "UPDATE user SET left_num = left_num + 2 WHERE left_num >= ? AND  id <> ?";
		return DBUtil.executeUpdate(sql, right_num, id) > 0;
	}
	/**
	 * 更新右下标值
	 */
	@Override
	public boolean updateRight_num(int left_num, int id) {
		// 大于新增节点的左下标值的右下标值，都加2
		String sql = "UPDATE user SET right_num = right_num + 2 WHERE right_num >= ? AND id <> ?";
		return DBUtil.executeUpdate(sql, left_num, id) > 0;
	}
	/**
	 * 根据用户账号查询该账号所在的树形结构的层级数
	 */
	@Override
	public int getUserLevelByUserId(String user_id) {
		String sql = "SELECT COUNT(parent.user_id)+1 FROM  user AS son , user AS parent WHERE son.left_num > parent.left_num AND son.right_num < parent.right_num AND son.user_id = ?";
		return DBUtil.executeQuery(sql, user_id) == null ? 0 : Integer
				.parseInt(DBUtil.executeQuery(sql, user_id).toString());
	}

	/**
	 * 根据用户账号更新用户的层级数
	 */
	@Override
	public boolean updateUser_level(int user_level, String user_id) {
		String sql = "UPDATE user SET user_level = ? WHERE user_id = ?";
		return DBUtil.executeUpdate(sql, user_level, user_id) > 0;
	}

	/**
	 * 查询当前用户下的子节点集合
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getList(int left_num, int right_num, int user_level) {
		String sql = "SELECT user_id,created_time,left_num,right_num,user_level FROM user WHERE left_num >? AND right_num < ? AND user_level <= ? ORDER BY left_num asc LIMIT 12";
		return (List<User>) DBUtil.executeQuery(sql, new IResultSetUtil() {
			@Override
			public Object doHandler(ResultSet rs) throws SQLException {
				List<User> userList = new ArrayList<User>();
				while (rs.next()) {
					User user = new User();
					user.setUser_id(rs.getString(1));
					user.setCreated_time(rs.getLong(2));
					user.setLeft_num(rs.getInt(3));
					user.setRight_num(rs.getInt(4));
					user.setUser_level(rs.getInt(5));
					userList.add(user);
				}
				return userList;
			}
		}, left_num, right_num, (user_level + 2));
	}
}
