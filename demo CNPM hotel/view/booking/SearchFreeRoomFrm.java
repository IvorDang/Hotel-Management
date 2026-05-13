package view.booking;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import model.User;
import view.user.SellerHomeFrm;

public class SearchFreeRoomFrm extends JFrame implements ActionListener {
    private JTextField txtCheckin, txtCheckout;
    private JButton btnSearch, btnBack;
    private JTable tblResult;
    private DefaultTableModel tmRoom;
    private User user;
    private JButton btnSelect; // Thêm nút Chọn phòng
    private java.util.ArrayList<model.Room> listRoom; // Biến toàn cục để lưu danh sách phòng tìm được

    public SearchFreeRoomFrm(User user) {
        super("Search Available Room");
        this.user = user;

        JPanel pnMain = new JPanel(new BorderLayout());

        // --- TOP PANEL: Nhập liệu ---
        JPanel pnTop = new JPanel(new FlowLayout());
        pnTop.add(new JLabel("Check-in (dd/mm/yyyy):"));
        txtCheckin = new JTextField(10);
        pnTop.add(txtCheckin);

        pnTop.add(new JLabel("Check-out (dd/mm/yyyy):"));
        txtCheckout = new JTextField(10);
        pnTop.add(txtCheckout);

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(this);
        pnTop.add(btnSearch);

        pnMain.add(pnTop, BorderLayout.NORTH);

        // --- CENTER PANEL: Bảng kết quả ---
        String[] columnNames = {"No", "Name", "Type", "Price", "Description"};
        tmRoom = new DefaultTableModel(columnNames, 0);
        tblResult = new JTable(tmRoom);
        JScrollPane scrollPane = new JScrollPane(tblResult);
        pnMain.add(scrollPane, BorderLayout.CENTER);

        // --- BOTTOM PANEL: Nút Quay lại ---
        btnSelect = new JButton("Select Room");
        btnSelect.addActionListener(this);
        JPanel pnBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        btnBack = new JButton("Back");
        btnBack.addActionListener(this);
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
            try {
                // 1. Ép kiểu chuỗi người dùng nhập (dd/MM/yyyy) sang đối tượng Date
                java.text.SimpleDateFormat sdfUI = new java.text.SimpleDateFormat("dd/MM/yyyy");
                sdfUI.setLenient(false);
                java.util.Date dateIn = sdfUI.parse(txtCheckin.getText());
                java.util.Date dateOut = sdfUI.parse(txtCheckout.getText());
                
                if (dateIn.compareTo(dateOut) >= 0) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Errow: Check-out date must be after Check-in date!", 
                        "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return; // Dừng thực thi lệnh
                }

                // 3. CHỐT CHẶN 3: Ngày nhận phòng không được ở trong quá khứ
                java.util.Date today = sdfUI.parse(sdfUI.format(new java.util.Date()));
                if (dateIn.before(today)) {
                    javax.swing.JOptionPane.showMessageDialog(this, 
                        "Error: Check-in date cannot be in the past!", 
                        "Warning", javax.swing.JOptionPane.WARNING_MESSAGE);
                    return; 
                }
                // 2. Xóa dữ liệu cũ trên bảng
                tmRoom.setRowCount(0);

                // 3. Gọi CSDL truyền vào đối tượng Date
                dao.RoomDAO roomDAO = new dao.RoomDAO();
                java.util.ArrayList<model.Room> listRoom = roomDAO.searchFreeRoom(dateIn, dateOut);
                
                // 4. Đổ dữ liệu thật lên bảng
                int stt = 1;
                for (model.Room r : listRoom) {
                    tmRoom.addRow(new Object[]{
                        stt++, 
                        r.getName(), 
                        r.getType(), 
                        r.getPrice(), 
                        r.getDes()
                    });
                    if(listRoom.isEmpty()) {
                     javax.swing.JOptionPane.showMessageDialog(this, "No available rooms found for the selected dates.");
                    }
                }
            } catch (java.text.ParseException ex) {
                // Bắt lỗi khi người dùng gõ sai định dạng (vd: chữ, ngày ảo như 30/02/2026)
                javax.swing.JOptionPane.showMessageDialog(this, 
                    "Please enter the correct date format: dd/MM/yyyy (e.g., 15/05/2026) and ensure the date exists!", 
                    "Date Format Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        } else if (e.getSource() == btnSelect) {
            // Xử lý khi bấm nút "Select Room"
            int selectedRow = tblResult.getSelectedRow();
            if (selectedRow < 0) {
                javax.swing.JOptionPane.showMessageDialog(this, "Please select a room from the table!");
                return;
            }
            
            // Lấy phòng đang được click
            model.Room selectedRoom = listRoom.get(selectedRow);
            
        } else if (e.getSource() == btnBack) {
            (new SellerHomeFrm(user)).setVisible(true);
            this.dispose();
        }
    }
}