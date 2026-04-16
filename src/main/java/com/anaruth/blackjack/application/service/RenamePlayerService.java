package com.anaruth.blackjack.application.service;

import com.anaruth.blackjack.application.dto.RenamePlayerCommand;
import com.anaruth.blackjack.application.port.in.RenamePlayerUseCase;
import com.anaruth.blackjack.domain.exception.PlayerNotFoundException;
import com.anaruth.blackjack.domain.model.player.Player;
import com.anaruth.blackjack.domain.model.player.PlayerName;
import com.anaruth.blackjack.domain.port.out.PlayerRepository;
import reactor.core.publisher.Mono;

public class RenamePlayerService implements RenamePlayerUseCase {

    private final PlayerRepository playerRepository;

    public RenamePlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Mono<Player> renamePlayer(RenamePlayerCommand command) {
        validateCommand(command);

        return playerRepository.findById(command.playerId())
                .switchIfEmpty(Mono.error(new PlayerNotFoundException(command.playerId())))
                .flatMap(player -> {
                    player.rename(new PlayerName(command.newName()));
                    return playerRepository.update(player);
                });
    }

    private void validateCommand(RenamePlayerCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Rename player command cannot be null");
        }

        if (command.playerId() == null || command.playerId().isBlank()) {
            throw new IllegalArgumentException("Player id cannot be null or blank");
        }

        if (command.newName() == null || command.newName().isBlank()) {
            throw new IllegalArgumentException("New player name cannot be null or blank");
        }
    }
}