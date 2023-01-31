package com.examauthentication.utils;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ModelMapperService {
    @Autowired
    private final ModelMapper modelMapper;

    public <T> List<T> toList(Class<T> clazz, List<?> items) {
        return items.stream()
                .map(item -> modelMapper.map(item, clazz))
                .collect(Collectors.toList());
    }

    public <T> Set<T> toSet(Class<T> clazz, Set<?> items) {
        return items.stream()
                .map(item -> modelMapper.map(item, clazz))
                .collect(Collectors.toSet());
    }

    public <T> T toObject(Class<T> clazz, Object item) {
        if (item == null) return null;
        return modelMapper.map(item, clazz);
    }

    public <T, P> Map<String, Object> toMap(Class<T> tClass, Page<P> page) {
        if (page.isEmpty()) return Collections.emptyMap();
        return Map.of(
                "totalElementos", page.getTotalElements(),
                "totalPaginas", page.getTotalPages(),
                "result", toList(tClass, page.getContent())
        );
    }
}
