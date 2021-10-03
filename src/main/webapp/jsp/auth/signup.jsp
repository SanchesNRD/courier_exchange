<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="page.signup.title"/></title>
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
            <form action="${pageContext.request.contextPath}\controller" name="signUpForm" method="POST" class="login100-form validate-form">
                <input type="hidden" name="command" value="sign_up"/>

                <div class="login100-form-title">
                    <fmt:message key="page.signup.heading"/>
                </div>
                <div class="txt1">
                    <fmt:message key="page.signup.name"/>
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Username is required">
                    <input class="input100" type="text" name="name" pattern="[a-zA-Zа-яА-я]{2,20}">
                    <span class="focus-input100"></span>
                </div>

                <div class="txt1">
                    <fmt:message key="page.signup.surname"/>
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Username is required">
                    <input class="input100" type="text" name="surname" pattern="[a-zA-Zа-яА-я]{2,20}">
                    <span class="focus-input100"></span>
                </div>

                <div class="txt1">
                    <fmt:message key="page.signup.login"/>
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Username is required">
                    <input class="input100" type="text" name="login" pattern="(?=.*[a-z])[\w]{4,20}">
                    <span class="focus-input100"></span>
                </div>

                <div class="txt1">
                    <fmt:message key="page.signup.password"/>
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Password is required">
                    <input class="input100" type="password" name="password" pattern="(?=.*[\d])(?=.*[a-z])[\w]{8,40}">
                    <span class="focus-input100"></span>
                </div>

                <div class="txt1">
                    <fmt:message key="page.signup.mail"/>
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Username is required">
                    <input class="input100" type="email" name="mail"
                           pattern="^([a-z0-9_-]+\.)*[a-z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,6}$">
                    <span class="focus-input100"></span>
                </div>

                <div class="txt1">
                    <fmt:message key="page.signup.phone"/>
                </div>
                <div class="wrap-input100 validate-input" data-validate = "Username is required">
                    <input class="input100" type="tel" name="phone" pattern="(\+375|80)(29|25|44|33)[\d]{7}">
                    <span class="focus-input100"></span>
                </div>

                <div class="forgot-pass">
                    <c:if test="${requestScope.wrong_validation == true}">
                        <span class="error-content">
                            <fmt:message key="page.signup.validation"/>
                        </span>
                    </c:if>
                    <c:if test="${requestScope.wrong_sign_up == true}">
                        <span class="error-content">
                            <fmt:message key="page.signup.wrong"/>
                        </span>
                    </c:if>
                    <a href='${pageContext.request.contextPath}/jsp/auth/login.jsp' class="txt3">
                        <fmt:message key="page.signup.back"/>
                    </a>
                </div>

                <div class="container-login100-form-btn">
                    <input type="submit" value="<fmt:message key="page.signup.signup_but"/>" class="login100-form-btn"/>
                </div>

            </form>
        </div>
    </div>
</div>
</body>
</html>
