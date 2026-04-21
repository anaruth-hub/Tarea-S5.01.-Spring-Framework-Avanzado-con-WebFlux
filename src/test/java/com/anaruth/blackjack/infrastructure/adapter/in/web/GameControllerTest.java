package com.anaruth.blackjack.infrastructure.adapter.in.web;

import com.anaruth.blackjack.application.dto.CreateGameCommand;
import com.anaruth.blackjack.application.port.in.CreateGameUseCase;
import com.anaruth.blackjack.application.port.in.DeleteGameUseCase;
import com.anaruth.blackjack.application.port.in.GetGameUseCase;
import com.anaruth.blackjack.application.port.in.PlayGameUseCase;
import com.anaruth.blackjack.domain.model.game.Game;
import com.anaruth.blackjack.domain.model.player.Player;
import com.anaruth.blackjack.domain.model.player.PlayerId;
import com.anaruth.blackjack.domain.model.player.PlayerName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(GameController.class)
class GameControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private CreateGameUseCase createGameUseCase;

    @MockBean
    private GetGameUseCase getGameUseCase;

    @MockBean
    private PlayGameUseCase playGameUseCase;

    @MockBean
    private DeleteGameUseCase deleteGameUseCase;

    @Test
    void shouldCreateGameSuccessfully() {
        Player player = new Player(PlayerId.randomId(), new PlayerName("Ana"));
        Game game = new Game(player);

        when(createGameUseCase.createGame(any(CreateGameCommand.class)))
                .thenReturn(Mono.just(game));

        webTestClient.post()
                .uri("/game/new")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("""
                        {
                          "playerName": "Ana"
                        }
                        """)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isEqualTo(game.getId())
                .jsonPath("$.status").isEqualTo(game.getStatus().name())
                .jsonPath("$.playerId").isEqualTo(game.getPlayer().getId().getValue())
                .jsonPath("$.playerName").isEqualTo(game.getPlayer().getName().getValue())
                .jsonPath("$.playerScore").isEqualTo(game.getPlayerHand().calculateScore())
                .jsonPath("$.dealerScore").isEqualTo(game.getDealerHand().calculateScore());
    }

    @Test
    void shouldGetGameByIdSuccessfully() {
        Player player = new Player(PlayerId.randomId(), new PlayerName("Ana"));
        Game game = new Game(player);

        when(getGameUseCase.getGameById(game.getId()))
                .thenReturn(Mono.just(game));

        webTestClient.get()
                .uri("/game/{id}", game.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(game.getId())
                .jsonPath("$.status").isEqualTo(game.getStatus().name())
                .jsonPath("$.playerId").isEqualTo(game.getPlayer().getId().getValue())
                .jsonPath("$.playerName").isEqualTo(game.getPlayer().getName().getValue())
                .jsonPath("$.playerScore").isEqualTo(game.getPlayerHand().calculateScore())
                .jsonPath("$.dealerScore").isEqualTo(game.getDealerHand().calculateScore());
    }

    @Test
    void shouldDeleteGameSuccessfully() {
        String gameId = "game-123";

        when(deleteGameUseCase.deleteGameById(gameId))
                .thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/game/{id}/delete", gameId)
                .exchange()
                .expectStatus().isNoContent();
    }
}