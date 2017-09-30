<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/mui.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/iconfont.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/css/new_file.css?v=61">
    <script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/jquery-3.1.1.js"></script>
    <script type="text/javascript">var urlPix = "<%=request.getContextPath()%>";</script>
  
    <title>用户管理</title>
</head>

<body id="usermanage">
        <div id="pullrefresh" class="mui-content mui-scroll-wrapper backimgblue" style="padding-bottom: 0.8rem;overflow-y:auto;">
            <div class="mui-scroll">
                <nav class="h_top">
                    <input type="text" placeholder="" id="vagueSearch" value="${user.vagueSearch }"/>
                    <p 	id="userSearch" onclick="userSearch()">搜索</p>
                    
                </nav>
                <div>
                
                </div>
                <div class="mui-table-view mui-table-view-chevron" style="background-color: #f3f3f3;margin: 0 0.24rem">
                    <nav class="content_nav">
                        <span>用户管理</span>
                        <span style="background-color:#fff"></span>
                        <span class="adduser" >添加用户</span>
                    </nav>
      
 
 <%--                    
      <c:choose>
     	<c:when test="${not empty userList[0] }">
     		<c:forEach items="${userList[0]}" var="user" varStatus="status">
     		   <div class="h_xuliehao2 mui-table-view-cell">
                        <div>
                            <p>序号${status.index+1 }</p>
                            <img src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/img/展开.png" class="zhankai"/>
                        </div>
     		  			<div class="fenlan">
                            <span>姓名</span>
                            <span>${user.real_name }</span>
                        </div>
     		     		 <div class="user-content">
                            <div class="fenlan">
                                <span>手机号</span>
                                <span>${user.phone }</span>
                            </div>
                            <div class="fenlan">
                                <span>邮箱</span>
                                <span>${user.mail }</span>
                            </div>
                            <div class="fenlan">
                                <span>QQ号</span>
                                <span>${user.qq }</span>
                            </div>
                            <div class="fenlan">
                                <span>微信</span>
                                <span>${user.wx_status ==1?'已关注':'未关注' }</span>
                            </div>
                            <div class="fenlan">
                                <span>标签</span>
                               
                <c:choose>
            		<c:when test="${not empty user.tagList }">
            			<c:forEach items="${user.tagList }" var="tag">
            				<span >${tag.tag_name }</span>
            			</c:forEach>
            		</c:when>
            		<c:otherwise>
            		</c:otherwise>
            	</c:choose>

                            </div>
                            <div class="editor">
                                <img src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/img/编辑.png" alt="">
                                <span>编辑</span>
                            </div>
                            <div class="editor editor1">
                                <img src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/img/删除.png" alt="">
                                <span>删除</span>
                            </div>
                        </div>
                    </div>
   		</c:forEach>
     	</c:when>
     	<c:otherwise>
     	</c:otherwise>
     </c:choose> --%>
                </div>
            </div>
        </div>
</body>
</html>

<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/rem.js"></script>
<!--<script src="../commons/js/new_file.js"></script>-->
<script src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/mui.min.js"></script>
<script>
        mui.init({
            gestureConfig:{
                doubletap:true
            },
            pullRefresh: {
                container: '#pullrefresh',
                up: {
                    contentrefresh: '正在加载...',
                    callback: pullupRefresh
                }
            }
        });

        var currentPage = 0;
    	var totalPageSize = ${totalPage};
    	
   
        function pullupRefresh() {
            setTimeout(function () {
                mui('#pullrefresh').pullRefresh().endPullupToRefresh((++currentPage > totalPageSize)); //参数为true代表没有更多数据了  初始加载的不计入count  5代表可上拉的次数，即总数据/10向上取整。
                var table = document.body.querySelector('.mui-table-view');
                searchContent=$('#vagueSearch').val();
        		$.post(
        			"<%=request.getContextPath() %>/wmh/userManage/web/goUserManageListWx.htm",
        	   		{
        				currentpage:currentPage,searchContent:searchContent
        			},
        	   		function(response){
        				var res = JSON.parse(response);
        			
        				var list = res.userList[0];        				
        				for (var i = 0;  i < list.length; i++){
        					var user = list[i];	
        					//处理标签
        				
        					var tagList="";
        				 	var tagList1=user.tagList;
        				 	
        					for(var j = 0;  j < tagList1.length; j++){
        						tagList= tagList+"<span>"+tagList1[j].tag_name+" "+"</span>";
        					}
        					 var sec = document.createElement('div');
        					
        	                    sec.className = 'h_xuliehao2 mui-table-view-cell';
        	                    var indexNum=(i+1)+(currentPage-1)*10;
        	                    sec.innerHTML = '<div><p>序号'+indexNum+'</p><img src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/img/展开.png" class="zhankai"/></div>'
                                    +'<div class="fenlan"><span>姓名</span><span>'+user.real_name+'</span></div>'
                                    +'<div class="user-content"><div class="fenlan"><span>手机号</span><span>'+user.phone+'</span></div>'
                                    +'<div class="fenlan"><span>邮箱</span><span>'+user.mail+'</span></div>'
                                    +'<div class="fenlan"><span>QQ号</span><span>'+user.qq+'</span></div>'
                                    +'<div class="fenlan"><span>微信</span><span>'+(user.wx_status ==1?'已关注':'未关注')+'</span></div>'
                                    +'<div class="fenlan biaoqian"><span>标签</span><span class="user-biaoqian">'+tagList+'</span></div>'     
                                    +'<div class="editor editor2"  data-user-id='+user.id+' ><img src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/img/编辑.png" alt=""><span>编辑</span></div>'
                                    +'<div class="editor editor1" data-user-id='+user.id+'><img src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/img/删除.png" alt=""><span>删除</span></div></div>';
                                   
                            table.appendChild(sec);

        				}
        	   		}	
        	    );
            });
        }
        if (mui.os.plus) {
            mui.plusReady(function () {
                setTimeout(function () {
                    mui('#pullrefresh').pullRefresh().pullupLoading();
                });

            });
        } else {
            mui.ready(function () {
                mui('#pullrefresh').pullRefresh().pullupLoading();
            });
        }

        mui('body').on('tap', '.zhankai', function () {

            $(this).toggleClass('rotate');
            console.log($(this));
            $(this).parent('div').siblings('.user-content').toggleClass('content-display');

        });
      mui('body').on('tap', '.editor2', function () {$(this).click(edituser($(this))); });
      mui('body').on('tap', '#userSearch', function () {$(this).click();});
      
</script>


<script type="text/javascript"
		src="<%=request.getContextPath() %>/resource/wmh/wx/manage/commons/js/user.js"  charset="UTF-8">
</script>
	 
