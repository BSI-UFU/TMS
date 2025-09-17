/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author kensl
 */
public class BingoCard {

    private static final int SIZE = 5;
    private static final int FREE_SPACE_ROW = 2;
    private static final int FREE_SPACE_COL = 2;

    private final int[][] numbers;
    private final boolean[][] marks;

    /**
     * Cria uma nova cartela de Bingo com os números fornecidos.
     *
     * @param cardNumbers Uma matriz 5x5 de inteiros.
     * @throws IllegalArgumentException se a matriz não for 5x5.
     */
    public BingoCard(int[][] cardNumbers) {
        // Cenário de falha (entrada inválida)
        if (!isValidGrid(cardNumbers)) {
            throw new IllegalArgumentException("A cartela deve ser 5x5.");
        }

        this.numbers = cardNumbers;
        this.marks = new boolean[SIZE][SIZE];
        
        // Marca o espaço "LIVRE" (FREE) no centro
        this.marks[FREE_SPACE_ROW][FREE_SPACE_COL] = true;
    }

    /**
     * Valida se a grade de números fornecida é 5x5.
     */
    private boolean isValidGrid(int[][] grid) {
        if (grid == null || grid.length != SIZE) {
            return false;
        }
        for (int[] row : grid) {
            if (row == null || row.length != SIZE) {
                return false;
            }
        }
        return true;
    }

    /**
     * "Chama" um número e o marca na cartela se ele existir.
     *
     * @param number O número chamado.
     */
    public void call(int number) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (this.numbers[i][j] == number) {
                    this.marks[i][j] = true;
                    return; // Número encontrado e marcado
                }
            }
        }
        // Se o número não estiver na cartela, nada acontece.
    }

    /**
     * Verifica se a cartela é vencedora (BINGO).
     *
     * @return true se houver uma linha, coluna ou diagonal completa.
     */
    public boolean hasBingo() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    /**
     * Verifica todas as 5 linhas.
     */
    private boolean checkRows() {
        for (int i = 0; i < SIZE; i++) {
            boolean rowComplete = true;
            for (int j = 0; j < SIZE; j++) {
                if (!this.marks[i][j]) {
                    rowComplete = false;
                    break;
                }
            }
            if (rowComplete) return true;
        }
        return false;
    }

    /**
     * Verifica todas as 5 colunas.
     */
    private boolean checkColumns() {
        for (int j = 0; j < SIZE; j++) {
            boolean colComplete = true;
            for (int i = 0; i < SIZE; i++) {
                if (!this.marks[i][j]) {
                    colComplete = false;
                    break;
                }
            }
            if (colComplete) return true;
        }
        return false;
    }

    /**
     * Verifica as 2 diagonais.
     */
    private boolean checkDiagonals() {
        // Diagonal 1 (topo-esquerda para baixo-direita)
        boolean diag1Complete = true;
        for (int i = 0; i < SIZE; i++) {
            if (!this.marks[i][i]) {
                diag1Complete = false;
                break;
            }
        }
        if (diag1Complete) return true;

        // Diagonal 2 (topo-direita para baixo-esquerda)
        boolean diag2Complete = true;
        for (int i = 0; i < SIZE; i++) {
            if (!this.marks[i][(SIZE - 1) - i]) {
                diag2Complete = false;
                break;
            }
        }
        return diag2Complete; // Retorna true se a diag2 for completa
    }
}
