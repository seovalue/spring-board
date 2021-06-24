package com.joanne.board.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseTest {
    @Test
    public void 롬복_기능_테스트() {
        String name = "test";
        int amount = 100;

        HelloResponse helloResponse = new HelloResponse(name, amount);

        assertThat(helloResponse.getName()).isEqualTo(name);
        assertThat(helloResponse.getAmount()).isEqualTo(amount);
    }
}