package dualconnector.repository.impl;

import dualconnector.model.Field;
import dualconnector.model.Operation;
import dualconnector.repository.FieldRepository;
import dualconnector.repository.OperationRepository;
import lombok.extern.slf4j.Slf4j;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class OperationRepositoryImpl implements OperationRepository {

    private final List<Operation> operationList;
    private final FieldRepository fieldRepository;

    public OperationRepositoryImpl() {
        operationList = new ArrayList<>();
        fieldRepository = new FieldRepositoryImpl();
        createOperations();
    }

    @Override
    public List<Operation> getAllOperation() {
        return operationList;
    }

    private void createOperations() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File("params.xml"));
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("OPERATION");


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    List<Field> fieldList = createFieldList(element);

                    Operation operation = Operation.builder()
                            .id(Long.parseLong(
                                    element.getElementsByTagName("ID").item(0).getTextContent())
                            )
                            .name(element.getElementsByTagName("NAME").item(0).getTextContent())
                            .info(element.getElementsByTagName("INFO").item(0).getTextContent())
                            .fieldsresp(fieldList)
                            .build();

                    operationList.add(operation);

                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    private List<Field> createFieldList(Element element) {
        String fieldsresp = element.getElementsByTagName("FIELDSRESP")
                .item(0)
                .getTextContent()
                .replaceAll("[^-?0-9]+", " ");

        List<Long> fieldsrespId = Arrays.stream(fieldsresp.trim().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        List<Field> fieldList = new ArrayList<>();
        for (Long id : fieldsrespId) {
            fieldList.add(fieldRepository.createById(id));
        }


        String fields = element.getElementsByTagName("FIELDS")
                .item(0)
                .getTextContent()
                .replaceAll("[^-?0-9]+", " ");

        List<Long> fieldsId = Arrays.stream(fields.trim().split(" "))
                .map(Long::parseLong)
                .collect(Collectors.toList());

        for (Long id : fieldsId) {

            NodeList restElmLst = element.getElementsByTagName("FLDVAL" + id);

            if (restElmLst.getLength() > 0) {
                String fldval = element.getElementsByTagName("FLDVAL" + id)
                        .item(0)
                        .getTextContent();
                Optional<Field> field = fieldList.stream()
                        .filter(field1 -> field1.getId().equals(id))
                        .findFirst();
                field.ifPresent(value -> value.setRequest(fldval));
            }

        }

        return fieldList;
    }
}
