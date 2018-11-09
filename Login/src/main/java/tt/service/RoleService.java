package tt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.dao.DaoImp;
import tt.entity.Role;
import tt.entity.Role_menu;
@Service("roleService")
public class RoleService {
	@Resource(name="daoImp")
	private DaoImp dao;
	public void saveauth(Role_menu role_menu) throws Exception{
		dao.save("Role_MenuMapper.save",role_menu);
	}
	public void delauth(Role_menu role_menu) throws Exception{
		dao.delete("Role_MenuMapper.delete",role_menu);
	}
	//通过menu_id删除
	public void del(Role_menu role_menu) throws Exception{
		dao.delete("Role_MenuMapper.del",role_menu);
	}
	public Object findbyroleid(Role role) throws Exception{
		return (List<Role_menu>)dao.findForList("Role_MenuMapper.findbyroleid",role);
	}
	public Object findbymenuid(Role_menu role_menu) throws Exception{
		return (List<Role_menu>)dao.findForList("Role_MenuMapper.findbymenuid",role_menu);
	}
	
	public List<Role_menu> rmlist(Role_menu role_menu) throws Exception{
		return (List<Role_menu>) dao.findForList("Role_MenuMapper.datalist",role_menu);
	}
	public List<Role> list(Role role) throws Exception{
		return (List<Role>) dao.findForList("RoleMapper.datalist",role);
	}
	public Object findbyid(Role role) throws Exception{
		return dao.findForObject("RoleMapper.findbyid",role);
	}
}
