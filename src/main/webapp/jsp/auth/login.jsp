<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="page.login.title"/></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/util.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/auth/main.css">
</head>
<body>
<div class="limiter">
    <a class="container-language" href="${pageContext.request.contextPath}/controller?command=change_locale&locale=${sessionScope.locale}&current_url=${pageContext.request.requestURL}">
        <fmt:message key="sidebar.user.language"/>
    </a>
    <div class="container-login100">
        <div class="wrap-login100">
            <form action="${pageContext.request.contextPath}\controller?command=login" name="loginForm" method="POST" class="login100-form validate-form">
                <div class="login100-form-title">
                    <fmt:message key="page.login.heading"/>
                </div>
                <div class="txt1"> <fmt:message key="page.login.login"/> </div>
                <div class="wrap-input100 validate-input" data-validate = "Username is required">
                    <input class="input100" type="text" name="login" pattern="(?=.*[a-z])[\w]{4,20}">
                    <span class="focus-input100"></span>
                </div>

                <div class="txt1"> <fmt:message key="page.login.password"/> </div>
                <div class="wrap-input100 validate-input" data-validate = "Password is required">
                    <input class="input100" type="password" name="password" pattern="(?=.*[\d])(?=.*[a-z])[\w]{8,40}">
                    <span class="focus-input100"></span>
                </div>

                <div class="forgot-pass">
                    <c:if test="${requestScope.wrong_login_or_password == true}">
                        <span class="error-content">
                            <fmt:message key="page.login.wrong"/>
                        </span>
                    </c:if>
                    <c:if test="${requestScope.banned_user == true}">
                        <span class="error-content">
                            <fmt:message key="page.login.banned"/>
                        </span>
                    </c:if>
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_forgot_pass" class="txt3">
                        <fmt:message key="page.login.forgot"/>
                    </a>
                </div>

                <div class="container-login100-form-btn">
                    <input type="submit" name="command" value="<fmt:message key="page.login.login_but"/>" class="login100-form-btn"/>
                    <a href="${pageContext.request.contextPath}/controller?command=go_to_sign_up" class="signup-form-btn">
                        <fmt:message key="page.login.signup_but"/>
                    </a>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
