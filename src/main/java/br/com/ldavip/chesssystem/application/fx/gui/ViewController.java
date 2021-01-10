package br.com.ldavip.chesssystem.application.fx.gui;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

import br.com.ldavip.chesssystem.boardgame.Position;
import br.com.ldavip.chesssystem.chess.ChessException;
import br.com.ldavip.chesssystem.chess.ChessMatch;
import br.com.ldavip.chesssystem.chess.ChessPiece;
import br.com.ldavip.chesssystem.chess.ChessPosition;
import br.com.ldavip.chesssystem.chess.Color;
import br.com.ldavip.chesssystem.chess.pieces.Bishop;
import br.com.ldavip.chesssystem.chess.pieces.King;
import br.com.ldavip.chesssystem.chess.pieces.Knight;
import br.com.ldavip.chesssystem.chess.pieces.Pawn;
import br.com.ldavip.chesssystem.chess.pieces.Queen;
import br.com.ldavip.chesssystem.chess.pieces.Rook;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class ViewController implements Initializable {

	private static final Logger log = Logger.getLogger(ViewController.class.getName());

	@FXML
	private ImageView a8;
	@FXML
	private ImageView b8;
	@FXML
	private ImageView c8;
	@FXML
	private ImageView d8;
	@FXML
	private ImageView e8;
	@FXML
	private ImageView f8;
	@FXML
	private ImageView g8;
	@FXML
	private ImageView h8;
	@FXML
	private ImageView a7;
	@FXML
	private ImageView b7;
	@FXML
	private ImageView c7;
	@FXML
	private ImageView d7;
	@FXML
	private ImageView e7;
	@FXML
	private ImageView f7;
	@FXML
	private ImageView g7;
	@FXML
	private ImageView h7;
	@FXML
	private ImageView a6;
	@FXML
	private ImageView b6;
	@FXML
	private ImageView c6;
	@FXML
	private ImageView d6;
	@FXML
	private ImageView e6;
	@FXML
	private ImageView f6;
	@FXML
	private ImageView g6;
	@FXML
	private ImageView h6;
	@FXML
	private ImageView a5;
	@FXML
	private ImageView b5;
	@FXML
	private ImageView c5;
	@FXML
	private ImageView d5;
	@FXML
	private ImageView e5;
	@FXML
	private ImageView f5;
	@FXML
	private ImageView g5;
	@FXML
	private ImageView h5;
	@FXML
	private ImageView a4;
	@FXML
	private ImageView b4;
	@FXML
	private ImageView c4;
	@FXML
	private ImageView d4;
	@FXML
	private ImageView e4;
	@FXML
	private ImageView f4;
	@FXML
	private ImageView g4;
	@FXML
	private ImageView h4;
	@FXML
	private ImageView a3;
	@FXML
	private ImageView b3;
	@FXML
	private ImageView c3;
	@FXML
	private ImageView d3;
	@FXML
	private ImageView e3;
	@FXML
	private ImageView f3;
	@FXML
	private ImageView g3;
	@FXML
	private ImageView h3;
	@FXML
	private ImageView a2;
	@FXML
	private ImageView b2;
	@FXML
	private ImageView c2;
	@FXML
	private ImageView d2;
	@FXML
	private ImageView e2;
	@FXML
	private ImageView f2;
	@FXML
	private ImageView g2;
	@FXML
	private ImageView h2;
	@FXML
	private ImageView a1;
	@FXML
	private ImageView b1;
	@FXML
	private ImageView c1;
	@FXML
	private ImageView d1;
	@FXML
	private ImageView e1;
	@FXML
	private ImageView f1;
	@FXML
	private ImageView g1;
	@FXML
	private ImageView h1;
	@FXML
	private Label lblPlayer;
	@FXML
	private FlowPane blackCapturedPane;
	@FXML
	private FlowPane whiteCapturedPane;

	private final Map<String, Image> images = new HashMap<>();
	private final Map<Color, List<ChessPiece>> capturedPieces = new EnumMap<>(Color.class);
	private final List<ImageView> board = new ArrayList<>();
	private final Map<ImageView, ChessPiece> pieces = new HashMap<>();
	private final Map<ImageView, Position> imagePosition = new HashMap<>();

	private ChessMatch chessMatch;

	private ChessPiece selectedPiece;
	private boolean[][] possibleMoves;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		loadBoard();
		loadPieces();
		chessMatch = new ChessMatch();
		possibleMoves = new boolean[chessMatch.getBoardRows()][chessMatch.getBoardColumns()];
		capturedPieces.put(Color.BLACK, new ArrayList<>());
		capturedPieces.put(Color.WHITE, new ArrayList<>());
		updateView();
	}

	public void setupListeners(Stage stage) {
		stage.getScene().setOnKeyPressed(this::clearSelectedOnEscape);
	}

	public void clearSelectedOnEscape(KeyEvent event) {
		if (event.getCode() == KeyCode.ESCAPE) {
			clearSelected();
		}
	}

	private void loadBoard() {
		board.addAll(List.of(a8, b8, c8, d8, e8, f8, g8, a7, b7, c7, d7, e7, f7, g7, a6, b6, c6, d6, e6, f6, g6, a5, b5,
				c5, d5, e5, f5, g5, a4, b4, c4, d4, e4, f4, g4, a3, b3, c3, d3, e3, f3, g3, a2, b2, c2, d2, e2, f2, g2,
				a1, b1, c1, d1, e1, f1, g1));
		loadPanelKeys(board);
		board.forEach(this::configureClick);
		board.forEach(iv -> iv.setOnKeyPressed(event -> {

		}));
	}

	private void loadPanelKeys(List<ImageView> images) {
		Field[] fields = getClass().getDeclaredFields();
		for (ImageView iv : images) {
			Optional<Field> opField = Stream.of(fields).filter(f -> {
				try {
					return f.get(ViewController.this) == iv;
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}).findFirst();
			if (opField.isPresent()) {
				String key = opField.get().getName();
				imagePosition.put(iv,
						new ChessPosition(key.charAt(0), Integer.parseInt(key.substring(1))).toPosition());
			}
		}
	}

	private void configureClick(ImageView iv) {
		iv.setOnMouseClicked(event -> {
			Position targetPosition = imagePosition.get(iv);

			if (selectedPiece != null) {
				ChessPosition source = selectedPiece.getChessPosition();

				try {
					ChessPiece capturedPiece = chessMatch.performChessMove(source,
							ChessPosition.fromPosition(targetPosition));

					if (capturedPiece != null) {
						capturedPieces.get(capturedPiece.getColor()).add(capturedPiece);
					}

					if (chessMatch.getPromoted() != null) {
						log.severe("Enter piece for promotion (B/N/R/Q): ");
						showAlert(AlertType.INFORMATION, "Select promotion!");
					}

					if (chessMatch.getCheckMate()) {
						log.severe("Check mate!\nGame Over");
						showAlert(AlertType.INFORMATION, "Check mate!");
					}
				} catch (ChessException | InputMismatchException e) {
					log.severe(e.getMessage());
					showAlert(AlertType.WARNING, e.getMessage());
				}

				clearSelected();
			} else {
				select(pieces.get(iv));
			}
		});
	}

	private void loadPieces() {
		Class<?>[] piecesClasses = { Bishop.class, King.class, Knight.class, Pawn.class, Queen.class, Rook.class };
		for (Color color : Color.values()) {
			for (Class<?> pieceClass : piecesClasses) {
				loadPiece(pieceClass, color);
			}
		}
	}

	private void loadPiece(Class<?> pieceClass, Color color) {
		try {
			String key = getKey(pieceClass, color);
			Image image = new Image("img/" + key + ".png");
			images.put(key, image);
		} catch (IllegalStateException e) {
			log.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	private void select(ChessPiece piece) {
		if (piece != null) {
			try {
				if (piece.getColor() == chessMatch.getCurrentPlayer()) {
					boolean[][] moves = chessMatch.possibleMoves(piece.getChessPosition());
					Position position = piece.getChessPosition().toPosition();
					moves[position.getRow()][position.getColumn()] = true;

					set(moves, possibleMoves);
					selectedPiece = piece;
				}
				updateView();
			} catch (ChessException e) {
				log.info(e.getMessage());
				clearSelected();
			}
		}
	}

	private void clearSelected() {
		selectedPiece = null;
		clear(possibleMoves);
		updateView();
	}

	private void clearBoard() {
		board.stream().forEach(pane -> {
			pane.setImage(null);
			pieces.put(pane, null);
		});
		blackCapturedPane.getChildren().clear();
		whiteCapturedPane.getChildren().clear();
	}

	private void clear(boolean[][] matrix) {
		for (boolean[] row : matrix) {
			Arrays.fill(row, false);
		}
	}

	private void set(boolean[][] source, boolean[][] target) {
		for (int i = 0; i < target.length; i++) {
			target[i] = Arrays.copyOf(source[i], source[i].length);
		}
	}

	private void updateView() {
		lblPlayer.setText(chessMatch.getCurrentPlayer().toString());
		clearBoard();
		ChessPiece[][] matchPieces = chessMatch.getPieces();
		for (int i = 0; i < matchPieces.length; i++) {
			for (int j = 0; j < matchPieces[i].length; j++) {
				ChessPosition position = ChessPosition.fromPosition(new Position(i, j));
				ImageView pane = getPane(position.toString());

				if (matchPieces[i][j] != null) {
					ChessPiece chessPiece = matchPieces[i][j];
					Image piece = getPiece(chessPiece.getClass(), chessPiece.getColor());

					pane.setImage(piece);
					pieces.put(pane, chessPiece);
				}
				pane.getParent().getStyleClass().remove("selected");
				if (possibleMoves[i][j]) {
					pane.getParent().getStyleClass().add("selected");
				}
			}
		}
		capturedPieces.get(Color.BLACK).forEach(piece -> {
			Image image = getPiece(piece.getClass(), piece.getColor());
			whiteCapturedPane.getChildren().add(new ImageView(image));
		});
		capturedPieces.get(Color.WHITE).forEach(piece -> {
			Image image = getPiece(piece.getClass(), piece.getColor());
			blackCapturedPane.getChildren().add(new ImageView(image));
		});
	}

	private String getKey(Class<?> pieceClass, Color color) {
		return String.format("%s_%s", pieceClass.getSimpleName().toLowerCase(), color.toString().toLowerCase());
	}

	private Image getPiece(Class<?> pieceClass, Color color) {
		return images.get(getKey(pieceClass, color));
	}

	private ImageView getPane(String position) {
		Optional<Field> opField = Stream.of(getClass().getDeclaredFields()).filter(f -> f.getName().equals(position))
				.findFirst();
		if (opField.isPresent()) {
			try {
				return (ImageView) opField.get().get(this);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		throw new IllegalArgumentException("Invalid position: " + position);
	}

	private void showAlert(AlertType type, String message) {
		Alert alert = new Alert(type);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
