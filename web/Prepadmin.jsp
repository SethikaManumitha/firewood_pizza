<%-- 
    Document   : admin
    Created on : Dec 17, 2024, 8:37:22 AM
    Author     : MAS
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <a href="admin?submit=PreparedStateAll"><i class="fas fa-receipt mr-2"></i>Placed Orders</a>
                    </li>
                    <li>
                        <a href="admin?submit=PrepStateAll"><i class="fas fa-box-open mr-2"></i>Orders In Preperation</a>
                    </li>
                    <li>
                        <a href="admin?submit=OutStateAll"><i class="fas fa-user-friends mr-2"></i>Orders Out For Delivery</a>
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
                <div class="container-fluid">
                    <h3>Welcome to the Admin Dashboard!</h3>
                    <hr>
                   

                    <!-- Dashboard Cards -->
                    <div class="row">
                        <div class="col-md-4 mb-4">
                            <div class="card text-white bg-success">
                                <div class="card-body">
                                    <h5 class="card-title"><i class="fas fa-receipt mr-2"></i>Placed Orders</h5>
                                    <p class="card-text">${placeStateCount}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="card text-white bg-warning">
                                <div class="card-body">
                                    <h5 class="card-title"><i class="fas fa-box-open mr-2"></i>In Preparation</h5>
                                    <p class="card-text">${inPlaceStateCount}</p>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4 mb-4">
                            <div class="card text-white bg-info">
                                <div class="card-body">
                                    <h5 class="card-title"><i class="fas fa-user-friends mr-2"></i>Out for Delivery</h5>
                                    <p class="card-text">${outForOrderStateCount}</p>
                                </div>
                            </div>
                        </div>
                       
                    </div>

                    <!-- Orders Table -->
                    <table class="table">
                        <thead class="thead-dark">
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Address</th>
                                <th scope="col">Order</th>
                                <th scope="col">Total</th>
                                <th scope="col">Status</th>
                                <th scope="col">&nbsp;</th>
                               
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                List<Order> orders = (List<Order>) request.getAttribute("orders");
                                if (orders != null && !orders.isEmpty()) {
                                    for (int i = 0; i < orders.size(); i++) {
                                        Order order = orders.get(i);
                            %>
                            <tr>
                                <td><%= order.getEmail() %></td>
                                <td><%= order.getAddress() %></td>
                                <td>
                                    <ul>
                                        <%
                                            Map<String, String> pizzaDetails = order.getItems();
                                            for (String details : pizzaDetails.values()) { 
                                        %>
                                            <li><%= details %></li>
                                        <%
                                            }
                                        %>
                                    </ul>
                                </td>
                                <td>LKR.<%= order.getTotal() - order.getDiscount() %></td>
                                <td><%= order.getStatus() %></td>
                                <td>
                                    <form action="admin" method="POST">
                                    <input type="hidden" name="currentState" value="<%= order.getStatus()%>">
                                    <input type="hidden" name="txtid" value="<%= order.getId() %>">
                                    <button type="submit"  name="submit" value="ChangeState" class="btn btn-success">NEXT STATE</button>
                                    </form>
                                </td>
                            </tr>
                            <%
                                    } 
                                }
                            %>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>

        <!-- JavaScript Dependencies -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>
