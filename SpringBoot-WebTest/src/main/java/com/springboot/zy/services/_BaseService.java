package com.springboot.zy.services;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.zy.util.MyMapper;


/**
 * 通用service，实现单表常用操作，所有子类service继承该类
 * 
 * @author ZhouPingping
 *
 * @param <T>
 */
@Service
public abstract class _BaseService<T> {
	
	@Autowired
	protected MyMapper<T> mapper;
	
	/**---------------------------------Select---------------------------------*/
	
	/**
	 * 根据实体中的属性值进行查询，查询条件使用等号
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public List<T> select(T entity) throws Exception {
		return mapper.select(entity);
	
	}
	
	/**
	 * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public T selectByPrimaryKey(Object key) throws Exception {
		return mapper.selectByPrimaryKey(key);
	}
	
	/**
	 * 查询全部结果，select(null)方法能达到同样的效果
	 * @return
	 * @throws Exception
	 */
	public List<T> selectAll() throws Exception {
		return mapper.selectAll();
	}
	
	/**
	 * 根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 * @param entity
	 * @return
	 */
	public T selectOne(T entity) throws Exception {
		return mapper.selectOne(entity);
	}
	
	/**
	 * 根据实体中的属性查询总数，查询条件使用等号
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int selectCount(T entity) throws Exception {
		return mapper.selectCount(entity);
	}
	
	/**
	 * 根据Example条件进行查询，这个查询支持通过Example类指定查询列，通过selectProperties方法指定查询列
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public List<T> selectByExample(Object example) throws Exception {
		return mapper.selectByExample(example);
	}
	
	/**
	 * 根据Example条件进行查询总数
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int selectCountByExample(Object example) throws Exception {
		return mapper.selectCountByExample(example);
	}
	
	/**---------------------------------Insert---------------------------------*/
	
	/**
	 * 保存一个实体，null的属性也会保存，不会使用数据库默认值
	 * @param entity
	 * @return
	 */
	public int insert(T entity) throws Exception {
		return mapper.insert(entity);
	}
	
	/**
	 * 保存一个实体，null的属性不会保存，会使用数据库默认值
	 * @param entity
	 * @return
	 */
	public int insertSelective(T entity) throws Exception {
		return mapper.insertSelective(entity);
	}
	
	/**
	 * 插入数据，限制为实体包含id属性并且必须为自增列，实体配置的主键策略无效
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int insertUseGeneratedKeys(T entity) throws Exception {
		return mapper.insertUseGeneratedKeys(entity);
	}
	
	/**
	 * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
	 * @param list
	 * @return
	 * @throws Exception
	 */
	public int insertList(List<T> list) throws Exception {
		return mapper.insertList(list);
	}
	
	/**---------------------------------Update---------------------------------*/
	
	/**
	 * 根据主键更新实体全部字段，null值会被更新
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int updateByPrimaryKey(T entity) throws Exception {
		return mapper.updateByPrimaryKey(entity);
	}
	
	/**
	 * 根据主键更新属性不为null的值
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public int updateByPrimaryKeySelective(T entity) throws Exception {
		return mapper.updateByPrimaryKeySelective(entity);
	}
	
	/**
	 * 根据Example条件更新实体record包含的全部属性，null值会被更新
	 * @param record
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int updateByExample(@Param("record") T entity, @Param("example") Object example) throws Exception {
		return mapper.updateByExample(entity, example);
	}
	
	/**
	 * 根据Example条件更新实体record包含的不是null的属性值
	 * @param record
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int updateByExampleSelective(@Param("record") T entity, @Param("example") Object example) throws Exception {
		return mapper.updateByExampleSelective(entity, example);
	}
	
	/**---------------------------------Delete---------------------------------*/
	

	/**
	 * 根据实体属性作为条件进行删除，查询条件使用等号
	 * @param record
	 * @return
	 * @throws Exception
	 */
	public int delete(T entity) throws Exception {
		return mapper.delete(entity);
	}
	
	/**
	 * 根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public int deleteByPrimaryKey(Object key) throws Exception {
		return mapper.deleteByPrimaryKey(key);
	}
	
	/**
	 * 根据Example条件删除数据
	 * @param example
	 * @return
	 * @throws Exception
	 */
	public int deleteByExample(Object example) throws Exception {
		return mapper.deleteByExample(example);
	}
	
}
