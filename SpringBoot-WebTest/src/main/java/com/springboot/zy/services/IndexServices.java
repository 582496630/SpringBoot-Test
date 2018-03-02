package com.springboot.zy.services;

import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.springboot.zy.dao.LearndateMapper;
import com.springboot.zy.dao.UserMapper;
import com.springboot.zy.domain.Learndate;
import com.springboot.zy.domain.Users;

@Service
public class IndexServices extends _BaseService<Users>{

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
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IndexServices.class);
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private LearndateMapper learndateMapper;
	
	@Cacheable(value = "users")
	public List<Users> getUsers(){
//		logger.info("info");
//		logger.error("error");
//		logger.debug("debug");
		List<Users> list = userMapper.getUsers();
		return list;
	}
	
	@Cacheable(value = "learndate")
	public List<Learndate> getLearndates() {
		return learndateMapper.selectAll();
	}
	
	@Cacheable(value = "users", key = "#id")
	public Users getUser(Integer id){
		Users user = userMapper.getUser(id);
		return user;
	}
	
	public List<Learndate> getLearndate(){
		return learndateMapper.getLearndate();
	}
	
}
