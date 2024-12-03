<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
        <%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Signup</title>
</head>
<body>
    <h1>Create an Account</h1>
        <form action="${pageContext.request.contextPath}/signup" method="post">
            <!-- CSRF Token -->
                    <input type="hidden" name="csrfToken" value="${csrfToken}">

                    <!-- Username -->
                    <label for="username">Username:</label>
                    <input type="text" id="username" name="username" required>

                    <!-- Password -->
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>

                    <!-- Confirm Password -->
                    <label for="confirmPassword">Confirm Password:</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" required>

                    <!-- Submit Button -->
                    <button type="submit">Sign Up</button>
        </form>
    <br>
    </br>
    <a href="login.jsp">Already have an account? Login here</a>
</body>
</html>
