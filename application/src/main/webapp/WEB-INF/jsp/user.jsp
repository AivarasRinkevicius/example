<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<p>Prideti vartotoja:</p>
<form:form method="post" modelAttribute="user">

	<form:input path="userId" type="hidden" required="required" />
	<form:errors path="userId" />

	<form:label path="vardas">Vardas</form:label>
	<form:input path="vardas" type="text" required="required" />
	<form:errors path="vardas" />

	<form:label path="pavarde">Pavarde</form:label>
	<form:input path="pavarde" type="text" required="required" />
	<form:errors path="pavarde" />

	<form:label path="telefonoNumeris">Telefono numeris</form:label>
	<form:input path="telefonoNumeris" type="text" required="required" />
	<form:errors path="telefonoNumeris" />

	<form:label path="email">Email</form:label>
	<form:input path="email" type="text" required="required" />
	<form:errors path="email" />

	<form:label path="adresas">Adresas</form:label>
	<form:input path="adresas" type="text" required="required" />
	<form:errors path="adresas" />

	<form:label path="slaptazodis">Slaptazodis</form:label>
	<form:input path="slaptazodis" type="text" required="required" />
	<form:errors path="slaptazodis" />

	<button type="submit">PATEIKTI</button>
</form:form>
</div>
<%@ include file="common/footer.jspf"%>