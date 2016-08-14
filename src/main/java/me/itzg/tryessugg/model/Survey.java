package me.itzg.tryessugg.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.List;

/**
 * @author Geoff Bourne
 */
@Data
@Document(indexName = Survey.INDEX)
public class Survey {
    public static final String INDEX = "demo";

    @Id private String id;

    private final Date taken;

    @Field(type = FieldType.String)
    private final List<String> interests;
}
