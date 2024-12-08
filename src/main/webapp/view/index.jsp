<%@ page import="java.util.Map" %>
<%@ page import="org.example.model.Account" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
<%
    Map<String, Account> accounts = (Map<String, Account>) request.getAttribute("accounts");
%>
<h2>Все пользователи</h2>

<table>
    <tr>
        <th>Account name</th>
        <th>Balance</th>
    </tr>

    <c:forEach var="account" items="${accounts}">
        <tr>
            <td>${account.value.name}</td>
            <td>${account.value.balance}</td>
        </tr>
    </c:forEach>
    <%--<%
        for (Map.Entry<String, Account> account : accounts.entrySet()) {
    %>
    <tr>
        <th><%=account.getValue().getName()%>
        </th>
        <th><%=account.getValue().getBalance()%>
        </th>
    </tr>
    <%
        }
    %>--%>

</table>
<h2>Создание нового пользователя</h2>
<hr/>
<form method="post">
    <input type="text" name="name" placeholder="Account name" minlength="6" maxlength="20"><br/>
    <input type="password" name="password" placeholder="Password" minlength="5"><br/>
    <input type="number" name="balance" placeholder="Balance" min="0" step="0.01"><br/>
    <input type="submit" value="create"><br/>
</form>
<c:if test="${requestScope.accountExistError}" var="true">
    <div style="color:red">Такой пользователь уже существует</div>
</c:if>
<c:if test="${requestScope.emptyField}" var="true">
    <div style="color:red">Поле должно быть заполнено</div>
</c:if>
</body>
</html>