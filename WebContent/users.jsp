<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String message = (String)request.getAttribute("message"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div id="header">
		<h1>Mongoose wiki</h1>
	</div>
	<div id="content">
	
		<h2>Registrera</h2>
		
		<p><%= message %></p>
		
		<form action="" method="post">
			<fieldset>
				<legend>Fyll i användare</legend>
				<label for="first_name">Förnamn</label>
				<input name="first_name" id="first_name" placeholder="Sök" />
				<label for="last_name">Efternamn</label>
				<input name="last_name" id="last_name" placeholder="Sök" />
				<label for="email">Email</label>
				<input name="email" id="email" placeholder="Sök" />
				<label for="password">Lösenord</label>
				<input name="password" id="password" placeholder="Sök" />
				<input type="submit" name="register" value="Registrera ny användare" />
			</fieldset>
		</form>
		
		<h2>Befintlig användare? Logga in här</h2>
		
		<form action="" method="post">
			<fieldset>
				<legend>Logga in</legend>
				<label for="email">Email</label>
				<input type="text" name="email" id="email" placeholder="E-mailadress" />
				<label for="password">Lösenord</label>
				<input type="password" name="password" id="password" placeholder="Lösenord" />
				<input type="submit" name="login" value="Logga in" />
			</fieldset>
		</form>
		
	</div>

</body>
</html>