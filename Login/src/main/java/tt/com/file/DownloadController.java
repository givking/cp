package tt.com.file;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Myfile;
import tt.service.MyfileService;


@Controller
@RequestMapping("/downfile")
public class DownloadController {
	@Resource(name="myfileService")
	private MyfileService myfileService;
	
	@RequestMapping("/godownload")
	public ModelAndView godownload(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		Myfile myfile = new Myfile();
		List<Myfile> varlist = new ArrayList<Myfile>();
		varlist=myfileService.list(myfile);
		mv.addObject("varlist", varlist);
		mv.setViewName("file/download_list");
		return mv;
	}
	@RequestMapping("/download")
	public void download(HttpServletRequest req,HttpServletResponse res) throws Exception{
		Myfile myfile=new Myfile();
		myfile.setFile_id(req.getParameter("FILE_ID"));
		myfile=myfileService.findbyid(myfile);
		//获取网站部署路径(通过ServletContext对象)，用于确定下载文件位置，从而实现下载  
       // String path = req.getSession().getServletContext().getRealPath("/")+"uploadFile";  
  
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型  
        res.setContentType("multipart/form-data");  
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)  
        res.setHeader("Content-Disposition", "attachment;fileName="+myfile.getFile_name());  
        ServletOutputStream out;  
        //通过文件路径获得File对象(假如此路径中有一个icon.png文件)  
        File dfile = new File(myfile.getFile_path());  
  
        try {  
            FileInputStream inputStream = new FileInputStream(dfile);  
  
            //3.通过response获取ServletOutputStream对象(out)  
            out = res.getOutputStream();  
  
            int b = 0;  
            byte[] buffer = new byte[512];  
            while (b != -1){  
            	
                b = inputStream.read(buffer);  
                //4.写到输出流(out)中  
                if(b==-1) break;
                out.write(buffer,0,b);  
            }  
            inputStream.close();  
            out.flush();  
            out.close();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}
	@RequestMapping("/del")
	public ModelAndView del(HttpServletRequest req) throws Exception{
		ModelAndView mv=new ModelAndView();
		Myfile myfile=new Myfile();
		myfile.setFile_id(req.getParameter("FILE_ID"));
		myfileService.del(myfile);
		mv.setViewName("forward:godownload");
		return mv;
	}
	@RequestMapping("/search")
	public ModelAndView search(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		Myfile myfile = new Myfile();
		myfile.setKey_word(req.getParameter("SEARCH"));
		List<Myfile> varlist = new ArrayList<Myfile>();
		varlist=myfileService.search(myfile);
		mv.addObject("varlist", varlist);
		mv.setViewName("file/download_list");
		return mv;
	}
}
