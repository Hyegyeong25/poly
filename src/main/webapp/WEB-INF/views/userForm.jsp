<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>웹페이지 제목</title>
</head>
<body>
    <h1>회원가입</h1>
    <form action="userInsert">
        <div>
            아이디 : <input name="user_id" />
        </div>
        <div>
            비밀번호 : <input name="user_pwd" />
        </div>
        <div>
            이메일 : <input name="user_email" />
        </div>
        <div>
            이름 : <input name="user_name" />
        </div>
        <button type="submit">가입하기</button>
    </form>
</body>
</html>