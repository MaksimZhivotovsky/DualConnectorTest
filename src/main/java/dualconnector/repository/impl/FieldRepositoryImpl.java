package dualconnector.repository.impl;

import dualconnector.model.Field;
import dualconnector.repository.FieldRepository;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
public class FieldRepositoryImpl implements FieldRepository {

    private final List<Field> fieldList;

    public FieldRepositoryImpl() {
        fieldList = new ArrayList<>();
        createFields();
    }

    @Override
    public Field createById(Long id) {
        Optional<Field> field = fieldList.stream()
                .filter(field1 -> field1.getId().equals(id))
                .findFirst();
        return Field.builder()
                .id(field.get().getId())
                .name(field.get().getName())
                .description(field.get().getDescription())
                .info(field.get().getInfo())
                .build();
    }

    public void createFields() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("params.xml"));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("FIELD");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    NodeList restElmLst = element.getElementsByTagName("INFO");
                    String info = null;
                    if (restElmLst.getLength() > 0) {
                        info = element.getElementsByTagName("INFO")
                                .item(0)
                                .getTextContent();
                    }

                    Field field = Field.builder()
                            .id(Long.parseLong(element.getElementsByTagName("ID").item(0).getTextContent()))
                            .name(element.getElementsByTagName("NAME").item(0).getTextContent())
                            .description(element.getElementsByTagName("DESCRIPTION").item(0).getTextContent())
                            .info(info)
                            .build();

                    fieldList.add(field);
                    
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
