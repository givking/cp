package tt.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.dao.DaoImp;
import tt.entity.Dispatch;
import tt.entity.User;
import tt.tools.PageUtil;
@Service("dispatchService")
public class DispatchService {
	@Resource(name="daoImp")
	private DaoImp dao;
	public void save(Dispatch dispatch) throws Exception{
		dao.save("DispatchMapper.save",dispatch);
	}
	public void del(Dispatch dispatch) throws Exception{
		dao.delete("DispatchMapper.del",dispatch);
	}
	public void edit(Dispatch dispatch) throws Exception{
		dao.update("DispatchMapper.edit",dispatch);
	}
	public List<Dispatch> list(PageUtil page) throws Exception{
		return (List<Dispatch>) dao.findForList("DispatchMapper.datalistpage",page);
	}
	public List<Dispatch> pagelist(PageUtil page) throws Exception{
		return (List<Dispatch>) dao.findForList("DispatchMapper.ddatalistpage",page);
	}
	//check列表
	@SuppressWarnings("unchecked")
	public List<Dispatch> checklist(PageUtil page) throws Exception{
		return (List<Dispatch>) dao.findForList("DispatchMapper.checklistpage",page);
	}
	//approver列表
		public List<Dispatch> approverlist(PageUtil page) throws Exception{
			return (List<Dispatch>) dao.findForList("DispatchMapper.approverlistpage",page);
		}
	//receipt列表
			public List<Dispatch> receiptlist(PageUtil page) throws Exception{
				return (List<Dispatch>) dao.findForList("DispatchMapper.receiptlistpage",page);
			}
	//inbox列表
	public List<Dispatch> inboxlist(PageUtil page) throws Exception{
		return (List<Dispatch>) dao.findForList("DispatchMapper.inboxlistpage",page);
	}
	public Dispatch findbyid(Dispatch dispatch) throws Exception{
		return (Dispatch)dao.findForObject("DispatchMapper.findbyid",dispatch);
	}
}
