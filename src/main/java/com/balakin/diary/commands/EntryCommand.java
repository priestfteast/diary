package com.balakin.diary.commands;

import com.balakin.diary.domain.Activity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.sql.Date;

@Data
@NoArgsConstructor
public class EntryCommand {

    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private Date date;

    @NotEmpty
    private Activity activity;

    @NotEmpty
    private int duration;
}
