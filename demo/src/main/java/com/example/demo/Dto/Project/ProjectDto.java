package com.example.demo.Dto.Project;

import java.util.List;

public record ProjectDto (List<ProjectResponseDto> projects, int amount){
}
