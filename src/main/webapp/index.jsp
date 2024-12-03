<!DOCTYPE html>
<html>
<head>
    <title>BMI Calculator</title>
</head>
<body>
    <h1>BMI and Body Fat Calculator</h1>
    <form action="calculate" method="post">
        <label for="weight">Weight (kg):</label>
        <input type="number" id="weight" name="weight" step="0.1" required><br><br>

        <label for="height">Height (cm):</label>
        <input type="number" id="height" name="height" step="0.01" required><br><br>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age" required><br><br>

        <label for="gender">Gender:</label>
        <select id="gender" name="gender" required>
            <option value="male">Male</option>
            <option value="female">Female</option>
        </select><br><br>

        <button type="submit">Calculate</button>


    </form>

    <br>
        <a href="login.jsp"><button type="button">Login</button></a>
        <a href="signup.jsp"><button type="button">Signup</button></a>

    <br>
    <br>

    <form action="search" method="get">
      <input type="text" name="query" placeholder="Enter username to see progress" required>
       <button type="submit">Search</button>
    </form>
</body>
</html>