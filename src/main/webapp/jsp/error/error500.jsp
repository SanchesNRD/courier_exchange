<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="page.error500.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/util.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/main.css">
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-form validate-form">
                <div class="login100-form-title">
                    <fmt:message key="page.error500.heading"/>
                </div>
                <div class="txt1">
                    <c:choose>
                        <c:when test="${requestScope.exception ne null}">
                            ${requestScope.exception}
                        </c:when>
                        <c:otherwise>
                            Request from ${pageContext.errorData.requestURI} is failed
                            <br/>
                            Servlet name: ${pageContext.errorData.servletName}
                            <br/>
                            Status code: ${pageContext.errorData.statusCode}
                            <br/>
                            Exception: ${pageContext.exception}
                            <br/>
                            Message from exception: ${pageContext.exception.message}
                        </c:otherwise>
                    </c:choose>
                </div>
                <form action="${pageContext.request.contextPath}/controller?command=go_to_login">
                    <div class="container-login100-form-btn">
                        <input type="submit" value="<fmt:message key="page.signup.back"/>" class="login100-form-btn"/>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
