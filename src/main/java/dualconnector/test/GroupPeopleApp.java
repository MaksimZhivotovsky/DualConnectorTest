//package dualconnector.test;
//
//import javax.swing.*;
//import javax.swing.event.ListSelectionEvent;
//import javax.swing.event.ListSelectionListener;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//
//public class GroupPeopleApp {
//    private JFrame frame;
//    private JTable groupTable;
//    private JTable peopleTable;
//    private DefaultTableModel groupTableModel;
//    private DefaultTableModel peopleTableModel;
//
//    public GroupPeopleApp() {
//        frame = new JFrame("Groups and People");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLayout(new GridLayout(1, 2));
//
//        // Создание модели и таблицы для групп
//        groupTableModel = new DefaultTableModel(new Object[]{"Group ID", "Group Name"}, 0);
//        groupTable = new JTable(groupTableModel);
//        groupTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//        groupTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                if (!e.getValueIsAdjusting()) {
//                    int selectedRow = groupTable.getSelectedRow();
//                    if (selectedRow != -1) {
//                        String groupId = groupTableModel.getValueAt(selectedRow, 0).toString();
//                        updatePeopleTable(groupId);
//                    }
//                }
//            }
//        });
//
//        // Заполнение таблицы групп
//        populateGroupTable();
//
//        // Создание модели и таблицы для людей
//        peopleTableModel = new DefaultTableModel(new Object[]{"Person ID", "Person Name"}, 0);
//        peopleTable = new JTable(peopleTableModel);
//
//        // Добавление таблиц в панель
//        frame.add(new JScrollPane(groupTable));
//        frame.add(new JScrollPane(peopleTable));
//
//        frame.setSize(800, 400);
//        frame.setVisible(true);
//    }
//
//    private void populateGroupTable() {
//        // Пример данных для групп
//        groupTableModel.addRow(new Object[]{"1", "Group A"});
//        groupTableModel.addRow(new Object[]{"2", "Group B"});
//        groupTableModel.addRow(new Object[]{"3", "Group C"});
//    }
//
//    private void updatePeopleTable(String groupId) {
//        // Очистка предыдущих данных
//        peopleTableModel.setRowCount(0);
//
//        // Пример данных для людей в зависимости от выбранной группы
//        switch (groupId) {
//            case "1":
//                peopleTableModel.addRow(new Object[]{"1", "Alice"});
//                peopleTableModel.addRow(new Object[]{"2", "Bob"});
//                break;
//            case "2":
//                peopleTableModel.addRow(new Object[]{"3", "Charlie"});
//                peopleTableModel.addRow(new Object[]{"4", "David"});
//                break;
//            case "3":
//                peopleTableModel.addRow(new Object[]{"5", "Eve"});
//                peopleTableModel.addRow(new Object[]{"6", "Frank"});
//                break;
//        }
//    }
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(GroupPeopleApp::new);
//    }
//}
