package br.com.tecflix_app.shared.dto.v1;

import java.util.List;
import java.util.Map;

public record CustomPagedResponse<T>(
    List<T> content, Map<String, String> links, PageMetadata page) {}
