package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Hotel;

public class HotelDAO extends DAO {
    
    public HotelDAO() {
        super();
    }

    // Hàm tìm kiếm khách sạn theo tên (hoặc để trống để lấy tất cả)
    public ArrayList<Hotel> searchHotel(String key) {
        ArrayList<Hotel> listHotel = new ArrayList<>();
        String sql = "SELECT * FROM tblHotel WHERE name LIKE ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, "%" + key + "%");
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                Hotel h = new Hotel();
                h.setId(rs.getInt("id"));
                h.setName(rs.getString("name"));
                h.setAddress(rs.getString("address"));
                h.setStar(rs.getInt("star"));
                h.setDes(rs.getString("des"));
                listHotel.add(h);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listHotel;
    }
}