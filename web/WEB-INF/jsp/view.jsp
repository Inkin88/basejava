<%@ page import="webapp.model.ListSection" %>
<%@ page import="webapp.model.OrganizationListSection" %>
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
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<webapp.model.ContactsType, java.lang.String>"/>
            <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    </p>
    <p>
        <c:forEach var="sectionEntry" items="${resume.sections}">
            <jsp:useBean id="sectionEntry" type="java.util.Map.Entry<webapp.model.SectionType, webapp.model.Section>"/>
            <c:set var="type" value="${sectionEntry.key}"/>
            <c:set var="section" value="${sectionEntry.value}"/>
            <jsp:useBean id="section" type="webapp.model.Section"/>
    <h3>${type.title}:</h3>
    <c:choose>
        <c:when test="${type=='OBJECTIVE' || type=='PERSONAL'}">
            <span>${section}</span>
        </c:when>
        <c:when test="${type=='ACHIEVEMENT' || type=='QUALIFICATION'}">
            <c:forEach var="str" items="<%=((ListSection) section).getStringList()%>">
                <p>
                        ${str}
                </p>
            </c:forEach>
        </c:when>
        <c:when test="${type=='EXPERIENCE' || type=='EDUCATION'}">
            <table>
                <c:forEach var="organization"
                           items="<%=((OrganizationListSection) section).getOrganizationList()%>">
                    <tr>
                        <td colspan="2"><a href=${organization.url}>${organization.name}</a></td>

                        <c:forEach var="pos" items="${organization.positions}">
                            <jsp:useBean id="pos" type="webapp.model.Organization.Position"/>
                            <tr>
                            <td width="100">
                                <%=DateUtil.format(pos.getStartDate())%> - <%=DateUtil.format(pos.getEndDate())%><br>
                            </td>
                            <td>
                                <b>${pos.position}:</b><br>
                                    ${pos.description}
                            </td>
                            </tr>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
    </c:choose>
    </c:forEach>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
