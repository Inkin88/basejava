<%@ page import="webapp.model.ContactsType" %>
<%@ page import="webapp.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=50 value="${resume.fullName}"></dd>
        </dl>
        <h3>Контакты:</h3>
        <p>
            <c:forEach var="type" items="<%=ContactsType.values()%>">
        <dl>
            <dt>${type.contact}</dt>
            <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContacts(type)}"></dd>
        </dl>
        </c:forEach>
        </p>
        <h3>Секции:</h3>
        <p>
            <c:forEach var="sectionType" items="<%=SectionType.values()%>">
        <dl>
            <dt>${sectionType.title}</dt>
            <dd><input type="text" name="${sectionType.name()}" size=30 value="${resume.getSection(sectionType)}"></dd>
        </dl>
        </c:forEach>
        </p>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
