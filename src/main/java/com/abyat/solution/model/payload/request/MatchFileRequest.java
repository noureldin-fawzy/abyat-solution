package com.abyat.solution.model.payload.request;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class MatchFileRequest {

    @NotNull
    private Set<MultipartFile> files;
}
