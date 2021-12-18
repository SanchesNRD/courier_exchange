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

    <jsp:include page="../common/user_sidebar.jsp"/>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                <fmt:message key="page.address.title"/>
            </h1>
        </section>
        <section class="content">
            <c:if test="${sessionScope.user.userStatus == 'CONFIRMED'}">
                <form method="post" action="${pageContext.request.contextPath}/controller" class="wrap-login100">
                    <input type="hidden" name="command" value="update_client_address"/>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.address.country"/></div>
                        <div class="col-md-6">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="country"
                                       value="${sessionScope.address.country}" pattern="[a-zA-Zа-яА-я]{2,20}"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.address.city"/></div>
                        <div class="col-md-6">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="city"
                                       value="${sessionScope.address.city}" pattern="[a-zA-Zа-яА-я]{2,20}"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.address.street"/></div>
                        <div class="col-md-6">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="street"
                                       value="${sessionScope.address.street}" pattern="[a-zA-Zа-яА-я]{2,20}"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.address.street_number"/></div>
                        <div class="col-md-4">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="street_number"
                                       value="${sessionScope.address.street_number}" pattern="\d+"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.address.apartment"/></div>
                        <div class="col-md-4">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="apartment"
                                       value="${sessionScope.address.apartment}" pattern="\d+"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-offset-2 container-login100-form-btn ">
                        <input type="submit" value="<fmt:message key="page.profile.update"/>" class="login100-form-btn"/>
                    </div>
                </form>
            </c:if>
            <c:if test="${sessionScope.user.userStatus == 'NON_CONFIRMED'}">
                <div class="wrap-login100">
                    <fmt:message key="page.profile.auth"/>
                </div>
            </c:if>
        </section>
    </div>

    <ftg:footer/>
</div>

<script src="${pageContext.request.contextPath}/lib/bootstrap/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.js"></script>
</body>
</html>
