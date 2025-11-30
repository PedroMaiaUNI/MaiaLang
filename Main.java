public class Main {
    private static final String INPUT_FILE = "input.txt";

    public static void main(String[] args) {
        
        // Teste do Scanner (Análise Léxica)
        ScannerTest.runTest(INPUT_FILE);
        
        // Teste do Parser (Análise Sintática e AST)
        ParserTest.runTest(INPUT_FILE);

        System.out.println("--- Demonstração Integrada Concluída com Sucesso! ---");
    }
}