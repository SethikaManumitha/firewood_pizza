<%@ page import="java.util.List" %>
<%@ page import="model.Crust" %>
<%@ page import="model.Sauce" %>
<%@ page import="model.Topping" %>
<%@ page import="model.Pizza" %>
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
        
        
        <div class="form-banner">
            <div class="form-container">
                <center><h2>Build Your Pizza</h2></center>
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
                            <input class="form-check-input" type="checkbox" name="topping" id="<%= topping.getName() %>" value="<%= topping.getName() %>" data-price="<%= topping.getPrice() %>">
                            <label class="form-check-label" for="<%= topping.getName() %>"><%= topping.getName() %> (LKR <span class="price"><%= topping.getPrice() %></span>)</label>
                        </div>
                        <% 
                            }
                        }
                        %>
                        
                    </div>
                </div>
                <div class="form-group">
    <label for="quantity">Quantity</label>
    <div class="input-group">
        <div class="input-group-prepend">
            <button class="btn btn-outline-secondary" type="button" id="decrementQty">-</button>
        </div>
        <input type="text" class="form-control text-center" id="txtqty" name="txtqty" value="1" readonly>
        <div class="input-group-append">
            <button class="btn btn-outline-secondary" type="button" id="incrementQty">+</button>
        </div>
    </div>
</div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="cheese" name="cheese">
                    <label class="form-check-label" for="flexCheckDefault">
                        Include Cheese <span style="color:grey">(Additional Charges May Include)</span>
                    </label>
                </div>
                <div class="form-group">
                    <label for="totalAmountField">Total Amount</label>
                    <input type="text" class="form-control" id="totalAmountField" name="totalAmountField" readonly>
                </div>

                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="favourite" name="favourite">
                    <label class="form-check-label" for="favourite">
                        Save To Favourites
                    </label>
                </div>
                        
                <div class="row">
                    <div class="col-md-12">
                        <button type="submit" name="submit" value="BuildPizza" class="btn btn-danger mt-3" style="width:100%">Build Pizza</button>                        
                    </div>
                </div>
                </form>          
            </div>
        </div>
                
        <!-- Basket Section -->
        <div class="basket">
            <div id="basketContent">
     <% 
                        List<Pizza> pizzas = (List<Pizza>) request.getAttribute("pizzas");
                        if (pizzas != null) {
                            for (Pizza pizza : pizzas) {
                        %>
                        <p><%= pizza.getName() %></p>
                        <p><%= pizza.getCrust() %></p>
                        <p><%= pizza.getSauce()%></p>
                        <% 
                            }
                        }else{
                        %>
                        <p>No Data</p>
                        <% 
                        }
                        %>
                       
    <div class="row">
        <div class="col-md-12">
            <button type="button" class="btn btn-success mt-3" id="checkoutBtn" onclick="window.location.href='checkout.jsp'" style="width:100%;">Continue Order</button>
        </div>
    </div>
</div>

        </div>
    </div>

    <script src="assets/js/buildpizza.js"></script>
</body>
</html>