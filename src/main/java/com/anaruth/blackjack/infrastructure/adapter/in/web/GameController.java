package com.anaruth.blackjack.infrastructure.adapter.in.web;

import com.anaruth.blackjack.application.dto.CreateGameCommand;
import com.anaruth.blackjack.application.dto.PlayGameCommand;
import com.anaruth.blackjack.application.port.in.CreateGameUseCase;
import com.anaruth.blackjack.application.port.in.GetGameUseCase;
import com.anaruth.blackjack.application.port.in.PlayGameUseCase;
import com.anaruth.blackjack.domain.model.game.Game;
import com.anaruth.blackjack.infrastructure.adapter.in.web.request.CreateGameRequest;
import com.anaruth.blackjack.infrastructure.adapter.in.web.request.PlayGameRequest;
import com.anaruth.blackjack.infrastructure.adapter.in.web.response.GameResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/game")
public class GameController {

    private final CreateGameUseCase createGameUseCase;
    private final GetGameUseCase getGameUseCase;
    private final PlayGameUseCase playGameUseCase;

    public GameController(CreateGameUseCase createGameUseCase,
                          GetGameUseCase getGameUseCase,
                          PlayGameUseCase playGameUseCase) {
        this.createGameUseCase = createGameUseCase;
        this.getGameUseCase = getGameUseCase;
        this.playGameUseCase = playGameUseCase;
    }

    @PostMapping("/new")
    public Mono<GameResponse> createGame(@RequestBody CreateGameRequest request) {
        return createGameUseCase.createGame(new CreateGameCommand(request.playerName()))
                .map(this::toResponse);
    }

    @GetMapping("/{id}")
    public Mono<GameResponse> getGame(@PathVariable String id) {
        return getGameUseCase.getGameById(id)
                .map(this::toResponse);
    }

    @PostMapping("/{id}/play")
    public Mono<GameResponse> play(@PathVariable String id,
                                   @RequestBody PlayGameRequest request) {
        return playGameUseCase.playGame(
                        new PlayGameCommand(id, request.playType()))
                .map(this::toResponse);
    }

    private GameResponse toResponse(Game game) {
        return new GameResponse(
                game.getId(),
                game.getStatus().name(),
                game.getPlayerHand().getCards().stream()
                        .map(card -> card.getRank() + " of " + card.getSuit())
                        .toList(),
                game.getDealerHand().getCards().stream()
                        .map(card -> card.getRank() + " of " + card.getSuit())
                        .toList(),
                game.getPlayerHand().calculateScore(),
                game.getDealerHand().calculateScore()
        );
    }
}