package br.com.tecflix_app.config.file;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageConfig {
  private String uploadDir;
  private String resourcesPath;

  public FileStorageConfig() {}

  public FileStorageConfig(String uploadDir, String resourcesPath) {
    this.uploadDir = uploadDir;
    this.resourcesPath = resourcesPath;
  }

  public String getUploadDir() {
    return uploadDir;
  }

  public void setUploadDir(String uploadDir) {
    this.uploadDir = uploadDir;
  }

  public String getResourcesPath() {
    return resourcesPath;
  }

  public void setResourcesPath(String resourcesPath) {
    this.resourcesPath = resourcesPath;
  }
}
