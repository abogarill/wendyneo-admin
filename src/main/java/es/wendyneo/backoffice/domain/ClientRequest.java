package es.wendyneo.backoffice.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public record ClientRequest(@Id @JsonSerialize(using = ToStringSerializer.class) ObjectId id,
                            String name, String phone, String petName, String petType, String allergy, String notes) {
}
