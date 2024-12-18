package controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Builder.Order;
import dao.AdminDao;
import model.state.OrderContext;  // Importing the correct OrderContext

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private final AdminDao adminDao = new AdminDao();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String formAction = request.getParameter("submit");

        // Retrieve the OrderContext from the session, or create it if not present
        OrderContext orderContext = new OrderContext();
        if (orderContext == null) {
            orderContext = new OrderContext();
            request.getSession().setAttribute("orderContext", orderContext);
        }
        try {
            if ("PreparedState".equals(formAction)) {
                changeState(request, response, orderContext); 
            } else if ("PlacedState".equals(formAction)) {
                placedState(request, response, orderContext); 
            }  else if ("PrepState".equals(formAction)) {
                prepState(request, response, orderContext); 
            }  else if ("OutState".equals(formAction)) {
                outState(request, response, orderContext); 
            }else {
                showBuilderForm(request, response, orderContext); 
            }
        } catch (Exception ex) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in doGet", ex);
            request.setAttribute("errorMessage", "Unable to load admin data: " + ex.getMessage());
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

   private void showBuilderForm(HttpServletRequest request, HttpServletResponse response, OrderContext orderContext)
        throws ServletException, IOException {
    try {
        System.out.println("Current State: " + orderContext.getCurrentState());
        List<Order> orders = adminDao.selectAllOrders(orderContext.getCurrentState());
        
        int placeStateCount = adminDao.selectOrderCount("Placed");
        int inPlaceStateCount = adminDao.selectOrderCount("In_Preparation");
        int outForOrderStateCount = adminDao.selectOrderCount("Out_for_Delivery");
        
        request.setAttribute("orders", orders);
        request.setAttribute("totalOrders", orders.size());

        // Set the order counts as request attributes
        request.setAttribute("placeStateCount", placeStateCount);
        request.setAttribute("inPlaceStateCount", inPlaceStateCount);
        request.setAttribute("outForOrderStateCount", outForOrderStateCount);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    } catch (Exception ex) {
        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in showBuilderForm", ex);
        request.setAttribute("errorMessage", "Unable to load orders: " + ex.getMessage());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}

private void changeState(HttpServletRequest request, HttpServletResponse response, OrderContext orderContext)
        throws ServletException, IOException {
    try {
        // Get order counts from database
        int placeStateCount = adminDao.selectOrderCount("Placed");
        int inPlaceStateCount = adminDao.selectOrderCount("In_Preparation");
        int outForOrderStateCount = adminDao.selectOrderCount("Out_for_Delivery");

        // Parse the delivery option ID
        String deliveryOption = request.getParameter("txtid");
        if (deliveryOption == null || deliveryOption.isEmpty()) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Delivery option ID is missing");
            request.setAttribute("errorMessage", "Delivery option ID is required.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        int deliveryOptionId;
        try {
            deliveryOptionId = Integer.parseInt(deliveryOption);
        } catch (NumberFormatException e) {
            Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Invalid delivery option ID: " + deliveryOption, e);
            request.setAttribute("errorMessage", "Invalid ID: Please provide a valid number.");
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Debug: Log current state
        System.out.println("Current State: " + orderContext.getCurrentState());

        // Transition to the next state
        orderContext.nextState();

        // Debug: Log state after transition
        System.out.println("State after transition: " + orderContext.getCurrentState());

        // Update pizza order state in the database
        adminDao.updatePizza(deliveryOptionId, orderContext.getCurrentState());

        // Retrieve updated list of orders
        List<Order> orders = adminDao.selectAllOrders(orderContext.getCurrentState());
        System.out.println(orders);

        // Set attributes for JSP
        request.setAttribute("orders", orders);
        request.setAttribute("totalOrders", orders.size());
        request.setAttribute("placeStateCount", placeStateCount);
        request.setAttribute("inPlaceStateCount", inPlaceStateCount);
        request.setAttribute("outForOrderStateCount", outForOrderStateCount);

        // Forward to JSP
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    } catch (Exception ex) {
        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in preparedState", ex);
        request.setAttribute("errorMessage", "Unable to load orders: " + ex.getMessage());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}


private void placedState(HttpServletRequest request, HttpServletResponse response, OrderContext orderContext)
        throws ServletException, IOException {
    try {
        List<Order> orders = adminDao.selectAllOrders("Placed");
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("placedadmin.jsp");
        dispatcher.forward(request, response);
    } catch (Exception ex) {
        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in preparedState", ex);
        request.setAttribute("errorMessage", "Unable to load orders: " + ex.getMessage());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}

private void prepState(HttpServletRequest request, HttpServletResponse response, OrderContext orderContext)
        throws ServletException, IOException {
    try {
        List<Order> orders = adminDao.selectAllOrders("InPreparation");
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Prepadmin.jsp");
        dispatcher.forward(request, response);
    } catch (Exception ex) {
        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in preparedState", ex);
        request.setAttribute("errorMessage", "Unable to load orders: " + ex.getMessage());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}

private void outState(HttpServletRequest request, HttpServletResponse response, OrderContext orderContext)
        throws ServletException, IOException {
    try {
        List<Order> orders = adminDao.selectAllOrders("Out for Delivery");
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Outadmin.jsp");
        dispatcher.forward(request, response);
    } catch (Exception ex) {
        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in preparedState", ex);
        request.setAttribute("errorMessage", "Unable to load orders: " + ex.getMessage());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
    }
}


}
