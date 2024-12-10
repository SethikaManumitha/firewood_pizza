$(document).ready(function() {

    let totalAmount = 0;
    let netTotal = 0;
    let quantity = 0;
    let selectedSize = "";
    let selectedCrust = "";
    let selectedSauce = "";
    let selectedToppings = [];
    
    


    function calculateTotal() {
    totalAmount = 0;

    // Add the price of the pizza size
    selectedSize = $('input[name="size"]:checked').next('label').text();
    let sizePrice = parseFloat($('input[name="size"]:checked').data('price') || 0);
    totalAmount += sizePrice;

    // Add the price of the crust
    selectedCrust = $('input[name="crust"]:checked').next('label').text();
    let crustPrice = parseFloat($('input[name="crust"]:checked').data('price') || 0);
    totalAmount += crustPrice;

    // Add the price of the selected toppings
    selectedToppings = [];
    $('input[name="topping"]:checked').each(function() {
        selectedToppings.push($(this).next('label').text());
        totalAmount += parseFloat($(this).data('price') || 0);
    });

    $('#totalAmountField').val(totalAmount);
    selectedSauce = $('input[name="sauce"]:checked').next('label').text();
    quantity = parseInt($('#txtqty').val());
    totalAmount *= quantity;

    // Update button and total amount
    $('#addToCartBtn').text('Add to Cart - LKR ' + totalAmount.toFixed(2));
    $('#totalAmount').text(totalAmount.toFixed(2));

    // Assign the total to the text field
    
}


    // Dropdown button click event
    $(".dropdown-btn").click(function() {
        var dropdownContainer = $(this).next(".dropdown-container");

        $(this).toggleClass("active");
        dropdownContainer.toggleClass("active");
        dropdownContainer.slideToggle();
    });
    
    $('#decrementQty').click(function() {
    let currentQty = parseInt($('#txtqty').val());
    if (currentQty > 1) {
        $('#txtqty').val(currentQty - 1);
        calculateTotal();
    }
});

$('#incrementQty').click(function() {
    let currentQty = parseInt($('#txtqty').val());
    $('#txtqty').val(currentQty + 1);
    calculateTotal();
});
    

    // Size change event
    $('input[name="size"]').change(function() {
        selectedSize = $('input[name="size"]:checked').next('label').text();
        $('#sizeDropdown').html(selectedSize + ' <i class="fas fa-chevron-down"></i>');
        calculateTotal();
    });

    // Crust change event
    $('input[name="crust"]').change(function() {
        selectedCrust = $('input[name="crust"]:checked').next('label').text();
        $('#crustDropdown').html(selectedCrust + ' <i class="fas fa-chevron-down"></i>');
        calculateTotal();
    });

    // Sauce change event
    $('input[name="sauce"]').change(function() {
        selectedSauce = $('input[name="sauce"]:checked').next('label').text();
        $('#sauceDropDown').html(selectedSauce + ' <i class="fas fa-chevron-down"></i>');
        calculateTotal();
    });

    // Topping change event
    $('input[name="topping"]').change(function() {
        selectedToppings = [];
    $('input[name="topping"]:checked').each(function() {
        selectedToppings.push($(this).next('label').text());
    });
    let toppingText = selectedToppings.join(', ') || "Select Topping";
    $('#toppingDropdown').html(toppingText + ' <i class="fas fa-chevron-down"></i>');
    calculateTotal();
    });

    $('#addToCartBtn').click(function () {
        let pizzaName = $('#txtname').val() || "Unnamed Pizza";
        let toppingList = selectedToppings.length > 0 ? selectedToppings.join(', ') : "No toppings";
        let pizzaPrice = totalAmount;

        if (pizzaPrice > 0) {
            netTotal += pizzaPrice;

            let pizzaCard = `
                <div class="basket-card">
                    <div class="details">
                        <div class="row">
                            <div class="col-md-6">
                                <h4>${pizzaName}</h4>
                            </div>
                            <div class="col-md-6">
                                <div class="actions">
                                    <button class="favorite"><i class="fas fa-heart"></i></button>
                                    <button class="delete"><i class="fas fa-trash-alt"></i></button>
                                </div>
                            </div>
                            <div style="margin-left:15px;">
                                <p><b>Size:</b> ${selectedSize}, <b>Crust:</b> ${selectedCrust}, <b>Sauce:</b> ${selectedSauce}</p>
                                <p><b>Toppings:</b> ${toppingList}</p>
                                <p><b>Include Cheese:</b> Yes</p>
                                <p><b>QTY:</b> ${quantity}</p>
                                <p><b>Price:</b> LKR ${pizzaPrice.toFixed(2)}</p>
                            </div>
                        </div>
                    </div>
                </div>
            `;

            $('#basketList').append(pizzaCard);
            $('#totalAmount').text(netTotal.toFixed(2));

            totalAmount = 0;
            selectedSize = selectedCrust = selectedSauce = "";
            selectedToppings = [];

            $('#addToCartBtn').text('Add to Cart - LKR 0');
            $('#sizeDropdown, #crustDropdown, #sauceDropDown, #toppingDropdown').html('Select <i class="fas fa-chevron-down"></i>');
            $('input[name="size"], input[name="crust"], input[name="sauce"], input[name="topping"]').prop('checked', false);
        } else {
            alert("Please complete your pizza selection before adding to cart.");
        }
    });

    // Event listener for delete button
    $('#basketList').on('click', '.delete', function () {
        let price = parseFloat($(this).closest('.basket-card').find('.details p:last-child').text().replace('Price: LKR ', ''));
        netTotal -= price;
        $('#totalAmount').text(netTotal.toFixed(2));
        $(this).closest('.basket-card').remove();
    });

    // Event listener for favorite button
    $('#basketList').on('click', '.favorite', function () {
        $(this).find('i').toggleClass('fas far');
    });
    
    $('#buildPizzaBtn').click(function() {
    calculateTotal();
    // Other actions can be added here
    });

});


