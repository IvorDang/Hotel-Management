package view.booking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.Client;
import model.Room;
import model.User;

public class SearchClientFrm extends JFrame implements ActionListener {
    private JTextField txtKey;
    private JButton btnSearch, btnSelect, btnBack;
    private JTable tblResult;
    private DefaultTableModel tmClient;
    
    // Lưu các biến của tiến trình Booking
    private User user;
    private Room room;
    private String checkin, checkout;
    private java.util.ArrayList<Client> listClient;

    public SearchClientFrm(User user, Room room, String checkin, String checkout) {
        super("Search Client");
        this.user = user;
        this.room = room;
        this.checkin = checkin;
        this.checkout = checkout;

        JPanel pnMain = new JPanel(new BorderLayout());

        // --- TOP: Nhập tên/SĐT khách ---
        JPanel pnTop = new JPanel(new FlowLayout());
        pnTop.add(new JLabel("Tên hoặc SĐT:"));
        txtKey = new JTextField(20);
        pnTop.add(txtKey);
        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);
        pnTop.add(btnSearch);
        pnMain.add(pnTop, BorderLayout.NORTH);

        // --- CENTER: Bảng kết quả ---
        String[] columnNames = {"No", "Name", "ID Card", "Tel", "Address"};
        tmClient = new DefaultTableModel(columnNames, 0);
        tblResult = new JTable(tmClient);
        pnMain.add(new JScrollPane(tblResult), BorderLayout.CENTER);

        // --- BOTTOM: Nút bấm ---
        JPanel pnBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSelect = new JButton("Select Client");
        btnSelect.addActionListener(this);
        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
        pnBottom.add(btnSelect);
        pnBottom.add(btnBack);
        pnMain.add(pnBottom, BorderLayout.SOUTH);

        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setContentPane(pnMain);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSearch) {
            String key = txtKey.getText();
            tmClient.setRowCount(0);
            
            // Gọi ClientDAO
            dao.ClientDAO clientDAO = new dao.ClientDAO();
            listClient = clientDAO.searchClient(key);
            
            int stt = 1;
            for (Client c : listClient) {
                tmClient.addRow(new Object[]{
                    stt++, c.getName(), c.getIdcard(), c.getTel(), c.getAddress()
                });
            }
        } else if (e.getSource() == btnSelect) {
            int selectedRow = tblResult.getSelectedRow();
            if (selectedRow < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 khách hàng!");
                return;
            }
            Client selectedClient = listClient.get(selectedRow);
            
            // TODO: Chuyển sang form Lưu Hóa Đơn (Confirm Booking)
            JOptionPane.showMessageDialog(this, "Đã chọn khách: " + selectedClient.getName() + ". Chuyển sang Confirm Booking!");
            
        } else if (e.getSource() == btnBack) {
            // Quay lại tìm phòng
            (new SearchFreeRoomFrm(user)).setVisible(true);
            this.dispose();
        }
    }
}