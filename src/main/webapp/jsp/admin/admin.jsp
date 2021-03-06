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
                <fmt:message key="page.admin.heading_profile"/>
            </h1>
        </section>
        <section class="content">
            <form method="post" action="${pageContext.request.contextPath}/controller" class="wrap-login100">
                <input type="hidden" name="command" value="update_profile"/>
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.login"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="login" disabled="disabled"
                                   value="${sessionScope.user.login}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.mail"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="mail" disabled="disabled"
                                   value="${sessionScope.user.mail}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="txt1 col-md-2 text-right">
                        <fmt:message key="page.profile.name"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="name"
                                   value="${sessionScope.user.name}" pattern="[a-zA-Z??-????-??]{2,20}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.surname"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="surname"
                                   value="${sessionScope.user.surname}" pattern="[a-zA-Z??-????-??]{2,20}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.phone"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="phone"
                                   value="${sessionScope.user.phone}" pattern="(\+375|80)(29|25|44|33)[\d]{7}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <c:if test="${requestScope.wrong_validation == true}">
                    <div class="error-content">
                        <fmt:message key="page.signup.validation"/>
                    </div>
                </c:if>
                <div class="col-md-offset-2 container-login100-form-btn ">
                    <input type="submit" value="<fmt:message key="page.profile.update"/>" class="login100-form-btn"/>
                </div>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/controller" class="wrap-login100">
                <input type="hidden" name="command" value="change_password"/>
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.password"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="password" name="password"
                                   pattern="(?=.*[\d])(?=.*[a-z])[\w]{8,40}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.new_password"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="password" name="new_password"
                                   pattern="(?=.*[\d])(?=.*[a-z])[\w]{8,40}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <c:if test="${requestScope.password_change_suc == true}">
                    <div class="success-content">
                        <fmt:message key="page.profile.suc_change_pass"/>
                    </div>
                </c:if>
                <div class="col-md-offset-2 container-login100-form-btn ">
                    <input type="submit" value="<fmt:message key="page.profile.change"/>" class="login100-form-btn"/>
                </div>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/image_display"
                  enctype="multipart/form-data" class="wrap-login100">
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.choose_file"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input type="file" name="multiPartServlet" style="margin-left: 10px;margin-top: 20px;margin-bottom: 40px;">
                            <input class="login100-form-btn" type="submit" value="<fmt:message key="page.profile.upload"/>"/>
                        </div>
                    </div>
                </div>
            </form>
        </section>
    </div>

    <ftg:footer/>
</div>

<script src="${pageContext.request.contextPath}/lib/bootstrap/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.js"></script>
</body>
</html>

