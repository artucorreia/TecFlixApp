package br.com.tecflix_app.modules.shared.dto.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomPageResponseDTO<T> {
  private List<T> content;
  private int pageNumber;
  private int pageSize;
  private long totalElements;
  private int totalPages;
  private boolean hasNext;
  private boolean hasPrevious;
}
