<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pizza Builder</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">  
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
        .form-banner {
            width: 70%;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f8f9fa;
        }
        .basket {
            width: 30%;
            background-color: #fff;
            padding: 20px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            height: 100%;
        }
        .form-container {
            background: #fff;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 600px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .dropdown-container {
            display: none;
            overflow: hidden;
            margin-top: 10px;
        }
        .dropdown-btn {
            width: 100%;
            background-color: #f8f9fa;
            color: #495057;
            text-align: left;
            padding: 10px 15px;
            border: 1px solid #ced4da;
            border-radius: 4px;
            cursor: pointer;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .dropdown-btn:focus {
            outline: none;
        }
        .dropdown-btn i {
            transition: transform 0.3s;
        }
        .dropdown-btn.active i {
            transform: rotate(180deg);
        }
        .price {
            font-weight: bold;
            color: #dc3545;
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
                <ul id="basketList"></ul>
                <div>Total: LKR <span id="totalAmount">0</span></div>
            </div>
            <div class="row">
                <div class="col-md-12">
                    <button type="button" class="btn btn-success mt-3" id="addToCartBtn" style="width:100%;position: relative;top:100%;">Check Out</button>
                </div>
            </div>
        </div>
    </div>

    <script>
  $(document).ready(function() {
    // Initialize basket total
    let totalAmount = 0;
    let netTotal = 0;  // Net total for the entire basket

    // Function to calculate the total price for a single pizza
    function calculateTotal() {
        totalAmount = 0;

        // Add the price of the selected size
        let sizePrice = parseFloat($('input[name="size"]:checked').data('price') || 0);
        totalAmount += sizePrice;

        // Add the price of the selected crust
        let crustPrice = parseFloat($('input[name="crust"]:checked').data('price') || 0);
        totalAmount += crustPrice;

        // Add the price of the selected toppings
        $('input[name="topping"]:checked').each(function() {
            totalAmount += parseFloat($(this).data('price') || 0);
        });

        // Update the total price shown on the "Add to Cart" button
        $('#addToCartBtn').text('Add to Cart - LKR ' + totalAmount.toFixed(2));

        // Update total in the basket for this pizza
        $('#totalAmount').text(totalAmount.toFixed(2));
    }

    // Dropdown button click event to toggle dropdown visibility
    $(".dropdown-btn").click(function() {
        var dropdownContainer = $(this).next(".dropdown-container");

        // Toggle active class for the button
        $(this).toggleClass("active");

        // Toggle the visibility of the dropdown
        dropdownContainer.toggleClass("active");
        dropdownContainer.slideToggle(); // This will slide the dropdown up or down
    });

    // Size change event
    $('input[name="size"]').change(function() {
        var selectedSize = $('input[name="size"]:checked').next('label').text();
        $('#sizeDropdown').html(selectedSize + ' <i class="fas fa-chevron-down"></i>');
        calculateTotal();
    });

    // Crust change event
    $('input[name="crust"]').change(function() {
        var selectedCrust = $('input[name="crust"]:checked').next('label').text();
        $('#crustDropdown').html(selectedCrust + ' <i class="fas fa-chevron-down"></i>');
        calculateTotal();
    });

    // Sauce change event
    $('input[name="sauce"]').change(function() {
        var selectedSauce = $('input[name="sauce"]:checked').next('label').text();
        $('#sauceDropDown').html(selectedSauce + ' <i class="fas fa-chevron-down"></i>');
        calculateTotal();
    });

    // Topping change event
    $('input[name="topping"]').change(function() {
        var selectedToppings = [];
        $('input[name="topping"]:checked').each(function() {
            selectedToppings.push($(this).next('label').text());
        });
        var toppingText = selectedToppings.join(', ') || "Select Topping";
        $('#toppingDropdown').html(toppingText + ' <i class="fas fa-chevron-down"></i>');
        calculateTotal();
    });

    // Add to cart button event
    $('#addToCartBtn').click(function() {
        // Get pizza details
        let pizzaName = $('#txtname').val() || "Unnamed Pizza";
        let pizzaPrice = totalAmount;

        // Add pizza to basket list
        if (pizzaPrice > 0) {
            // Add pizza price to the net total
            netTotal += pizzaPrice;

            // Add pizza to the basket list
            $('#basketList').append('<li>' + pizzaName + ' - LKR ' + pizzaPrice.toFixed(2) + '</li>');

            // Update net total in the basket
            $('#totalAmount').text(netTotal.toFixed(2));

            // Reset total for the next pizza
            totalAmount = 0;
            $('#addToCartBtn').text('Add to Cart - LKR ' + totalAmount.toFixed(2));

            // Reset the dropdown text for all selections
            $('#sizeDropdown').html('Select Size <i class="fas fa-chevron-down"></i>');
            $('#crustDropdown').html('Select Crust <i class="fas fa-chevron-down"></i>');
            $('#sauceDropDown').html('Select Sauce <i class="fas fa-chevron-down"></i>');
            $('#toppingDropdown').html('Select Topping <i class="fas fa-chevron-down"></i>');
        } else {
            alert("Please complete your pizza selection before adding to cart.");
        }
    });
  });
</script>

</body>
</html>
