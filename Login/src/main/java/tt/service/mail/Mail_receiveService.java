package tt.service.mail;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import tt.dao.DaoImp;
import tt.entity.Mail_receive;
import tt.entity.Mail_send;
import tt.tools.PageUtil;
@Service("mail_receiveService")
public class Mail_receiveService {
	@Resource(name="daoImp")
	private DaoImp dao;
	public void save(Mail_receive mail_receive) throws Exception{
		dao.save("Mail_receiveMapper.save",mail_receive);
	}
	/*public void del(Menu menu) throws Exception{
		dao.delete("MenuMapper.del",menu);
	}
	public void edit(Mail mail) throws Exception{
		dao.update("MailMapper.edit",mail);
	}*/
	public List<Mail_receive> list(PageUtil page) throws Exception{
		return (List<Mail_receive>) dao.findForList("Mail_receiveMapper.datalistpage",page);
	}
	
	public Mail_receive findbyuid(Mail_receive mail_receive) throws Exception{
		return (Mail_receive)dao.findForObject("Mail_receiveMapper.findbyuid",mail_receive);
	}
	public Mail_receive findbyid(Mail_receive mail_receive) throws Exception{
		return (Mail_receive)dao.findForObject("Mail_receiveMapper.findbyid",mail_receive);
	}
}
