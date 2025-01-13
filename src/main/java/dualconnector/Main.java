package dualconnector;

import dualconnector.repository.impl.FieldRepositoryImpl;
import dualconnector.repository.impl.OperationRepositoryImpl;

public class Main {
    public static void main(String[] args) {

//        SimpleGuid app = new SimpleGuid();
//        app.setVisible(true);
//        app.setLocationRelativeTo(null);



        OperationRepositoryImpl operationRepository = new OperationRepositoryImpl();

        System.out.println(operationRepository.getAllOperation());
        System.out.println(operationRepository.getAllOperation().get(0).getFieldsresp());
    }
}