package com.balakin.diary.commands;

import com.balakin.diary.domain.Activity;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Digits;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class EntryCommand {

    private Long id;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @PastOrPresent
    private LocalDate date;

    @NotNull
    private Activity activity;

    @Positive
    @Digits(integer = 3, fraction = 0)
    private int duration;


}
