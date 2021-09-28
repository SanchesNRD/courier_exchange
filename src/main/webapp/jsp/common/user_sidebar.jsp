<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
<aside class="main-sidebar">
    <section class="sidebar">
        <ul class="sidebar-menu" data-widget="tree">
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_user_profile">
                <fmt:message key="sidebar.user.profile"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_orders_page">
                <fmt:message key="sidebar.user.orders"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_new_order_page">
                <fmt:message key="sidebar.user.neworder"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_my_orders_page">
                <fmt:message key="sidebar.user.myorders"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_history_page">
                <fmt:message key="sidebar.user.history"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_my_couriers_page">
                <fmt:message key="sidebar.user.mycourier"/></a></li>
            <li style="margin-top: 60px">
                <a href="${pageContext.request.contextPath}/controller?command=change_locale&locale=${sessionScope.locale}&current_url=${pageContext.request.requestURL}">
                <fmt:message key="sidebar.user.language"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=logout">
                <fmt:message key="sidebar.user.logout"/></a></li>
        </ul>
    </section>
</aside>
</html>
