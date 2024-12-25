<%@ page import="model.Topping" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="model.Builder.Order" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" 
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" 
              crossorigin="anonymous">
        <!-- Font Awesome for Icons -->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <title>Admin Dashboard</title>
        <style>
            html, body {
                height: 100%;
                margin: 0;
                font-family: Arial, sans-serif;
            }
            .wrapper {
                display: flex;
                align-items: stretch;
                height: 100%;
            }
            #sidebar {
                min-width: 250px;
                max-width: 250px;
                background: #343a40;
                color: #fff;
                height: 100vh; /* Full height */
                position: sticky;
                top: 0;
            }
            #sidebar .sidebar-header {
                padding: 20px;
                background: #495057;
            }
            #sidebar ul.components {
                padding: 20px 0;
            }
            #sidebar ul li a {
                padding: 10px;
                font-size: 1.1em;
                display: block;
                color: #adb5bd;
                transition: 0.3s;
            }
            #sidebar ul li a:hover {
                color: #ffffff;
                background: #495057;
            }
            .content {
                flex-grow: 1;
                padding: 20px;
                background: #f8f9fa;
                height: 100vh;
                overflow-y: auto;
            }
            .navbar {
                margin-bottom: 0;
            }
            
            .form-container {
                max-width: 1000px; /* Adjust width as needed */
                padding: 20px;
                margin-top: 50px;
                border: 1px solid #ccc;
                border-radius: 10px;
                background: #fff;
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            }
        </style>
    </head>
    <body>
        <%
            String alertMessage = (String) request.getAttribute("alertMessage");
            if (alertMessage != null) {
        %>
        <script>
            alert("<%= alertMessage %>");
        </script>
        <%
            }
        %>
        <!-- Wrapper for Sidebar and Content -->
        <div class="wrapper">
            <!-- Sidebar -->
            <nav id="sidebar">
                <div class="sidebar-header">
                    <h5>Admin Dashboard</h5>
                </div>

                <ul class="list-unstyled components">
                    <p>Menu</p>
                    <li>
                        <a href="admin"><i class="fas fa-tachometer-alt mr-2"></i>Dashboard</a>
                    </li>
                    
                     <li>
        <a href="admin?submit=GetToppings"><i class="fas fa-percent mr-2"></i>Offers for Toppings</a>
    </li>
                    <li>
                        <a href="login.jsp">
                            <i class="fas fa-sign-out-alt mr-1"></i>Logout
                        </a>
                    </li>
                </ul>
            </nav>

            <!-- Content -->
            <div class="content">
                <div class="container">
                    <div class="form-container mx-auto">
                        <form action="admin" method="POST">
                            <div class="form-group">
                                <label>Topping</label>
                                <select class="form-control" name="topping" id="toppingSelect">
                                    <option selected disabled>Select a Topping</option>
                                    <%
                                        List<Topping> toppings = (List<Topping>) request.getAttribute("toppings");
                                        if (toppings != null) {
                                            for (Topping topping : toppings) {
                                    %>
                                                <option 
                                                    value="<%= topping.getToppingId()%>" 
                                                    data-price="<%= topping.getPrice() - topping.getDiscount() %>">
                                                    <%= topping.getName() %>
                                                </option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>
                            <div class="form-group">
                                <label>Current Price</label>
                                <input type="text" class="form-control" id="total" name="total" value="0" readonly>
                            </div>
                            <div class="form-group">
                                <label>Discount Price</label>
                                <input type="text" class="form-control" id="discount" name="discount" placeholder="Enter discount">
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <button type="submit" name="submit" value="AddOfferTopping" class="btn btn-success" style="width:100%">Submit</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- JavaScript Dependencies -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.bundle.min.js"></script>

        <!-- Custom JS to update total price based on topping selection -->
        <script>
            $(document).ready(function () {
                $('#toppingSelect').change(function () {
                    var price = $(this).find(':selected').data('price');
                    $('#total').val(price || '0');
                });
            });
        </script>
    </body>
</html>
