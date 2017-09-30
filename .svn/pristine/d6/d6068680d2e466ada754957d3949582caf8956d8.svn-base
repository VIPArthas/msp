<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0">
    <title>投诉详情</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/fonts/iconfont.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/css.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/style.css"/>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/resource/xlwapp/css/weui.css">
    <%--<script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/jquery-1.9.1.min.js"></script>--%>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/zepto.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resource/xlwapp/js/validation_a.js"></script>
    <link rel="Shortcut Icon" href="<%=request.getContextPath()%>/resource/xlwapp/images/study.ico" />

    <script>
        function showUserImg(e) {
            $(e).attr('src',"<%=request.getContextPath()%>/resource/xlwapp/images/user_icon1.png")
        }

    </script>
    <script>
        $(document).ready(function(){
            $("#footC").find("a").each(function() {
                if($(this).text() == "我的") {
                    $(this).parent().addClass("cur");
                }else{
                    $(this).parent().removeClass();
                }
            })
        })

    </script>

</head>
<body class="combg">
<header>
    <div class="header_licon"><a href="javascript:void(0)" onclick="history.go(-1)"><span class="iconfont icon-lineprev"></span></a></div>
    <h2>投诉详情</h2>
</header>
<div class="container">
    <div class="tous_detail">
        <div class="direct-chat-messages">
            <div class="direct-chat-msg">
                <div class="direct-chat-info clearfix">
                    <span class="direct-chat-name pull-left">${comSug.real_name}</span>
                    <input type="hidden" value="${comSug.id}" id="sugId">
                    <span class="direct-chat-timestamp pull-right"><fmt:formatDate value="${comSug.submit_time}" pattern="MM/dd/yyyy"/></span>
                </div>
                <img class="direct-chat-img" src="<%=request.getContextPath()%>${comSug.logo_url}" alt="message user image" onerror="showUserImg(this);">
                <div class="direct-chat-text bggreen">
                    ${comSug.sug_content}
                </div>
            </div>
            <c:forEach items="${sugRepList}" var="sugRep">
                <c:if test="${sugRep.creator == comSug.submitter}">
                    <div class="direct-chat-msg">
                        <div class="direct-chat-info clearfix">
                            <span class="direct-chat-name pull-left">${sugRep.userName}</span>
                            <span class="direct-chat-timestamp pull-right"><fmt:formatDate value="${sugRep.reply_time}" pattern="MM/dd/yyyy"/></span>
                        </div>
                        <img class="direct-chat-img" src="<%=request.getContextPath()%>${sugRep.logo_url}" alt="message user image" onerror="showUserImg(this);">
                        <div class="direct-chat-text bggreen">
                                ${sugRep.reply_content}
                        </div>
                    </div>
                </c:if>
                <c:if test="${sugRep.creator != comSug.submitter}">
                    <div class="direct-chat-msg right">
                        <div class="direct-chat-info clearfix">
                            <span class="direct-chat-name pull-right">${sugRep.userName}</span>
                            <span class="direct-chat-timestamp pull-left"><fmt:formatDate value="${sugRep.reply_time}" pattern="MM/dd/yyyy"/></span>
                        </div>
                        <img class="direct-chat-img" src="<%=request.getContextPath()%>${sugRep.logo_url}" alt="message user image" onerror="showUserImg(this);">
                        <div class="direct-chat-text bgred">
                                ${sugRep.reply_content}
                        </div>
                    </div>
                </c:if>
            </c:forEach>

        </div>

    </div>
</div>
<div class="btmline"></div>

<div class="tous_huifu">
    <input class="tous_huifuk" type="text" id="reply" maxlength="33">
    <a class="tous_huifubtn" href="javascript:void(0)" id="submit">发送</a>
</div>
<script>
    $(document).ready(function() {
        $("#submit").click(function() {
            var reply = $("#reply").val();
            var sugId = $("#sugId").val();
            if(null == reply || reply.trim() == ''){
                alert_t("请输入回复内容");
                return;
            } else if(reply.trim().length > 33) {
                alert_t("字数超过限制");
                return;
            }

            $.post(
                    '<%=request.getContextPath()%>/common/complaintSuggestions/wx/saveSugReply.htm',
                    {
                        sugId:sugId,
                        replyContent : reply
                    },
                    function(data){
                        if(data.indexOf("<html") > -1){
                            window.location.reload();
                            return;
                        }
                        var returnJson = JSON.parse(data)
                        if(returnJson.code == 1) {
                            window.location.reload();
                        }else{
                            alert_t(returnJson.msg);
                        }
                    }
            );

        });
    });
</script>

</body>
</html>
