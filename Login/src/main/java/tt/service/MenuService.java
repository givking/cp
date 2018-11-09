package tt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.dao.DaoImp;
import tt.entity.Menu;
import tt.entity.User;
@Service("menuService")
public class MenuService {
	@Resource(name="daoImp")
	private DaoImp dao;
	public void save(Menu menu) throws Exception{
		dao.save("MenuMapper.save",menu);
	}
	public void del(Menu menu) throws Exception{
		dao.delete("MenuMapper.del",menu);
	}
	public void edit(Menu menu) throws Exception{
		dao.update("MenuMapper.edit",menu);
	}
	public List<Menu> list(Menu menu) throws Exception{
		return (List<Menu>) dao.findForList("MenuMapper.datalist",menu);
	}
	
	public Menu findbyid(Menu menu) throws Exception{
		return (Menu)dao.findForObject("MenuMapper.findbyid",menu);
	}
}
