<%-- 
    Document   : index
    Created on : Dec 8, 2024, 10:07:41 AM
    Author     : MAS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Firewood Pizza</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">

    <style>
        .banner {
    position: relative;
    width: 100%;
    height: 60vh; /* Set height to 50% of the viewport height */
    overflow: hidden; /* Ensures content stays within the banner */
    
}

.banner img {
    width: 100%;
    height: 100%;
    object-fit: cover; /* Ensures the image fills the container proportionally */
}

.banner::after {
    content: "";
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* Black overlay with 50% opacity */
    z-index: 1;
}

.banner-content {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    color: white;
    z-index: 2;
    text-align: center;
}

  .order-section {
            margin: 30px auto;
            max-width: 600px;
            text-align: center;
            border:3px solid #ccc;
            border-radius:10px;
            padding:20px;
    }
        .order-section h1 {
            font-size: 1.8rem;
            font-weight: bold;
            color: #d0021b; /* Similar red color */
            margin-bottom: 20px;
        }
        .btn-group-toggle .btn {
            border: 1px solid #ccc;
        }
        .form-control {
            border-radius: 0;
        }
        .view-deals-btn {
            background-color: #d0021b;
            color: white;
            border: none;
            border-radius: 0;
            width:100%;
            height: 50px;
            font-size: 20px;
        }
        
        .view-deals-btn:hover {
            background-color: #ccc;
            color: black;
            border: none;
            border-radius: 0;
            width:100%;
            height: 50px;
            font-size: 20px;
        }
        .current-location {
            color: #d0021b;
            margin-top: 10px;
            display: block;
            font-size: 0.9rem;
            font-weight: bold;
        }
        
                /* Default button styles */
        .btn-group-toggle .btn {
            width: 50%; /* Distribute buttons evenly */
            text-align: center;
            border: 1px solid #ccc;
            transition: all 0.3s ease; /* Smooth transition for style changes */
        }

        /* Styles for the selected button */
        .btn-group-toggle .btn.active {
            background-color: #b80000; 
            color: white; 
            font-size: 18px; 
            font-weight: bold;
        }
        
        .btn-promo{
            width:100%;
        }
        


    </style>
</head>
<body>
    <nav class="navbar navbar-dark bg-danger justify-content-between">
    <a class="navbar-brand text-white">Firewood Pizza</a>
    <form class="form-inline">
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
        %>
            <span class="text-white mr-3">Welcome, <%= session.getAttribute("userEmail") %>!</span>
            <a href="login.jsp">
                <button class="btn btn-outline-light my-2 my-sm-0" type="button">Logout</button>
            </a>
        <%
            }
        %>
    </form>
</nav>

   
    <div class="banner">
        
         <img src="assets/banner2.jpg"/>   
        <div class="banner-content">
            <h1>Welcome to Firewood Pizza</h1>
            <p>The best pizza in town!</p>
        </div>
    </div>
    
    <div class="order-section">
        <h1>START YOUR ORDER</h1>
        <div class="btn-group btn-group-toggle mb-3 w-100" data-toggle="buttons">
            <label class="btn btn-light">
                <input type="radio" name="options" id="deliver" autocomplete="off"> <i class="fas fa-motorcycle"></i> Deliver
            </label>
            <label class="btn btn-light">
                <input type="radio" name="options" id="collect" autocomplete="off"> <i class="fas fa-map-marker-alt"></i> Pick Up
            </label>
        </div>
        <div class="row">
            <div class="col-md-12">
                <a href="build">
                <button class="btn view-deals-btn" type="button">Order Now</button>
                </a>
            </div>
        </div> 
    </div>
    
    <hr>
    
    <footer style="background-color: black; color: white; padding: 20px 0; margin-top: 40px;">
    <div class="container text-center">
        <div class="row">
            <div class="col-md-4">
                <h5>About Us</h5>
                <p>Firewood Pizza is your go-to destination for delicious, handcrafted pizzas made with love and passion. Join us and experience the best pizza in town!</p>
            </div>
            <div class="col-md-4">
                <h5>Contact Us</h5>
                <p>Email: support@firewoodpizza.com</p>
                <p>Phone: +94 123 456 789</p>
                <p>Address: 123 Pizza Lane, Colombo, Sri Lanka</p>
            </div>
            <div class="col-md-4">
                <h5>Follow Us</h5>
                <a href="#" style="color: white; margin: 0 10px;"><i class="fab fa-facebook-f"></i></a>
                <a href="#" style="color: white; margin: 0 10px;"><i class="fab fa-twitter"></i></a>
                <a href="#" style="color: white; margin: 0 10px;"><i class="fab fa-instagram"></i></a>
            </div>
        </div>
        <hr style="background-color: #666; margin: 20px 0;">
        <p style="margin: 0;">&copy; 2024 Firewood Pizza. All rights reserved.</p>
    </div>
</footer>

    
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39jFES/heMQPyh39jO5p8N" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFwAIlUjK9M9GqOUcmgfM0XKp4" crossorigin="anonymous"></script>
    <script src="assets/js/index.js"> </script>
  
</body>
</html>
