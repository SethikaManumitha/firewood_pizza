    <%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
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

            .btn-group-toggle .btn {
                width: 50%; 
                text-align: center;
                border: 1px solid #ccc;
                transition: all 0.3s ease; 
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


/* Feedback Cards */
    .feedback-cards-container {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
        gap: 20px;
        margin: 40px 0;
    }

    .card {
        width: 100%;
        max-width: 300px;
        margin-bottom: 20px;
        border: 1px solid #ddd;
        border-radius: 10px;
        transition: transform 0.2s ease-in-out;
    }

    .card:hover {
        transform: scale(1.05);
    }

    .card img {
        width: 100%;
        height: 200px;
        object-fit: cover;
    }

    .card-body {
        padding: 15px;
        text-align: center;
    }

    .card-title {
        font-size: 1.2rem;
        color: #333;
        margin-bottom: 15px;
    }

    .card-text {
        font-size: 1rem;
        color: #555;
        margin-bottom: 20px;
    }

    .btn-success {
        background-color: #28a745;
        color: white;
        border: none;
        border-radius: 5px;
        width: 100%;
    }

    .btn-success:hover {
        background-color: #218838;
    }

.stars {
            display: flex;
        }

        .star {
            font-size: 24px;
            color: #ccc;
        }

        .star.filled {
            color: #f4c542;
        }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-danger">
    <a class="navbar-brand text-white" href="new">Firewood Pizza</a>
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
                    <input type="radio" name="options" id="deliver" value="Deliver" autocomplete="off"> <i class="fas fa-motorcycle"></i> Deliver
                </label>
                <label class="btn btn-light">
                    <input type="radio" name="options" id="collect" value="Pick Up" autocomplete="off"> <i class="fas fa-map-marker-alt"></i> Pick Up
                </label>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <%
                    if (status == null || !status.equals("logged")) {
                    %>
                        <a href="login.jsp">
                        <button class="btn view-deals-btn" type="button">Order Now</button>
                        </a>
                    <%
                        }else{
                    %>
                    <form action="order" method="POST">
                        <input type="hidden" name="deliveryOption" id="deliveryOption">
                        <input type="hidden" value="<%= session.getAttribute("userEmail") %>" name="email" id="email">
                        <button type="submit" class="btn view-deals-btn">Order Now</button>
                    </form>
                    <%
                    }
                    %>

                </div>
            </div> 
        </div>

        <hr>

        <div class="container-fluid">
    <%
    // Retrieve the feedback list from the request
    List<HashMap<String, String>> feedback = (List<HashMap<String, String>>) request.getAttribute("feedback");
    if (feedback != null && !feedback.isEmpty()) {
    %>

    <div class="feedback-cards-container">
        <%
        // Iterate over the feedback list and display the details
        for (HashMap<String, String> item : feedback) {
        int rating = Integer.parseInt(item.get("rating")); 
        %>
            <div class="card">
                <img src="https://placehold.co/400" >
                <div class="card-body">
                    <h5 class="card-title"><%= item.get("item") %></h5>
                      <%
                            // Generate star rating
                            for (int i = 1; i <= 5; i++) {
                                if (i <= rating) {
                            %>
                                <i class="fa fa-star" style="color: gold;"></i>
                            <%
                                } else {
                            %>
                                <i class="fa fa-star-o" style="color: gold;"></i>
                            <%
                                }
                            }
                            %>

                    <form action="order" method="POST">
                        <input type="hidden" value="<%= item.get("item") %>" name="nameadd">
                        <input type="hidden" value="<%= session.getAttribute("userEmail") %>" name="emailadd">
                        <button type="submit" name="submit" value="AddToCart" class="btn btn-success">Add To Cart</button>
                    </form>
                </div>
            </div>
        <% } %>
    </div>

    <%
    } else {
    %>
        <p>No feedback available.</p>
    <%
    }
    %>
</div>
        <footer style="background-color: black; color: white; padding: 20px 0; margin-top: 40px;">
        <div class="container-fluid text-center">
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


       
        <script src="assets/js/index.js"> </script>
        <script>
        document.addEventListener("DOMContentLoaded", function () {
            // Get the selected button
            const deliverRadio = document.getElementById("deliver");
            const collectRadio = document.getElementById("collect");
            const deliveryOptionField = document.getElementById("deliveryOption");

            deliverRadio.addEventListener("change", function () {
                if (deliverRadio.checked) {
                    deliveryOptionField.value = "Deliver";
                }
            });

            collectRadio.addEventListener("change", function () {
                if (collectRadio.checked) {
                    deliveryOptionField.value = "Pick Up";
                }
            });
        });
    </script>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
    </html>
