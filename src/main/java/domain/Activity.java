

package domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class Activity {

    @Id
    private Long id;

    private String description;

    private Type type;
}
