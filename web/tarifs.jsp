<%@ page import="model.TarifEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.aryukov.domain.Tarif" %>
<%--
  Created by IntelliJ IDEA.
  User: oleg
  Date: 03.12.2015
  Time: 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<%
  List<Tarif> lt = session.getAttribute("tarifs")
%>
<form action="/getAllTarifs" method="GET">
        <div align="center">
          <table width="900" border="2" cellspacing="0" cellpadding="0" style="background:#CCC">
            <tr>
              <td align="center">Доступные тарифы</td>
            </tr>
            <tr>
              <td align="center">
                <table width="750" border="2" cellspacing="0" cellpadding="0">
                <tr>
                    <td width="50"><div align="center" style="background-color:#999"></div></td>
                    <td width="350"><div align="center" style="background-color:#999">Название</div></td>
                    <td width="150"><div align="center" style="background-color:#999">Цена</div></td>
                  </tr>
                  <%for (Tarif t:lt){%>
                  <tr>
                    <td width="50"><div align="center" style="background-color:#09F"><input type="radio" name="tarifId" value="<%=t.getId()%>"></div></td>
                    <td width="350"><div align="center" style="background-color:#999"><%=t.getName()%></div></td>
                    <td width="150"><div align="center" style="background-color:#999"><%=t.getPrice()%></div></td>
                  </tr>
                  <%}%>
              </table></td>
            </tr>
          </table>
          <p>&nbsp;</p>
          <table width="600" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td><div align="center">
              <input type="submit" value="Изменитб тариф" name="Change"/>
            </div></td>
            <td><div align="center">
              <input type="submit" value="Удалить тариф" name="Delete"/>
            </div></td>
            <td><div align="center">
              <input type="submit" value="Добавить тариф" name="Add"/>
            </div></td>
            </tr>
          </table>
        </div>
        </form>

</body>
</html>
