package com.anaruth.blackjack.application.port.in;

import com.anaruth.blackjack.application.dto.RenamePlayerCommand;
import com.anaruth.blackjack.domain.model.player.Player;
import reactor.core.publisher.Mono;

public interface RenamePlayerUseCase {
    Mono<Player> renamePlayer(RenamePlayerCommand command);
}
