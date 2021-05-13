package userInfo;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


/**
 * Servlet implementation class GetFileListServlet
 */
@WebServlet("/getUserListServlet")
public class GetUserListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetUserListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// json 데이터... DAO: 전체리스트 메소드.
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		UserDAO dao = new UserDAO();
		JSONArray ary = new JSONArray();

		List<UserVO> list = dao.getUserList();

		for (UserVO vo : list) {
			JSONObject obj = new JSONObject();
			obj.put("id", vo.getUser_id());
			obj.put("name",vo.getUser_name());
			obj.put("pwd", vo.getUser_pass());
			obj.put("tel", vo.getUser_phone());
			obj.put("gender", vo.getUser_gender());
			ary.add(obj);
		}
		
		response.getWriter().print(ary);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
