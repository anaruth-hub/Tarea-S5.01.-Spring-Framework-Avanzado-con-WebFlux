package com.anaruth.blackjack.application.service;

import com.anaruth.blackjack.application.dto.CreateGameCommand;
import com.anaruth.blackjack.domain.model.game.Game;
import com.anaruth.blackjack.domain.port.out.GameRepository;
import com.anaruth.blackjack.domain.port.out.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CreateGameServiceTest {

    private final GameRepository gameRepository = mock(GameRepository.class);
    private final PlayerRepository playerRepository = mock(PlayerRepository.class);

    private final CreateGameService service =
            new CreateGameService(gameRepository, playerRepository);

    @Test
    void shouldCreateGameSuccessfully() {


        CreateGameCommand command = new CreateGameCommand("Ana");

        when(playerRepository.save(any()))
                .thenReturn(Mono.empty());

        when(gameRepository.save(any()))
                .thenAnswer(invocation -> Mono.just(invocation.getArgument(0)));


        Game result = service.createGame(command).block();


        assertNotNull(result);
        assertNotNull(result.getId());
        assertNotNull(result.getPlayer());

        verify(playerRepository, times(1)).save(any());
        verify(gameRepository, times(1)).save(any());
    }
}