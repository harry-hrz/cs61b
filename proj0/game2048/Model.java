package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author Harry Zhang
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private final Board _board;
    /** Current score. */
    private int _score;
    /** Maximum score so far.  Updated when game ends. */
    private int _maxScore;
    /** True iff game is ended. */
    private boolean _gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        _board = new Board(size);
        _score = _maxScore = 0;
        _gameOver = false;
    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        _board = new Board(rawValues);
        this._score = score;
        this._maxScore = maxScore;
        this._gameOver = gameOver;
    }

    /** Same as above, but gameOver is false. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore) {
        this(rawValues, score, maxScore, false);
    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     * */
    public Tile tile(int col, int row) {
        return _board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return _board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (_gameOver) {
            _maxScore = Math.max(_score, _maxScore);
        }
        return _gameOver;
    }

    /** Return the current score. */
    public int score() {
        return _score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return _maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        _score = 0;
        _gameOver = false;
        _board.clear();
        setChanged();
    }

    /** Allow initial game board to announce a hot start to the GUI. */
    public void hotStartAnnounce() {
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        _board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE.
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     */
    public void tilt(Side side) {
        _board.setViewingPerspective(side); // so that the only direction we consider is moving up
        int board_size = _board.size();
        for (int c = 0; c < board_size; c++) {
            calStepAndMove(c, board_size);
        }
        _board.setViewingPerspective(Side.NORTH);
        checkGameOver();
    }

    public void calStepAndMove(int col, int bord_size) {
        int[] col_move = new int[bord_size];
        int[] merged_row = new int[bord_size];
        for (int row = bord_size - 2; row >= 0; row--) {
            int cur_row = row;
            Tile t0 = _board.tile(col, row);
            if (t0 == null){
                col_move[row] = 0;
                continue;
            }
            for (int r = row + 1; r < bord_size; r++) {
                if (_board.tile(col, r) == null) {
                    col_move[row] += 1;
                    cur_row += 1;
                } else {
                    Tile t = _board.tile(col, r);
                    if (t.value() == t0.value() && merged_row[r] == 0 && r - cur_row == 1) {
                        col_move[row] += 1;
                        merged_row[r] = 1;
                        break;
                    }
                }
            }
            if (col_move[row] > 0) {
                updateOneTile(col, row, col_move[row]);
            }
        }
    }

    public void updateOneTile(int c, int r, int col_move) { // return scores added
        boolean add_score;
        if (col_move > 0) {
            Tile t = _board.tile(c, r);
            add_score = _board.move(c, r + col_move, t);
            if (add_score) {
                _score += _board.tile(c, r + col_move).value();
            }
        }
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        _gameOver = checkGameOver(_board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     */
    public static boolean emptySpaceExists(Board b) {
        int board_size = b.size();
        int yes = 0;
        for (int col = 0; col < board_size; col++) {
            for (int row = 0; row < board_size; row++) {
                Tile t = b.tile(col, row);
                if (t == null) {
                    yes = 1;
                    break;
                }
            }
            if (yes == 1) {
                break;
            }
        }
        return yes == 1;
    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by this.MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        int board_size = b.size();
        int yes = 0;
        for (int col = 0; col < board_size; col++) {
            for (int row = 0; row < board_size; row++) {
                Tile t = b.tile(col, row);
                if (t != null) {
                    if (t.value() == MAX_PIECE) {
                        yes = 1;
                        break;
                    }
                }
            }
            if (yes == 1) {
                break;
            }
        }
        return yes == 1;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {
        int board_size = b.size();
        int yes = 0;
        if (emptySpaceExists(b)) {
            yes = 1;
        } else {
            int m_e_v = 0;
            int m_w_v = 0;
            int m_s_v = 0;
            int m_n_v = 0;
            for (int col = 0; col < board_size; col++) {
                for (int row = 0; row < board_size; row++) {
                    Tile t = b.tile(col, row);
                    int tv = t.value();
                    if (col + 1 < board_size) {m_e_v = b.tile(col + 1, row).value();}
                    if (col - 1 >= 0) {m_w_v = b.tile(col - 1, row).value();}
                    if (row - 1 >= 0) {m_n_v = b.tile(col, row - 1).value();}
                    if (row + 1 < board_size) {m_s_v = b.tile(col, row + 1).value();}
                    if (col - 1 < 0 && row - 1 < 0) {
                        if(m_e_v == tv || m_s_v == tv) {
                            yes = 1;
                            break;
                        }
                    } else if (row - 1 < 0 && col - 1 >= 0 && col + 1 < board_size) {
                        if(m_w_v == tv || m_e_v == tv || m_s_v == tv) {
                            yes = 1;
                            break;
                        }
                    } else if (col + 1 == board_size && row - 1 < 0) {
                        if(m_w_v == tv || m_s_v == tv) {
                            yes = 1;
                            break;
                        }
                    } else if (col + 1 == board_size && row - 1 >= 0 && row + 1 < board_size) {
                        if(m_w_v == tv || m_s_v == tv || m_n_v == tv) {
                            yes = 1;
                            break;
                        }
                    } else if (col + 1 == board_size && row + 1 == board_size) {
                        if(m_w_v == tv || m_n_v == tv) {
                            yes = 1;
                            break;
                        }
                    } else if (row + 1 == board_size && col + 1 < board_size && col - 1 >= 0) {
                        if(m_w_v == tv || m_e_v == tv || m_n_v == tv) {
                            yes = 1;
                            break;
                        }
                    } else if (row + 1 == board_size && col - 1 < 0) {
                        if(m_n_v == tv || m_e_v == tv) {
                            yes = 1;
                            break;
                        }
                    } else if (col - 1 < 0 && row + 1 < board_size && row - 1 >= 0){
                        if(m_e_v == tv || m_s_v == tv || m_n_v == tv) {
                            yes = 1;
                            break;
                        }
                    } else {
                        if(m_w_v == tv || m_e_v == tv || m_n_v == tv || m_s_v == tv) {
                            yes = 1;
                            break;
                        }
                    }
                }
                if (yes == 1) {
                    break;
                }
            }
        }
        return yes == 1;
    }

    /** Returns the model as a string, used for debugging. */
    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    /** Returns whether two models are equal. */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    /** Returns hash code of Model’s string. */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
