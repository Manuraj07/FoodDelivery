package com.food.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.food.dao.CartItem;
import com.food.daoimpl.OrderDAOImpl;
import com.food.model.Cart;
import com.food.model.Order;
import com.food.model.User;


@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private OrderDAOImpl orderDAOImpl;
	@Override
	public void init() throws ServletException {
		orderDAOImpl = new OrderDAOImpl();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		Cart cart = (Cart)session.getAttribute("cart");
		User user = (User)session.getAttribute("user");
		
		
		
		if(cart!=null && user!=null && !cart.getItem().isEmpty()) {
			String paymentMethod = request.getParameter("PaymentMethod");
			
			Order order = new Order();
			order.setUserId(user.getUserId());
			order.setRestaurantId((int)session.getAttribute("restaurantId"));
			order.setOrderDate(new Date());
			order.setPaymentMethod(paymentMethod);
			order.setStatus("Pending");
			
			System.out.println(order);
			double totalAmount=0;
			for(CartItem item : cart.getItem().values()) {
				
				totalAmount += item.getPrice() * item.getQuantity();
			}
			order.setTotalAmount(totalAmount);
			
			orderDAOImpl.addOrder(order);
			System.out.print(order);
			
			session.setAttribute("orders",order);
			response.sendRedirect("JSP/confirmation.jsp");
		}else {
			response.sendRedirect("JSP/cart.jsp");
		}
	}
	

}
