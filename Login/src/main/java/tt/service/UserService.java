package tt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.dao.DaoImp;
import tt.entity.User;
@Service("userService")
public class UserService {
	@Resource(name="daoImp")
	private DaoImp dao;
	public void save(User user) throws Exception{
		dao.save("UserMapper.save",user);
	}
	public List<User> list(User user) throws Exception{
		return (List<User>) dao.findForList("UserMapper.datalist",user);
	}
	public List<User> fulltree(User user) throws Exception{
		return (List<User>) dao.findForList("UserMapper.fulltree",user);
	}
	public Object findbyname(User user) throws Exception{
		return dao.findForObject("UserMapper.findbyname",user);
	}
	public Object findbyid(User user) throws Exception{
		return dao.findForObject("UserMapper.findbyid",user);
	}
	public void edit(User user) throws Exception{
		dao.update("UserMapper.edit",user);
	}
}
