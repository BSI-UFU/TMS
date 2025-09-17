/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;

/**
 *
 * @author kensl
 */
@DisplayName("Testes do Bingo Kata")
public class BingoCardTest {

    private BingoCard card;
    private int[][] sampleGrid;

    @BeforeEach
    void setUp() {
        // Uma cartela 5x5 válida para os testes
        sampleGrid = new int[][]{
            { 1,  2,  3,  4,  5},
            { 6,  7,  8,  9, 10},
            {11, 12, 13, 14, 15}, // O número 13 está no espaço LIVRE
            {16, 17, 18, 19, 20},
            {21, 22, 23, 24, 25}
        };
        card = new BingoCard(sampleGrid);
    }

    @Test
    @DisplayName("Cenário de Falha: Não deve criar cartela com grade inválida (não 5x5)")
    void testInvalidGridThrowsException() {
        int[][] badGrid = new int[][]{ {1, 2}, {3, 4} };
        assertThrows(IllegalArgumentException.class, () -> {
            new BingoCard(badGrid);
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new BingoCard(null);
        });
    }

    @Test
    @DisplayName("Não deve ter Bingo logo após a criação (só o espaço livre)")
    void testNoBingoOnStart() {
        assertFalse(card.hasBingo());
    }

    @Test
    @DisplayName("Cenário de Sucesso: Deve dar Bingo com uma linha (sem espaço livre)")
    void testRowBingo() {
        card.call(1);
        card.call(2);
        card.call(3);
        card.call(4);
        assertFalse(card.hasBingo(), "Não deve dar bingo com 4 números");
        card.call(5); // Chama o último número da linha
        assertTrue(card.hasBingo(), "Deve dar bingo com a linha 0 completa");
    }
    
    @Test
    @DisplayName("Cenário de Sucesso: Deve dar Bingo com uma linha (usando o espaço livre)")
    void testRowBingoWithFreeSpace() {
        card.call(11);
        card.call(12);
        // O número 13 é o espaço livre, já está marcado
        card.call(14);
        card.call(15);
        assertTrue(card.hasBingo(), "Deve dar bingo com a linha 2 completa (com espaço livre)");
    }

    @Test
    @DisplayName("Cenário de Sucesso: Deve dar Bingo com uma coluna (sem espaço livre)")
    void testColumnBingo() {
        card.call(1);
        card.call(6);
        card.call(11);
        card.call(16);
        card.call(21);
        assertTrue(card.hasBingo(), "Deve dar bingo com a coluna 0 completa");
    }

    @Test
    @DisplayName("Cenário de Sucesso: Deve dar Bingo com uma coluna (usando o espaço livre)")
    void testColumnBingoWithFreeSpace() {
        card.call(3);
        card.call(8);
        // O número 13 (posição [2][2]) é o espaço livre
        card.call(18);
        card.call(23);
        assertTrue(card.hasBingo(), "Deve dar bingo com a coluna 2 completa (com espaço livre)");
    }

    @Test
    @DisplayName("Cenário de Sucesso: Deve dar Bingo com a diagonal principal (com espaço livre)")
    void testDiagonal1Bingo() {
        card.call(1);
        card.call(7);
        // O número 13 (posição [2][2]) é o espaço livre
        card.call(19);
        card.call(25);
        assertTrue(card.hasBingo(), "Deve dar bingo com a diagonal principal");
    }

    @Test
    @DisplayName("Cenário de Sucesso: Deve dar Bingo com a diagonal secundária (com espaço livre)")
    void testDiagonal2Bingo() {
        card.call(5);
        card.call(9);
        // O número 13 (posição [2][2]) é o espaço livre
        card.call(17);
        card.call(21);
        assertTrue(card.hasBingo(), "Deve dar bingo com a diagonal secundária");
    }
    
    @Test
    @DisplayName("Cenário de Falha: Não deve dar Bingo com números aleatórios (não vencedores)")
    void testNoBingoOnRandomCalls() {
        card.call(1);
        card.call(10);
        card.call(19);
        card.call(22);
        card.call(99); // Número que não existe na cartela
        assertFalse(card.hasBingo());
    }
}