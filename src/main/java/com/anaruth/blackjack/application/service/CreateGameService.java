package com.anaruth.blackjack.application.service;

import com.anaruth.blackjack.application.dto.CreateGameCommand;
import com.anaruth.blackjack.application.port.in.CreateGameUseCase;
import com.anaruth.blackjack.domain.model.game.Game;
import com.anaruth.blackjack.domain.model.player.Player;
import com.anaruth.blackjack.domain.model.player.PlayerId;
import com.anaruth.blackjack.domain.model.player.PlayerName;
import com.anaruth.blackjack.domain.port.out.GameRepository;
import com.anaruth.blackjack.domain.port.out.PlayerRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CreateGameService implements CreateGameUseCase {

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    public CreateGameService(GameRepository gameRepository, PlayerRepository playerRepository) {
        this.gameRepository = gameRepository;
        this.playerRepository = playerRepository;
    }

    @Override
    public Mono<Game> createGame(CreateGameCommand command) {
        validateCommand(command);

        Player player = new Player(
                PlayerId.randomId(),
                new PlayerName(command.playerName())
        );

        Game game = new Game(player);

        return playerRepository.save(player)
                .then(gameRepository.save(game));
    }

    private void validateCommand(CreateGameCommand command) {
        if (command == null) {
            throw new IllegalArgumentException("Create game command cannot be null");
        }
    }
}