package com.anaruth.blackjack.infrastructure.adapter.in.web;

import com.anaruth.blackjack.application.dto.RenamePlayerCommand;
import com.anaruth.blackjack.application.port.in.RenamePlayerUseCase;
import com.anaruth.blackjack.domain.model.player.Player;
import com.anaruth.blackjack.infrastructure.adapter.in.web.request.RenamePlayerRequest;
import com.anaruth.blackjack.infrastructure.adapter.in.web.response.PlayerResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/player")
public class PlayerController {

    private final RenamePlayerUseCase renamePlayerUseCase;

    public PlayerController(RenamePlayerUseCase renamePlayerUseCase) {
        this.renamePlayerUseCase = renamePlayerUseCase;
    }

    @PutMapping("/{playerId}")
    public Mono<PlayerResponse> renamePlayer(@PathVariable String playerId,
                                             @RequestBody RenamePlayerRequest request) {

        return renamePlayerUseCase.renamePlayer(
                        new RenamePlayerCommand(playerId, request.newName()))
                .map(this::toResponse);
    }

    private PlayerResponse toResponse(Player player) {
        return new PlayerResponse(
                player.getId().getValue(),
                player.getName().getValue()
        );
    }
}