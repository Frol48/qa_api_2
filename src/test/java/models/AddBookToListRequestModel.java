package models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class AddBookToListRequestModel {
    String userId;
    List<CollectionOfIsbns> collectionOfIsbns;

    @AllArgsConstructor
    @Data
    public static class CollectionOfIsbns {
        String isbn;
    }
}

/*
{
  "userId": "f32add9d-7e44-4f07-8a73-e13706466df7",
  "collectionOfIsbns": [
    {
      "isbn": "9781449331818"
    }
  ]
}
 */