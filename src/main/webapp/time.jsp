<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!DOCTYPE html>
    <html lang="zh-TW">

    <head>
      <title>CRM</title>
      <script src="${pageContext.request.contextPath}/js/jquery-3.4.1.js"></script>
      <style>
        body {
          background-color: #0d1326;
        }

        #timeCenter {
          color: #ffffff;
          position: relative;
          top: 350px;
          margin: auto;
          width: 40px;
        }

        form {
          position: absolute;
          left: 45%;
          top: 700px;

        }
      </style>
    </head>

    <body>

      <div id="timeCenter">Error</div>
      <script src="${pageContext.request.contextPath}/js/time.js"></script>
      <form action="${pageContext.request.contextPath}/home" method="post">
        <input type="text" placeholder="帳號" name="userName" ><br>
        <input type="password" placeholder="密碼" name="userPassword" ><br>
        <span style="color: red;">${param.mess=="1"?"帳號密碼錯誤 或 權限不夠":""}</span>
        <input type="submit" value="登入">
        
      </form>

      <br />
      <script>

      </script>
    </body>

    </html>