package dualconnector.model;

import lombok.*;

import java.util.List;

@Data
@ToString(of = {"name", "info"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Operation {

    private Long id;
    private String name;
    private String info;
    private List<String> fields;
    private List<Field> fieldsresp;

}
