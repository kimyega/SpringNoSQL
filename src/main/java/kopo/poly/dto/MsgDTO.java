package kopo.poly.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record MsgDTO (

        int result,     // 성공 : 1 / 실패 : 그 외
        String msg      // 메시지
){
}
