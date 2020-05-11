<%@ page import="webapp.model.ContactsType" %>
<%@ page import="webapp.model.ListSection" %>
<%@ page import="webapp.model.OrganizationListSection" %>
<%@ page import="webapp.model.SectionType" %>
<%@ page import="webapp.util.DateUtil" %>
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
        <h1>Контакты:</h1>
        <c:forEach var="type" items="<%=ContactsType.values()%>">
            <dl>
                <dt>${type.contact}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value="${resume.getContacts(type)}"></dd>
            </dl>
        </c:forEach>
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <c:set var="section" value="${resume.getSection(type)}"/>
            <jsp:useBean id="section" type="webapp.model.Section"/>
            <h2><a>${type.title}</a></h2>
            <c:choose>
                <c:when test="${type=='OBJECTIVE'}">
                    <input type='text' name='${type}' size=75 value='<%=section%>'>
                </c:when>
                <c:when test="${type=='PERSONAL'}">
                    <textarea name='${type}' cols=75 rows=3><%=section%></textarea>
                </c:when>
                <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATION'}">
                <textarea name='${type}' cols=75 rows=5><%=String.join("\n",
                        ((ListSection) section).getStringList())%></textarea>
                </c:when>
                <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
                    <c:forEach var="organization"
                               items="<%=((OrganizationListSection) section).getOrganizationList()%>" varStatus="counter">
                        <dl>
                            <dt>Сайт организации:</dt>
                            <dd><input type="text" name='${type}url' value='${organization.url}'></dd>
                        </dl>
                        <dl>
                            <dt>Название организации:</dt>
                            <dd><input type="text" name='${type}' value='${organization.name}'></dd>
                        </dl>
                        <br>
                        <c:forEach var="pos" items="${organization.positions}">
                            <jsp:useBean id="pos" type="webapp.model.Organization.Position"/>
                            <dl>
                                <dt>Начальная дата:</dt>
                                <dd>
                                    <input type="text" name="${type}${counter.index}startDate" size=10
                                           value="<%=DateUtil.format(pos.getStartDate())%>">
                                </dd>
                            </dl>
                            <dl>
                                <dt>Конечная дата:</dt>
                                <dd>
                                    <input type="text" name="${type}${counter.index}endDate" size=10
                                           value="<%=DateUtil.format(pos.getEndDate())%>">
                            </dl>
                            <dl>
                                <dt>Должность:</dt>
                                <dd><input type="text" name='${type}${counter.index}title' size=75
                                           value="${pos.position}">
                            </dl>
                            <dl>
                                <dt>Описание:</dt>
                                <dd><textarea name="${type}${counter.index}description" rows=5
                                              cols=75>${pos.description}</textarea></dd>
                            </dl>
                        </c:forEach>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
