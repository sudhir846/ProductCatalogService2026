package org.example.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseModel {
    private Long id;

    private Long createdAt;

    private Long lastUpdatedAt;

    private State state;
}
