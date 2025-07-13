import Data.List
import Data.Char

type Vars = String

data Prop = Var Vars | Const Bool | And Prop Prop | Or Prop Prop | Not Prop | Imp Prop Prop | Iff Prop Prop | Xor Prop Prop deriving (Show,Eq)

prop1 = Var "X" `And` Var "Y" -- X /\ Y
prop2 = Var "X" `Imp` Var "Y" -- X -> Y
prop3 = Not (Var "X") `Or` (Var "Y") -- !X \/ Y
prop4 = Not (Var "X") `Iff` Not (Var "Y") -- !X <-> !Y

lookUp :: Vars -> [(Vars,Bool)] -> Bool
lookUp _ [] = False
lookUp v (vs:vars)
               | v == fst vs = snd vs
               | otherwise = lookUp v vars

data BOps = AndOp | OrOp | ImpOp | IffOp | XorOp deriving (Show,Eq)

data Token = VSym Vars | CSym Bool | BOp BOps | NotOp | LPar | RPar| PB Prop deriving (Show,Eq)

type Env = [(Vars,Bool)]

eval :: Env -> Prop -> Bool
eval x (Var v) = lookUp v x
eval env (Const b) = b
eval env (And f1 f2) = eval env f1 && eval env f2
eval env (Or f1 f2) = eval env f1 || eval env f2
eval env (Not x) = not (eval env x)
eval env (Imp f1 f2) = eval env f1 `imp` eval env f2
eval env (Iff f1 f2) = eval env f1 `iff` eval env f2
eval env (Xor f1 f2) = eval env f1 `xor` eval env f2

lexer :: String -> [Token]
lexer "" = []
lexer ('(':s) = LPar : lexer s
lexer (')':s) = RPar : lexer s
lexer ('/':'\\':s) = BOp AndOp : lexer s
lexer ('\\':'/':s) = BOp OrOp : lexer s
lexer ('!':s) = NotOp : lexer s
lexer ('-':'>':s) = BOp ImpOp : lexer s 
lexer ('<':'-':'>':s) = BOp IffOp : lexer s
lexer ('<':'+':'>':s) = BOp XorOp : lexer s
lexer ('t':'t':s) = CSym True : lexer s
lexer ('f':'f':s) = CSym False : lexer s
lexer (c:s) | isAlpha c =
  let (num,rst) = span (\x -> isAlphaNum x || x == '_') s
   in VSym (c:num) : lexer rst
lexer (c:s) | isSpace c = lexer s
lexer s = error ("Lexical error: " ++ s)

parseProp :: [Token] -> Prop
parseProp pp = pr [] pp

pr :: [Token] -> [Token] -> Prop
pr [PB b] [] = b
pr (VSym x : s) b = pr (PB (Var x) : s) b
pr (CSym x : s) b = pr (PB (Const x) : s) b
pr (PB b2 : BOp AndOp : PB b1 : s) b = pr (PB (And b1 b2) : s) b
pr (PB b2 : BOp OrOp : PB b1 : s) b = pr (PB (Or b1 b2) : s) b
pr (PB b2 : BOp ImpOp : PB b1 : s) b = pr (PB (Imp b1 b2) : s) b
pr (PB b2 : BOp IffOp : PB b1 : s) b = pr (PB (Iff b1 b2) : s) b
pr (PB b2 : BOp XorOp : PB b1 : s) b = pr (PB (Xor b1 b2) : s) b
pr (RPar : PB b : LPar : s) p = pr (PB b : s) p
pr (PB b : NotOp : s) p = pr (PB (Not b) : s) p
pr s (x:xs) = pr (x:s) xs
pr s [] = error ("Parse error: " ++ show s)

removeDups :: (Eq a) => [a] -> [a]
removeDups = foldr (\x -> (x :) . filter (/= x)) []

gv :: Prop -> [Vars]
gv (Var x) = [x]
gv (And x y) = removeDups (gv x ++ gv y)
gv (Or x y) = removeDups (gv x ++ gv y)
gv (Not x) = gv x
gv (Imp x y) = removeDups (gv x ++ gv y)
gv (Iff x y) = removeDups (gv x ++ gv y)
gv (Xor x y) = removeDups (gv x ++ gv y)
gv _ = []

genEnvs :: [Vars] -> [[(Vars,Bool)]]
genEnvs = foldr (\x y -> map ((x,True):) y ++ map ((x,False):) y) [[]]

evalAll :: Prop -> [[(Vars,Bool)]] -> Maybe Env
evalAll x [] = Nothing
evalAll x (y:ys) | (eval y x == True) = Just y
                 | otherwise = evalAll x ys

findSat :: Prop -> Maybe Env
findSat p = evalAll p (genEnvs (gv p))

solve :: String -> String
solve s = if (findSat (parseProp (lexer s)) == Nothing) then "No solution." else "Satisfiable."

imp :: Bool -> Bool -> Bool
imp True True = True
imp True False = False
imp False True = True
imp False False = True

iff :: Bool -> Bool -> Bool
iff True True = True
iff True False = False
iff False True = False
iff False False = True

xor :: Bool -> Bool -> Bool
xor True True = False
xor True False = True
xor False True = True
xor False False = False