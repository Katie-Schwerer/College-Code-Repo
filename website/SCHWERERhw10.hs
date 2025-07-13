data Safe a = Value a | Error String 
 deriving (Show, Eq)

-- 1
instance Functor Safe where
  fmap f (Value a) = Value (f a)
  fmap f (Error a) = Error a

unitSafe :: a -> Safe a
unitSafe a = Value a

bindSafe :: (a -> Safe b) -> Safe a -> Safe b
bindSafe f (Error a) = Error a
bindSafe f (Value a) = f a

instance Applicative Safe where
  pure = unitSafe
  fs <*> fx = bindSafe (<$> fx) fs

instance Monad Safe where
  return = unitSafe
  fx >>= f = bindSafe f fx

--2
data FTree a = Leaf a | Node [FTree a]
 deriving (Show, Eq)

unitFTree :: a -> FTree a
unitFTree a = Leaf a

bindFTree :: (a -> FTree b) -> FTree a -> FTree b
bindFTree f (Leaf a) = f a
bindFTree f (Node a) = Node (map (bindFTree f) a)

instance Functor FTree where
  fmap f = bindFTree (unitFTree . f)

instance Applicative FTree where
  pure = unitFTree
  tf <*> tx = bindFTree (<$> tx) tf

instance Monad FTree where
  return = unitFTree
  t >>= f = bindFTree f t

--3
data Prop a = PVar a | TT | FF | And (Prop a) (Prop a) | Or (Prop a) (Prop a)| Not (Prop a) | Imp (Prop a) (Prop a) | Iff (Prop a) (Prop a)
 deriving (Show, Eq)

unitProp :: a -> Prop a
unitProp v = PVar v

bindProp :: (a -> Prop b) -> Prop a -> Prop b
bindProp f (PVar x) = f x
bindProp f (TT) = TT
bindProp f (FF) = FF
bindProp f (And e1 e2) = And (bindProp f e1) (bindProp f e2)
bindProp f (Or e1 e2)  = Or (bindProp f e1) (bindProp f e2)
bindProp f (Not e1)    = Not (bindProp f e1)
bindProp f (Imp e1 e2) = Imp (bindProp f e1) (bindProp f e2)
bindProp f (Iff e1 e2) = Iff (bindProp f e1) (bindProp f e2)

instance Functor Prop where
  fmap f = bindProp (unitProp . f)

instance Applicative Prop where
  pure = unitProp
  pp <*> pr = bindProp (<$> pr) pp

instance Monad Prop where
  return = unitProp
  pr >>= p = bindProp p pr

--4
data Lam a = Var a | App (Lam a) (Lam a) | Abs (Lam (Maybe a)) deriving (Show, Eq)

unitLam :: a -> Lam a
unitLam a = Var a

bindLam :: (a -> Lam b) -> Lam a -> Lam b
bindLam f (Var a) = f a
bindLam f (App a1 a2) = App (bindLam f a1) (bindLam f a2)
bindLam f (Abs a) = Abs (bindLam (lift f) a)

lift :: (a -> Lam b) -> Maybe a -> Lam (Maybe b)
lift f (Nothing) = Var Nothing
lift f (Just a) = Just <$> f a 

instance Functor Lam where
  fmap f = bindLam (unitLam . f)

instance Applicative Lam where
  pure = unitLam
  pp <*> pr = bindLam (<$> pr) pp

instance Monad Lam where
  return = unitLam
  pr >>= p = bindLam p pr

--5
data Poly a = Mono Double [a] | Sum (Poly a) (Poly a) deriving (Show, Eq)

unitPoly :: a -> Poly a
unitPoly a  = Mono 1 [a]

bindPoly :: (a -> Poly b) -> Poly a -> Poly b
bindPoly f (Sum a1 a2) = Sum (bindPoly f a1) (bindPoly f a2)
bindPoly f (Mono d list) = foldr multPoly (Mono d []) (map f list)


multMono :: Double -> [a] -> Poly a -> Poly a
multMono d list (Mono c itlist) = Mono (d *c) (list ++itlist)
multMono d list (Sum a1 a2) = Sum (multMono d list a1) (multMono d list a2)

multPoly :: Poly a -> Poly a -> Poly a
multPoly a (Mono c list) = multMono c list a
multPoly a (Sum a1 a2) = Sum (multPoly a a1) (multPoly a a2)

instance Functor Poly where
  fmap f = bindPoly (unitPoly . f)

instance Applicative Poly where
  pure = unitPoly
  p <*> pr = bindPoly (<$> pr) p

instance Monad Poly where
  return = unitPoly
  pr >>= p = bindPoly p pr