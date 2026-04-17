package com.anaruth.blackjack.application.service;

import com.anaruth.blackjack.application.dto.PlayGameCommand;
import com.anaruth.blackjack.domain.model.game.Game;
import com.anaruth.blackjack.domain.model.game.PlayType;
import com.anaruth.blackjack.domain.model.player.Player;
import com.anaruth.blackjack.domain.model.player.PlayerId;
import com.anaruth.blackjack.domain.model.player.PlayerName;
import com.anaruth.blackjack.domain.port.out.GameRepository;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class PlayGameServiceTest {

    private final GameRepository gameRepository = mock(GameRepository.class);

    private final PlayGameService service =
            new PlayGameService(gameRepository);

    @Test
    void shouldPlayGameSuccessfully() {


        Player player = new Player(PlayerId.randomId(), new PlayerName("Ana"));
        Game game = new Game(player);

        PlayGameCommand command =
                new PlayGameCommand(game.getId(), PlayType.HIT);

        when(gameRepository.findById(game.getId()))
                .thenReturn(Mono.just(game));

        when(gameRepository.save(any()))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));

        Game result = service.playGame(command).block();


        assertNotNull(result);

        verify(gameRepository, times(1)).findById(game.getId());
        verify(gameRepository, times(1)).save(any());
    }
}