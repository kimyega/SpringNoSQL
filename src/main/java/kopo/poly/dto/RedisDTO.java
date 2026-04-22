package kopo.poly.dto;

import lombok.Builder;

@Builder
public record RedisDTO (
        String name,
        String email,
        String addr,
        String text,
        Float order
) {
}
