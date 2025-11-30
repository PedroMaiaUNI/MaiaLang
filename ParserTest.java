import java.io.*;
import java_cup.runtime.Symbol;

public class ParserTest {
    public static void runTest(String fileName) {
        System.out.println("--- Teste Unitário do Parser (Análise Sintática) ---");

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) 
        {
            scanner lexer = new scanner(reader);
            parser syntax_parser = new parser(lexer);
            
            System.out.println("Iniciando análise sintática do arquivo: " + fileName);
            
            Symbol result = syntax_parser.parse();
            
            System.out.println("\n Análise Sintática Concluída com Sucesso!");
            System.out.println("---------------------------------------------");
            System.out.println("--- Árvore Sintática Abstrata (AST) ---");
            
            if (result != null && result.value != null) {
                System.out.println(result.value.toString());
            } else {
                System.out.println("Nó raiz da AST nulo.");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo '" + fileName + "' não encontrado.");
        } catch (Exception e) {
            System.err.println("\n Erro Sintático Fatal durante a análise:");
            e.printStackTrace();
        }
    }
}