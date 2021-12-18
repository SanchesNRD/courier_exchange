<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="/WEB-INF/tld/footer.tld" prefix="ftg"%>

<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>

<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/AdminLTE.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/lib/font-awesome/css/font-awesome.min.css">
    <title>${sessionScope.user.login}</title>
</head>
<body>
<div class="wrapper">

    <jsp:include page="../common/header.jsp"/>

    <jsp:include page="../common/admin_sidebar.jsp"/>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                <fmt:message key="page.admin.all_users"/>
            </h1>
        </section>
        <section class="content">
            <c:if test="${requestScope.user_have_order}">
                <div class="wrap-login100 error-content">
                    <fmt:message key="page.admin.user_have_order"/>
                </div>
            </c:if>
            <c:if test="${requestScope.wrong_validation}">
                <div class="wrap-login100 error-content">
                    <fmt:message key="page.signup.validation"/>
                </div>
            </c:if>
            <div class="wrap-login100">
                <div class="row">
                    <div class="col-md-2 txt1 text-font text-left">
                        <fmt:message key="page.profile.login"/>
                    </div>
                    <div class="col-md-3 txt1 text-font text-left">
                        <fmt:message key="page.profile.fio"/>
                    </div>
                    <div class="col-md-3 txt1 text-font text-left">
                        <fmt:message key="page.profile.mail"/>
                    </div>
                    <div class="col-md-2 txt1 text-font text-left">
                        <fmt:message key="page.profile.phone"/>
                    </div>
                    <div class="col-md-2 txt1 text-font text-left">
                        <fmt:message key="page.orders.action"/>
                    </div>
                </div>
                <c:forEach var="users" items="${sessionScope.users}">
                    <div class="row">
                        <div class="col-md-2 txt3 text-left">
                            <c:out value="${users.login}"/>
                        </div>
                        <div class="col-md-3 txt3 text-left">
                            <c:out value="${users.name}"/>
                            <c:out value="${users.surname}"/>
                        </div>
                        <div class="col-md-3 txt3 text-left">
                            <c:out value="${users.mail}"/>
                        </div>
                        <div class="col-md-2 txt3 text-left">
                            <c:out value="${users.phone}"/>
                        </div>
                        <div class="col-md-2 text-left">
                            <form method="post" action="${pageContext.request.contextPath}/controller">
                                <input type="hidden" name="command" value="ban_user">
                                <input type="hidden" name="user_id" value="${users.id}">
                                <c:if test="${users.userStatus != 'BANED'}">
                                    <input type="submit" class="order-client-btn"
                                           value="<fmt:message key="page.admin.ban"/>"/>
                                </c:if>
                                <c:if test="${users.userStatus == 'BANED'}">
                                    <input type="submit" class="order-client-btn"
                                           value="<fmt:message key="page.admin.unban"/>"/>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
    </div>

    <ftg:footer/>
</div>

<script src="${pageContext.request.contextPath}/lib/bootstrap/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.js"></script>
</body>
</html>
