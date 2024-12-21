<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Page</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Feedback Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
        }
        .feedback-container {
            max-width: 600px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        .feedback-container h1 {
            text-align: center;
            color: #333;
        }
        .rating {
            text-align: center;
            margin: 20px 0;
        }
        .stars {
            display: flex;
            justify-content: center;
            gap: 5px;
        }
        .stars i {
            font-size: 30px;
            color: #ccc;
            cursor: pointer;
            transition: color 0.2s;
        }
        .stars i:hover,
        .stars i.selected {
            color: #ffd700;
        }
        textarea {
            width: 100%;
            height: 100px;
            margin-top: 10px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            resize: none;
        }
        button {
            display: block;
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #4caf50;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        
                .points-badge {
        display: flex;
        align-items: center;
        justify-content: center;
        background-color: #fff;
        color: #d0021b;
        border: 2px solid #d0021b;
        border-radius: 50px; /* Creates a circular rectangle */
        padding: 5px 15px;
        font-size: 1rem;
        font-weight: bold;
    }

    .points-badge i {
        margin-right: 5px; /* Add space between star icon and points */
    }
    .navbar .dropdown {
    margin-right: 100px; /* Adjusts the position of the user icon */
}
    </style>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">  
    
</head>
<body>
    <nav class="navbar navbar-dark bg-danger">
    <a class="navbar-brand text-white" href="index.jsp">Firewood Pizza</a>
    <form class="form-inline ml-auto">
        <% 
            String status = (String) session.getAttribute("status"); 
            if (status == null || !status.equals("logged")) {
        %>
            <a href="login.jsp">
                <button class="btn btn-outline-light my-2 my-sm-0" style="margin-right:20px;" type="button">Sign-In</button>
            </a>
            <a href="signup.jsp">
                <button class="btn btn-outline-light my-2 my-sm-0" type="button">Sign-Up</button>
            </a>
        <% 
            } else { 
                int points = (session.getAttribute("points") != null) ? (int) session.getAttribute("points") : 0;
        %>
            <div class="nav-item dropdown" style="position: relative; margin-right: 20px;">
            <!-- Bell icon with notification badge -->
            <a class="nav-link text-white" href="notification?email=<%= session.getAttribute("userEmail")  %>">
                <i class="fas fa-bell"></i>
                <span class="badge badge-light" id="notificationBadge" style="position: absolute; top: 5px; right: 0px; font-size: 0.75rem; padding: 2px 6px; color: red;">3</span>
            </a>
        </div>
            <span class="points-badge mr-3">
                <i class="fas fa-star" style="color: gold;"></i> <strong><%= points %></strong>
            </span>
            <div class="dropdown show">
                <a class="btn btn-danger dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  <i class="fas fa-user"></i>
                </a>

                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                  <a class="dropdown-item" href="profile.jsp"><i class="fas fa-user"></i> User Profile</a>
                  <hr>
                 <a class="dropdown-item" href="order?email=<%= session.getAttribute("userEmail") %>"><i class="fas fa-star"></i> Favourite List</a>
                  <a class="dropdown-item" href="login.jsp"><i class="fas fa-sign-out-alt"></i> Logout</a>
                </div>
              </div>
        <% 
            } 
        %>
    </form>
</nav>
    <div class="feedback-container">
        <h1>We value your feedback</h1>
        
        <!-- Star Rating Section -->
        <div class="rating">
            <h3>Rate your experience:</h3>
            <div class="stars">
                <i class="star" data-value="1">&#9733;</i>
                <i class="star" data-value="2">&#9733;</i>
                <i class="star" data-value="3">&#9733;</i>
                <i class="star" data-value="4">&#9733;</i>
                <i class="star" data-value="5">&#9733;</i>
            </div>
        </div>
        
        <form method="POST" action="order">
            <input type="hidden" name="id" value="${orderName}">
            <input type="hidden" name="user" value="<%= session.getAttribute("userEmail") %>">
            <input type="hidden" id="ratingField" name="rating" value="${rating}">
            <textarea id="feedback" name="feedback" placeholder="Write your feedback here..."><%= request.getAttribute("feedback") != null ? request.getAttribute("feedback") : "" %></textarea>
            
            <!-- Change the button dynamically based on the 'btnStatus' value -->
            <button type="submit" name="submit" value="GiveFeedback" id="feedbackButton" class="btn btn-success">
                Submit Feedback
            </button>
        </form>
    </div>

    <script>
        // Check the btnStatus value passed from the servlet and change the button accordingly
        <% String btnStatus = (String) request.getAttribute("btnStatus"); %>
        if("<%= btnStatus %>" === "yes") {
            var btn = document.getElementById('feedbackButton');
            btn.classList.remove('btn-success');
            btn.classList.add('btn-danger');
            btn.innerText = "Cancel Feedback";
        }

        // Star Rating Script
        const stars = document.querySelectorAll('.star');
        const ratingField = document.getElementById('ratingField'); 

        stars.forEach(star => {
            star.addEventListener('click', () => {
                const selectedRating = star.getAttribute('data-value');
                ratingField.value = selectedRating; 
                stars.forEach(s => s.classList.remove('selected'));
                for (let i = 0; i < selectedRating; i++) {
                    stars[i].classList.add('selected');
                }
            });
        });

        // Submit Button Script (optional for client-side feedback validation)
        const submitButton = document.getElementById('submitFeedback');
        const feedbackText = document.getElementById('feedback');

        submitButton.addEventListener('click', (event) => {
            if (ratingField.value === "0") {
                event.preventDefault();
                alert('Please provide a rating!');
            } else if (!feedbackText.value.trim()) {
                event.preventDefault();
                alert('Please provide feedback in the text area!');
            }
        });
    </script>
</body>
</html>
