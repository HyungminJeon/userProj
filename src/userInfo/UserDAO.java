package userInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	
	
	public int deleteFile(int num) {
		
		conn = DBCon.getConnect();
		String sql = "delete from file_board where num = ?";
		int r= 0;
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, num);
			r = psmt.executeUpdate();
			System.out.println(r + "건 삭제됨");

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	
	
	public List<UserVO> getUserList() {
		conn = DBCon.getConnect();
		List<UserVO> list = new ArrayList<UserVO>();
		try {
			psmt = conn.prepareStatement("select * from user_temp");
			rs = psmt.executeQuery();
			while (rs.next()) {
				UserVO vo = new UserVO();
				vo.setUser_id(rs.getString("user_id"));
				vo.setUser_name(rs.getString("user_name"));
				vo.setUser_pass(rs.getString("user_pass"));
				vo.setUser_phone(rs.getString("user_phone"));
				vo.setUser_gender(rs.getString("user_gender"));
				list.add(vo);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(psmt!=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		}
		return list;
	}
	
	
	public UserVO insertEmpBySeq(UserVO vo) {
		conn = DBCon.getConnect();
		//신규번호, 한건 입력, 한건 조회  //num열에서  제일 최고 값을 가져오고 그 값이 0인경우에는 1을 더하겠다
		String insertSql = "insert into user_temp(user_id, user_name, user_phone, user_gender) values (?, ?, ?, ?)";
		String selectSql = "select * from user_temp where user_id = ?";
		UserVO file = new UserVO();
		String key = vo.getUser_id();
		
		try {
			//새로운 키값으로 한건 입력
			psmt = conn.prepareStatement(insertSql);
			psmt.setString(1, key);
			psmt.setString(2, vo.getUser_name());
			psmt.setString(3, vo.getUser_phone());
			psmt.setString(4, vo.getUser_gender());
			int r = psmt.executeUpdate();
			System.out.println(r +"건 입력.");
		
			//신규입력된 전체정보 가져오도록.
			psmt = conn.prepareStatement(selectSql);
			psmt.setString(1, key);
			rs = psmt.executeQuery();
			if (rs.next()) {
				file.setUser_name(rs.getString("user_name"));
				file.setUser_phone(rs.getString("user_phone"));
				file.setUser_gender(rs.getString("user_gender"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(psmt!=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return file;
		
	}
	
	
	public UserVO getInsertKeyVal(UserVO vo) {
		conn = DBCon.getConnect();
		//신규번호, 한건 입력, 한건 조회
		String selectKey = "select nvl(max(user_id),0)+1 from user_temp";  //num열에서  제일 최고 값을 가져오고 그 값이 0인경우에는 1을 더하겠다
		String insertSql = "insert into user_temp(user_id, user_name, user_pass, user_phone, user_gender) values (?, ?, ?, ?, ?)";
		String selectSql = "select * from user_temp where num = ?";
		UserVO file = new UserVO();
		int key = 0;
		
		try {
			psmt = conn.prepareStatement(selectKey);
			rs = psmt.executeQuery();
			if(rs.next()) {
				key = rs.getInt(1);
			}
			//새로운 키값으로 한건 입력
			psmt = conn.prepareStatement(insertSql);
			psmt.setInt(1, key);
			psmt.setString(2, vo.getUser_name());
			psmt.setString(3, vo.getUser_pass());
			psmt.setString(4, vo.getUser_phone());
			psmt.setString(5, vo.getUser_gender());
			int r = psmt.executeUpdate();
			System.out.println(r +"건 입력.");
		
			//신규입력된 전체정보 가져오도록.
			psmt = conn.prepareStatement(selectSql);
			psmt.setInt(1, key);
			rs = psmt.executeQuery();
			if (rs.next()) {
				file.setUser_id(rs.getString("user_id"));
				file.setUser_name(rs.getString("user_name"));
				file.setUser_pass(rs.getString("user_pass"));
				file.setUser_phone(rs.getString("user_phone"));
				file.setUser_gender(rs.getString("user_gender"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(psmt!=null) {
				try {
					psmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		return file;
		
	}
	
	public boolean updateFile(UserVO vo) {
		
		conn = DBCon.getConnect();
		int modifyCnt = 0;
		
		String sql = "update user_temp "
				+ "set user_phone = ?"
				+ "where user_id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUser_phone());
			psmt.setString(2, vo.getUser_id());
			modifyCnt = psmt.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return modifyCnt == 0 ? false : true;
		
	}
	
	public void close() {
		
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(psmt!=null) {
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	

}
