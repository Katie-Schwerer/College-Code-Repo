import Data.Char
-- import Data.List

-- T ::= Z | T -> T
data Types = Ints | Fun Types Types

-- /\ ::= \/ | /\ /\ | \ \/ /\ | C Int | /\ + /\ | IfZero(/\,/\,/\) | Y
data Terms a = Var a | App (Terms a) (Terms a) | Abs (Terms (Maybe a)) | Const Integer | Add (Terms a) (Terms a) | IfZero (Terms a) (Terms a) (Terms a) | Y deriving (Eq,Show)

data Token = VSym String | CSym Integer | AddOp | IfZeroOp | YComb| LPar | RPar | Dot | Comma | Backslash | Err String | PT (Terms String) deriving (Eq,Show)


--1&2
unitTerms :: a -> Terms a
unitTerms a = Var a

liftTerms :: (a -> Terms b) -> Maybe a -> Terms (Maybe b)
liftTerms f (Nothing) = Var Nothing
liftTerms f (Just a) = Just <$> f a

bindTerms :: (a -> Terms b) -> Terms a -> Terms b
bindTerms f (Var a) = f a
bindTerms f (App a1 a2) = App (bindTerms f a1) (bindTerms f a2)
bindTerms f (Abs a) = Abs (bindTerms (liftTerms f) a) 
bindTerms f (Const int) = Const int
bindTerms f (Add a1 a2) = Add (bindTerms f a1) (bindTerms f a2)
bindTerms f (IfZero a1 a2 a3) = IfZero (bindTerms f a1) (bindTerms f a2) (bindTerms f a3)
bindTerms f (Y) = Y

instance Functor Terms where
  fmap f = bindTerms (unitTerms . f)

instance Applicative Terms where
  pure = unitTerms
  p <*> pr = bindTerms (<$> pr) p

instance Monad Terms where
  return = unitTerms
  pr >>= p = bindTerms p pr

lexer :: String -> [Token]
lexer (x:xs) | isLower x = VSym (x:vc) : lexer rst where (vc,rst) = span isAlphaNum xs
lexer ('+':xs) = AddOp : lexer xs
lexer ('I':'f':'Z':'e':'r':'o':xs) = IfZeroOp : lexer xs
lexer ('Y':xs) = YComb : lexer xs
lexer ('(':xs) = LPar : lexer xs
lexer (')':xs) = RPar : lexer xs
lexer ('.': xs) = Dot : lexer xs
lexer ('\\':xs) = Backslash : lexer xs
lexer (',':xs) = Comma : lexer xs
lexer ('-':c:s) | isDigit c =
  let (num,rst) = span isDigit s
  in CSym (negative (read (c:num))) : lexer rst
lexer (c:s) | isDigit c =
  let (num,rst) = span isDigit s
  in CSym (read (c:num)) : lexer rst
lexer (c:s) | isSpace c = lexer s
lexer "" = []


parser :: [Token] -> Either (Terms String) String
parser ts = case sr [] ts of
  [ PT t ] -> Left t
  [ Err s] -> Right ("Lexical error: " ++ s)
  s        -> Right ("Parse error: " ++ show s)


sr :: [Token] -> [Token] -> [Token]
--symbols
sr (VSym v : s)                              p = sr (PT (Var v) : s) p
sr (CSym c : s)                              p = sr (PT (Const c) : s) p
--functions
sr (PT t2 : PT t1 : s)                       p = sr (PT (App t1 t2) : s) p
sr (PT t : Dot : PT (Var x) : Backslash : s) p = sr (PT (Abs (capture x t)) : s) p
sr (PT t2 : AddOp : PT t1 :s)                p = sr (PT (Add t1 t2) : s) p
sr (RPar : PT t3 : Comma : PT t2 : Comma : PT t1 : LPar : IfZeroOp : s) 
                                             p = sr (PT (IfZero t1 t2 t3) : s) p
sr (YComb : s)                               p = sr (PT Y : s) p
sr (RPar : PT t : LPar : s)                  p = sr (PT t : s) p
sr (Err e : s)                               p = [Err e]
sr s                                     (p:q)= sr (p:s) q
sr s                                        [] = s

mul = "Y(\\f.\\m.\\n.IfZero(m,0,n+(f(m+-1)n)))"

capture :: String -> Terms String -> Terms (Maybe String)
capture x s = s >>= (\y -> if x==y then Var Nothing else Var (Just y))

negative :: Integer -> Integer
negative x = -1 * x

predstep :: Terms a -> Terms a
predstep (App (Abs x) t) = subst x t
predstep (Add (Const x) (Const y)) = Const (x + y)
predstep (IfZero (Const 0) s t) = s
predstep (IfZero r s t) = t
predstep (Var x) = Var x
predstep (Const v) = Const v

preds :: Eq a => Terms a -> Terms a
preds (Var x) = Var x
preds (App t1 t2) = App (predstep t1) (predstep t2)
preds (Abs t) = Abs (predstep t)
preds (Const c) = Const c
preds (IfZero t1 t2 t3) = IfZero (predstep t1) (predstep t2) (predstep t3)
preds (Add t1 t2) = Add (predstep t1) (predstep t2)

subst :: Terms (Maybe a) -> Terms a -> Terms a
subst s t = s >>= maybe t Var