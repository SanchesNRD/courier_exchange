<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="page.newpass.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/util.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/main.css">
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            ${requestScope.user_id}
            <form action="${pageContext.request.contextPath}\controller" name="loginForm" method="POST" class="login100-form validate-form">
                <input type="hidden" name="command" value="update_password"/>
                <input type="hidden" name="user_id" value=${requestScope.user_id}>

                <div class="login100-form-title">
                    <fmt:message key="page.newpass.heading"/>
                </div>
                <div class="txt1">
                    <fmt:message key="page.signup.password"/>
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Username is required">
                    <input class="input100" type="password" name="password"
                           pattern="(?=.*[\d])(?=.*[a-z])[\w]{8,40}"/>
                    <span class="focus-input100"></span>
                </div>

                <div class="forgot-pass">
                    <c:if test="${requestScope.wrong_validation == true}">
                        <span class="error-content">
                            <fmt:message key="page.newpass.validation"/>
                        </span>
                    </c:if>
                    <a href='${pageContext.request.contextPath}/jsp/auth/login.jsp' class="txt3">
                        <fmt:message key="page.signup.back"/>
                    </a>
                </div>

                <div class="container-login100-form-btn">
                    <input type="submit" value="<fmt:message key="page.newpass.update"/>" class="signup-form-btn"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
