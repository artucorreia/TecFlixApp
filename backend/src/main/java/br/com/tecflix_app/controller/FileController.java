package br.com.tecflix_app.controller;

import br.com.tecflix_app.config.file.FileStorageConfig;
import br.com.tecflix_app.data.DTO.v1.response.UploadFileResponseDTO;
import br.com.tecflix_app.service.file.FileStorageService;
import br.com.tecflix_app.service.util.FileNameGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/files")
@Tag(name = "File", description = "Endpoints for manager files")
public class FileController {
  private final FileStorageService service;
  private final FileStorageConfig config;

  @Autowired
  public FileController(FileStorageService service, FileStorageConfig config) {
    this.service = service;
    this.config = config;
  }

  @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(
      summary = "Upload a file",
      description = "Upload a file and generate a random name for it (random uuid + date time)",
      tags = {"File"},
      method = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UploadFileResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public ResponseEntity<UploadFileResponseDTO> uploadFile(
      @RequestParam(name = "file", required = true) MultipartFile file) {
    String[] subStrings = {UUID.randomUUID().toString(), LocalDateTime.now().toString()};

    String fileName = service.storeFile(file, FileNameGenerator.generateFileName(subStrings));
    String resourcePath = config.getUploadDir() + '/' + fileName;

    return ResponseEntity.ok(
        new UploadFileResponseDTO(fileName, resourcePath, file.getContentType(), file.getSize()));
  }

  @PostMapping(value = "/upload-files", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  @Operation(
      summary = "Upload files",
      description = "Upload files and generate a random name for each (random uuid + date time)",
      tags = {"File"},
      method = "POST")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Success",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = UploadFileResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
        @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
        @ApiResponse(responseCode = "500", description = "Internal Error", content = @Content)
      })
  public List<ResponseEntity<UploadFileResponseDTO>> uploadFiles(
      @RequestParam(name = "files", required = true) List<MultipartFile> files) {
    return files.stream().map(file -> uploadFile(file)).toList();
  }
}
