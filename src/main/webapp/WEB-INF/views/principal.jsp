<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<html>
<head>
<title>Tablas</title>



<link rel="stylesheet" href="/tablas/css/bootstrap.min.css">
<link rel="stylesheet" href="/tablas/css/principal.css">
<script type="text/JavaScript" src="/tablas/js/jquery.min.js"></script>
<script type="text/JavaScript" src="/tablas/js/principal.js"></script>


</head>



<body>
	<div class="container">

		<br>
		<h2 class="display-5 text-muted">Consulta</h2>



		<hr width="100%" />
		<br>

		<div>

			<b class="text-white">Nombre de la consulta: </b>&nbsp;&nbsp; <input
				class="border border-primary rounded-pill" type="text" id="consulta">
				
				<blockquote id="alertaconsulta" class="blockquote" style="display: none">
                    <p>Este campo no puede estar vacío</p>
                </blockquote>

		</div>


		<div>

			<br> <b class="text-white">Seleccione base de datos: </b> <select
				id="db" class="border border-primary rounded-pill" name="db">&nbsp;&nbsp;&nbsp;&nbsp;
				<c:forEach items="${dbs}" var="dbs">
					<option value="${dbs}">${dbs}</option>
				</c:forEach>
			</select>
		</div>

		<br>

		<div id="tablas" class="text-white" style="display: none">
			<b>Seleccione tabla: </b> <select id="t"
				class="border border-primary rounded-pill" name="tablas">&nbsp;&nbsp;&nbsp;&nbsp;

			</select>
		</div>

		<br> <br>


		<div class="container-lg" style="display: none">
			<div class="table-responsive">
				<div class="table-wrapper">
				
					<table class="table table-striped table-hover">
						<thead>
							<tr class="table-primary">
								<th>Campo</th>
								<th>Titulo</th>
								<th>AnchoColumna</th>
								<th>Tipo</th>
								<th>Visible</th>
								<th>Filtrar</th>
							</tr>
						</thead>
						<tbody id="tabla" style="background-color: rgba(0, 0, 0, 0.05);">
					
						</tbody>
					</table>
				</div>
				<blockquote id="alertatabla" class="blockquote" style="display: none">
                    <p>Los campos de anchoColumna no pueden estar vacios</p>
                </blockquote>
				<button id="post" type="button" class="btn btn-primary">Cargar</button>
			</div>
		</div>

	</div>
	<br>
	<footer class="bg-body-tertiary text-center text-lg-start">

		<div class="text-center p-3"
			style="background-color: rgba(0, 0, 0, 0.05);"></div>

	</footer>
</body>
</html>