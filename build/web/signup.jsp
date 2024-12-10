<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign In</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="assets/css/signup.css" rel="stylesheet">
        <script src="assets/js/signup.js"></script>
    </head>
    <body>
        <!-- Navbar -->
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

        <!-- Main Content -->
        <div class="container-fluid">
            <!-- Image Section with Black Overlay -->
            <div class="basket">
                <img src="assets/signup.jpg" alt="Pizza Image">
            </div>
            
            <!-- Form Section -->
            <div class="form-banner">
                <div class="form-container">
                    <h2>Sign Up</h2>
                    <p>Please fill in the details below to create an account.</p>
                    <form action="insert" method="POST" onsubmit="validateForm(event)">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="fullName">Full Name</label>
                                <input type="text" class="form-control" id="fullName" name="fullName" placeholder="Full Name" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="email">Email Address</label>
                                <input type="email" class="form-control" id="email" name="email" placeholder="Email Address" required>
                            </div>
                        </div>
                        <!-- Second Row: Password and Confirm Password -->
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Password" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="confirmPassword">Confirm Password</label>
                                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
                            </div>
                        </div>

                        <!-- Third Row: Phone and Date of Birth -->
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="phone">Phone Number</label>
                                <input type="tel" class="form-control" id="phone" name="phone" placeholder="Phone Number" required>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="dob">Date of Birth</label>
                                <input type="date" class="form-control" id="dob" name="dob" required>
                            </div>
                        </div>

                        <!-- Fourth Row: Address -->
                        <div class="form-group">
                            <label for="address">Address</label>
                            <textarea class="form-control" id="address" name="address" placeholder="Enter your address" rows="3" required></textarea>
                        </div>

                        <!-- Buttons -->
                        <button type="submit" class="btn btn-danger">Sign Up</button>
                        
                    </form>
                    <p class="small-text mt-3">
                        By signing up, you agree to the 
                        <a href="#">Terms of Service</a>. View our 
                        <a href="#">Privacy Policy</a>.
                    </p>
                </div>
            </div>
        </div>
    </body>
</html>
