<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ru_RU" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<aside class="main-sidebar">
    <section class="sidebar">
        <ul class="sidebar-menu" data-widget="tree">
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_user_profile">
                <fmt:message key="sidebar.user.profile"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_history_page">
                <fmt:message key="sidebar.user.history"/></a></li>

            <li style="margin-top: 40px"><a href="${pageContext.request.contextPath}/controller?command=change_locale?">
                <fmt:message key="sidebar.user.language"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=logout">
                <fmt:message key="sidebar.user.logout"/></a></li>
        </ul>
    </section>
</aside>
</html>