package com.fincrime.tictactoe.services;

import com.fincrime.tictactoe.dtos.GameGetDto;
import com.fincrime.tictactoe.dtos.GamePostDto;
import com.fincrime.tictactoe.dtos.MoveGetDto;
import com.fincrime.tictactoe.dtos.MovePostDto;
import com.fincrime.tictactoe.entities.Game;
import com.fincrime.tictactoe.entities.Move;
import com.fincrime.tictactoe.exceptions.GameNotFoundException;
import com.fincrime.tictactoe.mappers.GameMapper;
import com.fincrime.tictactoe.mappers.MoveMapper;
import com.fincrime.tictactoe.repositories.GameRepository;
import com.fincrime.tictactoe.repositories.MoveRespository;
import com.fincrime.tictactoe.utils.GameUtils;
import jakarta.ws.rs.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final MoveRespository moveRespository;
    private final GameMapper gameMapper;
    private final MoveMapper moveMapper;
    private final GameUtils gameUtils;

    public GameGetDto createGame(GamePostDto gamePostDto) {
        Game game = gameRepository.save(gameMapper.toEntity(gamePostDto));
        return gameMapper.fromEntity(game);
    }

    public List<GameGetDto> listAll() {
        List<Game> gameList = gameRepository.findAll();
        return gameList.stream().map(game -> gameMapper.fromEntity(game)).collect(Collectors.toList());
    }

    public GameGetDto findGameByName(String name) {
        Game game = gameRepository.findByName(name);
        if (game == null) {
            log.warn("Game with name:" + name + " not found");
            throw new GameNotFoundException("Game not found");
        }
        return gameMapper.fromEntity(gameRepository.findByName(name));
    }

    public GameGetDto findGameById(UUID id) {
        return gameMapper.fromEntity(findGameEntity(id));
    }

    public MoveGetDto performMove(MovePostDto movePostDto) {
        if (gameUtils.isMovePayloadValid(movePostDto)) {
            log.error("Invalid move payload while perform a new move, axis should be less than 3");
            throw new BadRequestException("Invalid move payload, axis should be within 3");
        }

        Game game = findGameEntity(movePostDto.getGameId());
        if (gameUtils.isPlayerValid(movePostDto, game)) {
            log.error("It should be opponent's turn to play");
            throw new BadRequestException("opponent's turn to play");
        }


        Set<Move> moveList = game.getMoves();

        Move move = moveMapper.toEntity(movePostDto);
        move.setGame(game);
        return moveMapper.fromEntity(moveRespository.save(move));
    }

    private Game findGameEntity(UUID id) {
        return gameRepository.findById(id).<GameNotFoundException>orElseThrow(() -> {
            log.warn("Game with id:" + id + " not found");
            throw new GameNotFoundException("Game not found");
        });
    }

}