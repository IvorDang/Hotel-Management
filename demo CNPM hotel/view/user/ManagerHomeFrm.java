package view.user;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.User;
import view.hotel.ManageHotelFrm;
import view.manager.ViewStatisticFrm;
import view.room.SearchRoomFrm;

public class ManagerHomeFrm extends JFrame implements ActionListener {
    private JButton btnHotelManage, btnRoomManage, btnStat, btnLogout;
    private User user;

    public ManagerHomeFrm(User user) {
        super("Manager Home");
        this.user = user;

        // Dùng BorderLayout làm khung chính
        JPanel pnMain = new JPanel(new BorderLayout());
        pnMain.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Căn lề 4 góc

        // 1. Tiêu đề
        JLabel lblTitle = new JLabel("Welcome Manager: " + user.getName(), JLabel.CENTER);
        lblTitle.setFont(lblTitle.getFont().deriveFont(20.0f));
        pnMain.add(lblTitle, BorderLayout.NORTH);

        // 2. Khu vực nút bấm (Dùng GridLayout để ép 4 nút bằng nhau tuyệt đối)
        JPanel pnButtons = new JPanel(new GridLayout(4, 1, 0, 15)); // 4 hàng, 1 cột, cách nhau 15px
        
        btnHotelManage = new JButton("Quản lý Khách sạn");
        btnRoomManage = new JButton("Quản lý Phòng");
        btnStat = new JButton("Xem Thống kê Doanh thu");
        btnLogout = new JButton("Đăng xuất");

        pnButtons.add(btnHotelManage);
        pnButtons.add(btnRoomManage);
        pnButtons.add(btnStat);
        pnButtons.add(btnLogout);

        // ĐĂNG KÝ SỰ KIỆN LẮNG NGHE (Có cái này bấm mới chạy)
        btnHotelManage.addActionListener(this);
        btnRoomManage.addActionListener(this);
        btnStat.addActionListener(this);
        btnLogout.addActionListener(this);

        // Đóng gói pnButtons vào một panel phụ để kiểm soát kích thước
        JPanel pnCenter = new JPanel();
        pnButtons.setPreferredSize(new Dimension(250, 200)); 
        pnCenter.add(pnButtons);
        pnCenter.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // Cách tiêu đề 30px

        pnMain.add(pnCenter, BorderLayout.CENTER);

        this.setSize(450, 350);
        this.setLocationRelativeTo(null); // Căn giữa màn hình
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == btnHotelManage) {
                // Đã bỏ thông báo MessageDialog, vào thẳng form Quản lý khách sạn
                (new ManageHotelFrm(user)).setVisible(true);
                this.dispose();
            } 
            else if (e.getSource() == btnRoomManage) {
                // Chuyển sang Quản lý phòng
                (new SearchRoomFrm(user)).setVisible(true);
                this.dispose();
            } 
            else if (e.getSource() == btnStat) {
                // Chuyển sang form Thống kê
                (new ViewStatisticFrm(user)).setVisible(true);
                this.dispose();
            } 
            else if (e.getSource() == btnLogout) {
                (new LoginFrm()).setVisible(true);
                this.dispose();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi mở chức năng: " + ex.getMessage());
        }
    }
}