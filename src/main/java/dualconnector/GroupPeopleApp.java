package dualconnector;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class GroupPeopleApp {
    private JFrame frame;
    private JList<String> groupList;
    private JList<String> peopleList;
    private DefaultListModel<String> groupListModel;
    private DefaultListModel<String> peopleListModel;
    private Map<String, String[]> groups;

    public GroupPeopleApp() {
        // Создаем фрейм
        frame = new JFrame("Группы и Люди");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Инициализируем модели списков
        groupListModel = new DefaultListModel<>();
        peopleListModel = new DefaultListModel<>();

        // Создаем список групп
        groupList = new JList<>(groupListModel);
        groupList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        groupList.addListSelectionListener(e -> updatePeopleList());

        // Создаем список людей
        peopleList = new JList<>(peopleListModel);

        // Заполняем группы
        groups = new HashMap<>();
        groups.put("Группа 1", new String[]{"Человек 1", "Человек 2", "Человек 3"});
        groups.put("Группа 2", new String[]{"Человек 4", "Человек 5"});
        groups.put("Группа 3", new String[]{"Человек 6", "Человек 7", "Человек 8", "Человек 9"});

        // Добавляем группы в модель
        for (String group : groups.keySet()) {
            groupListModel.addElement(group);
        }

        // Создаем панели для размещения списков
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.add(new JScrollPane(groupList));
        panel.add(new JScrollPane(peopleList));

        // Добавляем панели на фрейм
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void updatePeopleList() {
        // Очищаем список людей
        peopleListModel.clear();
        // Получаем выбранную группу
        String selectedGroup = groupList.getSelectedValue();
        if (selectedGroup != null) {
            // Получаем людей из выбранной группы и добавляем их в модель
            String[] people = groups.get(selectedGroup);
            if (people != null) {
                for (String person : people) {
                    peopleListModel.addElement(person);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GroupPeopleApp::new);
    }
}