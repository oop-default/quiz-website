<%--
  Created by IntelliJ IDEA.
  User: New User
  Date: 7/7/2019
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Image</title>
<body>
    <%String imageSource = "images/"+session.getAttribute("image");
        System.out.println(imageSource);%>
    <img src=<%=imageSource%> width="500" height="500">
</body>
</html>
