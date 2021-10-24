<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<aside class="main-sidebar">
    <section class="sidebar">
        <ul class="sidebar-menu" data-widget="tree">
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_admin_page">
                <fmt:message key="sidebar.user.profile"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_admin_all_users">
                <fmt:message key="sidebar.admin.users"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_admin_client_templates">
                <fmt:message key="sidebar.admin.templates"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_admin_transports">
                <fmt:message key="sidebar.admin.transports"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_admin_products">
                <fmt:message key="sidebar.admin.products"/></a></li>

            <li style="margin-top: 60px">
                <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=${sessionScope.locale}&current_url=${pageContext.request.requestURL}">
                    <fmt:message key="sidebar.user.language"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=logout">
                <fmt:message key="sidebar.user.logout"/></a></li>
        </ul>
    </section>
</aside>
</html>
