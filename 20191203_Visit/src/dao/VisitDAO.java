package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.DBService;
import vo.VisitVO;

public class VisitDAO {
	//Single-ton : 객체1개만 생성해서 서비스하자
	static VisitDAO single = null;

	public static VisitDAO getInstance() {
		if (single == null)
			single = new VisitDAO();
		return single;
	}
	
	//방명록 조회
	public List<VisitVO> selectList() {

		List<VisitVO> list = new ArrayList<VisitVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from visit order by idx desc";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement(명령처리객체) 얻기 
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter설정

			//4.ResultSet(결과행처리객체) 얻어
			rs = pstmt.executeQuery();

			//5.전체레코드 읽어오기
			while (rs.next()) {
				//1건의 데이터(레코드)를 담을 객체  
				VisitVO vo = new VisitVO();

				//현재레코드->Vo포장
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setPwd(rs.getString("pwd"));
				vo.setIp(rs.getString("ip"));
				vo.setRegdate(rs.getString("regdate"));
				//리스트 추가
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {

			try {
				//닫기(열린역순)
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return list;
	}
	
	//idx에 해당되는 게시글 1건
	public VisitVO selectOne(int idx) {

		VisitVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from visit where idx = ?";

		try {
			//1.Connection 얻어오기
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement(명령처리객체) 얻기 
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter설정
			pstmt.setInt(1, idx);
			
			//4.ResultSet(결과행처리객체) 얻어
			rs = pstmt.executeQuery();

			//5.전체레코드 읽어오기
			while (rs.next()) {
				//1건의 데이터(레코드)를 담을 객체  
				vo = new VisitVO();

				//현재레코드->Vo포장
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setPwd(rs.getString("pwd"));
				vo.setIp(rs.getString("ip"));
				vo.setRegdate(rs.getString("regdate"));
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {

			try {
				//닫기(열린역순)
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return vo;
	}
	
	//방명록 추가
	public int insert(VisitVO vo) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into visit values(seq_visit_idx.nextval,?,?,?,?,sysdate)";

		try {
			//1.Connection얻기
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement얻기
			pstmt = conn.prepareStatement(sql);
			//3.pstmt's parameter채우기
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getIp());
			//4.DB SQL 전송
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//닫기(역순)
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	
	//수정
	public int update(VisitVO vo) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update visit set name=?, content=?, pwd=?, ip=?,regdate=sysdate where idx =?";
			

		try {
			//1.Connection얻기
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement얻기
			pstmt = conn.prepareStatement(sql);
			//3.pstmt's parameter채우기
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getIp());
			pstmt.setInt(5, vo.getIdx());
			//4.DB SQL 전송
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//닫기(역순)
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	
	public int delete(int idx) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "delete from visit where idx = ?";

		try {
			//1.Connection얻기
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement얻기
			pstmt = conn.prepareStatement(sql);
			//3.pstmt's parameter채우기
			pstmt.setInt(1, idx);
			//4.DB SQL 전송
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//닫기(역순)
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
}
