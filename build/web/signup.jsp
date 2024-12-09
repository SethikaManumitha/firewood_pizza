<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign Up</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <style>
            html, body {
    margin: 0;
    padding: 0;
    overflow-x: hidden;
    height: 100%;
}
.container-fluid {
    display: flex;
    height: 100vh;
    padding: 0;
}
.basket, .form-banner {
    width: 50%;
    height: 100%;
    position: relative;
}
.basket img {
    width: 100%;
    height: 100%;
    object-fit: cover;
}
.basket::after {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5);
    z-index: 1;
}
.form-banner {
    display: flex;
    justify-content: center;
    align-items: center;
    background-color: #f8f9fa;
}
.form-container {
    background: #fff;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
    width: 100%;
    max-width: 600px; /* Increased from 400px to 600px */
}
.form-container h2 {
    font-size: 24px;
    font-weight: bold;
    margin-bottom: 10px;
    text-transform: uppercase;
}
.form-container p {
    font-size: 14px;
    color: #6c757d;
    margin-bottom: 20px;
}
.form-container .btn {
    width: 100%;
    font-size: 16px;
    margin-bottom: 10px;
}
.form-container .btn-outline-danger {
    border: 2px solid #dc3545;
    color: #dc3545;
}
.form-container .small-text {
    font-size: 12px;
    text-align: center;
    color: #6c757d;
}

        </style>
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-dark bg-danger justify-content-between">
            <a class="navbar-brand text-white">Firewood Pizza</a>
            <form class="form-inline">
                <a href="signup.jsp">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="button">Sign-Up</button>
                </a>
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
                    <form action="insert" method="POST">
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
