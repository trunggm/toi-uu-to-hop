<!DOCTYPE html>
<html>
<head>
<title>Test jsp</title>
</head>

<body>
	<h1>Toi uu to hop - <% out.print("GRaps");  %></h1>
	<h2>Ip: <% out.print(request.getRemoteAddr()); %></h2>	
	<%! int i = 1; %>
	<%! int a = 2; %>
	<p><%
			String test = (String) request.getAttribute("testing"); 
			out.print(test);
			%></p>
	<% if (a == 1 | a == 7) { %>
	     <p> Today is weekend</p>
	<% } else { %>
	     <p> Today is not weekend</p>
	<% } %>
</body>
</html>