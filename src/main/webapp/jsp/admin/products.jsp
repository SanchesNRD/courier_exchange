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

    <jsp:include page="../common/admin_sidebar.jsp"/>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                <fmt:message key="page.templates.heading"/>
            </h1>
        </section>
        <section class="content">
            <c:if test="${requestScope.product_was_used}">
                <div class="wrap-login100">
                    products was used
                </div>
            </c:if>
            <c:if test="${requestScope.wrong_validation}">
                <div class="wrap-login100">
                    wrong validation
                </div>
            </c:if>
            <div class="wrap-login100">
                <div class="row">
                    <div class="col-md-1 txt1 text-font text-left">
                        <fmt:message key="page.transport.id"/>
                    </div>
                    <div class="col-md-2 txt1 text-font text-left">
                        <fmt:message key="page.profile.name"/>
                    </div>
                    <div class="col-md-2 txt1 text-left">
                        <div class="text-font">
                            <fmt:message key="page.orders.size"/>
                        </div>
                        <div class="txt2"  style="text-transform: none">
                            <fmt:message key="page.orders.lwh"/>
                        </div>
                    </div>
                    <div class="col-md-2 txt1 text-font text-left">
                        <fmt:message key="page.orders.weight"/>
                    </div>
                    <div class="col-md-2 txt1 text-font text-left">
                        <fmt:message key="page.neworder.type"/>
                    </div>
                    <div class="col-md-2 txt1 text-font text-right">
                        <fmt:message key="page.orders.action"/>
                    </div>
                </div>
                <c:forEach var="products" items="${sessionScope.products}">
                    <div class="row">
                        <div class="col-md-1 txt3 text-left">
                            <c:out value="${products.id}"/>
                        </div>
                        <div class="col-md-2 txt3 text-left">
                            <c:out value="${products.name}"/>
                        </div>
                        <div class="col-md-2 txt3 text-left">
                            <c:out value="${products.length}"/>
                            -
                            <c:out value="${products.width}"/>
                            -
                            <c:out value="${products.height}"/>
                        </div>
                        <div class="col-md-2 txt3 text-left">
                            <c:out value="${products.weight}"/>
                        </div>
                        <div class="col-md-2 txt3 text-left">
                            <c:out value="${products.productType.toString()}"/>
                        </div>
                        <div class="col-md-2 text-left">
                            <a class="order-client-btn"
                               href="${pageContext.request.contextPath}/controller?command=delete_product&id=${products.id}">
                                <fmt:message key="page.orders.delete"/>
                            </a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </section>
    </div>

    <jsp:include page="../common/footer.jsp"/>
</div>

<script src="${pageContext.request.contextPath}/lib/bootstrap/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.js"></script>
</body>
</html>
