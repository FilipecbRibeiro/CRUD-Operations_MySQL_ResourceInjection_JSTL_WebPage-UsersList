package org.light.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.light.entity.User;
import org.light.model.UserModel;



//this controller is handling list user,user adding,and some more opeartions! 
@WebServlet("/operation")
public class OperationControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	@Resource(name = "jdbc/project")
	private DataSource dataSource;// connector to db

	
	/////////////////////////////////////////////////Deciding what forwarding page should be selected!
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String page = request.getParameter("page");
		
		page = page.toLowerCase();

		switch (page) {

		case "listuser":

			listUsers(request, response);
			break;
		case "adduser":

			addUserFormLoader(request, response);
			break;
			
		case "updateuser":
		
			updateUserform(request,response);
			break;
		case "deleteuser":
			deleteUser(Integer.parseInt(request.getParameter("usersId")));
			listUsers(request, response);
			break;
		default:
			errorPage(request, response);
			break;

		}
	}

	///////////////////////////////////////////////////////////AFTER COMING BACK FROM THE FORWARDING PAGES THIS SWITCH WILL
	//CALL THE MECHANISMS REQUIRED INTO DATABASE!
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//if we have multiple forms we should use identifiers inside form !
	String operation  = request.getParameter("form");
	operation= operation.toLowerCase();
	
	switch(operation) {
	case "adduseroperation":
							User newUser=new User(request.getParameter("username"),request.getParameter("email"));
							addUserDbOperation(newUser);
							listUsers(request, response);
							break;
	case "updateuseroperation":
					User updateUser= new User(Integer.parseInt(request.getParameter("usersId")),request.getParameter("username"),request.getParameter("email"));
					//Preparing to use this object to update the dataBase
					updatingUserOperation(updateUser);
					listUsers(request, response);		
					break;
	default:
		errorPage(request, response);
		break;
	}

	
	}
	
	
	
	////////////////////////////////////////////////////FORWARDING TO THE RIGHT PAGES///////////////////////////////////
	protected void addUserFormLoader(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("title", "Add User");
		request.getRequestDispatcher("addUser.jsp").forward(request, response);

	}
	private void updateUserform(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		request.setAttribute("title", "Update List of Users");
		request.getRequestDispatcher("updateUser.jsp").forward(request, response);
		
		
	}

	protected void errorPage(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("title", "Error file");
		request.getRequestDispatcher("error.jsp").forward(request, response);
	}
	private void deleteUser(int usersId) {
		new UserModel().deleteUser(usersId,dataSource);
		
	}
//////////////////////////////////////////////////////DATABASE RELATED METHODS ////////////////////////////////////////////

	private void addUserDbOperation(User newUser) {
		new UserModel().addUser(newUser,dataSource);
  		return;//this return will make the code comeback to the case "adduseroperation" 
		
	}
	private void updatingUserOperation( User updateUser) {
		new UserModel().updateUser(updateUser,dataSource);
		return; 
		
	}

	protected void listUsers(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<User> users = new ArrayList<User>();// preparing list to receive info coming from database
		users = new UserModel().listUsers(dataSource);
		request.setAttribute("listUsers", users);
		request.setAttribute("title", "List of users");
		request.getRequestDispatcher("userList.jsp").forward(request, response);
	}

	
}
