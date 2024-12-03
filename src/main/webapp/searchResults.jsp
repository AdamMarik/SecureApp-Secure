<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Results</title>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
    <h1>BMI and Body Fat Results for User</h1>

    <canvas id="resultsGraph" width="400" height="200"></canvas>

    <script>
    window.onload = function() {
        const ctx = document.getElementById('resultsGraph').getContext('2d');
        const labels = [];
        const bmiData = [];
        const bodyFatData = [];

        <c:forEach var="input" items="${results}">
            labels.push('${input.date}');
            bmiData.push(${input.bmi});
            bodyFatData.push(${input.bodyFatPercentage});
        </c:forEach>;

        const myChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [
                    {
                        label: 'BMI',
                        data: bmiData,
                        backgroundColor: 'rgba(75, 192, 192, 0.2)',
                        borderColor: 'rgba(75, 192, 192, 1)',
                        borderWidth: 1
                    },
                    {
                        label: 'Body Fat Percentage',
                        data: bodyFatData,
                        backgroundColor: 'rgba(192, 75, 75, 0.2)',
                        borderColor: 'rgba(192, 75, 75, 1)',
                        borderWidth: 1
                    }
                ]
            },
            options: {
                scales: {
                    x: {
                        display: true,
                        title: {
                            display: true,
                            text: 'Date'
                        }
                    },
                    y: {
                        display: true,
                        title: {
                            display: true,
                            text: 'Value'
                        },
                        beginAtZero: true
                    }
                }
            }
        });
    };
    </script>

    <h2>Records</h2>
    <table border="1">
        <thead>
            <tr>
                <th>Date</th>
                <th>Weight (kg)</th>
                <th>Height (cm)</th>
                <th>Age</th>
                <th>Gender</th>
                <th>BMI</th>
                <th>Body Fat (%)</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="input" items="${results}">
                <tr>
                    <td>${input.date}</td>
                    <td>${input.weight}</td>
                    <td>${input.height}</td>
                    <td>${input.age}</td>
                    <td>${input.gender}</td>
                    <td>${input.bmi}</td>
                    <td>${input.bodyFatPercentage}</td>
                    <td>
                        <form action="deleteInput" method="post">
                              <input type="hidden" name="inputId" value="${input.id}">
                              <input type="hidden" name="inputDate" value="${input.date}">
                              <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br>
    <br>

    <a href="index.jsp">Back to Search</a>

</body>
</html>