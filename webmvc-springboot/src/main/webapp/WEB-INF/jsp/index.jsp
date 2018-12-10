<!-- 使用jsp指令生成xml格式的文件: -->
<jsp:root 
	xmlns:jsp="http://java.sun.com/JSP/Page" 
	xmlns:c="http://java.sun.com/jsp/jstl/core"
	version="2.0">
	<jsp:directive.page isELIgnored="false"/>
    <Hello>
        <message>${message}</message>
        <language>${acceptLanguage}</language>
        <jsessionId>${jsessionId}</jsessionId>
        <users>
        <c:forEach items="${users}"  var="u">
		  <user>${u}</user>
		</c:forEach>
		</users>
    </Hello>
</jsp:root>