package tt.dao;


public interface Dao {
	//增
	public Object save(String str,Object obj) throws Exception;	
	//删
	public Object delete(String str,Object obj) throws Exception;
	//改
	public Object update(String str,Object obj) throws Exception;
	//查
	public Object find(String str) throws Exception;
	//用户是否存在
	public Object findForObject(String str,Object obj) throws Exception;
	//列表
	public Object findForList(String str,Object obj) throws Exception;

}
