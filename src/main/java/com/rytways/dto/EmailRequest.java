package com.rytways.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmailRequest {
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String body;
    private String[] attachmentPaths;
}