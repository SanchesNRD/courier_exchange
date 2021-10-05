<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<header class="main-header">
    <a href="${pageContext.request.contextPath}/controller?command=go_to_user_profile" class="logo">
        <span class="logo-lg"><b>Courier</b>XCH</span>
    </a>

    <nav class="navbar navbar-static-top">
        <div class="navbar-custom-menu">
            <div class="nav navbar-nav">
                <div class="dropdown user user-menu">
                        <span class="sidebar-login">
                            <span>
                                <c:out value="${sessionScope.user.name}"/>
                                <c:out value="${sessionScope.user.surname}"/>
                            </span>
                            <img src="${pageContext.request.contextPath}/image_display" class="user-image" alt="User Image">
                        </span>
                </div>
            </div>
        </div>
    </nav>
</header>
