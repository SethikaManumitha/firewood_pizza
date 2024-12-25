<%-- 
    Document   : notification
    Created on : Dec 18, 2024, 9:43:46 PM
    Author     : MAS
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="model.Notification" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Notifications</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
         
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
      <style>
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
    
    <% 
        List<Notification> notifications = (List<Notification>) request.getAttribute("notification");
        if (notifications != null && !notifications.isEmpty()) {
            for (int i = 0; i < notifications.size(); i++) {
                Notification notification = notifications.get(i);
    %>
        <div class="card">
            <div class="card-body">
                <%= notification.getNotificationMessage() %>
            </div>
        </div>
    <% 
            }
        } else { 
    %>
        <div class="col-12">
            <p class="text-center">No favorite pizzas available.</p>
        </div>
    <% } %>

     <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>

