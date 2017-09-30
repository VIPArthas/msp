<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="direct-chat-msg">
  <div class="direct-chat-info clearfix">
    <input type="hidden" id="sugId" value="${comSugMap.id}" name="sugId">
    <span class="direct-chat-name pull-left">${comSugMap.real_name}</span>
    <span class="direct-chat-timestamp pull-right"><fmt:formatDate value="${comSugMap.submit_time}" pattern="MM/dd/yyyy"/></span>
  </div>
  <img class="direct-chat-img" src="${comSugMap.logo_url}" alt="message user image">
  <div class="direct-chat-text bggreen">
    ${comSugMap.sug_content}
  </div>
</div>

<c:forEach items="${sugRepMapList}" var="sugRepMap">
  <c:if test="${sugRepMap.creator == comSugMap.submitter}">
    <div class="direct-chat-msg">
      <div class="direct-chat-info clearfix">
        <span class="direct-chat-name pull-left">${sugRepMap.real_name}</span>
        <span class="direct-chat-timestamp pull-right"><fmt:formatDate value="${sugRepMap.reply_time}" pattern="MM/dd/yyyy"/></span>
      </div>
      <img class="direct-chat-img" src="${sugRepMap.logo_url}" alt="message user image">
      <div class="direct-chat-text bggreen">
          ${sugRepMap.reply_content}
      </div>
    </div>
  </c:if>
  <c:if test="${sugRepMap.creator != comSugMap.submitter}">
    <div class="direct-chat-msg right">
      <div class="direct-chat-info clearfix">
        <span class="direct-chat-name pull-right">${sugRepMap.real_name}</span>
        <span class="direct-chat-timestamp pull-left"><fmt:formatDate value="${sugRepMap.reply_time}" pattern="MM/dd/yyyy"/></span>
      </div>
      <img class="direct-chat-img" src="${sugRepMap.logo_url}" alt="message user image">
      <div class="direct-chat-text bgred">
          ${sugRepMap.reply_content}
      </div>
    </div>
  </c:if>
</c:forEach>

