import Data.List
import Data.Char

-- Variables
type Vars = String
-- Arithmetic expressions
data AExpr = Var Vars | Const Integer -- variables and constants
            | Add AExpr AExpr | Sub AExpr AExpr -- addition and subtraction
            | Mul AExpr AExpr | Div AExpr AExpr -- multiplication and division
            | Exp AExpr AExpr | Mod AExpr AExpr -- exponential and remainder
      deriving Show
-- Boolean expressions
data BExpr = TT | FF -- the true and false constants
           | And BExpr BExpr | Or BExpr BExpr | Not BExpr -- boolean operations
           | Eq AExpr AExpr -- equality of arithmetic expressions
           | Lt AExpr AExpr -- true if the first is less than the second
           | Lte AExpr AExpr -- true if it’s less than or equal to
    deriving Show
-- Instructions
data Instr = Assign Vars AExpr -- assign X to the value of an expression
          | IfThenElse BExpr Instr Instr -- conditional
          | While BExpr Instr -- looping construct
          | Do [Instr] -- a block of several instructions
          | Nop -- the "do nothing" instruction
    deriving Show

type Env = [ ( Vars,Integer) ]
-- Environment
-- update (x,v) e sets the value of x to v and keeps other variables in e the same
update :: (Vars, Integer) -> Env -> Env
update (v,i) [] = [(v,i)]
update (v,i) ((vars,int):es) | v == vars = (v,i) : es
update (v,i) ((vars,int):es) | v /= vars = (vars, int) : update (v,i) es

-- Question 1. Evaluating arithmetic and boolean expressions

evala :: Env -> AExpr -> Integer
evala envs (Var v) = maybe (error ("Variable not found:" ++ v)) (id) (lookup v envs)
evala envs (Const n) = n
evala envs (Add v1 v2) = evala envs v1 + evala envs v2
evala envs (Sub v1 v2) = evala envs v1 - evala envs v2
evala envs (Mul v1 v2) = evala envs v1 * evala envs v2
evala envs (Div v1 v2) = evala envs v1 `div` evala envs v2
evala envs (Mod v1 v2) = evala envs v1 `mod` evala envs v2
evala envs (Exp v1 v2) = evala envs v1 ^ evala envs v2

evalb :: Env -> BExpr -> Bool
evalb envs (TT) = True
evalb envs (FF) = False
evalb envs (And f1 f2) = evalb envs f1 && evalb envs f2
evalb envs (Or f1 f2) = evalb envs f1 || evalb envs f2
evalb envs (Not f1) = not (evalb envs f1)
evalb envs (Eq f1 f2) =  evala envs f1 == evala envs f2
evalb envs (Lt f1 f2) = evala envs f1 < evala envs f2
evalb envs (Lte f1 f2) = evala envs f1 <= evala envs f2

--exec (While (Not (Eq (Var "X") (Const 0))) (Do [ (Assign "Y" (Mul (Var "X") (Var "Y"))) , (Assign "X" (Sub (Var "X") (Const 1)))])) [("X",5),("Y",1)]
-- Question 2. Executing instructions
exec :: Instr -> Env -> Env
exec (Assign var ex) env = update (var, evala env ex) env
exec (IfThenElse b ex1 ex2) env = if (evalb env b) then exec ex1 env else exec ex2 env
exec (While b ex1) env = if (evalb env b) then exec (While b ex1) (exec ex1 env) else env
exec (Do []) env = env
exec (Do (i:is)) env = exec (Do is) (exec i env)
exec (Nop) env = env

-- Question 3. Lexical analysis
data UOps = NotOp deriving Show
data BOps = AddOp | SubOp | MulOp | DivOp | ModOp | ExpOp
                  | AndOp | OrOp | EqOp | LtOp | LteOp
      deriving Show
data Keywords = IfK | ThenK | ElseK | WhileK | NopK
      deriving Show
data Token = VSym String | CSym Integer | BSym Bool
            | UOp UOps | BOp BOps | AssignOp
            | LPar | RPar | LBra | RBra | Semi
            | Keyword Keywords
            | Err String
            | PA AExpr | PB BExpr | PI Instr | Block [Instr]
      deriving Show

