package tt.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

@Repository("daoImp")
public class DaoImp implements Dao {
	@Resource(name="sqlSessionTemplate")
	SqlSessionTemplate sqlSession;
	
	public Object save(String str,Object obj) throws Exception {
		return sqlSession.insert(str,obj);
	}

	public Object delete(String str,Object obj) throws Exception {
		return sqlSession.delete(str, obj);
	}

	public Object update(String str,Object obj) throws Exception {
		return sqlSession.update(str, obj);
	}

	public Object find(String str) throws Exception {
		return null;
	}
	//用户是否存在
	public Object findForObject(String str, Object obj) throws Exception {
		 return sqlSession.selectOne(str,obj);
	}
	//列表
	public Object findForList(String str, Object obj) throws Exception {
		return sqlSession.selectList(str, obj);
	}

}
