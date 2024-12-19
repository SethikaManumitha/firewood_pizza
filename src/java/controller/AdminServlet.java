    package controller;

   
    import java.util.List;
    import java.util.logging.Level;
    import java.util.logging.Logger;
    import javax.servlet.RequestDispatcher;
    
    import model.Builder.Order;
    import dao.AdminDao;
    import model.state.*;  
    import model.observer.*;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;
    import java.io.IOException;
    import java.io.PrintWriter;
    import java.util.concurrent.ConcurrentHashMap;
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
            String state = request.getParameter("currentState"); 
            OrderContext orderContext = new OrderContext();

            try {    
                if ("ChangeState".equals(formAction)) {
                    changeState(request, response, orderContext);
                }
                selectState(request, response, orderContext, state);
            } catch (Exception ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in doGet", ex);
                request.setAttribute("errorMessage", "Unable to load admin data: " + ex.getMessage());
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

        private void selectState(HttpServletRequest request, HttpServletResponse response, OrderContext orderContext, String state)
        throws ServletException, IOException {

    try {
        orderContext.setState(new PlacedState());
        List<Order> placedList = adminDao.selectAllOrders(orderContext.getStatus());

        orderContext.setState(new InPreparationState());
        List<Order> inPreparationList = adminDao.selectAllOrders(orderContext.getStatus());

        orderContext.setState(new OutForDeliveryState());
        List<Order> outForOrderList = adminDao.selectAllOrders(orderContext.getStatus());

        if (state == null || state.isEmpty()) {
            state = "Placed";
        }

        switch (state) {
            case "InPreparation":
                request.setAttribute("orders", inPreparationList);
                forwardRequest(request, response, "Prepadmin.jsp");
                break;

            case "OutForDelivery":
                request.setAttribute("orders", outForOrderList);
                forwardRequest(request, response, "Outadmin.jsp");
                break;

            default:
                request.setAttribute("orders", placedList);
                forwardRequest(request, response, "placedadmin.jsp");
                break;
        }

        

    } catch (Exception ex) {
        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in selectState", ex);
        handleError(response, "Error in selectState: " + ex.getMessage());
    }
}

private void changeState(HttpServletRequest request, HttpServletResponse response, OrderContext orderContext)
        throws ServletException, IOException {

    try {
        String currentState = request.getParameter("currentState");
        String deliveryOption = request.getParameter("txtid");
        
        String email = request.getParameter("email");
        String name = request.getParameter("name");

        if (deliveryOption == null || deliveryOption.isEmpty()) {
            throw new IllegalArgumentException("Delivery option ID is missing");
        }

        int orderId = Integer.parseInt(deliveryOption);

        switch (currentState) {
            case "Placed":
                orderContext.setState(new PlacedState());
                break;
            case "InPreparation":
                orderContext.setState(new InPreparationState());
                break;
            case "OutForDelivery":
                orderContext.setState(new OutForDeliveryState());
                break;
            default:
                throw new ServletException("Invalid state: " + currentState);
        }

        orderContext.processOrder();
        System.out.println(orderContext.getStatus());
        adminDao.updatePizza(orderId, orderContext.getStatus());
        
        OrderObserver order = new OrderObserver(orderId);
        User user = new User(name,email);
        order.setUser(user);
        
        String notification = order.changeStatus(orderContext.getStatus());
        adminDao.insertNotification(email, notification);
        selectState(request, response, orderContext, orderContext.getStatus());

    } catch (NumberFormatException e) {
        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Invalid delivery option ID", e);
        handleError(response, "Invalid delivery option ID: " + e.getMessage());
    } catch (Exception ex) {
        Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, "Error in changeState", ex);
        handleError(response, "Unable to process order state change: " + ex.getMessage());
    }
}

private void forwardRequest(HttpServletRequest request, HttpServletResponse response, String path)
        throws ServletException, IOException {
    if (!response.isCommitted()) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(path);
        dispatcher.forward(request, response);
    }
}

private void handleError(HttpServletResponse response, String errorMessage) throws IOException {
    if (!response.isCommitted()) {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, errorMessage);
    }
}

    }
