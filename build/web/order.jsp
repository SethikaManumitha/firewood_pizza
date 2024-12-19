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
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
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
    </style>
        <title>Order Page</title>
    </head>
    <body>
        <nav class="navbar navbar-dark bg-danger">
    <a class="navbar-brand text-white" href="index.jsp">Firewood Pizza</a>
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
        
    <div class="container-fluid">
        
        <div class="row">
            <div class="col-md-8">
                
                <div class="row" style="padding:20px">
                    <div class="container-fluid">
                        
                    <div class="row">
                    <div class="col-md-12">
                        <form action="build" method="POST">
                       <input type="hidden" value="<%= session.getAttribute("userEmail") %>" name="email" id="email">
                       <button type="submit" class="btn btn-success" style="width: 100%">Build Custom Pizza</button>
                        </form>
                       
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
                        <div class="d-flex justify-content-center align-items-center">
                            <img class="card-img-top" src="assets/new.jpg" alt="Card image cap" style="width:200px;height:200px;">
                        </div>
                        <hr>
                        <div class="card-body">
                            <h5 class="card-title"><%= favpizza.getName() %></h5>
                            <ul class="card-text">
                                <li><b>Size </b><%= favpizza.getSize() %></li>
                                <li><b>Crust: </b><%= favpizza.getCrust() %></li>
                                <li><b>Sauce: </b><%= favpizza.getSauce() %></li>
                                <li><b>Toppings: </b><%= favpizza.getToppings() %></li>
                                <li><b>Price: </b> LKR.<%= favpizza.getPrice()%></li>
                            </ul>
                          
                             <form action="order" method="POST">
            <input type="hidden" value="<%= favpizza.getName() %>" name="nameadd">
            <input type="hidden" value="<%= session.getAttribute("userEmail") %>" name="emailadd">
            <button type="submit" name="submit" value="AddToCart" class="btn btn-success" style="width:100%">Add To Cart</button>
          </form>   

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
            <div class="col-md-4" style="height:900px;border-left: 2px solid #ccc;padding-top:20px">
                    <button type="button" class="btn btn-success" style="width: 100%" data-toggle="modal" data-target="#exampleModal" onclick="updateNetTotal()">Checkout</button>
                  <% 
                        float totalPrice = 0;
                        List<Pizza> pizzas = (List<Pizza>) request.getAttribute("pizzas");
                        if (pizzas != null) {
                            for (Pizza pizza : pizzas) {
                        %>
                                    
                                  <div class="card" style="margin-top: 20px">
                                    <div class="card-body">
                                      <h5 class="card-title"><%= pizza.getName() %></h5>
                                      <p class="card-text">
                                      <ul>
                                          <li><%= pizza.getSize()%></li>
                                          <li><%= pizza.getCrust() %></li>                               
                                          <li><%= pizza.getSauce() %></li>
                                          <li><%= pizza.getToppings() %></li>
                                          <li><%= pizza.isIncludeCheese() ? "Yes" : "No" %></li> 
                                        
                                          <li> 
                                              <div class="form-group">
                                            <label for="qty">Quantity</label>
                                            <div class="input-group">
    <button type="button" class="btn btn-secondary" onclick="decreaseQty(this)">-</button>
    <input type="number" class="form-control" id="qty" name="qty" value="1" min="1" readonly>
    <button type="button" class="btn btn-secondary" onclick="increaseQty(this)">+</button>
</div>
                                              </div>
                                          </li>
                                        <li id="unitPrice">PRICE :<%= pizza.getPrice() %></li>
                                          </ul>
                                          <hr>
                                           <div class="form-group">
                
                <input type="hidden" class="form-control" id="totalAmountInput" value="LKR.<%= pizza.getPrice() %>" readonly>
            </div>
                                          <h4>TOTAL: <b id="totalAmount">LKR.<%= pizza.getPrice()%></b></h4>
                                          
                                             <div class="row">
                                        <div class="col-md-6">
                                        <form action="order" method="POST" class="me-2">
                                          <input type="hidden" value="<%= pizza.getName() %>" name="namedelete">
                                          <input type="hidden" value="<%= session.getAttribute("userEmail") %>" name="emaildelete">
                                          <button type="submit" name="submit" value="DeletePizza" class="btn btn-danger" style="width:100%">Delete</button>
                                        </form>
                                              </div>
                                          <div class="col-md-6">
                                        <form action="order" method="POST">
                                          <input type="hidden" value="<%= pizza.getName() %>" name="namefav">
                                          <input type="hidden" value="<%= session.getAttribute("userEmail") %>" name="emailfav">
                                          <button type="submit" name="submit" value="UpdatePizza" class="btn btn-success" style="width:100%">Add To Favourite</button>
                                        </form>
                                          </div>
                                      </p>
                                      <hr>
                                      
                                       </div>
                                  </div>
                              
                                      
                        <% 
                            }
                        }
                        %>
                       
            </div>
        </div>
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Payment Method</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
            <form action="order" method="post">
                <%
                    String deliveryOption = request.getParameter("deliveryOption");
                %>
                <input type="hidden" class="form-control" id="deliveryOption" name="deliveryOption" value="${param.deliveryOption}">
                 <div class="form-group">
          <label for="nettotal">Net Total:</label>
          <input type="text" class="form-control" id="nettotal" name="nettotal" readonly>
        </div>
            <input type="hidden" value="<%= session.getAttribute("userEmail") %>" name="emailOrder" id="emailOrder">    
             <input type="hidden" value="<%= totalPrice %>">
            <div class="form-group">
      <label for="date">Date:</label>
      <input type="date" class="form-control" id="date" name="date" required>
    </div>
               <div class="form-group">
    <label for="address">Address:</label>
    <input type="text" class="form-control" id="address" name="address" value="<%= session.getAttribute("address") %>">
  </div>
              <hr>
            <div class="mb-3">
            <label for="paymentMethod" class="form-label">Payment Method:</label>
            <select class="form-control" id="paymentMethod" name="paymentMethod" required>
                <option value="" selected disabled>Select a payment method</option>
                <option value="creditCard">Credit Card</option>
                <option value="digitalWallet">Digital Wallet</option>
                <option value="loyaltyProgram">Loyalty Program</option>
            </select>
            <div class="invalid-feedback">
                Please select a payment method.
            </div>
        </div>
       
        <div id="creditCardDetails" class="mb-3" style="display:none;">
            <label for="cardNumber" class="form-label">Card Number:</label>
            <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder="Enter your card number" />
        </div>

        <div id="digitalWalletDetails" class="mb-3" style="display:none;">
            <label for="walletId" class="form-label">Wallet ID:</label>
            <input type="text" class="form-control" id="walletId" name="walletId" placeholder="Enter your wallet ID" />
        </div>

       
        <button type="submit" name="submit" class="btn btn-success" value="ProcessPayment" style="width:100%">Pay</button>
  
    </form>
      </div>
      
    </div>
  </div>
