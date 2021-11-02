<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<head>
    <title><fmt:message key="page.mail_message.title"/></title>
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
            <form action="${pageContext.request.contextPath}/controller" class="login100-form validate-form">
                <input type="hidden" name="command" value="go_to_login"/>
                <div class="login100-form-title">
                    <fmt:message key="page.mail_message.heading"/>
                </div>

                <div class="container-login100-form-btn">
                    <input type="submit" value="<fmt:message key="page.signup.back"/>" class="login100-form-btn"/>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
