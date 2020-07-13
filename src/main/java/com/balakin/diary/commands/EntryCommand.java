package com.balakin.diary.commands;

import com.balakin.diary.domain.Activity;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import java.sql.Date;

@Data
@NoArgsConstructor
public class EntryCommand {

    private Long id;

    @PastOrPresent
    private Date date;

    @NotEmpty
    private Activity activity;

    @NotEmpty
    private int duration;
}
