<%-- 
    Document   : order
    Created on : Dec 12, 2024, 9:44:47 AM
    Author     : MAS
--%>
<%@ page import="java.util.List" %>
<%@ page import="model.Builder.Pizza" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <title>Order Page</title>
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
        
    <div class="container-fluid">
        
        <div class="row">
            <div class="col-md-8">
                
                <div class="row" style="padding:20px">
                    <div class="container-fluid">
                        
                    <div class="row">
                    <div class="col-md-12">
                        <a href="build">
                        <button class="btn btn-success" style="width:100%">Build Custom Pizza</button>
                        </a>
                    </div>
                    </div>
                        
                    <div class="row" style="padding-top: 20px">
    <% 
        List<Pizza> favpizzas = (List<Pizza>) request.getAttribute("favpizzas");
        if (favpizzas != null && !favpizzas.isEmpty()) {
            for (int i = 0; i < favpizzas.size(); i++) {
                Pizza favpizza = favpizzas.get(i);
                // Open a new row every 3 pizzas
                if (i % 3 == 0) { 
    %>
                    <div class="w-100"></div>
                <% } %>
                <div class="col-md-4 mb-4">
                    <div class="card h-100">
                        <img class="card-img-top" src="https://placehold.co/600x400/png" alt="Card image cap">
                        <div class="card-body">
                            <h5 class="card-title"><%= favpizza.getName() %></h5>
                            <ul class="card-text">
                                <li><b>Crust: </b><%= favpizza.getCrust() %></li>
                                <li><b>Sauce: </b><%= favpizza.getSauce() %></li>
                                <li><b>Toppings: </b><%= favpizza.getToppings() %></li>
                                <li><b>Price: </b> LKR.<%= favpizza.getPrice()%></li>
                            </ul>
                            <a href="#" class="btn btn-success" style="width:100%;">Add To Cart</a>
                        </div>
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
</div>

                    </div>
                    
                    
                </div> 
                    
               
                
            </div>
            <div class="col-md-4" style="height:900px;border-left: 2px solid #ccc;">
                  <% 
                        List<Pizza> pizzas = (List<Pizza>) request.getAttribute("pizzas");
                        if (pizzas != null) {
                            for (Pizza pizza : pizzas) {
                        %>
                            <div class="row" style="padding-top: 20px">
                                <div class="col-md-12">
                                  <div class="card">
                                    <div class="card-body">
                                      <h5 class="card-title"><%= pizza.getName() %></h5>
                                      <p class="card-text">
                                      <ul>
                                          <li><%= pizza.getCrust() %></li>
                                          <li><%= pizza.getSauce() %></li>
                                          <li><%= pizza.getToppings() %></li>
                                          <li><%= pizza.isIncludeCheese() ? "Yes" : "No" %></li> 
                                        <li>QTY :<%= pizza.getQty() %></li>
                                        <li>PRICE :<%= pizza.getPrice() %></li>
                                          </ul>
                                          <hr>
                                          <h4>TOTAL: <b>LKR.<%= pizza.getPrice() * pizza.getQty() %></b></h4>
                                      </p>
                                      
                                    </div>
                                  </div>
                                </div>
                                
                              </div>
                                      <hr>
                        <% 
                            }
                        }
                        %>
            </div>
        </div>
    </div>
    </body>
</html>
