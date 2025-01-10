package dualconnector;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class XMLParserExample {
    public static void main(String[] args) {
        try {
            // Создаем экземпляр DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Создаем DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Загружаем XML файл
            Document document = builder.parse(new File("example.xml"));
            // Нормализуем XML структуру
            document.getDocumentElement().normalize();

            // Получаем список всех элементов "employee"
            NodeList nodeList = document.getElementsByTagName("employee");

            // Проходим по каждому элементу "employee"
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    // Получаем данные о сотруднике
                    String id = element.getElementsByTagName("id").item(0).getTextContent();
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String department = element.getElementsByTagName("department").item(0).getTextContent();

                    // Выводим информацию о сотруднике
                    System.out.println("ID: " + id);
                    System.out.println("Name: " + name);
                    System.out.println("Department: " + department);
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
