package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@Builder
public record MovieDTO (
        String collectTime,
        String rank,
        String name,
        String reserve,
        String score,
        String openDay,
        @NotBlank(message = "음성 명령 메시지는 필수 입력 사항입니다.")
        String speechCommand
) {
}