lexer :: String -> [Token]
lexer "" = []
lexer ('i':'f':s) = Keyword IfK : lexer s
lexer ('t':'h':'e':'n':s) = Keyword ThenK: lexer s
lexer ('e':'l':'s':'e':s) = Keyword ElseK : lexer s
lexer ('w':'h':'i':'l':'e':s) = Keyword WhileK : lexer s
lexer ('n':'o':'p':s) = Keyword NopK : lexer s
lexer ('(':s) = LPar : lexer s
lexer (')':s) = RPar : lexer s
lexer ('{':s) = LBra : lexer s
lexer ('}':s) = RBra : lexer s
lexer (';':s) = Semi : lexer s
lexer ('+':s) = BOp AddOp : lexer s
lexer ('-':s) = BOp SubOp : lexer s
lexer ('*':s) = BOp MulOp : lexer s
lexer ('/':'\\':s) = BOp AndOp : lexer s
lexer ('/':s) = BOp DivOp : lexer s
lexer ('^':s) = BOp ExpOp : lexer s
lexer ('%':s) = BOp ModOp : lexer s
lexer (':':'=':s) = AssignOp : lexer s
lexer ('\\':'/':s) = BOp OrOp : lexer s
lexer ('!':s) = UOp NotOp : lexer s
lexer ('=':'=':s) = BOp EqOp : lexer s
lexer ('<':'=':s) = BOp LteOp : lexer s
lexer ('<':s) = BOp LtOp : lexer s
lexer ('T':s) = BSym True : lexer s
lexer ('F':s) = BSym False : lexer s
lexer (c:s) | isDigit c =
  let (num,rst) = span isDigit s
   in CSym (read (c:num)) : lexer rst
lexer (c:s) | isLower c =
  let (num,rst) = span (\x -> isAlphaNum x || x == '_') s
   in VSym (c:num) : lexer rst
lexer (c:s) | isSpace c = lexer s
lexer s = error ("Lexical error: " ++ s)


-- Question 4. Parsing
parser :: [Token] -> [Instr]
parser pp = reverse (pr [] pp)

run :: [Instr] -> Env
run p = exec (Do p) []

pr :: [Token] -> [Token] -> [Instr]
pr [] [] = []
pr (Semi : PA v2 : AssignOp : PA (Var v) : s) b = pr (PI (Assign v v2) : s) b
pr (VSym x : s) b = pr (PA (Var x) : s) b
pr (CSym x : s) b = pr (PA (Const x) : s) b
pr (BSym True : s) b = pr (PB TT : s) b
pr (BSym False : s) b = pr (PB FF : s) b
pr (PB b2 : BOp AndOp : PB b1 : s) b = pr (PB (And b1 b2) : s) b
pr (PB b2 : BOp OrOp : PB b1 : s) b = pr (PB (Or b1 b2) : s) b
pr (PA b2 : BOp EqOp : PA b1 : s) b = pr (PB (Eq b1 b2) : s) b
pr (PA b2 : BOp LtOp : PA b1 : s) b = pr (PB (Lt b1 b2) : s) b
pr (PA b2 : BOp LteOp : PA b1 : s) b = pr (PB (Lte b1 b2) : s) b
pr (PA b2 : BOp AddOp : PA b1 : s) b = pr (PA (Add b1 b2) : s) b
pr (PA b2 : BOp SubOp : PA b1 : s) b = pr (PA (Sub b1 b2) : s) b
pr (PA b2 : BOp MulOp : PA b1 : s) b = pr (PA (Mul b1 b2) : s) b
pr (PA b2 : BOp DivOp : PA b1 : s) b = pr (PA (Div b1 b2) : s) b
pr (PA b2 : BOp ModOp : PA b1 : s) b = pr (PA (Mod b1 b2) : s) b
pr (PA b2 : BOp ExpOp : PA b1 : s) b = pr (PA (Exp b1 b2) : s) b
pr (PI e : Keyword ElseK : PI t: Keyword ThenK : PB i : Keyword IfK : s) b = pr (PI (IfThenElse i t e) : s) b
pr (PI i : PB c : Keyword WhileK : s) b = pr (PI (While c i) : s) b
--pr (Keyword NopK : s) b = pr (PI Nop : s) b
pr (Semi : Keyword NopK : s) b = pr (PI Nop : s) b
pr (Semi : Block l : s) p = pr ( PI (Do (l)) : s) p
pr (RPar : PB b : LPar : s) p = pr (PB b : s) p
pr (RPar : PA a : LPar : s) p = pr (PA a : s) p
pr (RPar : PI i : LPar : s) p = pr (PI i : s) p
pr (RBra : PI i : ts)            q = pr (PI i : ts) q
pr (RBra : Semi : PI i : ts) q = pr (Block [i] : ts) q
pr (LBra : s) p = pr (Block [] : s) p
pr (PI i : Block ls : s) p = pr (Block (i:ls) :s) p
pr (RBra : Block ls : s) p = pr (PI (Do (reverse ls)) : s) p
--pr (RBra : PI ls : s) p = pr (Block [ls] : s) p
pr (PB b : UOp NotOp : s) p = pr (PB (Not b) : s) p
pr s (x:xs) = pr (x:s) xs
pr (PI i : s) [] = i : (pr s [])
pr s p = error ("Cannot find pattern for " ++ (show s)) 


linesToString :: [String] -> String
linesToString [] = ""
linesToString (x:xs) = x ++ (linesToString xs)
-- Question 5. I/O
main :: IO ()
main = do 
  putStrLn "Enter file name"
  filename <- getLine
  input <- readFile filename
  let ls = linesToString (lines input)
  let lexLine = lexer ls
  let parseInstr = parser lexLine
  let execute = run parseInstr
  print execute