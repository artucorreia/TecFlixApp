package br.com.tecflix_app.data.DTO.v1.response;

import java.util.List;
import java.util.Map;

public record CustomPagedResponse<T>(List<T> content, Map<String, String> links, PageMetadata page) {
}
