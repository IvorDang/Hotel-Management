package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class StatDAO extends DAO {
    public StatDAO() {
        super();
    }

    // Thống kê tổng doanh thu theo loại phòng
    public ArrayList<Object[]> getRevenueByRoomType() {
        ArrayList<Object[]> listStat = new ArrayList<>();
        
        // Truy vấn JOIN 2 bảng, tính tổng (giá - giảm giá), gom nhóm theo loại phòng
        String sql = "SELECT r.type AS RoomType, SUM(br.price - br.saleoff) AS TotalRevenue "
                   + "FROM tblBookedRoom br "
                   + "JOIN tblRoom r ON br.idRoom = r.id "
                   + "GROUP BY r.type "
                   + "ORDER BY TotalRevenue DESC";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Object[] row = new Object[2];
                row[0] = rs.getString("RoomType");     // Cột 1: Loại phòng
                row[1] = rs.getFloat("TotalRevenue");  // Cột 2: Tổng doanh thu
                listStat.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listStat;
    }
}