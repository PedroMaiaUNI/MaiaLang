public class Main {
    private static final String INPUT_FILE = "input.txt";

    public static void main(String[] args) {
        
        // 1. Executa o teste do Scanner (Análise Léxica)
        ScannerTest.runTest(INPUT_FILE);
        
        // 2. Executa o teste do Parser (Análise Sintática e AST)
        // Nota: Um novo Reader precisa ser criado para o Parser, pois o ScannerTest já consumiu o arquivo.
        ParserTest.runTest(INPUT_FILE);

        System.out.println("\n=======================================================");
        System.out.println("--- ✅ Demonstração Integrada Concluída com Sucesso! ---");
        System.out.println("=======================================================");
    }
}