</div>
    </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
            <script>
                function updateNetTotal() {
    var totalAmountInputs = document.querySelectorAll('input[id="totalAmountInput"]'); // Get all the totalAmountInput fields
    var netTotal = 0;

    // Loop through each totalAmountInput field and add the amount to netTotal
    totalAmountInputs.forEach(function(input) {
        var amount = parseFloat(input.value.replace('LKR.', '').trim());
        if (!isNaN(amount)) {
            netTotal += amount;
        }
    });

    // Update the nettotal field in the modal with the calculated total
    var netTotalField = document.getElementById('nettotal');
    netTotalField.value = "LKR." + netTotal.toFixed(2); 
}

             function updateTotal(qtyInput) {
    // Get the quantity and unit price
    var qty = parseInt(qtyInput.value, 10);
    var card = qtyInput.closest('.card'); 
    var unitPrice = parseFloat(card.querySelector("#unitPrice").textContent.replace('PRICE :', '').trim());
    var totalAmountElement = card.querySelector("#totalAmount");
    var totalAmountInput = card.querySelector("#totalAmountInput"); 

    // Calculate and update the total amount
    var totalAmount = qty * unitPrice;
    totalAmountElement.textContent = "LKR." + totalAmount.toFixed(2);
    totalAmountInput.value = "LKR." + totalAmount.toFixed(2); 
}

function increaseQty(button) {
    var qtyInput = button.closest('.input-group').querySelector('#qty');
    var qty = parseInt(qtyInput.value, 10);
    qtyInput.value = qty + 1;
    updateTotal(qtyInput);
}

function decreaseQty(button) {
    var qtyInput = button.closest('.input-group').querySelector('#qty');
    var qty = parseInt(qtyInput.value, 10);
    if (qty > 1) {
        qtyInput.value = qty - 1;
        updateTotal(qtyInput);
    }
}

const paymentMethodSelect = document.querySelector('[name="paymentMethod"]');
    paymentMethodSelect.addEventListener('change', () => {
        document.getElementById('creditCardDetails').style.display = paymentMethodSelect.value === 'creditCard' ? 'block' : 'none';
        document.getElementById('digitalWalletDetails').style.display = paymentMethodSelect.value === 'digitalWallet' ? 'block' : 'none';
    });
            </script>    
    
    </body>
</html>
