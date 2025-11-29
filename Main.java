import java.io.*;
import java_cup.runtime.*;
import java.util.List;

public class Main {
    
    public static void runScannerTest(String filename) {
        System.out.println("--- Teste do Scanner (Análise Léxica) ---");
        try (Reader reader = new FileReader(filename)) {
            scanner scanner = new scanner(reader);
            Symbol token;
            
            while (true) {
                token = scanner.next_token();
                
                if (token.sym == sym.EOF) {
                    break;
                }
                
                String tokenName = sym.terminalNames[token.sym];
                String value = (token.value != null) ? " <" + token.value + ">" : "";
                
                System.out.println("Linha " + token.left + ", Coluna " + token.right + 
                                   ": Tipo=" + tokenName + value);
            }
        } catch (Exception e) {
            System.err.println("Erro durante a Análise Léxica: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Fim do Teste do Scanner ---\n");
    }

    public static void runParserTest(String filename) {
        System.out.println("--- Teste do Parser (Análise Sintática) ---");
        try (Reader reader = new FileReader(filename)) {
            scanner scanner = new scanner(reader);
            
            parser parser = new parser(scanner);
            
            parser.parse();
            
            System.out.println("\nAnálise Sintática concluída com sucesso.");
            
        } catch (Exception e) {
            System.err.println("\nErro durante a Análise Sintática/Semântica (Parser): " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("--- Fim do Teste do Parser ---\n");
    }

    public static void main(String[] args) {
        String inputFilename = "input.txt";

        runScannerTest(inputFilename);
        runParserTest(inputFilename);
    }
}

class Program { }
class VarDecl { String type; String id; public VarDecl(String t, String i) { type=t; id=i; } }
class FunDecl { String type; String id; List<VarDecl> params; Stmt body; public FunDecl(String t, String i, List<VarDecl> p, Stmt b) { type=t; id=i; params=p; body=b; } }
class Stmt { }
class BlockStmt extends Stmt { List<Stmt> stmts; public BlockStmt(List<Stmt> s) { stmts=s; } }
class IfStmt extends Stmt { Expr cond; Stmt thenStmt; Stmt elseStmt; public IfStmt(Expr c, Stmt t, Stmt e) { cond=c; thenStmt=t; elseStmt=e; } }
class WhileStmt extends Stmt { Expr cond; Stmt body; public WhileStmt(Expr c, Stmt b) { cond=c; body=b; } }
class ReturnStmt extends Stmt { Expr expr; public ReturnStmt(Expr e) { expr=e; } }
class AssignStmt extends Stmt { String id; Expr expr; public AssignStmt(String i, Expr e) { id=i; expr=e; } }
class FunCallStmt extends Stmt { String id; List<Expr> args; public FunCallStmt(String i, List<Expr> a) { id=i; args=a; } }
class Expr { }
class IntConst extends Expr { Integer value; public IntConst(Integer v) { value=v; } }
class FloatConst extends Expr { Float value; public FloatConst(Float v) { value=v; } }
class IdExpr extends Expr { String id; public IdExpr(String i) { id=i; } }
class BinaryExpr extends Expr { String op; Expr left; Expr right; public BinaryExpr(String o, Expr l, Expr r) { op=o; left=l; right=r; } }
class UnaryExpr extends Expr { String op; Expr expr; public UnaryExpr(String o, Expr e) { op=o; expr=e; } }
class FunCallExpr extends Expr { String id; List<Expr> args; public FunCallExpr(String i, List<Expr> a) { id=i; args=a; } }