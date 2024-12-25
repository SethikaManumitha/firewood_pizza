<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
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
                border-radius: 50px;
                padding: 5px 15px;
                font-size: 1rem;
                font-weight: bold;
            }

            .points-badge i {
                margin-right: 5px;
            }

            .navbar .dropdown {
                margin-right: 100px;
            }

           
            .form-container {
                max-width: 600px;
                margin: 50px auto;
                padding: 20px;
                border: 2px solid #ddd;
                border-radius: 10px;
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

        <div class="container">
            <!-- Form container with border and centering -->
            <div class="form-container">
                <form>
                     <div class="form-group">
                        <label>Full Name</label>
                        <input type="text" class="form-control"  value="<%= session.getAttribute("name") %>" readonly>  
                    </div>
                    <div class="form-group">
                        <label>Email address</label>
                        <input type="email" class="form-control" value="<%= session.getAttribute("userEmail") %>" readonly>
                    </div>
                    <div class="form-group">
                        <label >Password</label>
                        <input type="text" class="form-control" value="<%= session.getAttribute("pass") %>" readonly>
                    </div>
                    <div class="form-group">
                        <label>Phone Number</label>
                        <input type="text" class="form-control"  value="<%= session.getAttribute("phone") %>" readonly>  
                    </div>
                    <div class="form-group">
                        <label>Address</label>
                        <input type="text" class="form-control"  value="<%= session.getAttribute("address") %>" readonly>
                    </div>
                    <div class="form-group">
                        <label>Date of Birth</label>
                        <input type="date" class="form-control"  value="<%= session.getAttribute("dob") %>" readonly>  
                    </div>
                </form>
            </div>
        </div>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
