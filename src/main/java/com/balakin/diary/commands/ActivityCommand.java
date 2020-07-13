package com.balakin.diary.commands;

import com.balakin.diary.domain.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class ActivityCommand {
    private Long id;

    @NotBlank
    @Size(min = 2, max = 20)
    private String description;


    private Byte[] logo;

    private Type type;




}
