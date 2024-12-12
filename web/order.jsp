<%-- 
    Document   : order
    Created on : Dec 12, 2024, 9:44:47 AM
    Author     : MAS
--%>
<%@ page import="java.util.List" %>
<%@ page import="model.Builder.Pizza" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                    <div class="col-md-4">
                        <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="https://placehold.co/600x400/png" alt="Card image cap">
                        <div class="card-body">
                          <h5 class="card-title">Card title</h5>
                          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                          <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                      </div>
                    </div>
                    
                    <div class="col-md-4">
                        <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="https://placehold.co/600x400/png" alt="Card image cap">
                        <div class="card-body">
                          <h5 class="card-title">Card title</h5>
                          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                          <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="https://placehold.co/600x400/png" alt="Card image cap">
                        <div class="card-body">
                          <h5 class="card-title">Card title</h5>
                          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                          <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                      </div>
                    </div>
                </div> 
                <div class="row" style="padding:20px">
                    <div class="col-md-4">
                        <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="https://placehold.co/600x400/png" alt="Card image cap">
                        <div class="card-body">
                          <h5 class="card-title">Card title</h5>
                          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                          <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                      </div>
                    </div>
                    
                    <div class="col-md-4">
                        <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="https://placehold.co/600x400/png" alt="Card image cap">
                        <div class="card-body">
                          <h5 class="card-title">Card title</h5>
                          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                          <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                      </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card" style="width: 18rem;">
                        <img class="card-img-top" src="https://placehold.co/600x400/png" alt="Card image cap">
                        <div class="card-body">
                          <h5 class="card-title">Card title</h5>
                          <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
                          <a href="#" class="btn btn-primary">Go somewhere</a>
                        </div>
                      </div>
                    </div>
                </div> 
                <div class="row">
                    <div class="col-md-12">
                        <a href="build">
                        <button class="btn btn-success" style="width:100%">Build Custom Pizza</button>
                        </a>
                    </div>
                        
                </div>
                
            </div>
            <div class="col-md-4" style="border-left: 2px solid #ccc">
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
                                          </ul>
                                      <hr>
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
