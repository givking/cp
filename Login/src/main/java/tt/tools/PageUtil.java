package tt.tools;

public class PageUtil {
	private Integer pagecount; //总页数
	private Integer pagelistcount; //页面显示条数
	private Integer listcount; //数据总条数
	private Integer currentpage=1; //当前页
	private Integer pagebefore; //前一页
	private Integer pageafter; //后一页
	private Integer startindex; //页面第一条数据的索引
	private Integer endindex; //页面最后一条数据的索引
	private Object obj;
	public Integer getPagecount() {
		if(this.listcount%this.pagelistcount!=0){
			pagecount=this.listcount/this.pagelistcount+1;
		}else{
			pagecount=this.listcount/this.pagelistcount;
		}
		return pagecount;
	}
	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}
	public Integer getPagelistcount() {
		return pagelistcount;
	}
	public void setPagelistcount(Integer pagelistcount) {
		this.pagelistcount = pagelistcount;
	}
	public Integer getListcount() {
		return listcount;
	}
	public void setListcount(Integer listcount) {
		this.listcount = listcount;
	}
	public Integer getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(Integer currentpage) {
		if(currentpage==null||currentpage<1){
			currentpage=1;
		}
		this.currentpage = currentpage;
	}
	public Integer getPagebefore() {
		pagebefore=this.currentpage-1<1?1:this.currentpage-1;
		return pagebefore;
	}
	public void setPagebefore(Integer pagebefore) {
		this.pagebefore = pagebefore;
	}
	public Integer getPageafter() {
		pageafter=this.currentpage+1>=this.pagecount?this.pagecount:this.currentpage+1;
		return pageafter;
	}
	public void setPageafter(Integer pageafter) {
		this.pageafter = pageafter;
	}
	public Integer getStartindex() {
		startindex=(this.currentpage-1)*this.pagelistcount;
		return startindex;
	}
	public void setStartindex(Integer startindex) {
		this.startindex = startindex;
	}
	public Integer getEndindex() {
		if(this.currentpage==this.pagecount&&this.listcount%this.pagelistcount!=0){
			endindex=this.startindex+this.listcount%this.pagelistcount-1;
		}else{
			endindex=this.startindex+this.pagelistcount-1;
		}
		return endindex;
	}
	/*public void setEndindex(Integer endindex) {
		this.endindex = endindex;
	}*/
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
}
