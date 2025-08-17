package br.com.tecflix_app.shared.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PageMetadata {
  private Integer size;
  private Long totalElements;
  private Integer totalPages;
  private Integer number;
}
