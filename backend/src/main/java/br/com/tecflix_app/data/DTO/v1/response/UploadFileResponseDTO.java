package br.com.tecflix_app.data.DTO.v1.response;

import java.io.Serializable;

public class UploadFileResponseDTO implements Serializable {
  private String fileName;
  private String resourcePath;
  private String fileType;
  private Long size;

  public UploadFileResponseDTO() {}

  public UploadFileResponseDTO(String fileName, String resourcePath, String fileType, Long size) {
    this.fileName = fileName;
    this.resourcePath = resourcePath;
    this.fileType = fileType;
    this.size = size;
  }

  public String getFileName() {
    return fileName;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getResourcePath() {
    return resourcePath;
  }

  public void setResourcePath(String resourcePath) {
    this.resourcePath = resourcePath;
  }

  public String getFileType() {
    return fileType;
  }

  public void setFileType(String fileType) {
    this.fileType = fileType;
  }

  public Long getSize() {
    return size;
  }

  public void setSize(Long size) {
    this.size = size;
  }
}
