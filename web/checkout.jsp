<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.bundle.min.js"></script>
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">  
        <link rel="stylesheet" href="assets/css/checkout.css">
        <title>Checkout Page</title>
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

       
        <div class="container containerBox">
            <div class="row">
                <h1>Sender Details</h1>
            </div>
            <form>
                <div class="form-group">
                    <label for="senderName">Full Name</label>
                    <input type="text" class="form-control" id="senderName" placeholder="Enter your full name">
                </div>
                <div class="form-group">
                    <label for="senderEmail">Email</label>
                    <input type="email" class="form-control" id="senderEmail" placeholder="Enter your email">
                </div>
                <div class="form-group">
                    <label for="senderPhone">Phone</label>
                    <input type="text" class="form-control" id="senderPhone" placeholder="Enter your phone number">
                </div>
            </form>
        </div>

        <!-- Receiver Details -->
        <div class="container containerBox">
            <div class="row">
                <h1>Receiver Details</h1>
            </div>
            <form>
                <div class="form-group">
                    <label for="receiverName">Full Name</label>
                    <input type="text" class="form-control" id="receiverName" placeholder="Enter receiver's full name">
                </div>
                <div class="form-group">
                    <label for="receiverAddress">Address</label>
                    <textarea class="form-control" id="receiverAddress" rows="3" placeholder="Enter receiver's address"></textarea>
                </div>
                <div class="form-group">
                    <label for="receiverPhone">Phone</label>
                    <input type="text" class="form-control" id="receiverPhone" placeholder="Enter receiver's phone number">
                </div>
            </form>
        </div>

        <!-- Payment Options -->
        <div class="container containerBox">
            <div class="row">
                <h1>Payment Options</h1>
            </div>
            <form>
                <div class="form-group">
                    <label for="paymentMethod">Select Payment Method</label>
                    <select class="form-control" id="paymentMethod">
                        <option>Credit/Debit Card</option>
                        <option>PayPal</option>
                        <option>Bank Transfer</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="cardNumber">Card Number</label>
                    <input type="text" class="form-control" id="cardNumber" placeholder="Enter card number">
                </div>
                <div class="form-group">
                    <label for="expiryDate">Expiry Date</label>
                    <input type="text" class="form-control" id="expiryDate" placeholder="MM/YY">
                </div>
                <div class="form-group">
                    <label for="cvv">CVV</label>
                    <input type="password" class="form-control" id="cvv" placeholder="Enter CVV">
                </div>
            </form>
        </div>

        <div class="container">
        <div class="row">
            <div class="col-md-12">
                <button class="btn btn-success btnproceed">Proceed To Pay</button>
            </div>
        </div>
        </div>
    </body>
</html>
