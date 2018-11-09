package tt.service.mail;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.dao.DaoImp;
import tt.entity.Mail_send;
import tt.tools.PageUtil;
@Service("mail_sendService")
public class Mail_sendService {
	@Resource(name="daoImp")
	private DaoImp dao;
	public void save(Mail_send mail_send) throws Exception{
		dao.save("Mail_sendMapper.save",mail_send);
	}
	/*public void del(Menu menu) throws Exception{
		dao.delete("MenuMapper.del",menu);
	}
	public void edit(Mail mail) throws Exception{
		dao.update("MailMapper.edit",mail);
	}*/
	public List<Mail_send> list(PageUtil page) throws Exception{
		return (List<Mail_send>) dao.findForList("Mail_sendMapper.datalistpage",page);
	}
	
	/*public Mail findbyid(Mail mail) throws Exception{
		return (Mail)dao.findForObject("MailMapper.findbyid",mail);
	}*/
}
