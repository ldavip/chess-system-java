package br.com.ldavip.chesssystem.chess;

import br.com.ldavip.chesssystem.boardgame.BoardException;

public class ChessException extends BoardException {
	private static final long serialVersionUID = 1L;

	public ChessException(String msg) {
		super(msg);
	}
}
