package com.patika.Library.dto.response.publisher;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponse {
    @NotNull
    private long id;
    private String name;
    private int establishmentYear;
    private String address;
}
