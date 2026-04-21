package com.anaruth.blackjack.infrastructure.adapter.in.web;

import com.anaruth.blackjack.application.dto.CreateGameCommand;
import com.anaruth.blackjack.application.dto.PlayGameCommand;
import com.anaruth.blackjack.application.port.in.CreateGameUseCase;
import com.anaruth.blackjack.application.port.in.DeleteGameUseCase;
import com.anaruth.blackjack.application.port.in.GetGameUseCase;
import com.anaruth.blackjack.application.port.in.PlayGameUseCase;
import com.anaruth.blackjack.domain.model.game.Game;
import com.anaruth.blackjack.infrastructure.adapter.in.web.request.CreateGameRequest;
import com.anaruth.blackjack.infrastructure.adapter.in.web.request.PlayGameRequest;
import com.anaruth.blackjack.infrastructure.adapter.in.web.response.GameResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Tag(name = "Game", description = "Blackjack game operations")
@RestController
@RequestMapping("/game")
public class GameController {

    private final CreateGameUseCase createGameUseCase;
    private final GetGameUseCase getGameUseCase;
    private final PlayGameUseCase playGameUseCase;
    private final DeleteGameUseCase deleteGameUseCase;

    public GameController(
            CreateGameUseCase createGameUseCase,
            GetGameUseCase getGameUseCase,
            PlayGameUseCase playGameUseCase,
            DeleteGameUseCase deleteGameUseCase
    ) {
        this.createGameUseCase = createGameUseCase;
        this.getGameUseCase = getGameUseCase;
        this.playGameUseCase = playGameUseCase;
        this.deleteGameUseCase = deleteGameUseCase;
    }

    @Operation(summary = "Create a new blackjack game")
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<GameResponse> createGame(@RequestBody CreateGameRequest request) {
        return createGameUseCase.createGame(new CreateGameCommand(request.playerName()))
                .map(this::toResponse);
    }

    @Operation(summary = "Get game details by id")
    @GetMapping("/{id}")
    public Mono<GameResponse> getGame(@PathVariable String id) {
        return getGameUseCase.getGameById(id)
                .map(this::toResponse);
    }

    @Operation(summary = "Play a move in an existing game")
    @PostMapping("/{id}/play")
    public Mono<GameResponse> play(@PathVariable String id,
                                   @RequestBody PlayGameRequest request) {
        return playGameUseCase.playGame(new PlayGameCommand(id, request.playType()))
                .map(this::toResponse);
    }

    @Operation(summary = "Delete a game by id")
    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteGame(@PathVariable String id) {
        return deleteGameUseCase.deleteGameById(id);
    }

    private GameResponse toResponse(Game game) {
        return new GameResponse(
                game.getId(),
                game.getStatus().name(),
                game.getPlayer().getId().getValue(),
                game.getPlayer().getName().getValue(),
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