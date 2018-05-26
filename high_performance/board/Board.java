package high_performance.board;

import game.Game;
import sun.awt.image.ImageWatched;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.LinkedList;

public class Board {



    long[] values = new long[12];
    long[] team_total = new long[2];
    long[] occupied = new long[2];
    long[][] hashIdentities = new long[12][];

    public Board() {
        reset();
    }

    public void reset() {
        values[1 + 6] = Board.set_bit(values[1 + 6], 1 + 7 * 8);
        values[1 + 6] = Board.set_bit(values[1 + 6], 6 + 7 * 8);
        values[2 + 6] = Board.set_bit(values[2 + 6], 2 + 7 * 8);
        values[2 + 6] = Board.set_bit(values[2 + 6], 5 + 7 * 8);
        values[3 + 6] = Board.set_bit(values[3 + 6], 0 + 7 * 8);
        values[3 + 6] = Board.set_bit(values[3 + 6], 7 + 7 * 8);
        values[4 + 6] = Board.set_bit(values[4 + 6], 3 + 7 * 8);
        values[5 + 6] = Board.set_bit(values[5 + 6], 4 + 7 * 8);

        values[1] = Board.set_bit(values[1], 1);
        values[1] = Board.set_bit(values[1], 6);
        values[2] = Board.set_bit(values[2], 2);
        values[2] = Board.set_bit(values[2], 5);
        values[3] = Board.set_bit(values[3], 0);
        values[3] = Board.set_bit(values[3], 7);
        values[4] = Board.set_bit(values[4], 3);
        values[5] = Board.set_bit(values[5], 4);


        for (int i = 0; i < 8; i++) {
            values[0] = Board.set_bit(values[0], 8 + i);
            values[6] = Board.set_bit(values[6], 48 + i);
        }

        update_longs();
    }

    public void update_longs() {
        team_total[0] = values[0];
        team_total[1] = values[6];
        for (int i = 1; i < 6; i++) {
            team_total[0] = Board.xor(team_total[0], values[i]);
            team_total[1] = Board.xor(team_total[1], values[i + 6]);
        }
        occupied[0] = Board.or(team_total[0], team_total[1]);
        occupied[1] = not(occupied[0]);
    }

    public LinkedList<Move> possibleMoves(int id) {
        int p = id == 1 ? 0 : 1;
        int e = id == 1 ? 1 : 0;

        int moveIndex = 0;
        LinkedList<Move> moves = new LinkedList<>();

        int index = 0;
        long[] out = new long[50];

        if (p == 0) {
            long b1 = and(team_total[1], shift_south_west(values[0]));
            long b2 = and(team_total[1], shift_south_east(values[0]));
            long b3 = and(occupied[1], shift_south(values[0]));

            for (int i = 16; i < 64; i++) {
                if (get_bit(b1, i)) {
                    out[index] = (i-9) * 64 + i;
                    index ++;
                    //moves.add(new Move(i - 9, i, 1));
                }
                if (get_bit(b2, i)) {
                    out[index] = (i-7) * 64 + i;
                    index ++;
                    //moves.add(new Move(i - 7, i, 1));
                }
                if (get_bit(b3, i)) {
                    out[index] = (i-8) * 64 + i;
                    index++;
                    //moves.add(new Move(i - 8, i, 1));
                }
            }
        } else {
            long b1 = and(team_total[0], shift_north_east(values[6]));
            long b2 = and(team_total[0], shift_north_west(values[6]));
            long b3 = and(occupied[1], shift_north(values[6]));


            for (int i = 48; i >= 0; i--) {
                if (get_bit(b1, i)) {
                    moves.add(new Move(i + 9, i, 1));
                }
                if (get_bit(b3, i)) {
                    moves.add(new Move(i + 8, i, 1));
                }
                if (get_bit(b2, i)) {
                    moves.add(new Move(i + 7, i, 1));
                }

            }
        }

        for(int i = 0; i < 64; i++){
            if(get_bit(values[1 + p * 6], i)){
                long v = and(KNIGHT_TABLE[i], not(team_total[p]));
                for(int n = Math.max(0, i - 18); n < Math.min(63, i + 18); n++){
                    if(get_bit(v, n)){
                        out[index] = i * 64 + n;
                        index++;
                        //moves.add(new Move(i,n, 1 + p * 6));
                    }
                }
            }
        }

        return moves;


    }


