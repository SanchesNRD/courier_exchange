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
                <fmt:message key="page.neworder.title"/>
            </h1>
        </section>
        <section class="content">
            <c:if test="${sessionScope.user.userStatus == 'CONFIRMED'
                    && sessionScope.client.address != 0}">
                <form action="${pageContext.request.contextPath}/controller" class="wrap-login100">
                    <input type="hidden" name="command" value="create_new_order"/>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.neworder.name"/></div>
                        <div class="col-md-6">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="name"
                                       pattern="[a-zA-Zа-яА-я]{2,20}"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.neworder.width"/></div>
                        <div class="col-md-4">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="width" pattern="[+]?\d+"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.neworder.height"/></div>
                        <div class="col-md-4">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="height" pattern="[+]?\d+"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.neworder.length"/></div>
                        <div class="col-md-4">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="length" pattern="[+]?\d+"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.neworder.weight"/></div>
                        <div class="col-md-4">
                            <div class="wrap-input100">
                                <input class="validate-input input100" type="text" name="weight" pattern="[+]?\d+"/>
                                <span class="focus-input100"></span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group row">
                        <div class="col-md-2 txt1 text-right">
                            <fmt:message key="page.neworder.type"/></div>
                        <div class="col-md-6">
                            <div class="radio-content">
                                <input id="Default" class="validate-input radio100" value="1" type="radio" name="order_type"/>
                                <label for="Default" class="radio-label100">
                                    <fmt:message key="page.neworder.default"/>
                                </label>
                            </div>
                            <div class="radio-content">
                                <input id="Fragile" class="validate-input radio100" value="2" type="radio" name="order_type"/>
                                <label for="Fragile"  class="radio-label100">
                                    <fmt:message key="page.neworder.fragile"/>
                                </label>
                            </div>
                            <div class="radio-content">
                                <input id="Explosive" class="validate-input radio100" value="3" type="radio" name="order_type"/>
                                <label for="Explosive"  class="radio-label100">
                                    <fmt:message key="page.neworder.explosive"/>
                                </label>
                            </div>
                            <div class="radio-content">
                                <input id="Poisonous" class="validate-input radio100" value="4" type="radio" name="order_type"/>
                                <label for="Poisonous"  class="radio-label100">
                                    <fmt:message key="page.neworder.poisonous"/>
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-offset-2 container-login100-form-btn ">
                        <input type="submit" value="<fmt:message key="page.neworder.create"/>" class="login100-form-btn"/>
                    </div>
                </form>
            </c:if>
            <c:if test="${sessionScope.client.address == 0}">
                <div class="wrap-login100">
                    Введите данные своего адреса
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
