package view.hotel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import dao.HotelDAO;
import model.Hotel;
import model.User;
import view.user.ManagerHomeFrm;

public class ManageHotelFrm extends JFrame implements ActionListener {
    private JTextField txtSearch;
    private JButton btnSearch, btnBack, btnEdit;
    private JTable tblHotel;
    private DefaultTableModel tmHotel;
    private User user;
    private ArrayList<Hotel> listHotel;

    public ManageHotelFrm(User user) {
        super("Quản lý Khách sạn");
        this.user = user;

        JPanel pnMain = new JPanel(new BorderLayout());

        // --- TOP: Khu vực tìm kiếm ---
        JPanel pnTop = new JPanel(new FlowLayout());
        pnTop.add(new JLabel("Nhập tên khách sạn: "));
        txtSearch = new JTextField(15);
        pnTop.add(txtSearch);
        
        btnSearch = new JButton("Tìm kiếm");
        btnSearch.addActionListener(this);
        pnTop.add(btnSearch);
        
        pnMain.add(pnTop, BorderLayout.NORTH);

        // --- CENTER: Bảng danh sách khách sạn ---
        String[] columnNames = {"ID", "Tên KS", "Địa chỉ", "Hạng sao", "Mô tả"};
        tmHotel = new DefaultTableModel(columnNames, 0);
        tblHotel = new JTable(tmHotel);
        pnMain.add(new JScrollPane(tblHotel), BorderLayout.CENTER);

        // --- BOTTOM: Nút điều hướng ---
        JPanel pnBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnEdit = new JButton("Chỉnh sửa (Edit)");
        btnEdit.addActionListener(this);
        
        btnBack = new JButton("Quay lại (Back)");
        btnBack.addActionListener(this);
        
        pnBottom.add(btnEdit);
        pnBottom.add(btnBack);
        pnMain.add(pnBottom, BorderLayout.SOUTH);

        // Tự động load tất cả khách sạn khi vừa mở form
        loadData("");

        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Hàm gọi DAO để đổ dữ liệu lên bảng
    private void loadData(String key) {
        HotelDAO hotelDAO = new HotelDAO();
        listHotel = hotelDAO.searchHotel(key);
        
        tmHotel.setRowCount(0); // Xóa dữ liệu cũ
        for (Hotel h : listHotel) {
            tmHotel.addRow(new Object[]{
                h.getId(), h.getName(), h.getAddress(), h.getStar(), h.getDes()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            // Lấy từ khóa và tìm kiếm
            String key = txtSearch.getText().trim();
            loadData(key);
        } 
        else if (e.getSource() == btnEdit) {
            int selectedRow = tblHotel.getSelectedRow();
            if (selectedRow < 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 khách sạn trong bảng để sửa!");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Chức năng Edit đang phát triển!");
                // Nếu muốn làm chức năng Sửa, bạn sẽ lấy ID khách sạn và mở EditHotelFrm ở đây
            }
        }
        else if (e.getSource() == btnBack) {
            // Trở về trang Manager Home
            (new ManagerHomeFrm(user)).setVisible(true);
            this.dispose();
        }
    }
}