    public static void main(String[] args) {

    }


    static final long not_a_file = 0xfefefefefefefefeL;
    static final long not_h_file = 0x7f7f7f7f7f7f7f7fL;

    static final long[] KNIGHT_TABLE;
    static final SlidingPieceBuffer[] ROOK_TABLE;

    static {
        KNIGHT_TABLE = new long[64];
        for(int i =0; i < 64; i++){

            for(int[] k: new int[][]{{1,2},{-1,2},{1,-2},{-1,-2},{2,1},{-2,1},{2,-1},{-2,-1}}){
                int x = fileIndex(i);
                int y = rankIndex(i);

                int nx = x + k[0];
                int ny = y + k[1];
                if(nx >= 0 && nx < 8 && ny >= 0 && ny < 8){
                    KNIGHT_TABLE[i] = set_bit(KNIGHT_TABLE[i], squareIndex(ny, nx));
                }
            }
        }

        ROOK_TABLE = new SlidingPieceBuffer[64];
        for(int i = 0; i < 64; i++){

            long p = set_bit(0,i);
            long m = 0L;

            for(int n = 0; n < 7; n++){
                m = set_bit(m, squareIndex(n, fileIndex(i)));
                m = set_bit(m, squareIndex(rankIndex(i), n));
            }

            //ROOK_TABLE[i] = new SlidingPieceBuffer(p, m, );
        }
    }

    static final void print_bits(long number) {
        String s = Long.toBinaryString(number);
        String zeros = "0000000000000000000000000000000000000000000000000000000000000000"; //String of 64 zeros
        s = zeros.substring(s.length()) + s;
        for (int i = 7; i >= 0; i--) {
            for (int n = 0; n < 8; n++) {
                //System.out.print(antiDiagonalIndex(squareIndex(i,n)) + " ");
                System.out.print(s.charAt(squareIndex(i, n)));
            }
            System.out.println();
        }
        System.out.println();
    }

    static final int rankIndex(int square_index) {
        return square_index >> 3;
    }

    static final int fileIndex(int square_index) {
        return square_index & 7;
    }

    static final int squareIndex(int rank, int file) {
        return 8 * rank + file;
    }

    static final int diagonalIndex(int square_index) {
        return (rankIndex(square_index) - fileIndex(square_index)) & 15;
    }

    static final int antiDiagonalIndex(int square_index) {
        return (rankIndex(square_index) - fileIndex(square_index)) ^ 15;
    }

    static final long toggle_bit(long number, int index) {
        return (number ^= (1L << (63 - index)));
    }

    static final long set_bit(long number, int index) {
        return (number |= (1L << (63 - index)));
    }

    static final long unset_bit(long number, int index) {
        return (number &= ~(1L << (63 - index)));
    }

    static final boolean get_bit(long number, int index) {
        return ((number >> (63 - index)) & 1) == 1 ? true : false;
    }

    static final long xor(long a, long b) {
        return a ^ b;
    }

    static final long or(long a, long b) {
        return a | b;
    }

    static final long and(long a, long b) {
        return a & b;
    }

    static final long not(long a) {
        return ~a;
    }

    static final long shift_south(long b) {
        return b >> 8;
    }

    static final long shift_north(long b) {
        return b << 8;
    }

    static final long shift_east(long b) {
        return (b << 1) & not_a_file;
    }

    static final long shift_north_east(long b) {
        return (b << 9) & not_a_file;
    }

    static final long shift_south_east(long b) {
        return (b >> 7) & not_a_file;
    }

    static final long shift_west(long b) {
        return (b >> 1) & not_h_file;
    }

    static final long shift_south_west(long b) {
        return (b >> 9) & not_h_file;
    }

    static final long shift_north_west(long b) {
        return (b << 7) & not_h_file;
    }



    static final class SlidingPieceBuffer{
        long pointer;
        long mask;
        long magic;
        int shift;
    }
}
