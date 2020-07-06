package domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
public class Entry {
    @Id
    private Long id;

    private Date date;

    private Type type;

    private Activity activity;

    private int duration;
}
