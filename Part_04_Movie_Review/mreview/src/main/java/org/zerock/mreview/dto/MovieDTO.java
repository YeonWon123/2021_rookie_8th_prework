package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

// DTO와 서비스 계층을 구성해 주는 작업 처리
// 화면에 영화 이미지들도 같이 수집해서 전달해야 하므로, 내부적으로 리스트를 이용하여 수집함
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {

    private Long mno;
    private String title;

    @Builder.Default
    private List<MovieImageDTO> imageDTOList = new ArrayList<>();
}
