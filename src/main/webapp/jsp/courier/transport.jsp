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

    <jsp:include page="../common/courier_sidebar.jsp"/>


    <div class="content-wrapper">
        <section class="content-header">
            <h1>
                <fmt:message key="page.transport.title"/>
            </h1>
        </section>
        <section class="content">
            <c:if test="${sessionScope.user.userStatus == 'COURIER_CONFIRMED'}">
                <form action="${pageContext.request.contextPath}/controller" class="wrap-login100">
                    <input type="hidden" name="command" value="update_courier_transport"/>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.transport.name"/></div>
                        <div class="col-md-6">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="name"
                                       value="${sessionScope.transport.name}" pattern="[a-zA-Zа-яА-я]{2,20}"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.transport.speed"/></div>
                        <div class="col-md-6">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="speed"
                                       value="${sessionScope.transport.averageSpeed}" pattern="\d+"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.transport.weight"/></div>
                        <div class="col-md-6">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="weight"
                                       value="${sessionScope.transport.maxProductWeight}" pattern="\d+"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.neworder.type"/></div>
                        <div class="col-md-6">
                            <div class="radio-content">
                                <input id="Bike" class="validate-input radio100" value="1" type="radio"
                                        <c:if test="${sessionScope.transport.transportType.id==1}">
                                            checked="checked"
                                        </c:if>
                                       name="transport_type"/>
                                <label for="Bike" class="radio-label100">
                                    <fmt:message key="page.transport.bike"/>
                                </label>
                            </div>
                            <div class="radio-content">
                                <input id="Car" class="validate-input radio100" value="2" type="radio"
                                        <c:if test="${sessionScope.transport.transportType.id==2}">
                                            checked="checked"
                                        </c:if>
                                       name="transport_type"/>
                                <label for="Car"  class="radio-label100">
                                    <fmt:message key="page.transport.car"/>
                                </label>
                            </div>
                            <div class="radio-content">
                                <input id="Truck" class="validate-input radio100" value="3" type="radio"
                                        <c:if test="${sessionScope.transport.transportType.id==3}">
                                            checked="checked"
                                        </c:if>
                                       name="transport_type"/>
                                <label for="Truck"  class="radio-label100">
                                    <fmt:message key="page.transport.truck"/>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-offset-2 container-login100-form-btn ">
                        <input type="submit" value="<fmt:message key="page.profile.update"/>" class="login100-form-btn"/>
                    </div>
                </form>
            </c:if>
            <c:if test="${sessionScope.user.userStatus != 'COURIER_CONFIRMED'}">
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
