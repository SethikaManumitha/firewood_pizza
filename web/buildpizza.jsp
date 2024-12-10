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
        <!-- Form Section -->
        <div class="form-banner">
            <div class="form-container">
                <center><h2>Build Your Pizza</h2></center>
                <div class="form-group">
                    <label for="exampleInputEmail1">Name</label>
                    <input type="text" class="form-control" id="txtname" name="txtname" aria-describedby="name" placeholder="Enter Name For Your Pizza">
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
                            <label class="form-check-label" for="large">Large (LKR <span class="price">1500</span>)</label>
                        </div>
                    </div>
                </div>
                
                <!-- Crust Dropdown -->
                <div class="form-group">
                    <label for="crust">Crust</label>
                    <button class="dropdown-btn" type="button" id="crustDropdown">
                        Select Crust
                        <i class="fas fa-chevron-down"></i>
                    </button>
                    <div class="dropdown-container" id="crustOptions">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="crust" id="thin" value="Thin" data-price="100">
                            <label class="form-check-label" for="thin">Thin Crust (LKR <span class="price">100</span>)</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="crust" id="thick" value="Thick" data-price="150">
                            <label class="form-check-label" for="thick">Thick Crust (LKR <span class="price">150</span>)</label>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="sauce">Sauce</label>
                    <button class="dropdown-btn" type="button" id="sauceDropDown">
                        Select Sauce
                        <i class="fas fa-chevron-down"></i>
                    </button>
                    <div class="dropdown-container" id="sauceOption">
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="sauce" id="tomato" value="Tomato" >
                            <label class="form-check-label" for="tomato">Tomato</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="sauce" id="spicy" value="Spicy" >
                            <label class="form-check-label" for="spicy">Spicy</label>
                        </div>
                    </div>
                </div>

                <!-- Topping Dropdown -->
                <div class="form-group">
                    <label for="topping">Topping</label>
                    <button class="dropdown-btn" type="button" id="toppingDropdown">
                        Select Topping
                        <i class="fas fa-chevron-down"></i>
                    </button>
                    <div class="dropdown-container" id="toppingOptions">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="topping" id="pepperoni" value="Pepperoni" data-price="300">
                            <label class="form-check-label" for="pepperoni">Pepperoni (LKR <span class="price">300</span>)</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="topping" id="veggie" value="Veggie" data-price="250">
                            <label class="form-check-label" for="veggie">Veggie (LKR <span class="price">250</span>)</label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" name="topping" id="chicken" value="Chicken" data-price="400">
                            <label class="form-check-label" for="chicken">Chicken (LKR <span class="price">400</span>)</label>
                        </div>
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
                    <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                    <label class="form-check-label" for="flexCheckDefault">
                        Include Cheese <span style="color:grey">(Additional Charges May Include)</span>
                    </label>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <button type="button" class="btn btn-danger mt-3" id="addToCartBtn" style="width:100%">Add to Cart - LKR 0</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Basket Section -->
        <div class="basket">
            <h3>Basket</h3>
            <div id="basketContent">
    <div id="basketList" class="basket-items"></div>
    <div class="basket-summary">
        <h5>Total: LKR <span id="totalAmount">0</span></h5>
    </div>
    <div class="row">
        <div class="col-md-12">
            <button type="button" class="btn btn-success mt-3" id="checkoutBtn" onclick="window.location.href='checkout.jsp'" style="width:100%;">Check Out</button>
        </div>
    </div>
</div>

        </div>
    </div>

    <script src="assets/js/buildpizza.js"></script>
</body>
</html>
