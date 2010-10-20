<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="se.kyh.wiki.Article" %>
<% String message = (String)request.getAttribute("message"); %>
<% Article article = (Article)request.getAttribute("article"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
		<title>Insert title here</title>
	</head>
	<body>
		<div id="header">
			<h1>Mongoose wiki</h1>
		</div>
		<div id="content">
		
			<h2>Ändra artikeln</h2>
			
			<p><%= message %></p>
			
			<form action="" method="post">
				<fieldset>
					<legend><%= article.getTitle() %></legend>
					<label for="body">Text</label>
					<textarea name="body" id="body" rows="30" cols="100"><%= article.getNakedBody() %></textarea>
					<input type="submit" name="update_article" value="Spara" />
				</fieldset>
			</form>
		</div>
	</body>
</html>