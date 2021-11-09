<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
<H1>Vartotoju sarasas:</H1>

<!--
<p>${users}</p>
-->

<table border="1">
<thead>
<tr>
    <th>UserId</th>
    <th>Vardas</th>
    <th>Pavarde</th>
    <th>Telefono numeris</th>
    <th>Email</th>
    <th>Adresas</th>
    <th>Slaptazodis</th>
</tr>
</thead>
<tbody>
<c:forEach items="${users}" var="user">
<tr>
    <td>${user.userId}</td>
    <td>${user.vardas}</td>
    <td>${user.pavarde}</td>
    <td>${user.telefonoNumeris}</td>
    <td>${user.email}</td>
    <td>${user.adresas}</td>
    <td>${user.slaptazodis}</td>

    <td><a type="button" href="/update-user/${user.userId}">ATNAUJINTI</a></td>
    <td><a type="button" href="/delete-user/${user.userId}">ISTRINTI</a></td>
</tr>
</c:forEach>

</tbody>
</table>

<div>
<a href="add-user">Prideti vartotoja</a>
</div>
</div>
<%@ include file="common/footer.jspf"%>