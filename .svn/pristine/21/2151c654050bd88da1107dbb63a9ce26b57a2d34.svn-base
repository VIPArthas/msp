package com.wh.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.wh.constants.Constants;

/**
 * @Title: PageTag.java
 * @Description: 分页工具类
 */
public class PageTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private int pagesize;// 每页记录

	private int rscount;// 总记录数

	private int currentpage;// 当前页

	private String action;// 提交action

	private int pagecount;// 总页数

	private int pagenum = 7;// 显示分页的页数

	private List<Integer> pageBox;// 页码的箱子

	private String className = INDEXTEXT;// 样式

	/** **************样式常量******************** */
	public static final String INDEX_PAGE = "indexPage";
	public static final String ADMIN_PAGE = "adminPage";
	public static final String AJAX_PAGE = "ajaxPage";

	public static final String INDEXTEXT = "indextext";

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public int getRscount() {
		return rscount;
	}

	public void setRscount(int rscount) {
		this.rscount = rscount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		if (0 != pagesize) {
			this.pagesize = pagesize;
		}else {
			this.pagesize = Constants.pageSize;
		}
		try {
			this.pagecount = ((this.rscount - 1) / this.pagesize) + 1;// 计算总页数
			pageBox = new ArrayList<Integer>();
			// 计算显示的页码
			if (pagecount <= pagenum) {// 总页数小于需显示页
				for (int i = 1; i <= pagecount; i++) {
					pageBox.add(i);
				}
			} else {
				if (pagenum - currentpage < 2) {// 当前页小于总页数
					pageBox.add(1);
					pageBox.add(2);
					pageBox.add(0);

					for (int i = pagenum / 3; i >= 1; i--) {
						pageBox.add(currentpage - i);
					}
					pageBox.add(currentpage);
					for (int i = 1; i <= pagenum / 3; i++) {
						if ((currentpage + i) <= pagecount) {
							pageBox.add(currentpage + i);
						} else {
							break;
						}
					}
					if (currentpage + (pagenum / 3) < pagecount) {
						pageBox.add(0);
					}
				} else {
					for (int i = 0; i < pagenum; i++) {
						pageBox.add(i + 1);
					}
					pageBox.add(0);
				}
			}
		} catch (Exception ex) {
			this.pagecount = 0;
		}
	}

	public int doStartTag() throws JspException {
		// 自动生成方法存根
		String pagetool = pagetool(className);

		JspWriter out = pageContext.getOut();
		try {
			out.write(pagetool);
		} catch (IOException e) {
			// 自动生成 catch 块
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	/**
	 * 分页工具条
	 * 
	 * @param fileName
	 *            String
	 * @return String
	 */
	public String pagetool(String flag) {
		StringBuffer str = new StringBuffer();
		String url = this.getParamUrl();
		int ProPage = this.currentpage - 1;
		int Nextpage = this.currentpage + 1;
		if (flag.equals(ADMIN_PAGE)) {
			str.append("<div class='pager'>");
            str.append("<div class='bk_page_a'>");
            // str.append("总共" + rscount + "个记录，共" + pagecount + "页，当前第" + currentpage + "页 | ");
            str.append("<label><i>" + "共" + rscount + "条&nbsp;&nbsp;第" + currentpage + "页</i>/共" + pagecount + "页</label>");
            if (currentpage == 1) {
                str.append("<span>首页</span>&nbsp;");

            } else {
                int s = currentpage - 1;
                str.append("<a href='" + url + "=1'>首页</a>&nbsp;");
                str.append("<a class='pre' href='" + url + "=" + s + "'>上一页</a>");
            }
            if (currentpage == pagecount) {
                str.append("<span>末页</span>&nbsp;");
            } else {
                int s = currentpage + 1;
                str.append("<a class='next' href='" + url + "=" + s + "'>下一页</a>");
                str.append("<a href='" + url + "=" + pagecount + "'>末页</a>&nbsp;");
            }
            str.append("</div></div>");
		}
		// 文字的分页
		if (flag.equals(INDEX_PAGE)) {
			str.append("<div class='pagelist mr20'>");
			if (currentpage == 1) {
				str.append("<span class='onepage'>首页</span> ");
				str.append("<span class='onepage'>上一页</span>");
			} else {
				str.append("<a href='" + url + "=1'>首页</a>");
				str.append("<a href='" + url + "=" + ProPage + "'>上一页</a>");
			}
			for (Integer i : pageBox) {
				if (i == 0 || i == -1) {
					str.append("...");
				} else {
					if (currentpage == i) {
						str.append("<a href='javascript:;' class='page_first'>" + i + "</a>");
					} else {
						str.append("<a href='" + url + "=" + i + "'>" + i + "</a>");
					}
				}
			}
			if (currentpage == pagecount) {
				str.append("<span class='onepage'>下一页</span> ");
				str.append("<span class='onepage'>尾页</span> ");
			} else {
				str.append("<a href='" + url + "=" + Nextpage + "'>下一页</a>");
				str.append("<a href='" + url + "=" + pagecount + "'>尾页</a>");
			}
			str.append(
					"&nbsp;&nbsp;跳到至<input type='text' class='jump_page' id='goto' value='' onkeyup=\"this.value=this.value.replace(/\\D/g,'')\" onafterpaste=\"this.value=this.value.replace(/\\D/g,'')\"/>页<input type='button' value='确定' onClick='pageSearch()' class='go'/>");
			str.append("</div>");
			str.append("<script language='javascript'>");
			str.append("function pageSearch(){");
			str.append("var page = document.getElementById('goto').value;");
			str.append("if(page>" + pagecount + "){");
			str.append("alert('请输入正确的页数');");
			str.append("return false;}");
			str.append("if(page==''){");
			str.append("alert('请输入正确的页数');");
			str.append("return false;}");
			str.append("window.location.href = '" + url + "='+page+'';");
			str.append("}");
			str.append("</script>");
		}
		
		if (flag.equals(AJAX_PAGE)) {
			str.append("<div class='pager'>");
			str.append("<div class='bk_page_a'>");
			str.append("<label><i>"+"共"+rscount+"条&nbsp;&nbsp;第"+currentpage+"页</i>/共"+ pagecount+"页</label>");
			if (currentpage == 1) {
				str.append("<span>首页</span>&nbsp;");
			
			} else {
				int s = currentpage - 1;
				str.append("<a href='javascript:" + this.action + "(1);'>首页</a>&nbsp;");
				str.append("<a class='pre' href='javascript:" + this.action + "(" + s + ");'>上一页</a>");
			}
			if (currentpage == pagecount) {
				str.append("<span>末页</span>&nbsp;");
			} else {	
				int s = currentpage + 1;
				str.append("<a class='next' href='javascript:" + this.action + "(" + s + ");'>下一页</a>");
				str.append("<a href='javascript:" + this.action + "(" + pagecount + ");'>末页</a>&nbsp;");
			}
			str.append("</div></div>");
		}
		return str.toString();
	}

	public String getParamUrl() {
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
		String url = request.getContextPath() + "/" + action;
		if (url.indexOf("?") == -1) {
			url = url + "?";
		}
		String totalParams = "";
        @SuppressWarnings("rawtypes")
        Enumeration params = request.getParameterNames();// 得到所有参数名
		while (params.hasMoreElements()) {
			String tempName = params.nextElement().toString();
			String tempValue = request.getParameter(tempName);
			if (tempValue != null && !"".equals(tempValue)) {
				if (java.nio.charset.Charset.forName("ISO-8859-1").newEncoder().canEncode(tempValue)) {
					try {
						tempValue = new String(tempValue.getBytes("iso-8859-1"), "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
				}
			}
			request.setAttribute(tempName, tempValue);
			if (tempValue != null && !tempValue.equals("") && !tempName.equals("currentpage")
					&& !tempName.equals("rscount")) {
				if (totalParams.equals("")) {

					totalParams = totalParams + tempName + "=" + tempValue;
				} else {
					totalParams = totalParams + "&" + tempName + "=" + tempValue;
				}
			} else if (tempValue != null && !tempValue.equals("") && tempName.equals("currentpage")) {
				currentpage = Integer.parseInt(tempValue);
			} else if (tempValue != null && !tempValue.equals("") && tempName.equals("rscount")) {
				rscount = Integer.parseInt(tempValue);
			}
		}
		response.setContentType("text/html;charset=UTF-8");
		String totalUrl = url + totalParams + "&rscount=" + rscount + "&currentpage";
		return totalUrl;
	}
}
