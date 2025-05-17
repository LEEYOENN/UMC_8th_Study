package umc.spring_study.web.discord;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "discordClient",
        url = "${monitoring.webhook-url}",
        configuration = DiscordFeignConfiguration.class
)
public interface DiscordClient {
    @PostMapping()
    void sendAlarm(@RequestBody DiscordMessage message);
}
