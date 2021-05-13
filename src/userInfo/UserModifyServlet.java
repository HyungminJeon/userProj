package userInfo;


import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;


@WebServlet("/userModifyServlet")
public class UserModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserModifyServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		System.out.println("doPost call()");
		
		String id = req.getParameter("id");    // getParameter("last_name") 의  last_name은 form의 name부분과 똑같이 적어줘야 함
		String name = req.getParameter("name"); 
		String pwd = req.getParameter("pwd");
		String gender = req.getParameter("gender");
		String tel = req.getParameter("tel");
		
		
		UserVO user = new UserVO();
		user.setUser_id(id);
		user.setUser_name(name);
		user.setUser_pass(pwd);
		user.setUser_gender(gender);
		user.setUser_phone(tel);
		
		
		UserDAO dao = new UserDAO();
		
		JSONObject obj = new JSONObject();
		if(dao.updateFile(user)) {
			obj.put("retCode" ,"Success");
			
		} else {
			obj.put("retCode","Fail");
		}
		
		response.getWriter().print(obj);
		
	}

}
