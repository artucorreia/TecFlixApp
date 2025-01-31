package br.com.tecflix_app.service.file;

import br.com.tecflix_app.config.file.FileStorageConfig;
import br.com.tecflix_app.exception.file.FileStorageException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

  private final Logger LOGGER = Logger.getLogger(FileStorageService.class.getName());
  private final Path folderPath;

  @Autowired
  public FileStorageService(FileStorageConfig fileStorageConfig) {
    this.folderPath = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

    try {
      Files.createDirectories(this.folderPath);
    } catch (Exception e) {
      throw new FileStorageException("Could not create upload directory", e);
    }
  }

  public String storeFile(MultipartFile file) {
    LOGGER.info("storing a file");

    String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

    if (fileName.contains("..") || fileName.contains(" "))
      throw new FileStorageException(
          "Não foi possível salvar o arquivo. Nome inválido " + fileName);

    try {
      Path targetLocation = folderPath.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      return fileName;
    } catch (Exception e) {
      throw new FileStorageException(
          "Não foi possivel salvar o arquivo " + fileName + ".Por favor tente novamente", e);
    }
  }

  public String storeFile(MultipartFile file, String fileName) {
    LOGGER.info("storing a file: " + fileName);

    try {
      Path targetLocation = folderPath.resolve(fileName);
      Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
      return fileName;
    } catch (Exception e) {
      throw new FileStorageException(
          "Não foi possivel salvar o arquivo " + fileName + ". Por favor tente novamente", e);
    }
  }
}
