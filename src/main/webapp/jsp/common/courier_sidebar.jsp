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
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_courier_transport">
                <fmt:message key="sidebar.courier.transport"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_courier_all_orders">
                <fmt:message key="sidebar.user.orders"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_courier_order">
                <fmt:message key="sidebar.courier.my_order"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=go_to_courier_history">
                <fmt:message key="sidebar.user.history"/></a></li>

            <li style="margin-top: 40px"><a href="${pageContext.request.contextPath}/controller?command=change_locale?">
                <fmt:message key="sidebar.user.language"/></a></li>
            <li><a href="${pageContext.request.contextPath}/controller?command=logout">
                <fmt:message key="sidebar.user.logout"/></a></li>
        </ul>
    </section>
</aside>
</html>