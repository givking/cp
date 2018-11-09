package tt.tools;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.imageio.ImageIO;

public class PictureCheckCode extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public PictureCheckCode() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void init() throws ServletException {
		super.init();
	}
	/*该方法主要作用是获得随机生成的颜色*/ 
	public Color getRandColor(int s,int e){
		Random random=new Random ();
		if(s>255) s=255;
		if(e>255) e=255;
		int r,g,b;
		r=s+random.nextInt(e-s);	//随机生成RGB颜色中的r值
		g=s+random.nextInt(e-s);	//随机生成RGB颜色中的g值
		b=s+random.nextInt(e-s);	//随机生成RGB颜色中的b值
		return new Color(r,g,b);
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 // 告知浏览当作图片处理

	       response.setContentType("image/jpeg");

	 

	       // 告诉浏览器不缓存

	       response.setHeader("pragma", "no-cache");

	       response.setHeader("cache-control", "no-cache");

	       response.setHeader("expires", "0");

	 

	       // 产生由4位数字构成的验证码

	       int length = 4;

	       String valcode = "";

	       Random rd = new Random();

	       for(int i=0; i<length; i++)

	           valcode+=rd.nextInt(10);

	 

	       // 把产生的验证码存入到Session中

	       HttpSession session = request.getSession();

	       session.setAttribute("randCheckCode", valcode);

	 

	       // 产生图片

	       int width = 80;

	       int height = 25;

	       BufferedImage img = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);

	 

	       // 获取一个Graphics

	       Graphics g = img.getGraphics();

	 

	       // 填充背景色

	       g.setColor(Color.WHITE);

	       g.fillRect(0, 0, width, height);

	 

	       // 填充干扰线50

	       for(int i=0; i<50; i++){

	           g.setColor(new Color(rd.nextInt(100)+155,rd.nextInt(100)+155,rd.nextInt(100)+155));

	           g.drawLine(rd.nextInt(width), rd.nextInt(height),rd.nextInt(width), rd.nextInt(height));

	       }

	 

	       // 绘制边框

	       g.setColor(Color.GRAY);

	       g.drawRect(0, 0, width-1, height-1);

	 

	       // 绘制验证码

	       Font[] fonts = {new Font("隶书",Font.BOLD,18),new Font("楷体",Font.BOLD,18),new Font("宋体",Font.BOLD,18),new Font("幼圆",Font.BOLD,18)};

	       for(int i=0; i<length; i++){

	           g.setColor(new Color(rd.nextInt(150),rd.nextInt(150),rd.nextInt(150)));

	           g.setFont(fonts[rd.nextInt(fonts.length)]);

	           g.drawString(valcode.charAt(i)+"", width/valcode.length()*i+2, 18);

	       }

	 

	       // 输出图像

	       g.dispose();

	       ImageIO.write(img, "jpeg", response.getOutputStream());
	}
}
