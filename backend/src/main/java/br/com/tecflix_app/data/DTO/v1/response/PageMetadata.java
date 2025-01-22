package br.com.tecflix_app.data.DTO.v1.response;


public class PageMetadata {
  private Integer size;
  private Long totalElements;
  private Integer totalPages;
  private Integer number;

  public PageMetadata() {}

  public PageMetadata(Integer size, Long totalElements, Integer totalPages, Integer number) {
    this.size = size;
    this.totalElements = totalElements;
    this.totalPages = totalPages;
    this.number = number;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public Long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(Long totalElements) {
    this.totalElements = totalElements;
  }

  public Integer getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(Integer totalPages) {
    this.totalPages = totalPages;
  }

  public Integer getNumber() {
    return number;
  }

  public void setNumber(Integer number) {
    this.number = number;
  }
}
