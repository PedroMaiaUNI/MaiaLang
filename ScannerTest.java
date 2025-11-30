import java.io.*;
import java_cup.runtime.Symbol;

public class ScannerTest {
    public static void runTest(String fileName) {
        System.out.println("--- Teste Unitário do Scanner (Análise Léxica) ---");

        try (FileReader fileReader = new FileReader(fileName)) 
        {
            // Nota: O JFlex espera um objeto Reader para a leitura
            scanner lexer = new scanner(fileReader);
            Symbol token;
            
            System.out.println("Linha, Coluna: Tipo=Token <Valor>");
            
            // Loop para consumir e imprimir todos os tokens até o EOF
            do {
                token = lexer.next_token();

                // 1. VERIFICAÇÃO DE FIM DE ARQUIVO (EOF)
                if (token.sym == sym.EOF) {
                    System.out.println("Linha " + (token.left + 1) + ", Coluna " + (token.right + 1) + ": Tipo=EOF");
                    break; // Sai do loop imediatamente
                }

                // 2. IMPRESSÃO DOS TOKENS
                String tokenType = sym.terminalNames[token.sym]; 
                
                String tokenValue = token.value != null ? "<" + token.value + ">" : "";
                
                System.out.printf("Linha %d, Coluna %d: Tipo=%s %s%n", 
                                  token.left + 1, token.right + 1, tokenType, tokenValue);
                
            } while (true);  
            
            System.out.println("Teste do Scanner Concluído com Sucesso! (Tokens lidos até o EOF).");
        
        } catch (FileNotFoundException e) {
            System.err.println("Erro: Arquivo '" + fileName + "' não encontrado.");
        } catch (Exception e) {
            System.err.println("Erro Léxico Fatal durante a análise:");
            e.printStackTrace();
        }
    }
}