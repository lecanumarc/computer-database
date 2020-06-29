
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
  <head>
    <title>Exemple</title>
  </head>

  <body>
  TEST
  <c:forEach items="${computerList}" var="item">
    ${item.name}<br>
      ${item.id}<br>
</c:forEach>
END
  </body>
</html>