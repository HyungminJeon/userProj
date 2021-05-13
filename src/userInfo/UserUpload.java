package userInfo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;



/**
 * Servlet implementation class UserUpload
 */
@WebServlet("/userUpload")
public class UserUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserUpload() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		System.out.println("doPost call()");
		
		String id = req.getParameter("id");    // getParameter("last_name") 의  last_name은 form의 name부분과 똑같이 적어줘야 함
		String name = req.getParameter("name"); 
		String gender = req.getParameter("gender");
		String tel = req.getParameter("tel");
		
		
		UserDAO dao = new UserDAO();
		
		UserVO user = new UserVO();
		user.setUser_id(id);
		user.setUser_name(name);
		user.setUser_gender(gender);
		user.setUser_phone(tel);
		
		
		
		UserVO rvo = dao.insertEmpBySeq(user);
		
		//가져온 값을 json 형식으로 생성. {"num":?, "author":?, "title":?}
		JSONObject obj = new JSONObject();
		obj.put("id", rvo.getUser_id());
		obj.put("name", rvo.getUser_name());
		obj.put("tel", rvo.getUser_phone());
		obj.put("gender", rvo.getUser_gender());
		
		response.getWriter().print(obj);
	}
	

}
