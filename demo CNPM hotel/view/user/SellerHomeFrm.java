package view.user;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.User;
import view.booking.SearchFreeRoomFrm;

public class SellerHomeFrm extends JFrame implements ActionListener {
    private JButton btnBookRoom;
    private JButton btnLogout;
    private User user;

    public SellerHomeFrm(User user) {
        super("Seller Home");
        this.user = user;

        JPanel pnMain = new JPanel();
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.PAGE_AXIS));
        pnMain.add(Box.createRigidArea(new Dimension(0, 20)));

        JLabel lblTitle = new JLabel("Welcome Seller: " + user.getName());
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTitle.setFont(lblTitle.getFont().deriveFont(20.0f));
        pnMain.add(lblTitle);
        pnMain.add(Box.createRigidArea(new Dimension(0, 30)));

        btnBookRoom = new JButton("Đặt phòng (Book Room)");
        btnBookRoom.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnBookRoom.addActionListener(this);
        pnMain.add(btnBookRoom);
        pnMain.add(Box.createRigidArea(new Dimension(0, 15)));

        btnLogout = new JButton("Đăng xuất");
        btnLogout.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogout.addActionListener(this);
        pnMain.add(btnLogout);

        this.setSize(400, 300);
        this.setLocationRelativeTo(null); // Hiển thị ra giữa màn hình
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBookRoom) {
            // Chuyển sang form tìm phòng trống
            (new SearchFreeRoomFrm(this.user)).setVisible(true);
            this.dispose();
        } else if (e.getSource() == btnLogout) {
            (new LoginFrm()).setVisible(true);
            this.dispose();
        }
    }
}