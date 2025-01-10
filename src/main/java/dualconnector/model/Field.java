package dualconnector.model;

import lombok.*;

@Data
@ToString(of = {"name", "description"})
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Field {

    private Long id;
    private String name;
    private String description;
    private String info;
    private String request;
    private String response;

}
