package tt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.dao.DaoImp;
import tt.entity.Area;
@Service("areaService")
public class AreaService {
	@Resource(name="daoImp")
	private DaoImp dao;
	public void save(Area area) throws Exception{
		dao.save("AreaMapper.save",area);
	}
	public void del(Area area) throws Exception{
		dao.delete("AreaMapper.del",area);
	}
	public List<Area> list(Area area) throws Exception{
		return (List<Area>) dao.findForList("AreaMapper.datalist",area);
	}
	public List<Area> fulltree(Area area) throws Exception{
		return (List<Area>) dao.findForList("AreaMapper.fulltree",area);
	}
	public Object findbyname(Area area) throws Exception{
		return dao.findForObject("AreaMapper.findbyname",area);
	}
	public Object findbyid(Area area) throws Exception{
		return dao.findForObject("AreaMapper.findbyid",area);
	}
	public void edit(Area area) throws Exception{
		dao.update("AreaMapper.edit",area);
	}
}
