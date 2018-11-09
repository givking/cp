package tt.service.mail;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.dao.DaoImp;
import tt.entity.Mail;
@Service("mailService")
public class MailService {
	@Resource(name="daoImp")
	private DaoImp dao;
	public void save(Mail mail) throws Exception{
		dao.save("MailMapper.save",mail);
	}
	/*public void del(Menu menu) throws Exception{
		dao.delete("MenuMapper.del",menu);
	}*/
	public void edit(Mail mail) throws Exception{
		dao.update("MailMapper.edit",mail);
	}
	public List<Mail> list(Mail mail) throws Exception{
		return (List<Mail>) dao.findForList("MailMapper.datalistpage",mail);
	}
	
	public Mail findbyid(Mail mail) throws Exception{
		return (Mail)dao.findForObject("MailMapper.findbyid",mail);
	}
}
