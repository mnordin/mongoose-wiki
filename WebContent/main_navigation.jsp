<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="se.kyh.wiki.MainNavigation" %>
<% MainNavigation main = (MainNavigation)request.getAttribute("mainNavigation"); %>
<ul id="main_navigation">
	<% for( String item : main.getItems() ) { %>
		<li>
			<%= item %>
		</li>
	<% } %>
</ul>