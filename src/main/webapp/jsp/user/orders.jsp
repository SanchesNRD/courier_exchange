<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
                <fmt:message key="page.orders.title"/>
            </h1>
        </section>
        <section class="content">
            <c:if test="${sessionScope.user.userStatus == 'CONFIRMED'}">
                <div class="wrap-login100">
                    <div class="row">
                        <div class="col-md-3 txt1 text-left">
                            <fmt:message key="page.orders.name_client"/>
                        </div>
                        <div class="col-md-3 txt1 text-left">
                            <fmt:message key="page.orders.name_product"/>
                        </div>
                        <div class="col-md-4 txt1 text-left">
                            <fmt:message key="page.orders.size"/>
                        </div>
                        <div class="cpl-md-2 txt1 text-left">
                            <fmt:message key="page.orders.weight"/>
                        </div>
                    </div>
                    <c:forEach var="clientProducts" items="${sessionScope.clientProducts}">
                        <div class="row">
                            <div class="col-md-3 txt1 text-left">
                                <c:out value="${clientProducts.client.login}"/>
                            </div>
                            <div class="col-md-3 txt1 text-left">
                                <c:out value="${clientProducts.product.height}"/>
                            </div>
                            <div class="col-md-4 txt1 text-left">
                                <c:out value="${clientProducts.product.length}"/>
                            </div>
                            <div class="col-md-2 txt1 text-left">
                                <c:out value="${clientProducts.address.country}"/>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            <c:if test="${sessionScope.user.userStatus == 'NON_CONFIRMED'}">
                <div class="wrap-login100">
                    Авторизуйтесь
                </div>
            </c:if>
        </section>
    </div>

    <jsp:include page="../common/footer.jsp"/>
</div>

<script src="${pageContext.request.contextPath}/lib/bootstrap/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.js"></script>
</body>
</html>
