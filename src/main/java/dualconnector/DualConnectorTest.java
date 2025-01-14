package dualconnector;

import dualconnector.model.Field;
import dualconnector.model.Operation;
import dualconnector.repository.OperationRepository;
import dualconnector.repository.impl.OperationRepositoryImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Optional;

public class DualConnectorTest {
    private final JTable operationTable;
    private final DefaultTableModel operationTableModel;
    private final DefaultTableModel fieldTableModel;
    private final List<Operation> operationList;

    public DualConnectorTest() {
        JFrame frame = new JFrame("Dual Connector Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(1, 2));
        OperationRepository operationRepository = new OperationRepositoryImpl();
        operationList = operationRepository.getAllOperation();

        // Создание модели и таблицы для групп
        operationTableModel = new DefaultTableModel(new Object[]{"ID", "Name"}, 0);
        operationTable = new JTable(operationTableModel);
        operationTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        operationTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = operationTable.getSelectedRow();
                if (selectedRow != -1) {
                    String groupId = operationTableModel.getValueAt(selectedRow, 0).toString();
                    updatePeopleTable(groupId);
                }
            }
        });

        // Заполнение таблицы операции
        populateOperationTable();

        // Создание модели и таблицы для полей
        fieldTableModel = new DefaultTableModel(
                new Object[]{"ID", "Description", "Name", "Request", "Response"}, 0
        );
        JTable fieldTable = new JTable(fieldTableModel);

        // Добавление таблиц в панель
        frame.add(new JScrollPane(operationTable));
        frame.add(new JScrollPane(fieldTable));

        frame.setSize(800, 400);
        frame.setVisible(true);
    }

    private void populateOperationTable() {
        for (Operation operation : operationList) {
            operationTableModel.addRow(
                    new Object[] {operation.getId(), operation.getName()}
            );
        }
    }

    private void updatePeopleTable(String groupId) {
        // Очистка предыдущих данных
        fieldTableModel.setRowCount(0);


        Optional<Operation> oper = operationList.stream()
                .filter(operation -> operation.getId().equals(Long.parseLong(groupId)))
                .findFirst();

        List<Field> fieldList = oper.get().getFieldsresp();

        for (Field field: fieldList) {
            fieldTableModel.addRow(
                    new Object[] {field.getId(), field.getDescription(), field.getName(), field.getRequest()}
            );
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DualConnectorTest::new);
//        DualConnectorTest app = new DualConnectorTest();
//        app.setVisible(true);
    }
}
