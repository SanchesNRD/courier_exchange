<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="page.error404.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/util.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/main.css">
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-form validate-form">
                <div class="login100-form-title">
                    <fmt:message key="page.error404.heading"/>
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

