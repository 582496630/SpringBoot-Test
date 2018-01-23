package com.springboot.zy.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.zy.dao.LearndateMapper;
import com.springboot.zy.dao.UserMapper;
import com.springboot.zy.domain.Learndate;
import com.springboot.zy.domain.Users;

@Service
public class IndexServices {

/*	   //JDBC连接数据库
 		@Autowired
	    private JdbcTemplate jdbcTemplate;
	    public List<Users> getList(){
	        String sql = "SELECT *   FROM users";
	        return (List<Users>) jdbcTemplate.query(sql, new RowMapper<Users>(){
	            @Override
	            public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
	            	Users stu = new Users();
	                stu.setId(rs.getInt("id"));
	                stu.setUsername(rs.getString("username"));
	                return stu;
	            }
	        });
	    }*/
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LearndateMapper learndateMapper;
	
	public List<Users> getUsers(){
		return userMapper.getUsers();
	}
	
	public List<Learndate> getLearndate(){
		return learndateMapper.getLearndate();
	}
	
}
