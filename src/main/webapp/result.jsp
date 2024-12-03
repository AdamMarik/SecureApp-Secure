<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Results</title>
</head>
<body>
    <h1>Results</h1>
    <p>Your BMI is: <span id="bmi">${bmi}</span></p>
    <p>Your Body Fat Percentage is: <span id="bodyFatPercentage">${bodyFatPercentage}%</span></p>

    <form action="addResult" method="post">
        <input type="hidden" name="weight" value="${weight}">
        <input type="hidden" name="height" value="${height}">
        <input type="hidden" name="age" value="${age}">
        <input type="hidden" name="gender" value="${gender}">
        <input type="hidden" name="bmi" value="${bmi}">
        <input type="hidden" name="bodyFatPercentage" value="${bodyFatPercentage}">
        <button type="submit">Save Results</button>
    </form>

    <p>Debug - Weight: ${weight}</p>
    <p>Debug - Height: ${height}</p>
    <p>Debug - Age: ${age}</p>
    <p>Debug - Gender: ${gender}</p>
    <p>Debug - bmi: ${bmi}</p>
    <p>Debug - bodyFatPercentage: ${bodyFatPercentage}</p>

    <a href="index.jsp">Go Back</a>
</body>
</html>