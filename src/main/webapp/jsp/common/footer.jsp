<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.locale}" scope="session"/>
<fmt:setBundle basename="prop.pagecontent"/>
<html>
    <footer class="main-footer">
    <div class="pull-right hidden-xs">
        <b><fmt:message key="footer.version"/></b>2.4.0
    </div>
    <strong>Copyright &copy; 2014-2016 <a class="almassed-studio" href="https://adminlte.io"><fmt:message key="footer.heading"/></a>.</strong>
        <fmt:message key="footer.rights"/>
</footer>
</html>
