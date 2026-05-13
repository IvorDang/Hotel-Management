package view.manager;

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
import javax.swing.table.DefaultTableModel;
import model.User;
import view.user.ManagerHomeFrm;

public class ViewStatisticFrm extends JFrame implements ActionListener {
    private JTable tblStat;
    private DefaultTableModel tmStat;
    private JButton btnBack;
    private User user;

    public ViewStatisticFrm(User user) {
        super("Statistic - Revenue by Room Type");
        this.user = user;

        JPanel pnMain = new JPanel(new BorderLayout());

        // --- TOP: Tiêu đề ---
        JPanel pnTop = new JPanel();
        JLabel lblTitle = new JLabel("BÁO CÁO DOANH THU THEO LOẠI PHÒNG");
        lblTitle.setFont(lblTitle.getFont().deriveFont(18.0f));
        pnTop.add(lblTitle);
        pnMain.add(pnTop, BorderLayout.NORTH);

        // --- CENTER: Bảng dữ liệu ---
        String[] columnNames = {"STT", "Loại phòng (Type)", "Tổng Doanh Thu ($)"};
        tmStat = new DefaultTableModel(columnNames, 0);
        tblStat = new JTable(tmStat);
        pnMain.add(new JScrollPane(tblStat), BorderLayout.CENTER);

        // --- BOTTOM: Nút Back ---
        JPanel pnBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBack = new JButton("Quay lại (Back)");
        btnBack.addActionListener(this);
        pnBottom.add(btnBack);
        pnMain.add(pnBottom, BorderLayout.SOUTH);

        // NẠP DỮ LIỆU TỰ ĐỘNG KHI MỞ FORM
        loadStatisticData();

        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    // Hàm gọi DAO và đổ lên bảng
    private void loadStatisticData() {
        dao.StatDAO statDAO = new dao.StatDAO();
        ArrayList<Object[]> listStat = statDAO.getRevenueByRoomType();
        
        tmStat.setRowCount(0);
        int stt = 1;
        for (Object[] row : listStat) {
            tmStat.addRow(new Object[]{
                stt++, 
                row[0].toString().toUpperCase(), 
                row[1] 
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            (new ManagerHomeFrm(user)).setVisible(true);
            this.dispose();
        }
    }
}