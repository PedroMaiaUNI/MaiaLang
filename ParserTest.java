import java.io.*;
import java_cup.runtime.Symbol;

public class ParserTest {
    public static void runTest(String fileName) {
        System.out.println("\n=======================================================");
        System.out.println("--- 🌳 Teste Unitário do Parser (Análise Sintática) ---");
        System.out.println("=======================================================");

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) 
        {
            // O Scanner e o Parser são criados para o teste isolado
            scanner lexer = new scanner(reader);
            parser syntax_parser = new parser(lexer);
            
            System.out.println("Iniciando análise sintática do arquivo: " + fileName);
            
            // Inicia a análise e recebe o resultado (o nó raiz da AST)
            Symbol result = syntax_parser.parse();
            
            System.out.println("\n✅ Análise Sintática Concluída com Sucesso!");
            System.out.println("---------------------------------------------");
            System.out.println("--- Árvore Sintática Abstrata (AST) ---");
            
            // Imprime a AST retornada
            if (result != null && result.value != null) {
                // Assumimos que o objeto AST tem um método toString() que imprime a estrutura
                System.out.println(result.value.toString());
            } else {
                System.out.println("Nó raiz da AST nulo. Verifique se a regra inicial no seu parser.cup usa 'RESULT = seuNoRaiz;'");
            }

        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo '" + fileName + "' não encontrado.");
        } catch (Exception e) {
            System.err.println("\n❌ Erro Sintático Fatal durante a análise:");
            e.printStackTrace();
        }
    }
}