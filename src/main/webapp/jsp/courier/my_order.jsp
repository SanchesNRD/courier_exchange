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

    <jsp:include page="../common/courier_sidebar.jsp"/>

    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                <fmt:message key="page.my_order.title"/>
            </h1>
        </section>
        <section class="content">
            <c:if test="${sessionScope.user.userStatus == 'COURIER_CONFIRMED'}">
                <c:if test="${requestScope.courier_have_order}">
                    <fmt:message key="page.my_order.have_order"/>
                </c:if>
                <c:if test = "${!requestScope.no_order}">
                    <form action="${pageContext.request.contextPath}/controller">
                        <input type="hidden" name="command" value="complete_order"/>
                        <div class="wrap-login100">
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.user_orders.name_product"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.product.name}" disabled>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.neworder.weight"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.product.weight}" disabled>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.neworder.height"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.product.height}" disabled>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.neworder.length"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.product.length}" disabled>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.neworder.width"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.product.width}" disabled>
                                </div>
                            </div>
                        </div>
                        <div class="wrap-login100">
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.address.country"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.address.country}" disabled>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.address.city"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.address.city}" disabled>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.address.street"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.address.street}" disabled>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.address.street_number"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.address.street_number}" disabled>
                                </div>
                            </div>
                            <div class="form-group row">
                                <div class="col-md-2 txt1 text-right">
                                    <fmt:message key="page.address.apartment"/>
                                </div>
                                <div class="col-md-6 wrap-input100">
                                    <input class="validate-input input100"
                                           value="${sessionScope.order.clientProduct.address.apartment}" disabled>
                                </div>
                            </div>
                            <div class="col-md-offset-2 container-login100-form-btn ">
                                <input type="submit" value="<fmt:message key="page.my_order.completed"/>" class="login100-form-btn"/>
                            </div>
                        </div>
                    </form>
                </c:if>
                <c:if test = "${requestScope.no_order}">
                    <div class="wrap-login100">
                        <fmt:message key="page.my_order.no_order"/>
                    </div>
                </c:if>
            </c:if>
            <c:if test="${sessionScope.user.userStatus != 'COURIER_CONFIRMED'}">
                <div class="wrap-login100">
                    <fmt:message key="page.profile.auth"/>
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
