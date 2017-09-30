<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<c:choose>
		<c:when test="${not empty list[0] }">
			<script>
        		moreData = true;
        	</script>
			<%-- <div class="findNum" name="findNum" count="${newsCount }">为您找到相关结果约<span>${newsCount }</span>条</div> --%>
			<c:forEach items="${list[0] }" var="news">
				<dl class="news"><dt><div><span>${news.day }</span><span>${news.yearMonth }</span></div></dt><dd><div data-id="${news.id }" onclick="newsInfo(this)" class="newstit">${news.news_title }</div><div class="t_gray">来源：${news.source_div == null or news.source_div eq '' ? '其他':news.source_div }</div> </dd></dl>
			</c:forEach>
		</c:when>
		<c:when test="${empty list[0] and list[1] < 2 }">
       		<script>
       			moreData = false;
       		</script>
       	</c:when>
       	<c:when test="${empty list[0] and list[1] > 1 }">
       		<script>
       			moreData = false;
       		</script>
       	</c:when>
		<c:otherwise>
			<div class="nothing">暂无新闻信息</div>
		</c:otherwise>
	</c:choose>