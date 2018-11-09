package tt.com.file;


import java.io.File;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tt.entity.Myfile;
import tt.service.MyfileService;
import tt.tools.FileUpload;
import tt.tools.Uid;


@Controller
@RequestMapping("/file")
public class UploadController {
	@Resource(name="myfileService")
	private MyfileService myfileService;
	
	@RequestMapping("/goupload")
	public ModelAndView goupload(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("file/upload");
		return mv;
	}
	@RequestMapping("/upload")
	public ModelAndView upload(HttpServletRequest req, MultipartFile file) throws Exception{
		ModelAndView mv = new ModelAndView();
		Myfile nfile = new Myfile();
		String ofileName = file.getOriginalFilename();
		nfile.setFile_name(ofileName);
		String log = "success";
		//String fileExt = ofileName.indexOf(".") > 0 ?ofileName.substring(ofileName.lastIndexOf("."),ofileName.length()):null;
		if(myfileService.findbyname(nfile)!=null){
			log="reapet";
			mv.setViewName("uploadfail");
		}else if(file.getSize()>2*1024*1024){
			log="bigfail";
			mv.setViewName("uploadfail");
		}else if(null != file && !file.isEmpty()) {
			String filePath = req.getSession().getServletContext().getRealPath("/") + "uploadFile" ;		//文件上传路径
			String realPath = filePath+"\\"+ofileName;
			FileUpload.fileUp(file,ofileName,filePath);				//执行上传
			nfile.setFile_id(Uid.getUid());
			nfile.setFile_name(ofileName);
			nfile.setFile_path(realPath);
			myfileService.save(nfile);
			mv.setViewName("uploadsuccess");
		}
		System.out.println(file.getSize());
		mv.addObject("result", log);
		
		
		return mv;
	}
	@RequestMapping("/del")
	public ModelAndView del(HttpServletRequest req) throws Exception{
		ModelAndView mv = new ModelAndView();
		Myfile myfile= new Myfile();
		myfile.setFile_id(req.getParameter("FILE_ID"));
		myfile=myfileService.findbyid(myfile);
		String path = myfile.getFile_path();
		File file = new File(path);
		if(file.isFile()&&file.exists()){
			file.delete();
			myfileService.del(myfile);
		}
		mv.setViewName("forward:/downfile/godownload");
		return mv;
	}
}
