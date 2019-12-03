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
	//Single-ton : ��ü1���� �����ؼ� ��������
	static VisitDAO single = null;

	public static VisitDAO getInstance() {
		if (single == null)
			single = new VisitDAO();
		return single;
	}
	
	//���� ��ȸ
	public List<VisitVO> selectList() {

		List<VisitVO> list = new ArrayList<VisitVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from visit order by idx desc";

		try {
			//1.Connection ������
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement(���ó����ü) ��� 
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter����

			//4.ResultSet(�����ó����ü) ���
			rs = pstmt.executeQuery();

			//5.��ü���ڵ� �о����
			while (rs.next()) {
				//1���� ������(���ڵ�)�� ���� ��ü  
				VisitVO vo = new VisitVO();

				//���緹�ڵ�->Vo����
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setContent(rs.getString("content"));
				vo.setPwd(rs.getString("pwd"));
				vo.setIp(rs.getString("ip"));
				vo.setRegdate(rs.getString("regdate"));
				//����Ʈ �߰�
				list.add(vo);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();

		} finally {

			try {
				//�ݱ�(��������)
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
	
	//idx�� �ش�Ǵ� �Խñ� 1��
	public VisitVO selectOne(int idx) {

		VisitVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "select * from visit where idx = ?";

		try {
			//1.Connection ������
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement(���ó����ü) ��� 
			pstmt = conn.prepareStatement(sql);

			//3.pstmt parameter����
			pstmt.setInt(1, idx);
			
			//4.ResultSet(�����ó����ü) ���
			rs = pstmt.executeQuery();

			//5.��ü���ڵ� �о����
			while (rs.next()) {
				//1���� ������(���ڵ�)�� ���� ��ü  
				vo = new VisitVO();

				//���緹�ڵ�->Vo����
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
				//�ݱ�(��������)
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
	
	//���� �߰�
	public int insert(VisitVO vo) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "insert into visit values(seq_visit_idx.nextval,?,?,?,?,sysdate)";

		try {
			//1.Connection���
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement���
			pstmt = conn.prepareStatement(sql);
			//3.pstmt's parameterä���
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getIp());
			//4.DB SQL ����
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(����)
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
	
	//����
	public int update(VisitVO vo) {
		int res = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "update visit set name=?, content=?, pwd=?, ip=?,regdate=sysdate where idx =?";
			

		try {
			//1.Connection���
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement���
			pstmt = conn.prepareStatement(sql);
			//3.pstmt's parameterä���
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getPwd());
			pstmt.setString(4, vo.getIp());
			pstmt.setInt(5, vo.getIdx());
			//4.DB SQL ����
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(����)
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
			//1.Connection���
			conn = DBService.getInstance().getConnection();
			//2.PreparedStatement���
			pstmt = conn.prepareStatement(sql);
			//3.pstmt's parameterä���
			pstmt.setInt(1, idx);
			//4.DB SQL ����
			res = pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			try {
				//�ݱ�(����)
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
