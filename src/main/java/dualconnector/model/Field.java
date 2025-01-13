package dualconnector.model;

import lombok.*;

@Builder
@Getter
@Setter
@ToString(of = {"name", "description", "request", "info"})
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
