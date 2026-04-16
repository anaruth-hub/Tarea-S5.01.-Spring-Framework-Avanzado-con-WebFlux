package com.anaruth.blackjack.domain.model.game;

import com.anaruth.blackjack.domain.model.player.Player;
import com.anaruth.blackjack.domain.model.player.PlayerId;
import com.anaruth.blackjack.domain.model.player.PlayerName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void shouldCreateGameWithInitialCardsAndInProgressStatusOrResolvedBlackjack() {
        Player player = new Player(PlayerId.randomId(), new PlayerName("Ana"));
        Game game = new Game(player);

        assertNotNull(game.getId());
        assertEquals(player, game.getPlayer());
        assertEquals(2, game.getPlayerHand().getCards().size());
        assertEquals(2, game.getDealerHand().getCards().size());

        assertTrue(
                game.getStatus() == GameStatus.IN_PROGRESS
                        || game.getStatus() == GameStatus.PLAYER_WON
                        || game.getStatus() == GameStatus.DEALER_WON
                        || game.getStatus() == GameStatus.DRAW
        );
    }

    @Test
    void shouldThrowExceptionWhenPlayerIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Game(null));
    }

    @Test
    void shouldThrowExceptionWhenPlayTypeIsNull() {
        Player player = new Player(PlayerId.randomId(), new PlayerName("Ana"));
        Game game = new Game(player);

        if (game.getStatus() == GameStatus.IN_PROGRESS) {
            assertThrows(IllegalArgumentException.class, () -> game.play(null));
        }
    }

    @Test
    void shouldNotAllowPlayWhenGameIsAlreadyFinished() {
        Player player = new Player(PlayerId.randomId(), new PlayerName("Ana"));
        Game game = new Game(player);

        while (game.getStatus() == GameStatus.IN_PROGRESS) {
            game.play(PlayType.STAND);
        }

        assertThrows(IllegalStateException.class, () -> game.play(PlayType.HIT));
    }
}