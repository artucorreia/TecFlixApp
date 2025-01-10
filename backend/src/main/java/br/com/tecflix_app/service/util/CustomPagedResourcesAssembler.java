package br.com.tecflix_app.service.util;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.stereotype.Service;

import br.com.tecflix_app.data.DTO.v1.response.CustomPagedResponse;
import br.com.tecflix_app.data.DTO.v1.response.PageMetadata;

@Service
public class CustomPagedResourcesAssembler<T extends RepresentationModel<T>> {
        public CustomPagedResponse<T> toModel(Page<T> page, Map<String, String> links, Pageable pageable) {
                return new CustomPagedResponse<T>(page.getContent(), links,
                                createPageMetadata(page));
        }

        private PageMetadata createPageMetadata(Page<T> page) {
                return new PageMetadata(page.getSize(), page.getTotalElements(), page.getTotalPages(),
                                page.getNumber());
        }
}
