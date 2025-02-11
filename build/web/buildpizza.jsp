<%@ page import="java.util.List" %>
<%@ page import="model.Crust" %>
<%@ page import="model.Sauce" %>
<%@ page import="model.Topping" %>
<%@ page import="model.Builder.Pizza" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pizza Builder</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">  
    <link href="assets/css/buildpizza.css" rel="stylesheet">
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

.old-price {
    text-decoration: line-through;
    color: red; /* Optional: To make the original price red */
}

    </style>
</head>
<body>
    <!-- Navbar -->
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

    <!-- Main Content -->
    <div class="container-fluid" style="height:900px">
        
        
        <div class="form-banner">
            <div class="form-container">
                <center><h2>Build Your Pizza</h2></center>
                 <%
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                <script>
                    alert("<%= errorMessage %>");
                </script>
                <%
                    }
                %>
                <form action="build" method="POST">
                <div class="form-group">
                    <label for="exampleInputEmail1">Name</label>
                    <input type="text" class="form-control" id="txtname" name="txtname" aria-describedby="name" placeholder="Enter Name For Your Pizza">
                    <input type="hidden" name="email" id="email" value="<%= session.getAttribute("userEmail") %>">
                </div>
                <!-- Size Dropdown -->
                <div class="form-group">
                    <label for="size">Size</label>
                    <button class="dropdown-btn" type="button" id="sizeDropdown">
                        Select Size
                        <i class="fas fa-chevron-down"></i>
                    </button>
                    <div class="dropdown-container" id="sizeOptions">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="size" id="small" value="Small" data-price="800">
                            <label class="form-check-label" for="small">Small (LKR <span class="price">800</span>)</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="size" id="medium" value="Medium" data-price="1200">
                            <label class="form-check-label" for="medium">Medium (LKR <span class="price">1200</span>)</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="size" id="large" value="Large" data-price="1500">
                            <label class="form-check-label" for="large">Large (LKR <span class="price">1500.0</span>)</label>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <label for="crust">Crust</label>
                    <button class="dropdown-btn" type="button" id="crustDropdown">
                        Select Crust
                        <i class="fas fa-chevron-down"></i>
                    </button>
                    <div class="dropdown-container" id="crustOption">
                        <% 
                        List<Crust> crusts = (List<Crust>) request.getAttribute("crusts");
                        if (crusts != null) {
                            for (Crust crust : crusts) {
                        %>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="crust" id="<%= crust.getName() %>" value="<%= crust.getName() %>" data-price="<%= crust.getPrice() %>">
                            <label class="form-check-label" for="<%= crust.getName() %>">
                                <%= crust.getName() %> (LKR <span class="price"><%= crust.getPrice() %></span>)
                            </label>
                        </div>
                        <% 
                            }
                        }
                        %>
                    </div>
                </div>
                    
                <div class="form-group">
                    <label for="sauce">Sauce</label>
                    <button class="dropdown-btn" type="button" id="sauceDropDown">
                        Select Sauce
                        <i class="fas fa-chevron-down"></i>
                    </button>
                    <div class="dropdown-container" id="sauceOption">
                        <% 
                        List<Sauce> sauces = (List<Sauce>) request.getAttribute("sauces");
                        if (sauces != null) {
                            for (Sauce sauce : sauces) {
                        %>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="sauce" id="<%= sauce.getName() %>" value="<%= sauce.getName() %>" >
                            <label class="form-check-label" for="<%= sauce.getName() %>">
                                <%= sauce.getName() %>
                            </label>
                        </div>
                        <% 
                            }
                        }
                        %>
                    </div>
                </div>

                
                <div class="form-group">
                    <label for="topping">Topping</label>
                    <button class="dropdown-btn" type="button" id="toppingDropdown">
                        Select Topping
                        <i class="fas fa-chevron-down"></i>
                    </button>
                    <div class="dropdown-container" id="toppingOptions">
                        <% 
                        List<Topping> toppings = (List<Topping>) request.getAttribute("toppings");
                        if (toppings != null) {
                            for (Topping topping : toppings) {
                        %>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="topping" id="<%= topping.getName() %>" value="<%= topping.getName() %>" data-price="<%= topping.getPrice()- topping.getDiscount() %>">
                            <label class="form-check-label" for="<%= topping.getName() %>">
                                <%= topping.getName() %> (LKR 
                                <span class="price">
                                    <% if (topping.getDiscount() > 0) { %>
                                        <span class="old-price"><%= topping.getPrice() %></span> 
                                    <% } %>
                                    <%= topping.getPrice() - topping.getDiscount() %>
                                </span>)
                            </label>                       
                        </div>
                        <% 
                            }
                        }
                        %>
                        
                    </div>
                </div>
                

               <div class="form-group">
                    <label for="totalAmountField">Total Amount</label>
                    <input type="text" class="form-control" id="totalAmountField" name="totalAmountField" readonly>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="cheese" name="cheese">
                    <label class="form-check-label" for="flexCheckDefault">
                        Include Cheese <span style="color:grey">(Additional Charges May Include)</span>
                    </label>
                </div>
                

         
                <div class="row">
                    <div class="col-md-12">
                        <button type="submit" name="submit" value="BuildPizza" class="btn btn-danger mt-3" id="new" style="width:100%">Build Pizza</button>                        
                    </div>
                </div>
                </form>          
            </div>
        </div>
                
        <!-- Basket Section -->
        <div class="basket" style="height: 900px">
            <div id="basketContent">
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
                                          <li><%= pizza.getSize()%></li>
                                          <li><%= pizza.getCrust() %></li>     
                                          <li><%= pizza.getSauce() %></li>
                                          <li><%= pizza.getToppings() %></li>
                                          <li>Included Cheese: <%= pizza.isIncludeCheese() ? "Yes" : "No" %></li> 
                                        
                                         
                                        <li id="unitPrice">PRICE :<%= pizza.getPrice() %></li>
                                          </ul>
                                          <hr>
                                          <h4>TOTAL: <b id="totalAmount">LKR.<%= pizza.getPrice()%></b></h4>
                                      </p>
                                       <div class="row">
          <div class="col-md-6">
          <form action="build" method="POST" class="me-2">
            <input type="hidden" value="<%= pizza.getName() %>" name="namedelete">
            <input type="hidden" value="<%= session.getAttribute("userEmail") %>" name="emaildelete">
            <button type="submit" name="submit" value="DeletePizza" class="btn btn-danger" style="width:100%">Delete</button>
          </form>
                </div>
            <div class="col-md-6">
          <form action="build" method="POST">
            <input type="hidden" value="<%= pizza.getName() %>" name="namefav">
            <input type="hidden" value="<%= session.getAttribute("userEmail") %>" name="emailfav">
            <button type="submit" name="submit" value="UpdatePizza" class="btn btn-success" style="width:100%">Add To Favourite</button>
          </form>
            </div>
                                    </div>
                                  </div>
                                </div>
                                
                              </div>
                                      <hr>
                       
            </div>

                                      <hr>
                        <% 
                            }
                        }
                        %>
                       
    <div class="row">
        <div class="col-md-12">
            <form action="order" method="POST">
            <input type="hidden" name="emailOrder" id="emailOrder" value="<%= session.getAttribute("userEmail") %>">
            <button type="submit" name="submit" value="GetPizza" class="btn btn-success mt-3" style="width:100%">Continue To Cart</button>
            </form>
        </div>
    </div>
</div>

        </div>
    </div>
    <script src="assets/js/buildpizza.js"></script>
</body>
</html>