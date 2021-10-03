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
                <fmt:message key="page.profile.title"/>
            </h1>
        </section>
        <section class="content">
            <form action="" class="wrap-login100">
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.login"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="login" disabled="disabled" value="${sessionScope.user.login}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.mail"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="mail" disabled="disabled" value="${sessionScope.user.mail}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="txt1 col-md-2 text-right">
                        <fmt:message key="page.profile.name"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="name" value="${sessionScope.user.name}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.surname"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="surname" value="${sessionScope.user.surname}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-md-2 txt1 text-right">
                        <fmt:message key="page.profile.phone"/></div>
                    <div class="col-md-6">
                        <div class="wrap-input100">
                            <input class="validate-input input100" type="text" name="phone" value="${sessionScope.user.phone}">
                            <span class="focus-input100"></span>
                        </div>
                    </div>
                </div>


                <div class="col-md-offset-2 container-login100-form-btn ">
                    <input type="submit" value="Update" class="login100-form-btn" onclick="location.href='${pageContext.request.contextPath}/jsp/guest/main.jsp'"/>
                </div>
            </form>
            <form method="post" action="${pageContext.request.contextPath}/image_display" enctype="multipart/form-data" class="wrap-login100">
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

    <jsp:include page="../common/footer.jsp"/>
</div>

<script src="${pageContext.request.contextPath}/lib/bootstrap/dist/js/bootstrap.js"></script>
<script src="${pageContext.request.contextPath}/js/adminlte.js"></script>
</body>
</html>

