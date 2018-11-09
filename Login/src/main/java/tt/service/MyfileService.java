package tt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.dao.DaoImp;
import tt.entity.Myfile;
import tt.entity.User;
@Service("myfileService")
public class MyfileService {
	@Resource(name="daoImp")
	private DaoImp dao;
	public void save(Myfile myfile) throws Exception{
		dao.save("MyfileMapper.save",myfile);
	}
	public void del(Myfile myfile) throws Exception{
		dao.delete("MyfileMapper.del",myfile);
	}
	public void edit(Myfile myfile) throws Exception{
		dao.update("MyfileMapper.edit",myfile);
	}
	public List<Myfile> list(Myfile myfile) throws Exception{
		return (List<Myfile>) dao.findForList("MyfileMapper.datalistpage",myfile);
	}
	//搜索功能
	public List<Myfile> search(Myfile myfile) throws Exception{
		return (List<Myfile>) dao.findForList("MyfileMapper.search",myfile);
	}
	public Myfile findbyid(Myfile myfile) throws Exception{
		return (Myfile)dao.findForObject("MyfileMapper.findbyid",myfile);
	}
	//通过名字查找
	public Myfile findbyname(Myfile myfile) throws Exception{
		return (Myfile)dao.findForObject("MyfileMapper.findbyname",myfile);
	}
}
