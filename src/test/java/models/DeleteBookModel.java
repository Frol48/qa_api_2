package models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class DeleteBookModel {
    String isbn, userId;
}
